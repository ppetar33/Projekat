package dispecer.pretragaAutomobila;

import automobili.Automobil;
import liste.doublyLinkedList.DoublyLinkedList;

import javax.swing.*;


public class PretragaAutomobila extends JFrame {
    /*
    * Kombinovana pretraga svih automobila po modelu, proizvođaču, godini proizvodnje,
    * broju registarske oznake i broju taksi vozila;
    * */
    //POLJE ZA PRETRAGU I PROLAZAK KROZ LISTU AUTOMOBILA I POREDIM PODATKE

    public DoublyLinkedList<Automobil> pretragaPoModelu(DoublyLinkedList<Automobil> automobili, String model ){
        DoublyLinkedList<Automobil> pretrazeni = new DoublyLinkedList<Automobil>();
        for (Automobil a: automobili) {
            if (a.getModel().equals(model)){
                pretrazeni.add(a);
            }
        }
        return pretrazeni;
    }

}
