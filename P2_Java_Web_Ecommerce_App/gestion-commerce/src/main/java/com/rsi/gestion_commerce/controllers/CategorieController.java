package com.rsi.gestion_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import java.nio.charset.StandardCharsets;

import com.rsi.gestion_commerce.models.Categorie;
import com.rsi.gestion_commerce.repositories.CategorieRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategorieController {
    @Autowired
    private CategorieRepository categorieRepo;

    @GetMapping
    public String list(Model model) {
        try {
            model.addAttribute("categories", categorieRepo.findAll());
            return "categories/list";
        } catch (Exception e) {
            String message = UriUtils.encode("Erreur lors du chargement des catégories", StandardCharsets.UTF_8);
            return "redirect:/categories?message=" + message + "&type=error";
        }
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("categorie", new Categorie());
        return "categories/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Categorie categorie, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "categories/form";
        }

        try {
            boolean isNew = categorie.getId() == null;

            // Validate if name is unique
            if (isNew && categorieRepo.findByNom(categorie.getNom().trim()).isPresent()) {
                result.rejectValue("nom", "duplicate", "Une catégorie avec ce nom existe déjà");
                return "categories/form";
            }

            categorieRepo.save(categorie);

            String message = UriUtils.encode(
                    isNew ? "Catégorie créée avec succès" : "Catégorie mise à jour avec succès",
                    StandardCharsets.UTF_8);
            return "redirect:/categories?message=" + message + "&type=success";
        } catch (DataIntegrityViolationException e) {
            String message = "Une catégorie avec ce nom existe déjà";
            result.rejectValue("nom", "duplicate", message);
            return "categories/form";
        } catch (Exception e) {
            String message = UriUtils.encode("Erreur lors de l'enregistrement de la catégorie",
                    StandardCharsets.UTF_8);
            return "redirect:/categories?message=" + message + "&type=error";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        try {
            Categorie categorie = categorieRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
            model.addAttribute("categorie", categorie);
            return "categories/form";
        } catch (RuntimeException e) {
            String message = UriUtils.encode("Catégorie non trouvée", StandardCharsets.UTF_8);
            return "redirect:/categories?message=" + message + "&type=error";
        } catch (Exception e) {
            String message = UriUtils.encode("Erreur lors du chargement de la catégorie",
                    StandardCharsets.UTF_8);
            return "redirect:/categories?message=" + message + "&type=error";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            Categorie categorie = categorieRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

            categorieRepo.delete(categorie);
            String message = UriUtils.encode("Catégorie supprimée avec succès", StandardCharsets.UTF_8);
            return "redirect:/categories?message=" + message + "&type=success";
        } catch (Exception e) {
            String message = UriUtils.encode(
                    "Impossible de supprimer cette catégorie car elle est utilisée par des produits",
                    StandardCharsets.UTF_8);
            return "redirect:/categories?message=" + message + "&type=error";
        }
    }
}
