package dispecer.izvestajVozaca;

public class Izvestaji {

    private String trenutniVozac;
    private double ukupnoVoznji;
    private double ukupnoKilometara;
    private double prosekKilometara;
    private double ukupnoTrajanje;
    private double prosekTrajanja;
    private double ukupnaZarada;
    private double prosecnaZarada;
    private double prosecnoBezVoznje;

    public Izvestaji(){
        this.trenutniVozac = "";
        this.ukupnoVoznji = 0;
        this.ukupnoKilometara = 0;
        this.prosekKilometara = 0;
        this.ukupnoTrajanje = 0;
        this.prosekTrajanja = 0;
        this.ukupnaZarada = 0;
        this.prosecnaZarada = 0;
        this.prosecnoBezVoznje = 0;
    }

    public Izvestaji(String trenutniVozac, double ukupnoVoznji, double ukupnoKilometara, double prosekKilometara, double ukupnoTrajanje, double prosekTrajanja, double ukupnaZarada, double prosecnaZarada, double prosecnoBezVoznje) {
        this.trenutniVozac = trenutniVozac;
        this.ukupnoVoznji = ukupnoVoznji;
        this.ukupnoKilometara = ukupnoKilometara;
        this.prosekKilometara = prosekKilometara;
        this.ukupnoTrajanje = ukupnoTrajanje;
        this.prosekTrajanja = prosekTrajanja;
        this.ukupnaZarada = ukupnaZarada;
        this.prosecnaZarada = prosecnaZarada;
        this.prosecnoBezVoznje = prosecnoBezVoznje;
    }


    public String getTrenutniVozac() {
        return trenutniVozac;
    }

    public double getUkupnoVoznji() {
        return ukupnoVoznji;
    }

    public double getUkupnoKilometara() {
        return ukupnoKilometara;
    }

    public double getProsekKilometara() {
        return prosekKilometara;
    }

    public double getUkupnoTrajanje() {
        return ukupnoTrajanje;
    }

    public double getProsekTrajanja() {
        return prosekTrajanja;
    }

    public double getUkupnaZarada() {
        return ukupnaZarada;
    }

    public double getProsecnaZarada() {
        return prosecnaZarada;
    }

    public double getProsecnoBezVoznje() {
        return prosecnoBezVoznje;
    }


    @Override
    public String toString() {
        return "Test{" +
                "trenutniVozac=" + trenutniVozac +
                ", ukupnoVoznji=" + ukupnoVoznji +
                ", ukupnoKilometara=" + ukupnoKilometara +
                ", prosekKilometara=" + prosekKilometara +
                ", ukupnoTrajanje=" + ukupnoTrajanje +
                ", prosekTrajanja=" + prosekTrajanja +
                ", ukupnaZarada=" + ukupnaZarada +
                ", prosecnaZarada=" + prosecnaZarada +
                ", prosecnoBezVoznje=" + prosecnoBezVoznje +
                '}';
    }
}
