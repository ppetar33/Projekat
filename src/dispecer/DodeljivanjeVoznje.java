package dispecer;

import automobili.Voznja;
import enumi.StatusVoznje;
import liste.Liste;
import musterija.NarucivanjeVoznjePrekoTelefona;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class DodeljivanjeVoznje extends JFrame {

    private JToolBar mainJtoolBar = new JToolBar();

    private DefaultTableModel tableModel;
    private JToolBar mainToolBar = new JToolBar();
    private JTable voznjeTabela;
    private JButton btnEdit = new JButton("Dodeli");

    private Liste ucitavanje;
    private NarucivanjeVoznjePrekoTelefona voznja;

    public DodeljivanjeVoznje(Liste ucitavanje, NarucivanjeVoznjePrekoTelefona voznja) {
        this.ucitavanje = ucitavanje;
        this.voznja = voznja;
        setTitle("Dodeljivanje voznji");
        setSize(1100, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
        initTable();
        initListeners();
    }

    private void initGUI(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
        btnEdit.setIcon(deleteIcon);
        mainToolBar.add(btnEdit);
        add(mainToolBar, BorderLayout.NORTH);
    }

    private void initTable(){
        add(mainJtoolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Vozac","Broj predjenih km","Trajanje voznje","Status voznje"};
        Object[][] sadrzaj = new Object[ucitavanje.neobrisaneVoznjeKreiranePutemTelefona2().size()][zaglavnje.length];
        for(int i = 0; i < ucitavanje.neobrisaneVoznjeKreiranePutemTelefona2().size(); i++){
            Voznja voznje = ucitavanje.neobrisaneVoznjeKreiranePutemTelefona2().get(i);
            if(voznje.getStatusVoznje() == StatusVoznje.KREIRANA){
                sadrzaj[i][0] = voznje.getId();
                sadrzaj[i][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                sadrzaj[i][2] = voznje.getAdresaPolaska();
                sadrzaj[i][3] = voznje.getAdresaDestinacije();
                sadrzaj[i][4] = voznje.getMusterija().getIme().substring(0,1).toUpperCase() + voznje.getMusterija().getIme().substring(1);
                if(voznje.getVozac().getKorisnickoIme() != "") {
                    sadrzaj[i][5] = voznje.getVozac().getIme().substring(0, 1).toUpperCase() + voznje.getVozac().getIme().substring(1);
                }else{
                    sadrzaj[i][5] = "Nema slobodan vozac";
                }
                if(voznje.getBrojKMpredjenih() == 0){
                    sadrzaj[i][6] = "/";
                }else {
                    sadrzaj[i][6] = voznje.getBrojKMpredjenih();
                }
                if(voznje.getTrajanjVoznje() == 0){
                    sadrzaj[i][7] = "/";
                }else {
                    sadrzaj[i][7] = voznje.getTrajanjVoznje();
                }
                sadrzaj[i][8] = voznje.getStatusVoznje();
            }
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
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel) voznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);
                    NarucivanjeVoznjePrekoTelefona voznja = ucitavanje.nadjiVoznjuNarucenuPrekoTelefonaPoId(id);
                    String vozac = tableModel.getValueAt(red,5).toString();
                    if(vozac == "Nema slobodan vozac"){
                        JOptionPane.showMessageDialog(null,"Trenutno nema slobodnog vozaca, molimo vas pokusajte kasnije.","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        if (voznja != null) {
                            int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da dodelite ovu voznju?", "Potvrda", JOptionPane.YES_NO_OPTION);
                            if (izbor == JOptionPane.YES_OPTION) {
                                ProzorZaDodeljivanjeVoznji prozorZaDodeljivanjeVoznji = new ProzorZaDodeljivanjeVoznji(ucitavanje, voznja);
                                prozorZaDodeljivanjeVoznji.setVisible(true);
                                DodeljivanjeVoznje.this.setVisible(false);
                                DodeljivanjeVoznje.this.dispose();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu voznju!", "Greska", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }
}

