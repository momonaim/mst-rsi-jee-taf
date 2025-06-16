package src.model;

import java.util.Comparator;

public abstract class VehiculeConnecte implements Comparator<VehiculeConnecte> {
    private String immatricule;
    private String marque;
    private String modele;
    private int annee;
    private double kilometrage;
    private boolean connecte;

    public VehiculeConnecte(String immatricule, String marque, String modele, int annee, double kilometrage,
            boolean connecte) {
        this.immatricule = immatricule;
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.kilometrage = kilometrage;
        this.connecte = connecte;
    }

    @Override
    public String toString() {
        return String.format("Immat: %s | %s %s (%d) | Km: %.1f | %s",
                immatricule,
                marque,
                modele,
                annee,
                kilometrage,
                connecte ? "Connecté" : "Hors-ligne");
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public int compare(VehiculeConnecte v1, VehiculeConnecte v2) {
        return v1.immatricule.compareTo(v2.immatricule);
    }

    // Getters
    public String getImmatricule() {
        return immatricule;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public int getAnnee() {
        return annee;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public boolean isConnecte() {
        return connecte;
    }
}