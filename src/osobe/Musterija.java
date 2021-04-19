package osobe;

import enumi.Obrisan;
import enumi.Pol;

public class Musterija extends Osoba {

    private Obrisan obrisan;

    public Musterija() {}

    public Musterija(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa, Pol pol, String brojTelefona, Obrisan obrisan) {
        super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
        this.obrisan = obrisan;
    }

    // string pripremiZaUpis()
    //{
    // super.pripremiZaUpis() + moje +"," + ....
    //}


    public Obrisan getObrisan() {
        return obrisan;
    }

    public void setObrisan(Obrisan obrisan) {
        this.obrisan = obrisan;
    }

    @Override
    public String toString() {
        return "Musterija{" +
                "obrisan=" + obrisan +
                "} " + super.toString();
    }
}
