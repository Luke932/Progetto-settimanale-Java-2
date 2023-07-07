package CatalogoBib;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	static Logger log = LoggerFactory.getLogger(Archivio.class);

	public static void main(String[] args) {
		Archivio biblio = new Archivio();

		Libri book1 = new Libri("Il signore degli anelli", LocalDate.of(1954, 5, 21), 456, "J. R. R. Tolkien",
				"Fantasy");
		Libri book2 = new Libri("Harry Potter", LocalDate.of(1998, 7, 23), 378, "Joanne Rowling", "Fantasy");

		Riviste magazine1 = new Riviste("Focus", LocalDate.of(1998, 8, 25), 45, Periodicità.SETTIMANALE);
		Riviste magazine2 = new Riviste("Ciak Magazine", LocalDate.of(1985, 12, 10), 30, Periodicità.MENSILE);

		biblio.addElem(book1);
		biblio.addElem(book2);
		biblio.addElem(magazine1);
		biblio.addElem(magazine2);

		String codiceISBN = "9781234567890"; // Codice ISBN generato casualmente
		Optional<Element> elementoOptional = biblio.searchForISBN(codiceISBN);

		System.out.println();
		if (elementoOptional.isPresent()) {
			Element elemento = elementoOptional.get();
			log.info("Elemento trovato: " + elemento);
		} else {
			log.error("Elemento non trovato per il codice ISBN: " + codiceISBN);
		}

		int yearSearch = 1998;
		List<Element> itemForYear = biblio.searchForYear(yearSearch);
		System.out.println();
		log.info("Elementi trovati per anno di pubblicazione {}: {}", yearSearch, itemForYear);
		System.out.println("-----------------------------------------");

		String authorSearch = "Joanne Rowling";
		List<Element> itemForAuthor = biblio.searchForAuthor(authorSearch);
		System.out.println();
		log.info("Elementi trovati per autore {}: {}", authorSearch, itemForAuthor);
		System.out.println("------------------------------------------");

		String nameFile = "src/CatalogoBib/archivio.dat";
		try {
			biblio.saveToDisk(nameFile);
			System.out.println();
			log.info("Archivio salvato su disco con nome: {}", nameFile);
			System.out.println("----------------------------------------");
		} catch (IOException e) {
			System.out.println();
			log.error("Errore durante il salvataggio dell'archivio su disco", e);

		}

		try {
			biblio.loadToDisk(nameFile);
			System.out.println();
			log.info("Archivio caricato dal disco con nome: {}", nameFile);
		} catch (IOException | ClassNotFoundException e) {
			log.error("Errore durante il caricamento dell'archivio dal disco", e);
		}
	}
}