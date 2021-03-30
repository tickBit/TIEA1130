import java.util.ArrayList;

public class Kirjat {

	
	private ArrayList<Kirja> kirjat = new ArrayList<Kirja>();
	private int kirjatLKM;	// kaikki kirjat yhteensä

	public Kirjat() {
		
	}
	
	public void setKirjatLKM(int lkm) {
		this.kirjatLKM = lkm;
	}
	
	public int getKirjatLKM() {
		return this.kirjatLKM;
	}
	
	
	public void poistaKirja(Kirja kirja) {
		this.kirjat.remove(kirja);
		this.kirjatLKM--;
		
	}
	public void lisaaKirja(Kirja kirja) {
		
		boolean kirjaOlemassa = false;
		int kirjaIndeksi = -1;
		
		// Onko samanniminen kirja olemassa?
		for (int i = 0; i < kirjat.size(); i++) {
			if (kirjat.get(i).getNimi().equals(kirja.getNimi())) {
				kirjaIndeksi = i;
				kirjaOlemassa = true;
			}
		}
		
		
		// Jos samannimistä kirjaa ei ole vielä olemassa kokoelmassa, lisätään se ja asetetaan ko. kirjojen lukumääräksi 1
		if (kirjaOlemassa == false) {
			kirja.setLukumaara(1);
			this.kirjat.add(kirja);
			this.kirjatLKM++;
		} else {
			// Yksittäisen kirjan kappalemäärä
			this.kirjat.get(kirjaIndeksi).setLukumaara((this.kirjat.get(kirjaIndeksi).getLukumaara() + 1));
						
			// Kirjojen kokonaismäärä
			this.kirjatLKM++;
			
		}
				
	}
	
	// annetaan kirja kirjan nimen mukaan
	public Kirja annaKirjaNimi(String kirjannimi) {
		
		Kirja kirja = null;
		
		for (int i = 0; i < kirjat.size(); i++) {
			if (kirjat.get(i).getNimi().equals(kirjannimi)) {
				kirja = kirjat.get(i);
				break;
			}
		}
		
		return kirja;
	}
	
	// annetaan kirjat julkaisijan mukaan
	public ArrayList<Kirja> annaKirjatJulkaisija(String julkaisija) {
		
		ArrayList<Kirja> books = new ArrayList<Kirja>();
				
		for (int i = 0; i < this.kirjat.size(); i++) {
			if ((this.kirjat.get(i).getJulkaisija()).equals(julkaisija)) {
				books.add(this.kirjat.get(i));
			}
		}
		
		return books;
	}
	
	
	// annetaan kirja kirjoittajan mukaan
	public ArrayList<Kirja> annaKirjatKirjoittaja(String kirjoittaja) {
		
		ArrayList<Kirja> books = new ArrayList<Kirja>();
		
		for (int i = 0; i < this.kirjat.size(); i++) {
			if (this.kirjat.get(i).getKirjoittaja().equals(kirjoittaja)) {
				books.add(this.kirjat.get(i));
			}
		}
		
		return books;
		
	}



	// annetaan kirjat kategorian nimen mukaan
	public ArrayList<Kirja> annaKirjatKategoriaNimi(String kategoria) {
		
		ArrayList<Kirja> books = new ArrayList<Kirja>();
				
		for (int i = 0; i < this.kirjat.size(); i++) {
			
			if (this.kirjat.get(i).getKategoriaNimi().equals(kategoria)) {
				books.add(this.kirjat.get(i));
			}
		}
		
		return books;
	}
	
	// anna kaikki kirjat
	public ArrayList<Kirja> annaKaikkiKirjat() {
		return this.kirjat;
	}
}
