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


                    String[] days = new String[7];
                    for (int i = 0; i < days.length; i++) {
                        days[i] = parsiranjeUnesenogDatuma.minusDays(days.length - i - 1).toString();
                    }
                    DoublyLinkedList<String> listaSedamDana = new DoublyLinkedList<>();
                    for (String x : days) {
                        listaSedamDana.add(x);
                    }
                    DoublyLinkedList<String> ukupanBrojVoznjiPrekoTelefona = ucitavanje.ukupanBrojVoznjiPrekoTelefona();
                    int voznjaTelefoni = 0;
                    for(String y : listaSedamDana){
                        for(String x : ukupanBrojVoznjiPrekoTelefona){
                            if(y.equals(x)){
                                voznjaTelefoni++;
                            }
                        }
                    }
                    System.out.println("Ukupan broj voznji narucenih preko telefona je: " + voznjaTelefoni);
                    DoublyLinkedList<String> ukupanBrojVoznjiPrekoAplikacije = ucitavanje.ukupanBrojVoznjiPrekoAplikacije();
                    int voznjaAplikacija = 0;
                    for(String y : listaSedamDana){
                        for(String x : ukupanBrojVoznjiPrekoAplikacije){
                            if(y.equals(x)){
                                voznjaAplikacija++;
                            }
                        }
                    }
                    System.out.println("Ukupan broj voznji narucenih preko telefona je: " + voznjaAplikacija);
                    int ukupanBrojSvihVoznji = voznjaTelefoni + voznjaAplikacija;
                    System.out.println("Ukupan broj svih voznji je: " + ukupanBrojSvihVoznji);


                    // prosecno trajanje voznje
                    double rezultat = 0;
                    double counter = 0;
                    double average;

                    String datumi = null;
                    for(String y : listaSedamDana){
                        for(String x : ukupanBrojVoznjiPrekoTelefona){
                            if(y.equals(x)){
                                datumi = x;
                                System.out.println(datumi);
                                DoublyLinkedList<Double> voznjePrekoTelefona = ucitavanje.nadjiVoznjuNarucenuPrekoTelefonaPoDatumu(datumi);
                                for(Double q : voznjePrekoTelefona){
                                    System.out.println(q + "min");
                                }
                            }
                        }
                    }
                    System.out.println(rezultat);


                    average = rezultat/counter;

                    // prosecna kilometraza
                    // ukupna zarada za sve voznje
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
}

