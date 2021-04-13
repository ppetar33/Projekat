package osobe;

public class Musterija extends Osoba {


    public Musterija() {}

    public Musterija(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa, Pol pol, String brojTelefona) {
        super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
    }

    // string pripremiZaUpis()
    //{
    // super.pripremiZaUpis() + moje +"," + ....
    //}

    @Override
    public String toString() {
        return "Musterija{" +
                "} " + super.toString();
    }
}
