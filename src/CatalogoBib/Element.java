package CatalogoBib;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Element {

	private String codiceISBN;
	private String titolo;
	private LocalDate yearPub;
	private int numPag;

	public Element(String titolo, LocalDate yearPub, int numPag) {
		this.codiceISBN = UUID.randomUUID().toString();
		this.titolo = titolo;
		this.yearPub = yearPub;
		this.numPag = numPag;
	}

	public String getCodiceISBN() {
		return codiceISBN;
	}

	public void setCodiceISBN(String codiceISBN) {
		this.codiceISBN = codiceISBN;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getYearPub() {
		return yearPub;
	}

	public void setYearPub(LocalDate yearPub) {
		this.yearPub = yearPub;
	}

	public int getNumPag() {
		return numPag;
	}

	public void setNumPag(int numPag) {
		this.numPag = numPag;
	}

	@Override
	public String toString() {
		return "ElementoCatalogo{" + "codiceISBN='" + codiceISBN + '\'' + ", titolo='" + titolo + '\''
				+ ", annoPubblicazione=" + yearPub + ", numeroPagine=" + numPag + '}';
	}

}
