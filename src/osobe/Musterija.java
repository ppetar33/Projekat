package osobe;

public class Musterija extends Osoba{

    private String prazanString01;
    private String prazanString02;
    private String prazanString03;
    private String tipKorisnika;

    public Musterija(){
        super();
        this.prazanString01 = "";
        this.prazanString02 = "";
        this.prazanString03 = "";
        this.tipKorisnika = "";
    }
    public Musterija(String korisnickoIme, String lozinka, String ime, String prezime, double jmbg, String adresa, String pol, int brojTelefona, String prazanString01, String prazanString02, String prazanString03, String tipKorisnika) {
        super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
        this.prazanString01 = prazanString01;
        this.prazanString02 = prazanString02;
        this.prazanString03 = prazanString03;
        this.tipKorisnika = tipKorisnika;
    }

    public String getPrazanString01() {
        return prazanString01;
    }

    public void setPrazanString01(String prazanString01) {
        this.prazanString01 = prazanString01;
    }

    public String getPrazanString02() {
        return prazanString02;
    }

    public void setPrazanString02(String prazanString02) {
        this.prazanString02 = prazanString02;
    }

    public String getPrazanString03() {
        return prazanString03;
    }

    public void setPrazanString03(String prazanString03) {
        this.prazanString03 = prazanString03;
    }

    public String getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(String tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    @Override
    public String toString() {
        return "Musterija{" +
                "prazanString01='" + prazanString01 + '\'' +
                ", prazanString02='" + prazanString02 + '\'' +
                ", prazanString03='" + prazanString03 + '\'' +
                ", tipKorisnika='" + tipKorisnika + '\'' +
                "} " + super.toString();
    }
}
