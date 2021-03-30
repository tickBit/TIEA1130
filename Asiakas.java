
public class Asiakas {

	private String nimi;
	private String email;

	private String katuosoite;
	private String kaupunki;
	private int postinumero;
	private String puhelinnumero;
	private int asiakasnumero;
	
	// Konstruktori 1
	public Asiakas() {
		
	}

	// Konstruktori 2
	public Asiakas(String nimi, String email, String katuosoite, String kaupunki, int postinumero, String puhelinnumero, int asiakasnumero) {
		this.nimi = nimi;
		this.email = email;
		
		this.katuosoite = katuosoite;
		this.kaupunki = kaupunki;
		this.postinumero = postinumero;
		this.puhelinnumero = puhelinnumero;
		this.asiakasnumero = asiakasnumero;
	}
	
	// asetusmetodit
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setKatuosoite(String katuosoite) {
		this.katuosoite = katuosoite;
	}
	
	public void setKaupunki(String kaupunki) {
		this.kaupunki = kaupunki;
	}
	
	public void setPostinumero(int postinumero) {
		this.postinumero = postinumero;
	}
	
	public void setPuhelinnumero(String puhelinnumero) {
		this.puhelinnumero = puhelinnumero;
	}
	
	public void setAsiakasnumero(int asiakasnumero) {
		this.asiakasnumero = asiakasnumero;
	}
	
	// saantimetodit
	public String getNimi() {
		return this.nimi;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getKatuosoite() {
		return this.katuosoite;
	}
	
	public String getKaupunki() {
		return this.kaupunki;
	}
	
	public int getPostinumero() {
		return this.postinumero;
	}
	
	public String getPuhelinnumero() {
		return this.puhelinnumero;
	}
	
	public int getAsiakasnumero() {
		return this.asiakasnumero;
	}
	
	// tulostus-metodi: Palauttaa luokan tiedon merkkijonona
	public String tulosta() {
		return this.nimi + ":" + this.email;
	}
}
