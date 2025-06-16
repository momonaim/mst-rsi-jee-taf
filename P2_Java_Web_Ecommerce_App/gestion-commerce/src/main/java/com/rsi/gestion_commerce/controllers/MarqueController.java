package com.rsi.gestion_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import java.nio.charset.StandardCharsets;

import com.rsi.gestion_commerce.models.Marque;
import com.rsi.gestion_commerce.repositories.MarqueRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/marques")
public class MarqueController {
    @Autowired
    private MarqueRepository marqueRepo;

    @GetMapping
    public String list(Model model) {
        try {
            model.addAttribute("marques", marqueRepo.findAll());
            return "marques/list";
        } catch (Exception e) {
            String message = UriUtils.encode("Erreur lors du chargement des marques", StandardCharsets.UTF_8);
            return "redirect:/marques?message=" + message + "&type=error";
        }
    }

    @GetMapping("/new")
    public String form(Model model) {
        Marque nouvelleMarque = new Marque();
        nouvelleMarque.setId(null);
        model.addAttribute("marque", nouvelleMarque);
        return "marques/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Marque marque, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "marques/form";
        }

        try {
            boolean isNew = marque.getId() == null;

            // Validate if name is unique
            if (isNew && marqueRepo.findByNom(marque.getNom().trim()).isPresent()) {
                result.rejectValue("nom", "duplicate", "Une marque avec ce nom existe déjà");
                return "marques/form";
            }

            // Si ID est défini mais ne correspond à rien, on l'annule
            if (marque.getId() != null && !marqueRepo.existsById(marque.getId())) {
                marque.setId(null);
                isNew = true;
            }

            marqueRepo.save(marque);

            String message = UriUtils.encode(
                    isNew ? "Marque créée avec succès" : "Marque mise à jour avec succès",
                    StandardCharsets.UTF_8);
            return "redirect:/marques?message=" + message + "&type=success";
        } catch (DataIntegrityViolationException e) {
            String message = "Une marque avec ce nom existe déjà";
            result.rejectValue("nom", "duplicate", message);
            return "marques/form";
        } catch (Exception e) {
            String message = UriUtils.encode("Erreur lors de l'enregistrement de la marque",
                    StandardCharsets.UTF_8);
            return "redirect:/marques?message=" + message + "&type=error";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        try {
            Marque marque = marqueRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Marque non trouvée"));
            model.addAttribute("marque", marque);
            return "marques/form";
        } catch (RuntimeException e) {
            String message = UriUtils.encode("Marque non trouvée", StandardCharsets.UTF_8);
            return "redirect:/marques?message=" + message + "&type=error";
        } catch (Exception e) {
            String message = UriUtils.encode("Erreur lors du chargement de la marque",
                    StandardCharsets.UTF_8);
            return "redirect:/marques?message=" + message + "&type=error";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            Marque marque = marqueRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Marque non trouvée"));

            marqueRepo.delete(marque);
            String message = UriUtils.encode("Marque supprimée avec succès", StandardCharsets.UTF_8);
            return "redirect:/marques?message=" + message + "&type=success";
        } catch (Exception e) {
            String message = UriUtils.encode(
                    "Impossible de supprimer cette marque car elle est utilisée par des produits",
                    StandardCharsets.UTF_8);
            return "redirect:/marques?message=" + message + "&type=error";
        }
    }
}
