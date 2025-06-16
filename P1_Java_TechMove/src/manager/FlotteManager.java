package src.manager;

import java.util.*;
import src.exception.CapacityExceededException;
import src.model.VehiculeConnecte;

public class FlotteManager {
    private static final int CAPACITE_MAX = 30;
    private Map<Long, src.model.VehiculeConnecte> vehicules = new HashMap<>();

    public void ajouterVehicule(Long id, src.model.VehiculeConnecte v) throws CapacityExceededException {
        if (vehicules.size() >= CAPACITE_MAX) {
            throw new CapacityExceededException();
        }
        vehicules.put(id, v);
    }

    public boolean supprimerParImmatricule(String immatricule) {
        return vehicules.entrySet().removeIf(entry -> entry.getValue().getImmatricule().equals(immatricule));
    }

    public Set<VehiculeConnecte> getTousLesVehicules() {
        return new HashSet<>(vehicules.values());
    }

    // Méthodes Streams
    public long compterVehiculesConnectesDepuis(int annee) {
        return vehicules.values().stream()
                .filter(v -> v.getAnnee() >= annee && v.isConnecte())
                .count();
    }

    public VehiculeConnecte getVehiculeKilometrageMax() {
        return vehicules.values().stream()
                .max(Comparator.comparingDouble(VehiculeConnecte::getKilometrage))
                .orElse(null);
    }

    public void afficherImmatriculationsParMarque(String marque) {
        vehicules.values().stream()
                .filter(v -> v.getMarque().equalsIgnoreCase(marque))
                .map(VehiculeConnecte::getImmatricule)
                .sorted()
                .forEach(System.out::println);
    }
}