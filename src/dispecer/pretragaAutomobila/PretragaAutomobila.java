package dispecer.pretragaAutomobila;

import automobili.Automobil;

import javax.swing.*;
import java.util.ArrayList;

public class PretragaAutomobila extends JFrame {
    /*
    * Kombinovana pretraga svih automobila po modelu, proizvođaču, godini proizvodnje,
    * broju registarske oznake i broju taksi vozila;
    * */
    //POLJE ZA PRETRAGU I PROLAZAK KROZ LISTU AUTOMOBILA I POREDIM PODATKE

    public ArrayList<Automobil> pretragaPoModelu(ArrayList<Automobil> automobili, String model ){
        ArrayList<Automobil> pretrazeni = new ArrayList<Automobil>();
        for (Automobil a: automobili) {
            if (a.getModel().equals(model)){
                pretrazeni.add(a);
            }
        }
        return pretrazeni;
    }

}
