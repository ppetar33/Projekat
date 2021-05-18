package vozac;

import automobili.Voznja;
import enumi.StatusVozacaIautomobila;
import enumi.StatusVoznje;
import liste.Liste;
import musterija.NarucivanjeVoznjePrekoTelefona;
import osobe.Vozac;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class PrikazDodeljenihVoznji extends JFrame{

    private JToolBar mainJtoolBar = new JToolBar();
    private JButton btnPrihvati = new JButton("Prihvati");
    private JButton btnOdbi = new JButton("Odbij");
    private DefaultTableModel tableModel;
    private JTable voznjeTabela;

    private Liste ucitavanje;

    public PrikazDodeljenihVoznji(Liste ucitavanje){
        this.ucitavanje = ucitavanje;
        mainJtoolBar.add(btnPrihvati);
        mainJtoolBar.add(btnOdbi);
        setTitle("Prikaz dodeljenih voznji narucenih putem telefona");
        setSize(1100, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
        initListeners();
    }


    private void initGUI(){
        add(mainJtoolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Vozac","Broj predjenih km","Trajanje voznje","Status voznje"};
        Object[][] sadrzaj = new Object[ucitavanje.prikazDodeljenihVoznji().size()][zaglavnje.length];
        for(int i = 0; i < ucitavanje.prikazDodeljenihVoznji().size(); i++){
            Voznja voznje = ucitavanje.prikazDodeljenihVoznji().get(i);
            sadrzaj[i][0] = voznje.getId();
            sadrzaj[i][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            sadrzaj[i][2] = voznje.getAdresaPolaska();
            sadrzaj[i][3] = voznje.getAdresaDestinacije();
            sadrzaj[i][4] = voznje.getMusterija().getIme().substring(0, 1).toUpperCase() + voznje.getMusterija().getIme().substring(1);
            if (voznje.getVozac().getKorisnickoIme() != "") {
                sadrzaj[i][5] = voznje.getVozac().getIme().substring(0, 1).toUpperCase() + voznje.getVozac().getIme().substring(1);
            } else {
                sadrzaj[i][5] = "Nema slobodan vozac";
            }
            if (voznje.getBrojKMpredjenih() == 0) {
                sadrzaj[i][6] = "/";
            } else {
                sadrzaj[i][6] = voznje.getBrojKMpredjenih();
            }
            if (voznje.getTrajanjVoznje() == 0) {
                sadrzaj[i][7] = "/";
            } else {
                sadrzaj[i][7] = voznje.getTrajanjVoznje();
            }
            sadrzaj[i][8] = voznje.getStatusVoznje();
        }
        tableModel = new DefaultTableModel(sadrzaj, zaglavnje);
        voznjeTabela = new JTable(tableModel);

        voznjeTabela.setRowSelectionAllowed(true);
        voznjeTabela.setColumnSelectionAllowed(false);
        voznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        voznjeTabela.setDefaultEditor(Object.class, null);
        voznjeTabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane jsp = new JScrollPane(voznjeTabela);
        add(jsp, BorderLayout.CENTER);
    }

    private void initListeners(){
        btnPrihvati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel) voznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);
                    NarucivanjeVoznjePrekoTelefona nadjiVoznju = ucitavanje.nadjiVoznjuNarucenuPrekoTelefonaPoId(id);
                    int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da prihvatite voznju?", "Potvrda prihvatanja", JOptionPane.YES_NO_OPTION);
                    if(izbor == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Uspesno ste prihvatili voznju!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        nadjiVoznju.setStatusVoznje(StatusVoznje.PRIHVACENA);
                        ucitavanje.snimanjeVoznji("voznje.txt");
                        tableModel.removeRow(red);
                        PrikazDodeljenihVoznji.this.setVisible(false);
                        PrikazDodeljenihVoznji.this.dispose();
                    }
                }
            }
        });
        btnOdbi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel) voznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);
                    NarucivanjeVoznjePrekoTelefona nadjiVoznju = ucitavanje.nadjiVoznjuNarucenuPrekoTelefonaPoId(id);
                    String vozacString = nadjiVoznju.getVozac().getKorisnickoIme();
                    int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da odbijete voznju?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                    if(izbor == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Uspesno ste odbili voznju!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        nadjiVoznju.setStatusVoznje(StatusVoznje.ODBIJENA);
                        Vozac vozac = ucitavanje.nadjiVozaca(vozacString);
                        vozac.setStatusVozaca(StatusVozacaIautomobila.SLOBODAN);
                        ucitavanje.dodavanjeKorisnika();
                        ucitavanje.snimanjeVoznji("voznje.txt");
                        tableModel.removeRow(red);
                        PrikazDodeljenihVoznji.this.setVisible(false);
                        PrikazDodeljenihVoznji.this.dispose();
                    }
                }

            }
        });
    }


}
