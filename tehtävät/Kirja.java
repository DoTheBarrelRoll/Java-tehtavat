
public class Kirja {
	private String nimi, tekija;
	private int julkaisuvuosi;
	
	public Kirja() {
		nimi = "Ei nimeä";
		tekija = "Ei tekijää";
		julkaisuvuosi = 0;
	}
	
	
	
	@Override
	public String toString() {
		return "Kirja [nimi=" + nimi + ", tekija=" + tekija + ", julkaisuvuosi=" + julkaisuvuosi + "]";
	}



	public Kirja(String nimi, String tekija, int julkaisuvuosi) {
		this.nimi = nimi;
		this.tekija = tekija;
		this.julkaisuvuosi = julkaisuvuosi;
	}

	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	public String getTekija() {
		return tekija;
	}
	public void setTekija(String tekija) {
		this.tekija = tekija;
	}
	public int getJulkaisuvuosi() {
		return julkaisuvuosi;
	}
	public void setJulkaisuvuosi(int julkaisuvuosi) {
		this.julkaisuvuosi = julkaisuvuosi;
	}
	
	
}
