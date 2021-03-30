
public class Kirja extends Kirjakategoria {

	private String nimi;
	private String kirjoittaja;
	private String julkaisija;
	private int sivumaara;
	private int kuntoluokka;			// 1..5
	private int lukumaara;				// kyseisen kirjan lukumäärä varastossa
	private float hinta;
	private int varauksia;				// kertoo, kunka monta varausta kirjasta on tehty
	
	// Konstruktori 1
	public Kirja() {
		
		
	}
	
	// Konstruktori 2. Ko. kirjan lukumäärä asetetaan lisaaKirjaa-metodin yhteydessä
	public Kirja(String nimi, String kirjoittaja, String julkaisija, int sivumaara, int kuntoluokka, String kategoria, float hinta) {
		this.nimi = nimi;
		this.kirjoittaja = kirjoittaja;
		this.julkaisija = julkaisija;
		this.kuntoluokka = kuntoluokka;
		this.setKategoriaNimi(kategoria);
		this.hinta = hinta;
		this.sivumaara = sivumaara;
		this.varauksia = 0;	// kun kirja luodaan, siitä on oletuksena 0 varausta
				
	}
	
	// asetusmetodit
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	public void setKirjoittaja(String kirjoittaja) {
		this.kirjoittaja = kirjoittaja;
	}
	
	public void setJulkaisija(String julkaisija) {
		this.julkaisija = julkaisija;
	}

	public void setSivumaara(int sivumaara) {
		this.sivumaara = sivumaara;
	}
	
	public void setKuntoluokka(int kuntoluokka) {
		this.kuntoluokka = kuntoluokka;
	}
	
	public void setLukumaara(int lukumaara) {
		this.lukumaara = lukumaara;
	}
	
	public void setHinta(float hinta) {
		this.hinta = hinta;
	}
	
	
	// saantimetodit
	public String getNimi() {
		return this.nimi;
	}
	
	public String getKirjoittaja() {
		return this.kirjoittaja;
	}

	public String getJulkaisija() {
		return this.julkaisija;
	}
	
	public int getSivumaara() {
		return this.sivumaara;
	}
	
	public int getKuntoluokka() {
		return this.kuntoluokka;
	}
	
	public int getLukumaara() {
		return this.lukumaara;
	}
	
	public String getKategoria() {
		return this.getKategoriaNimi();
	}
	
	public float getHinta() {
		return this.hinta;
	}
	
	public int getVarauksia() {
		return this.varauksia;
	}
	
	public void lisaaVarattu() {
		this.varauksia++;
	}
	
	public void vahennaVarattu() {
		this.varauksia--;
	}
	
	// Kaikki kirjaan liittyvät tiedot tulostettavaan muotoon
	public String tulosta() {
		String info = "";
		
		info = this.nimi + ":" + this.kirjoittaja + ":" + this.julkaisija + ":" + this.sivumaara + ":" + this.kuntoluokka + ":" + this.hinta + ":" + this.lukumaara;
		
		return info;
	}
}
