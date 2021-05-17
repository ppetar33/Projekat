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
        int j = 0;
        for(int i = 0; i < ucitavanje.neobrisaneVoznjeKreiranePutemTelefona2().size(); i++){
            Voznja voznje = ucitavanje.neobrisaneVoznjeKreiranePutemTelefona2().get(i);
            if(voznje.getStatusVoznje() == StatusVoznje.KREIRANA){
                sadrzaj[j][0] = voznje.getId();
                sadrzaj[j][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                sadrzaj[j][2] = voznje.getAdresaPolaska();
                sadrzaj[j][3] = voznje.getAdresaDestinacije();
                sadrzaj[j][4] = voznje.getMusterija().getIme();
                duplicatedCode(sadrzaj, j, voznje);
                j++;
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

    public static void duplicatedCode(Object[][] sadrzaj, int j, Voznja voznje) {
        if(voznje.getVozac().getKorisnickoIme() != "") {
            sadrzaj[j][5] = voznje.getVozac().getIme().substring(0, 1).toUpperCase() + voznje.getVozac().getIme().substring(1);
        }else{
            sadrzaj[j][5] = "Nema vozaca";
        }
        if(voznje.getBrojKMpredjenih() == 0){
            sadrzaj[j][6] = "/";
        }else {
            sadrzaj[j][6] = voznje.getBrojKMpredjenih();
        }
        if(voznje.getTrajanjVoznje() == 0){
            sadrzaj[j][7] = "/";
        }else {
            sadrzaj[j][7] = voznje.getTrajanjVoznje();
        }
        sadrzaj[j][8] = voznje.getStatusVoznje();
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
                    NarucivanjeVoznjePrekoTelefona voznja = ucitavanje.nadjiVoznjuPoId(id);
                    if(voznja != null){
                        int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da dodelite ovu voznju?", "Potvrda", JOptionPane.YES_NO_OPTION);
                        if ( izbor == JOptionPane.YES_OPTION ){
                            ProzorZaDodeljivanjeVoznji prozorZaDodeljivanjeVoznji = new ProzorZaDodeljivanjeVoznji(ucitavanje,voznja);
                            prozorZaDodeljivanjeVoznji.setVisible(true);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu voznju!", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}

