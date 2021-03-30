import java.util.ArrayList;

public class Kirjakategoriat {

	private ArrayList<Kirjakategoria> kategoriat = new ArrayList<Kirjakategoria>();
	private int kategorioita;	// kategorioiden lukumäärä
	private String kategoria;	// kirjakategoria
	
	public Kirjakategoriat() {
		
	}
	// asetusmetodit
	public void setKategorioita(int kategorioita) {
		this.kategorioita = kategorioita;
	}
	
	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}
	
	// saantimetodit
	public int getKategorioita() {
		return this.kategorioita;
	}

	public String getKategoria() {
		return this.kategoria;
	}
	
	public ArrayList<Kirjakategoria> getKirjakategoriat() {
		return this.kategoriat;
	}
	
	// muut metodit
	public boolean poistaKategoria(String kategoria) {
		// Onko lisättävä kirjakategoria jo olemassa?
		for (int i = 0; i < this.kategoriat.size(); i++) {
			if (this.kategoriat.get(i).getKategoriaNimi().equals(kategoria) && this.kategoriat.get(i).getKirjoja() == 0) {
				
				this.kategoriat.remove(i);
				return true;	// Poisto onnistui
			}
		}
		return false;
	}
	
	// poistetaan kirja kirjakategoriasta
	// käytännässä tämä tarkoittaa ko. kategoriasta kirjojen lukumäärän vähentämistä yhdellä
	public void poistaKirjaKategoriasta(Kirjakategoria kategoria) {
		
		for (int i = 0; i < this.kategoriat.size(); i++) {
			if (this.kategoriat.get(i).getKategoriaNimi().equals(kategoria.getKategoriaNimi())) {
				this.kategoriat.get(i).vahennaKirjojenMaaraa();
			}
		}
	}
	
	// lisätään uusi kirjakategoria
	public boolean lisaaUusiKirjakategoria(Kirjakategoria kategoria) {
		
		// Onko lisättävä kirjakategoria jo olemassa?
		for (int i = 0; i < this.kategoriat.size(); i++) {
			if (this.kategoriat.get(i).getKategoriaNimi().equals(kategoria.getKategoriaNimi())) {
				
				// jos yritetään lisätä kategoria, joka on jo olemassa, tarkoittaa se, että
				// kategoriassa kirjojen lukumäärä lisääntyy
				this.kategoriat.get(i).lisaaKirjojenMaaraa();
				return false;	// Lisäys epäonnistui: Kategoria jo olemassa
			} 
		}
		this.kategoriat.add(kategoria);
		this.kategorioita++;		// lisätään onnistuneen kategorialisäyksen jälkeen kategorioiden lukumäärää 1:llä
		kategoria.setKirjoja(1);
		return true;
		
	}
	
	
	public String listaaKategoriatiedot() {
		
		String kategorialista = "";

		for (int i = 0; i < kategoriat.size(); i++) {
			
			kategorialista += (this.kategoriat.get(i).getKategoriaNimi() + " (" + this.kategoriat.get(i).getKirjoja() + ")" + ";");
		}
		
		return kategorialista;
	}

}
