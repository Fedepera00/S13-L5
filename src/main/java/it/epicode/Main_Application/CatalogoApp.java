package it.epicode.Main_Application;

import it.epicode.model.Libro;
import it.epicode.model.Rivista;
import it.epicode.utils.CatalogoBiblioteca;
import it.epicode.utils.Periodicita; // ENUM PERIODICITA'
import org.slf4j.Logger; // LOGGER SLF4J
import org.slf4j.LoggerFactory; // LOGGER FACTORY
import java.util.Scanner;


public class CatalogoApp {
    private static final Logger logger = LoggerFactory.getLogger(CatalogoApp.class);


    public static void main(String[] args) {
        CatalogoBiblioteca catalogo = new CatalogoBiblioteca(); // INIZIALIZZO IL CATALOGO
        Scanner scanner = new Scanner(System.in);
        boolean esci = false;

        logger.info("Avvio Catalogo Bibliografico.");

        // CICLO PRINCIPALE DEL MENU INTERATTIVO
        while (!esci) {
            try {
                // MENU' INTERATTIVO CON LA PERSONA
                System.out.println("\n-------------------------------- APP CATALOGO  --------------------------------");
                System.out.println("1. Se vuoi aggiungere un libro");
                System.out.println("2. Se vuoi aggiungere una rivista");
                System.out.println("3. Per eliminare un elemento");
                System.out.println("4. Cerca un libro o una rivista per ISBN");
                System.out.println("5. Cerca un libro o una rivista per anno di pubblicazione");
                System.out.println("6. Se vuoi cercare i libri per autore");
                System.out.println("7. Visualizza le statistiche dei libri e riviste registrate");
                System.out.println("8. Esci dal programma ");
                System.out.print("Scegli una delle seguenti opzioni per andare avanti: ");

                int scelta = scanner.nextInt();
                scanner.nextLine();

                // SWITCH VARIE OPZIONI DI CASISTICHE POSSIBILI
                switch (scelta) {
                    case 1 -> aggiungiLibro(scanner, catalogo);
                    case 2 -> aggiungiRivista(scanner, catalogo);
                    case 3 -> rimuoviElemento(scanner, catalogo);
                    case 4 -> cercaPerISBN(scanner, catalogo);
                    case 5 -> cercaPerAnno(scanner, catalogo);
                    case 6 -> cercaPerAutore(scanner, catalogo);
                    case 7 -> catalogo.stampaStatistiche();
                    case 8 -> {
                        logger.info("Chiusura dell'applicazione di catalogo .");
                        esci = true; // ESCO DAL CICLO
                    }
                    default -> logger.warn("Attenzione l'opzione selezionata non è valida: {}", scelta);
                }
            } catch (Exception e) {
                logger.error("Errore nell'elaborazione dell'input utente: {}", e.getMessage());
                scanner.nextLine(); // CHIUDO LA LINEA PER EVITARE IL LOOP :)
            }
        }

        scanner.close();
    }
    // AGGIUNGO LIBRO AL CATALOGO
    private static void aggiungiLibro(Scanner scanner, CatalogoBiblioteca catalogo) {
        System.out.print("Inserisci il codice ISBN del libro: ");
        String isbn = scanner.nextLine();
        System.out.print("Inserisci il titolo del libro: ");
        String titolo = scanner.nextLine();
        System.out.print("Inserisci l'anno di pubblicazione del libro: ");
        int anno = scanner.nextInt();
        System.out.print("Inserisci il numero di pagine che possiede il libro: ");
        int pagine = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Inserisci l'autore del libro: ");
        String autore = scanner.nextLine();
        System.out.print("Inserisci il genere del libro: ");
        String genere = scanner.nextLine();

        // AGGIUNGO IL LIBRO AL CATALOGO CHE ABBIAMO
        catalogo.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
        logger.info("Libro aggiunto: ISBN {}, Titolo {}", isbn, titolo);
    }

    // AGGIUNGO RIVISTA AL CATALOGO CHE ABBIAMO UGUALE AL LIBRO
    private static void aggiungiRivista(Scanner scanner, CatalogoBiblioteca catalogo) {
        System.out.print("Inserisci il codice ISBN della rivista: ");
        String isbn = scanner.nextLine();
        System.out.print("Inserisci il titolo della rivista: ");
        String titolo = scanner.nextLine();
        System.out.print("Inserisci l'anno di pubblicazione della rivista: ");
        int anno = scanner.nextInt();
        System.out.print("Inserisci il numero di pagine che possiede la rivista: ");
        int pagine = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Inserisci la periodicità della rivista tra le seguenti (SETTIMANALE, MENSILE, SEMESTRALE): ");
        String periodicitaInput = scanner.nextLine(); // ENUM PERIODICITA' SETTIMANALE, MENSILE E ANNUALE

        //TRY CATCH
        try {
            Periodicita periodicita = Periodicita.valueOf(periodicitaInput.toUpperCase()); // CONVERTO INPUT IN PERIODICITA'
            catalogo.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, periodicita)); // AGGIUNGO LA RIVIST
            logger.info("Rivista aggiunta: ISBN {}, Titolo {}", isbn, titolo);
        } catch (IllegalArgumentException e) {
            logger.error("Periodicità non valida: {}", periodicitaInput);
        }
    }
    // CANCELLO ELEMENTO DAL NOSTRO CATALOGO
    private static void rimuoviElemento(Scanner scanner, CatalogoBiblioteca catalogo) {
        System.out.print("Inserisci il codice ISBN dell'elemento da rimuovere: ");
        String isbn = scanner.nextLine();
        catalogo.rimuoviElemento(isbn);
        logger.info("Elemento rimosso: ISBN {}", isbn);
    }
    // CERCO TRAMITE ISBN
    private static void cercaPerISBN(Scanner scanner, CatalogoBiblioteca catalogo) {
        System.out.print("Inserisci il codice ISBN del libro o rivista da cercare: ");
        String isbn = scanner.nextLine();
        catalogo.cercaElemento(isbn);
    }
    // CERCO ELEMENTI IN UN ANNO SPECIFICO
    private static void cercaPerAnno(Scanner scanner, CatalogoBiblioteca catalogo) {
        System.out.print("Inserisci l'anno di pubblicazione del libro o rivista d cercare: ");
        int anno = scanner.nextInt();
        var risultati = catalogo.cercaPerAnno(anno); // CERCO GLI ELEMENTI PER ANNO
        if (risultati.isEmpty()) {
            logger.info("Nessun elemento trovato per l'anno {}", anno);
        } else {
            logger.info("Elementi pubblicati nell'anno {}: {}", anno, risultati);
        }
    }
    // CERCO LIBRI DI UN AUTORE SPECIFICO
    private static void cercaPerAutore(Scanner scanner, CatalogoBiblioteca catalogo) {
        System.out.print("Inserisci il nome dell'autore da cercare: ");
        String autore = scanner.nextLine();
        var risultati = catalogo.cercaPerAutore(autore);
        if (risultati.isEmpty()) {
            logger.info("Nessun libro trovato per l'autore {}", autore);
        } else {
            logger.info("Libri scritti da {}: {}", autore, risultati);
        }
    }
}