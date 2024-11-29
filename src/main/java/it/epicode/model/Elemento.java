package it.epicode.model;

import java.io.Serializable; // IMPORT DI IO PER LA SERIALIZZAZIONE DI OGGETTI

public abstract class Elemento implements Serializable {
    private static final long serialVersionUID = 1L; // VERSIONE SERIALE PER GLI OGGETTI

    private String codiceISBN;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;

    // MI CREO UN COSTRUTTORE CHE INIIZALIZZA I CAMPI DELLA CLASSE
    public Elemento(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine) {
        this.codiceISBN = codiceISBN;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }
    //GETTER
    // ISBN
    public String getCodiceISBN() {
        return codiceISBN;
    }
    // TITOLO
    public String getTitolo() {
        return titolo;
    }
    // ANNO
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }
    // PAGINE
    public int getNumeroPagine() {
        return numeroPagine;
    }
    // TOSTRING PER RAPPRESENTARE ELEMENTO SOTTO FORMA DI STRINGA
    @Override
    public String toString() {
        return "ISBN: " + codiceISBN + ", Titolo: " + titolo + ", Anno: " + annoPubblicazione + ", Pagine: " + numeroPagine;
    }
}