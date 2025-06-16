package src.model;

public class MotoConnectee extends VehiculeConnecte {
    private String typeCasque;

    public MotoConnectee(String immatricule, String marque, String modele, int annee, double kilometrage,
            boolean connecte, String typeCasque) {
        super(immatricule, marque, modele, annee, kilometrage, connecte);
        this.typeCasque = typeCasque;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof MotoConnectee))
            return false;
        MotoConnectee moto = (MotoConnectee) obj;
        return getMarque().equals(moto.getMarque()) &&
                typeCasque.equals(moto.typeCasque);
    }

    @Override
    public String toString() {
        return super.toString() + " | Casque: " + typeCasque;
    }
}