package automobili;

import osobe.Musterija;
import osobe.Vozac;

public class Voznja {

    /*
        Osnovni entitet u sklopu poslovanja taksi službe je vožnja. Za svaku vožnju se evidentiraju sledeći podaci:
            Datum i vreme porudžbine, adresa polaska, adresa destinacije, mušterija koja je naručila vožnju,
            vozač koji je preuzeo porudžbinu, broj km pređenih u vožnji, trajanje vožnje i status vožnje.
            Status vožnje može biti KREIRANA, KREIRANA-NA ČEKANJU, DODELJENA, PRIHV AĆENA, ZAVRŠENA, ODBIJENA.
            Vožnje je moguće poručiti na dva načina:
                1. o Telefonskim pozivom: Kada se kreira ova vožnja njen inicijalni status je KREIRANA. Nakon što
                dispečer vožnju dodeli određenom vozaču njen status postaje DODELJENA.
                2. o Putem aplikacije: Za sve porudžbine preko aplikacije potrebno je evidentirati tekstualnu
                napomenu koju unose korisnici. Kada se kreira ova vožnja njen inicijalni status je
                KREIRANA-NA ČEKANJU.
    */

    private String datumIvremePorudzbine;
    private String adresaPolaska;
    private String adresaDestinacije;
    private Musterija musterija;
    private Vozac vozac;
    private double brojKMpredjenih;
    private double trajanjVoznje;
    private StatusVoznje statusVoznje;

    public Voznja(){}

    public Voznja(String datumIvremePorudzbine, String adresaPolaska, String adresaDestinacije, Musterija musterija, Vozac vozac, double brojKMpredjenih, double trajanjVoznje, StatusVoznje statusVoznje){
        this.datumIvremePorudzbine = datumIvremePorudzbine;
        this.adresaPolaska = adresaPolaska;
        this.adresaDestinacije = adresaDestinacije;
        this.musterija = musterija;
        this.vozac = vozac;
        this.brojKMpredjenih = brojKMpredjenih;
        this.trajanjVoznje = trajanjVoznje;
        this.statusVoznje = statusVoznje;
    }

    public String getDatumIvremePorudzbine() {
        return datumIvremePorudzbine;
    }

    public void setDatumIvremePorudzbine(String datumIvremePorudzbine) {
        this.datumIvremePorudzbine = datumIvremePorudzbine;
    }

    public String getAdresaPolaska() {
        return adresaPolaska;
    }

    public void setAdresaPolaska(String adresaPolaska) {
        this.adresaPolaska = adresaPolaska;
    }

    public String getAdresaDestinacije() {
        return adresaDestinacije;
    }

    public void setAdresaDestinacije(String adresaDestinacije) {
        this.adresaDestinacije = adresaDestinacije;
    }

    public Musterija getMusterija() {
        return musterija;
    }

    public void setMusterija(Musterija musterija) {
        this.musterija = musterija;
    }

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    public double getBrojKMpredjenih() {
        return brojKMpredjenih;
    }

    public void setBrojKMpredjenih(double brojKMpredjenih) {
        this.brojKMpredjenih = brojKMpredjenih;
    }

    public double getTrajanjVoznje() {
        return trajanjVoznje;
    }

    public void setTrajanjVoznje(double trajanjVoznje) {
        this.trajanjVoznje = trajanjVoznje;
    }

    public StatusVoznje getStatusVoznje() {
        return statusVoznje;
    }

    public void setStatusVoznje(StatusVoznje statusVoznje) {
        this.statusVoznje = statusVoznje;
    }

    @Override
    public String toString() {
        return "Voznja{" +
                "datumIvremePorudzbine='" + datumIvremePorudzbine + '\'' +
                ", adresaPolaska='" + adresaPolaska + '\'' +
                ", adresaDestinacije='" + adresaDestinacije + '\'' +
                ", musterija='" + musterija + '\'' +
                ", vozac='" + vozac + '\'' +
                ", brojKMpredjenih=" + brojKMpredjenih +
                ", trajanjVoznje=" + trajanjVoznje +
                ", statusVoznje=" + statusVoznje +
                '}';
    }
}
