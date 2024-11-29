package it.epicode.model;

import it.epicode.utils.Periodicita; // ENUM PERIODICITA'

// CLASSE RIVISTA CHE ESTENDE LA CLASSE ASTRATTA ELEMENTO
public class Rivista extends Elemento {
    private static final long serialVersionUID = 1L; // VERSIONE SERIALE PER GLI OGGETTI

    private Periodicita periodicita; // LA PERIODICITA' E NELL'ENUM E DEVE ESSERE SETTIMANALE,MENSILE O ANNUALE

    // MI CREO UN COSTRUTTORE CHE INIIZALIZZA I CAMPI DELLA RIVISTA
    public Rivista(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }
    // GETTER PERIODICITA' RIVISTA
    public Periodicita getPeriodicita() {
        return periodicita;
    }
    // TOSTRING PER RAPPRESENTARE LA RIVISTA SOTTO FORMA DI STRINGA
    @Override
    public String toString() {
        return super.toString() + ", Periodicità: " + periodicita;
    }
}