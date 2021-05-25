package dispecer.izvestaj;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class NedeljniIzvestaj extends JFrame {

    private JLabel datum = new JLabel("Datum: ");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancel = new JButton("Cancel");

    private Liste ucitavanje;

    public NedeljniIzvestaj(Liste ucitavanje) {
        this.ucitavanje = ucitavanje;
        setTitle("Nedeljeni izvestaj");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initGUI();
        initActions();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }
    private void initGUI(){
        MigLayout layout = new MigLayout("wrap 2");

        setLayout(layout);
        add(datum);
        add(datumUnos);
        add(new JLabel());
        add(btnOk,"split 2");
        this.getRootPane().setDefaultButton(btnOk);
        add(btnCancel);
    }
    private void initActions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(validacija() == true) {

                    String unosDatuma = datumUnos.getText().trim();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate parsiranjeUnesenogDatuma = LocalDate.parse(unosDatuma, formatter);
                    LocalDate sedamDana = parsiranjeUnesenogDatuma.minusDays(7);


                    String[] days = new String[7];
                    for (int i = 0; i < days.length; i++) {
                        days[i] = parsiranjeUnesenogDatuma.minusDays(days.length - i - 1).toString();
                    }
                    DoublyLinkedList<String> listaSedamDana = new DoublyLinkedList<>();
                    for (String x : days) {
                        listaSedamDana.add(x);
                    }

                    // UKUPAN BROJ VOZNJI KREIRANIH PUTEM TELEFONA
                    DoublyLinkedList<String> listaVoznjiTelefon = ucitavanje.ukupanBrojVoznjiPrekoTelefona();
                    int ukupanBrojVoznjiPrekoTelefona = 0;
                    for(String y : listaSedamDana){
                        for(String x : listaVoznjiTelefon){
                            if(y.equals(x)){
                                ukupanBrojVoznjiPrekoTelefona++;
                            }
                        }
                    }
                    System.out.println("Ukupan broj voznji narucenih preko telefona je: " + ukupanBrojVoznjiPrekoTelefona);

                    // UKUPAN BROJ VOZNJI KREIRANIH PUTEM APLIKACIJE
                    DoublyLinkedList<String> listaVoznjiAplikacija = ucitavanje.ukupanBrojVoznjiPrekoAplikacije();
                    int ukupanBrojVoznjiPrekoAplikacije = 0;
                    for(String y : listaSedamDana){
                        for(String x : listaVoznjiAplikacija){
                            if(y.equals(x)){
                                ukupanBrojVoznjiPrekoAplikacije++;
                            }
                        }
                    }
                    System.out.println("Ukupan broj voznji narucenih preko telefona je: " + ukupanBrojVoznjiPrekoAplikacije);

                    // UKUPAN BROJ SVIH VOZNJI
                    int ukupanBrojSvihVoznji = ukupanBrojVoznjiPrekoTelefona + ukupanBrojVoznjiPrekoAplikacije;
                    System.out.println("Ukupan broj svih voznji je: " + ukupanBrojSvihVoznji);

                    if(ukupanBrojSvihVoznji == 0){
                        JOptionPane.showMessageDialog(null, "Od: " + sedamDana + " do: " + unosDatuma + " nazalost, nema voznji.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    }

                    // PROSECNO TRAJANJE VOZNJI
                    String datumiZaTelefon;
                    String datumiZaAplikaciju;
                    DoublyLinkedList<Integer> novaListaTelefoni = new DoublyLinkedList<>();
                    DoublyLinkedList<Integer> novaListaAplikacija = new DoublyLinkedList<>();
                    for(String y : listaSedamDana){
                        for(String x : listaVoznjiTelefon){
                            if(y.equals(x)){
                                datumiZaTelefon = x;
                                DoublyLinkedList<Integer> listaIdevaTelefon = ucitavanje.nadjiVoznjuNarucenuPrekoTelefonaPoDatumu(datumiZaTelefon);
                                for(Integer q : listaIdevaTelefon) {
                                    novaListaTelefoni.add(q);
                                }
                            }
                        }
                        for(String q : listaVoznjiAplikacija){
                            if(y.equals(q)){
                                datumiZaAplikaciju = q;
                                DoublyLinkedList<Integer> listaIdevaAplikacija = ucitavanje.nadjiVoznjuNarucenuPrekoAplikacijePoDatumu(datumiZaAplikaciju);
                                for(Integer g : listaIdevaAplikacija){
                                    novaListaAplikacija.add(g);
                                }
                            }
                        }
                    }
                    double rezultatTelefoni;
                    double sumaTrajanjaVoznjeTelefoni = 0;
                    Set<Integer> listaBezDupliranihIDevaTelefon = findDuplicates(novaListaTelefoni);
                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaTelefon){
                        rezultatTelefoni = ucitavanje.ukupnoTrajanjeVoznjiTelefoni(idKojiTrebaPronaci);
                        sumaTrajanjaVoznjeTelefoni += rezultatTelefoni;
                    }
                    System.out.println("Suma telefoni = " + sumaTrajanjaVoznjeTelefoni);
                    double rezultatAplikacija;
                    double sumaTrajanjaVoznjeAplikacija = 0;
                    Set<Integer> listaBezDupliranihIDevaAplikacija = findDuplicates(novaListaAplikacija);
                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaAplikacija){
                        rezultatAplikacija = ucitavanje.ukupnoTrajanjeVoznjiAplikacija(idKojiTrebaPronaci);
                        sumaTrajanjaVoznjeAplikacija += rezultatAplikacija;
                    }
                    System.out.println("Suma aplikacija = " + sumaTrajanjaVoznjeAplikacija);

                    double ukupnoTrajanjeVoznjiTelefonIaplikacija = sumaTrajanjaVoznjeTelefoni + sumaTrajanjaVoznjeAplikacija;
                    double averageDoubleTrajanje = ukupnoTrajanjeVoznjiTelefonIaplikacija / ukupanBrojSvihVoznji;
                    int prosecnoTrajanjeVoznje = (int) averageDoubleTrajanje;
                    System.out.println("Prosecno trajanje voznje je: " + prosecnoTrajanjeVoznje);


                    // PROSECNA KILOMETRAZA
                    double rezultatTelefoni1;
                    double sumaPredjenihKilometaraTelefoni = 0;
                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaTelefon){
                        rezultatTelefoni1 = ucitavanje.ukupnaKilometrazaTelefoni(idKojiTrebaPronaci);
                        sumaPredjenihKilometaraTelefoni += rezultatTelefoni1;
                    }
                    System.out.println("Suma kilometara telefoni = " + sumaPredjenihKilometaraTelefoni);

                    double rezultatAplikacija1;
                    double sumaPredjenihKilometaraAplikacija = 0;
                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaAplikacija){
                        rezultatAplikacija1 = ucitavanje.ukupnaKilometrazaAplikacija(idKojiTrebaPronaci);
                        sumaPredjenihKilometaraAplikacija += rezultatAplikacija1;
                    }
                    System.out.println("Suma kilometara aplikacija = " + sumaPredjenihKilometaraAplikacija);

                    double ukupnaKilometrazaTelefoniIaplikacija = sumaPredjenihKilometaraTelefoni + sumaPredjenihKilometaraAplikacija;
                    double averageDoubleKM = ukupnaKilometrazaTelefoniIaplikacija / ukupanBrojSvihVoznji;
                    int prosecnaKilometraza = (int) averageDoubleKM;
                    System.out.println("Prosecno trajanje voznje je: " + prosecnaKilometraza);


                    // UKUPNA ZARADA ZA SVE VOZNJE
//                    double rezultatTelefoni2;
//                    double sumaZaradeTelefoni = 0;
//                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaTelefon){
//                        rezultatTelefoni2 = ucitavanje.ukupnaZaradaTelefoni(idKojiTrebaPronaci);
//                        sumaZaradeTelefoni += rezultatTelefoni2;
//                    }
//                    System.out.println("Suma zarade telefoni = " + sumaZaradeTelefoni);
//
//                    double rezultatAplikacija2;
//                    double sumaZaradeAplikacija = 0;
//                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaAplikacija){
//                        rezultatAplikacija2 = ucitavanje.ukupnaZaradaAplikacija(idKojiTrebaPronaci);
//                        sumaZaradeAplikacija += rezultatAplikacija2;
//                    }
//                    System.out.println("Suma zarade aplikacija = " + sumaZaradeAplikacija);


                    // prosecna zarada po voznji
                }

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                NedeljniIzvestaj.this.setVisible(false);
                NedeljniIzvestaj.this.dispose();
            }
        });
    }
    public boolean validacija() {

        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n";

        if(datumUnos.getText().equals("")){
            obavestenjeZaGresku += "\nMorate uneti datum! \n";
            ok = false;
        }

        if (ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Neispravni podaci!", JOptionPane.WARNING_MESSAGE);
        }

        return ok;
    }
    public Set<Integer> findDuplicates(DoublyLinkedList<Integer> list){
        Set<Integer> items = new HashSet<Integer>();
        Set<Integer> duplicates = new HashSet<Integer>();
        for (Integer item : list) {
            if (items.contains(item)) {
                duplicates.remove(item);
            } else {
                items.add(item);
            }
        }
        return items;
    }
}

