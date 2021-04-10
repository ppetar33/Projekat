package automobili;

public class Voznja {

    private String datumIvremePorudzbine;
    private String adresaPolaska;
    private String adresaDestinacije;
    private String musterija;
    private String vozac;
    private double brojKMpredjenih;
    private double trajanjVoznje;
    private StatusVoznje statusVoznje;

    public Voznja(){}

    public Voznja(String datumIvremePorudzbine, String adresaPolaska, String adresaDestinacije, String musterija, String vozac, double brojKMpredjenih, double trajanjVoznje, StatusVoznje statusVoznje){
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

    public String getMusterija() {
        return musterija;
    }

    public void setMusterija(String musterija) {
        this.musterija = musterija;
    }

    public String getVozac() {
        return vozac;
    }

    public void setVozac(String vozac) {
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
