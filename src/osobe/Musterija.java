package osobe;

public class Musterija {
	
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private String jmbg;
	private String adresa;
	private String pol;
	private String brojTelefona;
	
	public Musterija() {
		this.korisnickoIme = "";
		this.lozinka = "";
		this.ime = "";
		this.prezime = "";
		this.jmbg = "";
		this.adresa = "";
		this.pol = "";
		this.brojTelefona = "";
	}
	
	public Musterija(String korisnickoIme,String lozinka,String ime,String prezime,String jmbg,String adresa,String pol,String brojTelefona) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.pol = pol;
		this.brojTelefona = brojTelefona;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	@Override
	public String toString() {
		return "Musterija [korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", ime=" + ime + ", prezime="
				+ prezime + ", jmbg=" + jmbg + ", adresa=" + adresa + ", pol=" + pol + ", brojTelefona=" + brojTelefona
				+ "]";
	}
	
}
