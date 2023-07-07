package CatalogoBib;

import java.time.LocalDate;

public class Riviste extends Element {
	private Periodicità periodicità;

	public Riviste(String titolo, LocalDate yearPub, int numPag, Periodicità periodicità) {
		super(titolo, yearPub, numPag);
		this.periodicità = periodicità;
	}

	public Periodicità getPeriodicità() {
		return periodicità;
	}

	public void setPeriodicità(Periodicità periodicità) {
		this.periodicità = periodicità;
	}

	@Override
	public String toString() {
		return "Rivista{" + "codiceISBN='" + getCodiceISBN() + '\'' + ", titolo='" + getTitolo() + '\''
				+ ", annoPubblicazione=" + getYearPub() + ", numeroPagine=" + getNumPag() + ", periodicità="
				+ periodicità + '}';
	}

}
