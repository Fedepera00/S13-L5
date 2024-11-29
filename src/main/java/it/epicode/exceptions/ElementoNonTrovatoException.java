package it.epicode.exceptions;

public class ElementoNonTrovatoException extends RuntimeException {
    // COSTRUTTORE CHE ACCETTA MESS DI ERRORE PERSONALIZZATO
    public ElementoNonTrovatoException(String message) {
        super(message);
    }
}