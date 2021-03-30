
public class Kirjakategoria {
	
	private String kategoria;
	private int kirjoja;			// kirjojen lukumäärä kategoriassa
	
	// Konstruktori
	public Kirjakategoria() {
		this.kategoria = "";
	}
	
	// Konstruktori
	public Kirjakategoria(String kategoria) {
		this.kategoria = kategoria;
	}
	
	// asetusmetodit
	public void setKategoriaNimi(String kategoria) {
		this.kategoria = kategoria;
	}
	
	public void setKirjoja(int lkm) {
		this.kirjoja = lkm;
	}
	
	// saantimetodit
	public String getKategoriaNimi() {
		return this.kategoria;
	}
	
	public Kirjakategoria getKirjakategoria() {
		return this;
	}
	
	public int getKirjoja() {
		return this.kirjoja;
	}
	
	
	// muut metodit
	public void lisaaKirjojenMaaraa() {
		this.kirjoja++;
	}
	
	public void vahennaKirjojenMaaraa() {
		this.kirjoja--;
	}
	
	// tämä on rakenteeltaan sama, kuin edellinen, mutta ajattelin, että
	// tulosta metodi palauttaisi tiedon, mitä tulostetaan
	public String tulosta() {
		return this.kategoria;
	}
}
