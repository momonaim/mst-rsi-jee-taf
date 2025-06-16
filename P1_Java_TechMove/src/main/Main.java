package src.main;

import src.exception.CapacityExceededException;
import src.manager.FlotteManager;
import src.model.MotoConnectee;
import src.model.VoitureConnectee;

public class Main {
    public static void main(String[] args) {
        FlotteManager manager = new FlotteManager();

        try {
            System.out.println("===== AJOUT DE VÉHICULES =====");
            // Ajout de véhicules
            manager.ajouterVehicule(1L, new VoitureConnectee("AB-123-CD", "Renault", "Zoe", 2022, 15000.5, true, 5));
            manager.ajouterVehicule(2L,
                    new MotoConnectee("MC-456-EF", "Yamaha", "MT-07", 2023, 8000.0, true, "Intégral"));
            manager.ajouterVehicule(3L, new VoitureConnectee("GH-789-IJ", "Tesla", "Model 3", 2021, 42000.0, false, 5));
            manager.ajouterVehicule(4L, new VoitureConnectee("KL-012-MN", "Renault", "Megane", 2020, 35000.0, true, 5));
            manager.ajouterVehicule(5L,
                    new MotoConnectee("OP-345-QR", "Honda", "CBR 600", 2022, 12000.0, false, "Jet"));

            System.out.println("5 véhicules ajoutés avec succès!");

            System.out.println("\n===== LISTE DE TOUS LES VÉHICULES =====");
            manager.getTousLesVehicules().forEach(System.out::println);

            System.out.println("\n===== TEST DE SUPPRESSION =====");
            boolean suppression = manager.supprimerParImmatricule("GH-789-IJ");
            System.out.println("Suppression du véhicule GH-789-IJ : " + (suppression ? "SUCCÈS" : "ÉCHEC"));

            System.out.println("\n===== LISTE APRÈS SUPPRESSION =====");
            manager.getTousLesVehicules().forEach(System.out::println);

            System.out.println("\n===== TEST DES MÉTHODES STREAM =====");
            System.out.println("Nombre de véhicules connectés depuis 2020 : " +
                    manager.compterVehiculesConnectesDepuis(2020));

            System.out.println("Véhicule avec le kilométrage max : " +
                    manager.getVehiculeKilometrageMax());

            System.out.println("\nImmatriculations des Renault (triées) :");
            manager.afficherImmatriculationsParMarque("Renault");

            System.out.println("\n===== TEST D'EXCEPTION DE CAPACITÉ =====");
            // Tentative d'ajout au-delà de la capacité (pour tester l'exception)
            System.out.println("Tentative d'ajout de 26 véhicules supplémentaires...");
            for (int i = 6; i <= 32; i++) {
                manager.ajouterVehicule((long) i,
                        new VoitureConnectee("XX-" + i + "-XX", "Marque", "Modèle", 2020, 10000, true, 5));
            }
        } catch (CapacityExceededException e) {
            System.err.println("\nERREUR ATTENDUE: " + e.getMessage());
            System.out.println("Nombre actuel de véhicules: " + manager.getTousLesVehicules().size());
        }

        System.out.println("\n===== TEST D'ÉGALITÉ =====");
        VoitureConnectee v1 = new VoitureConnectee("XX-111-XX", "Peugeot", "208", 2021, 20000, true, 5);
        VoitureConnectee v2 = new VoitureConnectee("XX-222-XX", "Peugeot", "208", 2021, 25000, false, 5);
        System.out.println("v1.equals(v2) (même modèle/marque/portes) ? " + v1.equals(v2));

        MotoConnectee m1 = new MotoConnectee("YY-111-YY", "Kawasaki", "Ninja", 2022, 5000, true, "Intégral");
        MotoConnectee m2 = new MotoConnectee("YY-222-YY", "Kawasaki", "Ninja", 2022, 6000, true, "Modulable");
        System.out.println("m1.equals(m2) (même marque mais casque différent) ? " + m1.equals(m2));
    }
}