package osobe;

public class Vozac extends Musterija{
	
	
	private String plata;
	private String brojClanskeKarte;
	private String automobil;
	
	public Vozac() {
		super();
		this.plata = "";
		this.brojClanskeKarte = "";
		this.automobil = "";
	}

	public Vozac(String korisnickoIme, String lozinka, String ime, String prezime, double jmbg, String adresa,
			String pol, int brojTelefona, String plata, String brojClanskeKarte, String automobil) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
		this.plata = plata;
		this.brojClanskeKarte = brojClanskeKarte;
		this.automobil = automobil;
	}

	public String getPlata() {
		return plata;
	}

	public void setPlata(String plata) {
		this.plata = plata;
	}

	public String getBrojClanskeKarte() {
		return brojClanskeKarte;
	}

	public void setBrojClanskeKarte(String brojClanskeKarte) {
		this.brojClanskeKarte = brojClanskeKarte;
	}

	public String getAutomobil() {
		return automobil;
	}

	public void setAutomobil(String automobil) {
		this.automobil = automobil;
	}

	@Override
	public String toString() {
		return "Vozac [plata=" + plata + ", brojClanskeKarte=" + brojClanskeKarte + ", automobil=" + automobil + "]";
	}
	

}
