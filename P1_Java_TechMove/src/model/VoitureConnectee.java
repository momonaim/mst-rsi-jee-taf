package src.model;

public class VoitureConnectee extends VehiculeConnecte {
    private int nombrePortes;

    public VoitureConnectee(String immatricule, String marque, String modele, int annee, double kilometrage,
            boolean connecte, int nombrePortes) {
        super(immatricule, marque, modele, annee, kilometrage, connecte);
        this.nombrePortes = nombrePortes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof VoitureConnectee))
            return false;
        VoitureConnectee voiture = (VoitureConnectee) obj;
        return getModele().equals(voiture.getModele()) &&
                getMarque().equals(voiture.getMarque()) &&
                nombrePortes == voiture.nombrePortes;
    }

    @Override
    public String toString() {
        return super.toString() + " | Portes: " + nombrePortes;
    }
}