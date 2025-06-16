package com.rsi.gestion_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriUtils;
import java.nio.charset.StandardCharsets;

import com.rsi.gestion_commerce.models.Produit;
import com.rsi.gestion_commerce.repositories.CategorieRepository;
import com.rsi.gestion_commerce.repositories.MarqueRepository;
import com.rsi.gestion_commerce.services.ProduitService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/produits")
public class ProduitController {
    @Autowired
    private ProduitService produitService;
    @Autowired
    private MarqueRepository marqueRepo;
    @Autowired
    private CategorieRepository categorieRepo;

    @GetMapping
    public String list(Model model, @PageableDefault(size = 5, sort = "id") Pageable pageable) {
        try {
            Page<Produit> produitsPage = produitService.findAll(pageable);
            if (pageable.getPageNumber() > produitsPage.getTotalPages()) {
                return "redirect:/produits?page=0&size=" + pageable.getPageSize();
            }
            model.addAttribute("produitsPage", produitsPage);
            model.addAttribute("currentPage", pageable.getPageNumber());
            model.addAttribute("pageSize", pageable.getPageSize());
            return "produits/list";
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            // Fallback to empty page in case of error
            model.addAttribute("produitsPage", Page.empty(pageable));
            model.addAttribute("message", "Erreur lors du chargement des produits.");
            model.addAttribute("type", "error");
            return "produits/list";
        }
    }

    @GetMapping("/new")
    public String form(Model model) {
        try {
            Produit nouveauProduit = new Produit();
            nouveauProduit.setId(null);
            model.addAttribute("produit", nouveauProduit);
            model.addAttribute("marques", marqueRepo.findAll());
            model.addAttribute("categories", categorieRepo.findAll());
            return "produits/form";
        } catch (Exception e) {
            String message = UriUtils.encode("Erreur lors du chargement du formulaire", StandardCharsets.UTF_8);
            return "redirect:/produits?message=" + message + "&type=error";
        }
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Produit produit, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("marques", marqueRepo.findAll());
            model.addAttribute("categories", categorieRepo.findAll());
            return "produits/form";
        }

        try {
            boolean isNew = produit.getId() == null;

            // Validate if name is unique for new products
            if (isNew && produitService.findByNom(produit.getNom().trim()).isPresent()) {
                result.rejectValue("nom", "duplicate", "Un produit avec ce nom existe déjà");
                model.addAttribute("marques", marqueRepo.findAll());
                model.addAttribute("categories", categorieRepo.findAll());
                return "produits/form";
            }

            // Si ID est défini mais ne correspond à rien, on l'annule
            if (produit.getId() != null && !produitService.existsById(produit.getId())) {
                produit.setId(null);
                isNew = true;
            }

            produitService.save(produit);

            String message = UriUtils.encode(
                    isNew ? "Produit créé avec succès" : "Produit mis à jour avec succès",
                    StandardCharsets.UTF_8);
            return "redirect:/produits?message=" + message + "&type=success";
        } catch (DataIntegrityViolationException e) {
            result.rejectValue("nom", "duplicate", "Un produit avec ce nom existe déjà");
            model.addAttribute("marques", marqueRepo.findAll());
            model.addAttribute("categories", categorieRepo.findAll());
            return "produits/form";
        } catch (Exception e) {
            String message = UriUtils.encode("Erreur lors de l'enregistrement du produit", StandardCharsets.UTF_8);
            return "redirect:/produits?message=" + message + "&type=error";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        try {
            // Try to load everything needed for the form
            Produit produit = produitService.findById(id);
            model.addAttribute("produit", produit);
            model.addAttribute("marques", marqueRepo.findAll());
            model.addAttribute("categories", categorieRepo.findAll());

            // Validate relationships
            if (produit.getMarque() == null || !marqueRepo.existsById(produit.getMarque().getId())) {
                String message = UriUtils.encode("La marque associée à ce produit n'existe plus",
                        StandardCharsets.UTF_8);
                return "redirect:/produits?message=" + message + "&type=error";
            }

            if (produit.getCategorie() == null || !categorieRepo.existsById(produit.getCategorie().getId())) {
                String message = UriUtils.encode("La catégorie associée à ce produit n'existe plus",
                        StandardCharsets.UTF_8);
                return "redirect:/produits?message=" + message + "&type=error";
            }

            return "produits/form";
        } catch (RuntimeException e) {
            e.printStackTrace(); // Log the error
            String message = UriUtils.encode("Produit non trouvé ou invalide: " + e.getMessage(),
                    StandardCharsets.UTF_8);
            return "redirect:/produits?message=" + message + "&type=error";
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            String message = UriUtils.encode("Erreur lors du chargement du produit: " + e.getMessage(),
                    StandardCharsets.UTF_8);
            return "redirect:/produits?message=" + message + "&type=error";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            Produit produit = produitService.findById(id);
            if (produit == null) {
                throw new RuntimeException("Produit non trouvé");
            }

            produitService.delete(id);
            String message = UriUtils.encode("Produit supprimé avec succès", StandardCharsets.UTF_8);
            return "redirect:/produits?message=" + message + "&type=success";
        } catch (RuntimeException e) {
            String message = UriUtils.encode("Produit non trouvé", StandardCharsets.UTF_8);
            return "redirect:/produits?message=" + message + "&type=error";
        } catch (Exception e) {
            String message = UriUtils.encode("Erreur lors de la suppression du produit", StandardCharsets.UTF_8);
            return "redirect:/produits?message=" + message + "&type=error";
        }
    }
}
