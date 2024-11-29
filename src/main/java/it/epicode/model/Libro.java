package it.epicode.model;

// CLASSE LIBRO CHE ESTENDE LA CLASSE ASTRATTA ELEMENTO
public class Libro extends Elemento {
    private static final long serialVersionUID = 1L; // VERSIONE SERIALE PER GLI OGGETTI

    private String autore;
    private String genere;

    // MI CREO UN COSTRUTTORE CHE INIIZALIZZA I CAMPI DEL LIBRO
    public Libro(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }
    // OTTENGO NOME DELL'AUTORE
    public String getAutore() {
        return autore;
    }
    // OTTENGO GENERE LIBRO
    public String getGenere() {
        return genere;
    }
    // TOSTRING PER RAPPRESENTARE LIBRO SOTTO FORMA DI STRINGA
    @Override
    public String toString() {
        return super.toString() + ", Autore: " + autore + ", Genere: " + genere;
    }
}