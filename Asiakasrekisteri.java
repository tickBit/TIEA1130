import java.util.ArrayList;

public class Asiakasrekisteri {

	private ArrayList<Asiakas> rekisteri = new ArrayList<Asiakas>();
	
	public Asiakasrekisteri() {
		
	}
	
	public void lisaaAsiakas(Asiakas asiakas) {
		this.rekisteri.add(asiakas);
	}
	
	public void poistaAsiakas(Asiakas asiakas) {
		this.rekisteri.remove(asiakas);
	}
	
	public ArrayList<Asiakas> palautaAsiakkaat() {
		return this.rekisteri;
	}
	
	public Asiakas getAsiakasByNumber(int numero) {
		
		Asiakas a = null;
		
		for (int i = 0; i < rekisteri.size(); i++) {
			if (rekisteri.get(i).getAsiakasnumero() == numero) {
				a = rekisteri.get(i);
				break;
			}
		}
		return a;
	}
	
	public Asiakas getAsiakasEmail(String email) {
		
		for (int i = 0; i < rekisteri.size(); i++) {
			if (rekisteri.get(i).getEmail().equals(email)) {
				return rekisteri.get(i);
			}
		}
		
		return null;
	}
	
	public String tulostaAsiakkaat() {
		String asiakastiedot = "";
		
		for (int i = 0; i < rekisteri.size(); i++) {
			
		}
		
		return asiakastiedot;
	}
	
	public void tyhjennaRekisteri() {
		this.rekisteri.clear();
	}
}
