package CatalogoBib;

import java.io.Serializable;
import java.time.LocalDate;

public class Libri extends Element implements Serializable {
	private static final long serialVersionUID = 1L;
	private String autore;
	private String genere;

	public Libri(String titolo, LocalDate yearPub, int numPag, String autore, String genere) {
		super(titolo, yearPub, numPag);
		this.autore = autore;
		this.genere = genere;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	@Override
	public String toString() {
		return "Libri{" + "codiceISBN='" + getCodiceISBN() + '\'' + ", titolo='" + getTitolo() + '\''
				+ ", annoPubblicazione=" + getYearPub() + ", numeroPagine=" + getNumPag() + ", autore='" + autore + '\''
				+ ", genere='" + genere + '\'' + '}';
	}
}
