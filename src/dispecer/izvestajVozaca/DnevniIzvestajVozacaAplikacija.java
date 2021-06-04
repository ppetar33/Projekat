package dispecer.izvestajVozaca;

import automobili.Voznja;
import dispecer.izvestaj.Izvestaj;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class DnevniIzvestajVozacaAplikacija extends JFrame {

    private JLabel datum = new JLabel("Unesi datum");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOK = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private Liste ucitavanje;
    public JToolBar mainToolBar = new JToolBar();
    public DefaultTableModel table_model;
    public JTable izvestajiTabela;

    public DnevniIzvestajVozacaAplikacija(Liste ucitavanje){
        this.ucitavanje = ucitavanje;
        setTitle("Dnevni izvestaj vozaca");
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
        add(btnOK,"split 2");
        this.getRootPane().setDefaultButton(btnOK);
        add(btnCancel);
    }

    private void initActions(){
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija() == true){
                    String unosDatuma = datumUnos.getText().trim();
                    boolean voznje = ucitavanje.nadjiDatum(unosDatuma);

                    if (voznje == false){
                        JOptionPane.showMessageDialog(null, "Nazalost, za uneti datum, nema voznji.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        String uneseniDatum = datumUnos.getText().trim();

                        int ukupanBrojVoznjiAplikacija = ucitavanje.uporediDatumIvoznjeAplikacijom(uneseniDatum);
                        int ukupanBrojVoznjiTelefon = ucitavanje.uporediDatumIvoznjeTelefonom(uneseniDatum);
                        int ukupanBrojSvihVoznji = ukupanBrojVoznjiAplikacija + ukupanBrojVoznjiTelefon;
                        double ukupnoKilometara = ucitavanje.uporediDatumIkilometrazuZaStatistiku(uneseniDatum);
                        double prosekKilometara = ukupnoKilometara / ukupanBrojSvihVoznji;
                        double ukupnoTrajanje = ucitavanje.uporediDatumITrajanjeZaStatistiku(uneseniDatum);
                        double prosekTrajanja = ukupnoTrajanje / ukupanBrojSvihVoznji;
                        double ukupnaZarada = ucitavanje.uporediDatumIZaraduZaStatistiku(uneseniDatum);
                        double prosecnaZarada = ukupnaZarada / ukupanBrojSvihVoznji;
                        double prosecnoBezVoznje = 8 - ukupnoTrajanje;

//                        TabelaZaPrikazIzvestaja tabelaZaPrikazIzvestaja = new TabelaZaPrikazIzvestaja(ukupanBrojVoznjiAplikacija, ukupnoKilometara, prosekKilometara, ukupnoTrajanje, prosekTrajanja, ukupnaZarada, prosecnaZarada, prosecnoBezVoznje);
//                        tabelaZaPrikazIzvestaja.setVisible(true);

                    }

                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                DnevniIzvestajVozacaAplikacija.this.setVisible(false);
                DnevniIzvestajVozacaAplikacija.this.dispose();
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
