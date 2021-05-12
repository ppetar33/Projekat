package dispecer;

import automobili.Voznja;
import liste.Liste;
import osobe.Vozac;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
    da li se misli ukupna zarada za odredjenog vozaca
    ili
    preuzmem kilometrazu za sve voznje saberem je sa cenom startne voznje i pomnozim sa cenom po kilometru
*/

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
        ukupanBrojVoznji(ucitavanje);
        ukupanBrojVoznjiAplikacija(ucitavanje);
        ukupanBrojVoznjiTelefon(ucitavanje);
        ukupanBrojAktivnihVozaca(ucitavanje);
        prosecnoTrajanjeVoznje(ucitavanje);
        prosecanBrojPredjenihKm(ucitavanje);
        ukupnaZaradaZaSveVoznje(ucitavanje);
        prosecnaZaradaPoVoznji(ucitavanje);
    }

    public void ukupanBrojVoznji(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        int uporediDatum = ucitavanje.uporediDatum(trenutniDatum);
        if (uporediDatum != 0){
            System.out.println("Ukupan broj voznji: " + uporediDatum);
        }else{
            System.out.println("Za datum: " + trenutniDatum + " nema voznji!");
        }

    }
    public void ukupanBrojVoznjiAplikacija(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        int uporediDatumIvoznjeAplikacijom = ucitavanje.uporediDatumIvoznjeAplikacijom(trenutniDatum);
        if (uporediDatumIvoznjeAplikacijom != 0){
            System.out.println("Ukupan broj voznji kreiranih putem aplikacije: " + uporediDatumIvoznjeAplikacijom);
        }else{
            System.out.println("Za datum: " + trenutniDatum + " nema voznji kreiranih putem aplikacije!");
        }

    }
    public void ukupanBrojVoznjiTelefon(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        int uporediDatumIvoznjeTelefonom = ucitavanje.uporediDatumIvoznjeTelefonom(trenutniDatum);
        if (uporediDatumIvoznjeTelefonom != 0){
            System.out.println("Ukupan broj voznji kreiranih putem telefona: " + uporediDatumIvoznjeTelefonom);
        }else{
            System.out.println("Za datum: " + trenutniDatum + " nema voznji kreiranih putem telefona!");
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
            System.out.println("Prosecno trajanje voznji: " + uporediDatumItrajanjeVoznje + " min");
        }else{
            System.out.println("Za datum: " + trenutniDatum + " nema voznji!");
        }

    }
    public void prosecanBrojPredjenihKm(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        double uporediDatumIkilometrazu = ucitavanje.uporediDatumIkilometrazu(trenutniDatum);
        if (uporediDatumIkilometrazu != 0){
            System.out.println("Prosecna kilometraza je: " + uporediDatumIkilometrazu + " km");
        }else{
            System.out.println("Za datum: " + trenutniDatum + " nema voznji!");
        }

    }
    public void ukupnaZaradaZaSveVoznje(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        double ukupnaZaradaZaSveVoznje = ucitavanje.ukupnaZaradaZaSveVoznje(trenutniDatum);
        if (ukupnaZaradaZaSveVoznje != 0){
            System.out.println("Ukupna zarada za sve voznje je: " + ukupnaZaradaZaSveVoznje + " din");
        }else{
            System.out.println("Za datum: " + trenutniDatum + " nema voznji!");
        }

    }
    public void prosecnaZaradaPoVoznji(Liste ucitavanje){

        String trenutniDatum = trenutniDatum();
        double prosecnaZaradaPoVoznji = ucitavanje.prosecnaZaradaZaVoznje(trenutniDatum);
        if (prosecnaZaradaPoVoznji != 0){
            System.out.println("Prosecna zarada po voznji je: " + prosecnaZaradaPoVoznji + " din");
        }else{
            System.out.println("Za datum: " + trenutniDatum + " nema voznji!");
        }

    }





    /*
        nedeljni
    */

    /*
        mesecni
    */

    /*
        godisnji
    */



}
