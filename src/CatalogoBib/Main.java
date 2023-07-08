package CatalogoBib;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
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

		String codiceISBNToRemove = "9c911c46-9343-487b-a90c-e0dd1cab0462";
		boolean isRemoved = biblio.removeElementByISBN(codiceISBNToRemove);
		if (isRemoved) {
			System.out.println();
			log.info("Elemento rimosso con successo.");
		} else {
			System.out.println();
			log.error("Elemento non trovato nell'archivio.");
			System.out.println("----------------------------------------");
		}

		String codiceISBN = "9c911c46-9343-487b-a90c-e0dd1cab0462"; // Codice ISBN generato casualmente
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

		// Salvataggio su disco dell'archivio
		try {
			String archivioString = biblio.toString(); // Ottieni la rappresentazione testuale dell'archivio
			FileUtils.writeStringToFile(new File(nameFile), archivioString, StandardCharsets.UTF_8);
			System.out.println();
			log.info("Archivio salvato su disco con nome: {}", nameFile);
			System.out.println("----------------------------------------");
		} catch (IOException e) {
			System.out.println();
			log.error("Errore durante il salvataggio dell'archivio su disco", e);
		}

		// Caricamento dal disco dell'archivio
		try {
			String archivioString = FileUtils.readFileToString(new File(nameFile), StandardCharsets.UTF_8);
			System.out.println();
			log.info("Archivio caricato dal disco con nome: {}", nameFile);
			// Puoi fare ulteriori elaborazioni sulla stringa dell'archivio caricato se
			// necessario
			System.out.println(archivioString);
		} catch (IOException e) {
			log.error("Errore durante il caricamento dell'archivio dal disco", e);
		}

	}
}
