import java.util.ArrayList;

public class Tilaukset {

	private ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>();
	
	// Konstruktori
	public Tilaukset() {
		
	}
	
	public void lisaaTilaus(Tilaus tilaus) {
		this.tilaukset.add(tilaus);
	}
	
	public void poistaTilaus(Tilaus tilaus) {
		this.tilaukset.remove(tilaus);
	}
	
	public Tilaus getTilausByTilausnumero(int tilausnumero) {
		
		Tilaus tilaus = null;
		
		for (int i = 0; i < this.tilaukset.size(); i++) {
			if (this.tilaukset.get(i).getTilausnumero() == tilausnumero) {
				tilaus = tilaukset.get(i);
				break;
			}
		}
		return tilaus;
	}
	
	// poimitaan tilaukset asikasnumeron mukaan
	public ArrayList<Tilaus> getTilausByAsiakasnumero(int asiakasnumero) {
	
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>();
		
		for (int i = 0; i < this.tilaukset.size(); i++) {
			if (this.tilaukset.get(i).getAsiakasnumero() == asiakasnumero) {
				tilaukset.add(this.tilaukset.get(i));
			}
		}
		
		return tilaukset;
	}

	
	// palautetaan kaikki tilaukset
	public ArrayList<Tilaus> annaTilaukset() {
		return this.tilaukset;
	}
	
	// poimitaan tilaukset asikasnumeron mukaan
	public String getTilausTiedotByAsiakasnumero(int asiakasnumero) {
	
		String info = "";
		
		for (int i = 0; i < this.tilaukset.size(); i++) {
			if (this.tilaukset.get(i).getAsiakasnumero() == asiakasnumero) {
				Tilaus t = this.tilaukset.get(i);
				
				info+= t.tulostaTilaus();
			}
		}
		
		return info;
	}
	
	public String getTilauksenTiedot(int tilausnumero) {
		
		String info = "";
		Tilaus tilaus;
		
		for (int i = 0; i < this.tilaukset.size(); i++) {
			if (this.tilaukset.get(i).getTilausnumero() == tilausnumero) {
				tilaus = tilaukset.get(i);
				info = "Tilausnumero: " + tilaus.getTilausnumero() + " Asiakas: " + tilaus.getNimi() + " ##" + tilaus.getAsiakasnumero() + " Tilaus: " + tilaus.getTilatunKirjanNimi();
			}
		}
		
		return info;
	}
	
}
