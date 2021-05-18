package vozac;

import automobili.Voznja;
import enumi.StatusNaruceneVoznje;
import enumi.StatusVoznje;
import liste.Liste;
import musterija.NarucivanjeVoznjePrekoAplikacije;
import musterija.NarucivanjeVoznjePrekoTelefona;
import osobe.Vozac;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class PrikazVoznjiZakazanihPrekoAplikacije extends JFrame {

    private JToolBar mainJToolBar = new JToolBar();
    private JButton btnPrihvati = new JButton("Prihvati");
    private JButton btnOdbi = new JButton("Odbij");

    private DefaultTableModel table_model;
    private JTable istorijaVoznjeTabela;

    private Liste ucitavanje;
    private Vozac ulogovaniVozac;

    public PrikazVoznjiZakazanihPrekoAplikacije(Liste ucitavanje, Vozac ulogovaniVozac){
        this.ucitavanje = ucitavanje;
        this.ulogovaniVozac = ulogovaniVozac;
        mainJToolBar.add(btnPrihvati);
        mainJToolBar.add(btnOdbi);
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
        Object[][] sadrzaj = new Object[ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().size()][zaglavnje.length];
        int j = 0;
        for(int i = 0; i < ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().size(); i++){
            Voznja voznje = ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().get(i);

            Vozac ulogovaniVozac = null;
            try {
                File ulogovanVozac = new File("src/fajlovi/ulogovanKorisnik.txt");
                Scanner citanjeUlogovanogVozaca = new Scanner(ulogovanVozac);
                while (citanjeUlogovanogVozaca.hasNextLine()) {
                    String data = citanjeUlogovanogVozaca.nextLine();
                    ulogovaniVozac = new Vozac();
                    ulogovaniVozac.setKorisnickoIme(data);
                }
                citanjeUlogovanogVozaca.close();
            }  catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println("Greska");
            }

            if(voznje.getStatusNaruceneVoznje().equals(StatusNaruceneVoznje.APLIKACIJA) && voznje.getVozac().getKorisnickoIme().equals(ulogovaniVozac.getKorisnickoIme())){
                sadrzaj[j][0] = voznje.getId();
                sadrzaj[j][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm"));
                sadrzaj[j][2] = voznje.getAdresaPolaska();
                sadrzaj[j][3] = voznje.getAdresaDestinacije();
                sadrzaj[j][4] = voznje.getMusterija().getIme().substring(0,1).toUpperCase() + voznje.getMusterija().getIme().substring(1);
                sadrzaj[j][5] = voznje.getBrojKMpredjenih();
                sadrzaj[j][6] = voznje.getTrajanjVoznje();
                sadrzaj[j][7] = voznje.getStatusVoznje();
                sadrzaj[j][8] = voznje.getNapomena();
                j++;
            }
        }

        table_model = new DefaultTableModel(sadrzaj, zaglavnje);
        istorijaVoznjeTabela = new JTable(table_model);

        istorijaVoznjeTabela.setRowSelectionAllowed(true);
        istorijaVoznjeTabela.setColumnSelectionAllowed(false);
        istorijaVoznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        istorijaVoznjeTabela.setDefaultEditor(Object.class, null);
        istorijaVoznjeTabela.getTableHeader().setReorderingAllowed(false);

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
                    // todo AKO JE STATUS VOZNJE KREIRANA-NA-CEKANJU ONDA JE MOGUCE PRIHVATITI
                    DefaultTableModel tableModel = (DefaultTableModel) istorijaVoznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);
                    NarucivanjeVoznjePrekoAplikacije nadjiVoznju = ucitavanje.nadjiVoznjuNarucenuPrekoAplikacijePoId(id);
                    if (nadjiVoznju.getStatusVoznje().equals(StatusVoznje.KREIRANA_NA_CEKANJU)){
                        JOptionPane.showMessageDialog(null, "Uspesno ste prihvatili voznju!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        nadjiVoznju.setStatusVoznje(StatusVoznje.PRIHVACENA);
                        ucitavanje.snimanjeVoznji("voznje.txt");
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
                    // todo AKO JE STATUS VOZNJE KREIRANA-NA-CEKANJU ONDA JE MOGUCE ODBITI
                    DefaultTableModel tableModel = (DefaultTableModel) istorijaVoznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);
                    NarucivanjeVoznjePrekoAplikacije nadjiVoznju = ucitavanje.nadjiVoznjuNarucenuPrekoAplikacijePoId(id);
                    if (nadjiVoznju.getStatusVoznje().equals(StatusVoznje.KREIRANA_NA_CEKANJU)){
                        JOptionPane.showMessageDialog(null, "Uspesno ste odbili voznju!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        nadjiVoznju.setStatusVoznje(StatusVoznje.ODBIJENA);
                        ucitavanje.snimanjeVoznji("voznje.txt");
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
    }

}

                    /*
                        ako status voznje nije kreirana na cekanju
                        JOptionPane.showMessageDialog(null, "Morate prihvatiti samo kreirane voznje", "Greska", JOptionPane.WARNING_MESSAGE);
                    */
