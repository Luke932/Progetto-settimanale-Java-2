package CatalogoBib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Archivio implements Serializable {

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private static final long serialVersionUID = 1L;
	private List<Element> task;

	public Archivio() {
		task = new ArrayList<>();
	}

	public void addElem(Element item) {
		task.add(item);
	}

	public boolean removeElementByISBN(String codiceISBN) {
		boolean removed = task.removeIf(item -> item.getCodiceISBN().equals(codiceISBN));
		return removed;
	}

	public Optional<Element> searchForISBN(String codiceISBN) {
		return task.stream().filter(item -> item instanceof Libri || item instanceof Riviste)
				.filter(item -> item.getCodiceISBN().equals(codiceISBN)).findFirst();
	}

	public List<Element> searchForYear(int year) {
		return task.stream().filter(item -> item.getYearPub().getYear() == year).collect(Collectors.toList());
	}

	public List<Element> searchForAuthor(String autore) {
		return task.stream().filter(item -> item instanceof Libri && ((Libri) item).getAutore().equals(autore))
				.collect(Collectors.toList());
	}

	public void saveToDisk(String nameFile) throws IOException {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nameFile))) {
			outputStream.writeObject(task);
		}
	}

	@SuppressWarnings("unchecked")
	public void loadToDisk(String nameFile) throws IOException, ClassNotFoundException {
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nameFile))) {
			task = (List<Element>) inputStream.readObject();
		}
	}

}
