package dispecer;

import automobili.Voznja;
import liste.Liste;
import osobe.Vozac;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IzvestajiDispecera {

    private LocalDateTime vremeIdatum;
    private Voznja voznja;
    private Liste ucitavanje;
    private Vozac vozac;

    public IzvestajiDispecera(){
        this.vremeIdatum = null;
        this.voznja = null;
        this.ucitavanje = null;
        this.vozac = null;
    }

    public IzvestajiDispecera(LocalDateTime vremeIdatum, Voznja voznja, Liste ucitavanje, Vozac vozac){
        this.vremeIdatum = vremeIdatum;
        this.voznja = voznja;
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
    }

    private String trenutniDatum() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime date = LocalDateTime.now();
        String trenutniDatum = formatter.format(date);
        return trenutniDatum;
    }

    public void dnevniIzvestaj(Liste ucitavanje){
        System.out.println("");
        ukupanBrojAktivnihVozaca(ucitavanje);
        ukupanBrojVoznji(ucitavanje);
        ukupanBrojVoznjiAplikacija(ucitavanje);
        ukupanBrojVoznjiTelefon(ucitavanje);
        prosecnoTrajanjeVoznje(ucitavanje);
        prosecanBrojPredjenihKm(ucitavanje);
        ukupnaZaradaZaSveVoznje(ucitavanje);
        prosecnaZaradaPoVoznji(ucitavanje);
    }

    public void ukupanBrojVoznji(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        int uporediDatum = ucitavanje.uporediDatum(trenutniDatum);
        if (uporediDatum != 0){
            System.out.println("Za datum: " + trenutniDatum + ", ukupan broj voznji je: " + uporediDatum);
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji!");
        }

    }
    public void ukupanBrojVoznjiAplikacija(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        int uporediDatumIvoznjeAplikacijom = ucitavanje.uporediDatumIvoznjeAplikacijom(trenutniDatum);
        if (uporediDatumIvoznjeAplikacijom != 0){
            System.out.println("Za datum: " + trenutniDatum + ", ukupan broj voznji kreiranih putem aplikacije je: " + uporediDatumIvoznjeAplikacijom);
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji kreiranih putem aplikacije!");
        }

    }
    public void ukupanBrojVoznjiTelefon(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        int uporediDatumIvoznjeTelefonom = ucitavanje.uporediDatumIvoznjeTelefonom(trenutniDatum);
        if (uporediDatumIvoznjeTelefonom != 0){
            System.out.println("Za datum: " + trenutniDatum + ", ukupan broj voznji kreiranih putem telefona je: " + uporediDatumIvoznjeTelefonom);
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji kreiranih putem telefona!");
        }

    }
    public void ukupanBrojAktivnihVozaca(Liste ucitavanje){

        int brojAktivnihVozaca = ucitavanje.brojAktivnihVozaca();
        if (brojAktivnihVozaca != 0){
            System.out.println("Ukupan broj aktivnih vozaca je: " + brojAktivnihVozaca);
        }else{
            System.out.println("Nema aktivnih vozaca.");
        }

    }
    public void prosecnoTrajanjeVoznje(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        double uporediDatumItrajanjeVoznje = ucitavanje.uporediDatumItrajanjeVoznje(trenutniDatum);
        if (uporediDatumItrajanjeVoznje != 0){
            System.out.println("Za datum: " + trenutniDatum + ", prosecno trajanje voznji je: " + Math.round(uporediDatumItrajanjeVoznje) + " min");
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji!");
        }

    }
    public void prosecanBrojPredjenihKm(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        double uporediDatumIkilometrazu = ucitavanje.uporediDatumIkilometrazu(trenutniDatum);
        if (uporediDatumIkilometrazu != 0){
            System.out.println("Za datum: " + trenutniDatum + ", prosecna kilometraza je: " + Math.round(uporediDatumIkilometrazu) + " km");
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji!");
        }

    }
    public void ukupnaZaradaZaSveVoznje(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        double ukupnaZaradaZaSveVoznje = ucitavanje.ukupnaZaradaZaSveVoznje(trenutniDatum);
        if (ukupnaZaradaZaSveVoznje != 0){
            System.out.println("Za datum: " + trenutniDatum + ", ukupna zarada za sve voznje je: " + ukupnaZaradaZaSveVoznje + " din");
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji!");
        }

    }
    public void prosecnaZaradaPoVoznji(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        double prosecnaZaradaPoVoznji = ucitavanje.prosecnaZaradaZaVoznje(trenutniDatum);
        if (prosecnaZaradaPoVoznji != 0){
            System.out.println("Za datum: " + trenutniDatum + ", prosecna zarada po voznji je: " + prosecnaZaradaPoVoznji + " din");
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji!");
        }

    }

}
