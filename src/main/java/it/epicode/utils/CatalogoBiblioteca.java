package it.epicode.utils;

import it.epicode.model.Elemento;
import java.util.List; // INTERFACCIA LIST

public class CatalogoBiblioteca {
    private final ArchivioManager archivioManager;
    // DELEGO TUTTI I METODI SOTTO ALL'ARCHIO MANAGER IN MODO CHE SE NE OCCUPI LUI DELLA GESTIONE
    // MI CREO IL COSTRUTTORE CON CUI INIZIALIZZO L'ARCHIVIO MANAGER
    public CatalogoBiblioteca() {
        this.archivioManager = new ArchivioManager();
    }

    // AGGIUNGO
    public void aggiungiElemento(Elemento elemento) {
        archivioManager.aggiungiElemento(elemento);
    }

    // RIMUOVO CON ISBN
    public void rimuoviElemento(String isbn) {
        archivioManager.rimuoviElemento(isbn);
    }

    // CERCO CON ISBN
    public void cercaElemento(String isbn) {
        System.out.println(archivioManager.cercaElemento(isbn));
    }

    // CERCO ELEMENTI PER ANNO
    public List<Elemento> cercaPerAnno(int anno) {
        return archivioManager.cercaPerAnno(anno);
    }

    // CERCO LIBRI PER AUTORE
    public List<Elemento> cercaPerAutore(String autore) {
        return archivioManager.cercaPerAutore(autore);
    }

    // STAMPO LE STATISTICHE
    public void stampaStatistiche() {
        archivioManager.stampaStatistiche();
    }
}