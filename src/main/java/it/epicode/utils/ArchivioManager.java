package it.epicode.utils;

import it.epicode.exceptions.ElementoNonTrovatoException;
import it.epicode.model.Elemento;
import it.epicode.model.Libro;
import it.epicode.model.Rivista;
import org.slf4j.Logger; // LOGGER SLF4J
import org.slf4j.LoggerFactory; // FACTORY PER IL LOGGER
import java.util.ArrayList; // LISTADINAMICA
import java.util.List; // INTERFACCIA LIST
import java.util.stream.Collectors; // IMPORT PER LE OPERAZIONI STREAMS F

// OPERAZIONI CATALOGO
public class ArchivioManager {
    private static final Logger logger = LoggerFactory.getLogger(ArchivioManager.class);
    private final List<Elemento> archivio; // LISTA ELEMENTI PRESENTI

    // MI CREO IL COSTRUTTORE PER INIZIALIZZARE LA LISTA DEGLI ELEMENTI
    public ArchivioManager() {
        this.archivio = new ArrayList<>();
        logger.info("ArchivioManager inizializzato.");
    }
    //AGGIUNGI ELEMENTI CATALOGO
    public void aggiungiElemento(Elemento elemento) {
        archivio.add(elemento);
        logger.info("Aggiunto elemento al catalogo: {}", elemento);
    }
    // CANCELLO ELEMENTO DATO CODICE ISBN ( ID )
    public void rimuoviElemento(String isbn) {
        logger.debug("Tentativo di rimuovere elemento con ISBN: {}", isbn);
        Elemento elemento = archivio.stream() // STREAM ELEMENTI
                .filter(e -> e.getCodiceISBN().equals(isbn)) // FILTRO ISBN
                .findFirst() // PRIMO ELEMENTO CORRISPONDENTE
                .orElseThrow(() -> {
                    logger.error("Elemento non trovato per ISBN: {}", isbn); // GESTIONE ERRORI
                    return new ElementoNonTrovatoException("Elemento non trovato: " + isbn); // ECCEZIONE
                });
        archivio.remove(elemento); // CANCELLO ELEMENTO
        logger.info("Elemento rimosso con successo: {}", elemento);
    }
    // CERCA CON ISBN
    public Elemento cercaElemento(String isbn) {
        logger.debug("Ricerca elemento con ISBN: {}", isbn);
        return archivio.stream() // STREAM ELEMENTI
                .filter(e -> e.getCodiceISBN().equals(isbn))
                .findFirst()
                .orElseThrow(() -> {
                    logger.error("Elemento non trovato con ISBN: {}", isbn);
                    return new ElementoNonTrovatoException("Elemento non trovato con ISBN: " + isbn);
                });
    }
    // CERCA PER ANNO
    public List<Elemento> cercaPerAnno(int anno) {
        logger.debug("Ricerca elementi per anno di pubblicazione: {}", anno);
        return archivio.stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList()); // METTO RISULTATI IN UNA LISTA
    }
    // CERCA PER AUTORE
    public List<Elemento> cercaPerAutore(String autore) {
        logger.debug("Ricerca libri per autore: {}", autore);
        return archivio.stream()
                .filter(e -> e instanceof Libro) // FILTRO ELEMENTI SOLO DI TIPO LIBRO
                .filter(e -> ((Libro) e).getAutore().equalsIgnoreCase(autore)) // FILTRA LIBRI PER AUTORE
                .collect(Collectors.toList()); // METTO RISULTATO IN UNA LISTA
    }
    // STATISTICHE TOTALI LIBRI E RIVISTE
    public void stampaStatistiche() {
        long numeroLibri = archivio.stream().filter(e -> e instanceof Libro).count(); // NUMERO LIBRI
        long numeroRiviste = archivio.stream().filter(e -> e instanceof Rivista).count(); //NUMERO RIVISTE
        int maxPagine = archivio.stream().mapToInt(Elemento::getNumeroPagine).max().orElse(0); // NUMERO MIN E MAX DI PAGINE
        double mediaPagine = archivio.stream().mapToInt(Elemento::getNumeroPagine).average().orElse(0.0); // FACCIO LA MEDIA IN STATISTICA

        logger.info("Statistiche del catalogo:");
        logger.info("Numero di libri registrati: {}", numeroLibri);
        logger.info("Numero di riviste registrate: {}", numeroRiviste);
        logger.info("Massimo numero di pagine tra libri e riviste: {}", maxPagine);
        logger.info("Media numero di pagine tra entrambi: {}", mediaPagine);
    }
}