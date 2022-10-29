package liga.medical.medicalmonitoring.core.exception;

public class BadLoginDataException extends RuntimeException {
    public BadLoginDataException(String message) {
        super(message);
    }
}
