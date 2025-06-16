package src.exception;

public class CapacityExceededException extends Exception {
    public CapacityExceededException() {
        super("Capacité maximale de la flotte atteinte (30 véhicules)");
    }
}