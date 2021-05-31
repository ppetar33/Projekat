package vozac.prikazVoznji;

import enumi.StatusVozacaIautomobila;
import enumi.StatusVoznje;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import main.TaxiSluzbaMain;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import osobe.Vozac;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PrikazVoznjiZakazanihPrekoAplikacije extends JFrame {

    private JToolBar mainJToolBar = new JToolBar();
    private JButton btnPrihvati = new JButton("Prihvati");
    private JButton btnOdbi = new JButton("Odbij");
    private JButton btnOsvezi = new JButton("Osvezi");


    private DefaultTableModel table_model;
    private JTable istorijaVoznjeTabela;

    private Liste ucitavanje;
    private Vozac ulogovaniVozac;

    public PrikazVoznjiZakazanihPrekoAplikacije(Liste ucitavanje, Vozac ulogovaniVozac){
        this.ucitavanje = ucitavanje;
        this.ulogovaniVozac = ulogovaniVozac;
        mainJToolBar.add(btnPrihvati);
        mainJToolBar.add(btnOdbi);
        mainJToolBar.add(btnOsvezi);
        setTitle("Prikaz svih voznji zakazanih preko aplikacije");
        setSize(1050,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        initListeners();
    }


    private void initGUI(){
        add(mainJToolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Broj predjenih km","Trajanje voznje","Status voznje", "Napomena"};
        Object[][] sadrzaj = new Object[ucitavanje.prikazVozacuSvihVoznjiNarucenihPrekoAplikacije().size()][zaglavnje.length];
        int j = 0;
        for(int i = 0; i < ucitavanje.prikazVozacuSvihVoznjiNarucenihPrekoAplikacije().size(); i++){
            NarucivanjeVoznjePrekoAplikacije voznje = ucitavanje.prikazVozacuSvihVoznjiNarucenihPrekoAplikacije().get(i);
            sadrzaj[j][0] = voznje.getId();
            sadrzaj[j][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm"));
            sadrzaj[j][2] = voznje.getAdresaPolaska();
            sadrzaj[j][3] = voznje.getAdresaDestinacije();
            sadrzaj[j][4] = voznje.getMusterija().getKorisnickoIme();
            sadrzaj[j][5] = voznje.getBrojKMpredjenih();
            sadrzaj[j][6] = voznje.getTrajanjVoznje();
            sadrzaj[j][7] = voznje.getStatusVoznje();
            sadrzaj[j][8] = voznje.getNapomena();
            j++;
        }

        table_model = new DefaultTableModel(sadrzaj, zaglavnje);
        istorijaVoznjeTabela = new JTable(table_model);

        istorijaVoznjeTabela.setRowSelectionAllowed(true);
        istorijaVoznjeTabela.setColumnSelectionAllowed(false);
        istorijaVoznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        istorijaVoznjeTabela.setDefaultEditor(Object.class, null);
        istorijaVoznjeTabela.getTableHeader().setReorderingAllowed(false);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_model);
        istorijaVoznjeTabela.setRowSorter(sorter);

        JScrollPane jsp = new JScrollPane(istorijaVoznjeTabela);
        add(jsp, BorderLayout.CENTER);
    }

    private void initListeners() {
        btnPrihvati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = istorijaVoznjeTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel) istorijaVoznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);

                    DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> sveVoznjePrekoAplikacije = ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije();
                    int indexGdeSeNalazi = ucitavanje.pronadjiVoznjeAplikacijaBinarySearch(sveVoznjePrekoAplikacije,id);
                    NarucivanjeVoznjePrekoAplikacije nadjiVoznju = sveVoznjePrekoAplikacije.get(indexGdeSeNalazi);

                    if (nadjiVoznju.getStatusVoznje().equals(StatusVoznje.DODELJENA)){
                        ProzorZaPrihvatanjeVoznjeAplikacije prozorZaPrihvatanjeVoznjeAplikacije = new ProzorZaPrihvatanjeVoznjeAplikacije(ucitavanje,nadjiVoznju);
                        prozorZaPrihvatanjeVoznjeAplikacije.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(null, "Ovu voznju nije moguce prihvatiti!", "Greska", JOptionPane.WARNING_MESSAGE);
                        NarucivanjeVoznjePrekoAplikacije narucivanjeVoznjePrekoAplikacije = ucitavanje.nadjiVoznjuZakazanuPrekoAplikacije();
                        if(narucivanjeVoznjePrekoAplikacije != null){
                            JOptionPane.showMessageDialog(null, "Mozete prihvatiti samo voznje koje su kreirane na cekanju!", "Greska", JOptionPane.WARNING_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "Uspesno prihvacena voznja!", "Greska", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });
        btnOdbi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = istorijaVoznjeTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel) istorijaVoznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);

                    DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> sveVoznjePrekoAplikacije = ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije();
                    int indexGdeSeNalazi = ucitavanje.pronadjiVoznjeAplikacijaBinarySearch(sveVoznjePrekoAplikacije,id);
                    NarucivanjeVoznjePrekoAplikacije nadjiVoznju = sveVoznjePrekoAplikacije.get(indexGdeSeNalazi);
                    if (nadjiVoznju.getStatusVoznje().equals(StatusVoznje.DODELJENA)){
                        JOptionPane.showMessageDialog(null, "Uspesno ste odbili voznju!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        ulogovaniVozac.setStatusVozaca(StatusVozacaIautomobila.SLOBODAN);
                        ucitavanje.dodavanjeKorisnika();
                        NarucivanjeVoznjePrekoAplikacije voznjaKojaNemaVozaca = ucitavanje.voznjaKojaNemaVozaca();
                        if(voznjaKojaNemaVozaca != null){
                            Vozac vozac = new Vozac();
                            vozac.setKorisnickoIme(ulogovaniVozac.getKorisnickoIme());
                            voznjaKojaNemaVozaca.setVozac(vozac);
                            ulogovaniVozac.setStatusVozaca(StatusVozacaIautomobila.ZAUZET);
                            ucitavanje.dodavanjeKorisnika();
                            ucitavanje.snimanjeVoznji(TaxiSluzbaMain.VOZNJE_FAJL);
                        }else{
                            System.out.println("Sve voznje kreirane putem aplikacije imaju vozaca!");
                        }
                        nadjiVoznju.setStatusVoznje(StatusVoznje.ODBIJENA);
                        ucitavanje.snimanjeVoznji(TaxiSluzbaMain.VOZNJE_FAJL);
                        tableModel.fireTableDataChanged();
                    }else {
                        JOptionPane.showMessageDialog(null, "Ovu voznju nije moguce odbiti!", "Greska", JOptionPane.WARNING_MESSAGE);
                        NarucivanjeVoznjePrekoAplikacije narucivanjeVoznjePrekoAplikacije = ucitavanje.nadjiVoznjuZakazanuPrekoAplikacije();
                        if(narucivanjeVoznjePrekoAplikacije != null){
                            JOptionPane.showMessageDialog(null, "Mozete odbiti samo voznje koje su kreirane na cekanju!", "Greska", JOptionPane.WARNING_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "Uspesno odbijena voznja!", "Greska", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });
        btnOsvezi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrikazVoznjiZakazanihPrekoAplikacije.this.setVisible(false);
                PrikazVoznjiZakazanihPrekoAplikacije.this.dispose();
                PrikazVoznjiZakazanihPrekoAplikacije prikazVoznjiZakazanihPrekoAplikacije = new PrikazVoznjiZakazanihPrekoAplikacije(ucitavanje, ulogovaniVozac);
                prikazVoznjiZakazanihPrekoAplikacije.setVisible(true);
            }
        });
    }

}

