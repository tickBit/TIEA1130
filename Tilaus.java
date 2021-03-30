public class Tilaus extends Asiakas {

	private Kirja kirja;
	private String tilausPVM;
	private boolean maksettu;
	private boolean lahetetty;
	private int tilausnumero;
	
	public Tilaus() {
		this.maksettu = false;
	}
	
	public Tilaus(Kirja kirja) {
		this.kirja = kirja;
		this.maksettu = false;
	}
	
	public void maksaTilaus() {
		this.maksettu = true;
	}
	
	public void setTilausnumero(int numero) {
		this.tilausnumero = numero;
	}
	
	public void setLahetetty(boolean lahetetty) {
		this.lahetetty = lahetetty;
	}
	
	public int getTilausnumero() {
		return this.tilausnumero;
	}
	
	public void asetaTilausPVM(String date) {
		this.tilausPVM = date;
	}
	
	public void asetaTilattuKirja(Kirja kirja) {
		this.kirja = kirja;
	}
	
	public String getTilatunKirjanNimi() {
		return this.kirja.getNimi();
	}
	
	public boolean getMaksettu() {
		return this.maksettu;
	}
	
	public String getTilausPVM() {
		return this.tilausPVM;
	}
	
	public boolean getLahetetty() {
		return this.lahetetty;
	}
	
	public String tulostaTilaus() {
		
		String info = "";
				
		info = "Tilausnumero: " + this.tilausnumero + " Maksettu: " + this.maksettu + " Lähetetty: " + this.lahetetty + " Tilattu: " + this.tilausPVM + " " + this.kirja.getNimi() + " Asiakas: " + this.getNimi() + " ##" + this.getAsiakasnumero() + "\n";
		
		return info;
	}
}
