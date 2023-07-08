package CatalogoBib;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Archivio {

	/**
	 * 
	 */

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Element item : task) {
			sb.append(item.toString()).append("\n");
		}
		return sb.toString();
	}

//	public void saveToDisk(String nameFile) throws IOException {
//		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nameFile))) {
//			outputStream.writeObject(task);
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public void loadToDisk(String nameFile) throws IOException, ClassNotFoundException {
//		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nameFile))) {
//			task = (List<Element>) inputStream.readObject();
//		}
//	}

}
