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
    /*
        Prikaz sumiranog izveštaja na dnevnom, nedeljnom, mesečnom i godišnjem nivou. Izveštaj treba da sadrži
        podatke o ukupnom broju vožnji, broju vožnji poručenim putem aplikacije, broju vožnji poručenim putem
        telefonskog poziva, broju aktivnih vozača, prosečnom trajanju vožnje, prosečnom broju pređenih
        kilometara, ukupnoj zaradi za sve vožnje, i prosečnoj zaradi po vožnji. Zaradu vozača po vožnji
        računati po sledećoj formuli:
            (start + brojPredjenihKilometara) * cenaPoKilometru.
    */

    public IzvestajiDispecera(){}

    public IzvestajiDispecera(LocalDateTime vremeIdatum, Voznja voznja, Liste ucitavanje, Vozac vozac){
        this.vremeIdatum = vremeIdatum;
        this.voznja = voznja;
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
    }

    public void ukupanBrojVoznji(Liste ucitavanje){

        System.out.println("Ukupan broj voznji!");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime date = LocalDateTime.now();
        String trenutniDatum = formatter.format(date);
        int uporediDatum = ucitavanje.uporediDatum(trenutniDatum);

        if (uporediDatum != 0){
            int sum = 0;
            System.out.println(uporediDatum);
        }else{
            System.out.println("jeste null");
        }

        // uporediti trenutniDatum sa datumom iz fajla
        // jedna linija u fajlu = 1
        // proci kroz sve voznje i ubaciti u sumu
        // sumu prikazati
    }

    public void ukupanBrojVoznjiAplikacija(){

    }
    public void ukupanBrojVoznjiTelefon(){

    }
    public void ukupanBrojAktivnihVozaca(){

    }
    public void prosecnoTrajanjeVoznje(){

    }
    public void prosecanBrojPredjenihKm(){

    }
    public void ukupnaZaradaVoznji(){

    }
    public void prosecnaZaradaPoVoznji(){

    }
    /*
        dnevni

        LocalDateTime trenutna yyyy-mm-dd
        ukupan broj svih voznji
            suma = 0;
            preuzmem liniju u txt fajlu kao broj koji se uvecava i dodam na sumu
        ukupan broj voznji porucenih putem aplikacije
        ukupan broj voznji porucenih putem telefonskog poziva
        ukupan broj aktivinih vozaca (vozaci koji nisu obrisani)
        prosecno trajanje voznji
        prosecan broj predjenih kilometara
        ukupna zarada za sve voznje (start + brojPredjenihKilometara) * cenaPoKilometru
        prosecna zarada po voznji
    */

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
