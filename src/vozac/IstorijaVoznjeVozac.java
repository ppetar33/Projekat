package vozac;

import automobili.Voznja;
import enumi.StatusVoznje;
import liste.Liste;
import osobe.Musterija;
import osobe.Vozac;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class IstorijaVoznjeVozac extends JFrame {

    private JToolBar mainJToolBar = new JToolBar();

    private DefaultTableModel table_model;
    private JTable istorijaVoznjeTabela;

    private Liste ucitavanje;
    private Vozac ulogovaniVozac;

    public IstorijaVoznjeVozac(Liste ucitavanje, Vozac ulogovaniVozac){
        this.ucitavanje = ucitavanje;
        this.ulogovaniVozac = ulogovaniVozac;
        setTitle("Prikaz istorije sopstvene voznje");
        setSize(1050,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
    }

    private void initGUI(){
        add(mainJToolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Broj predjenih km","Trajanje voznje","Status voznje"};
        Object[][] sadrzaj = new Object[ucitavanje.getVoznja().size()][zaglavnje.length];
        int j = 0;
        for(int i = 0; i < ucitavanje.getVoznja().size(); i++){
            Voznja voznje = ucitavanje.getVoznja().get(i);

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

            if(voznje.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznje.getVozac().getKorisnickoIme().equals(ulogovaniVozac.getKorisnickoIme())){
                sadrzaj[j][0] = voznje.getId();
                sadrzaj[j][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm"));
                sadrzaj[j][2] = voznje.getAdresaPolaska();
                sadrzaj[j][3] = voznje.getAdresaDestinacije();
                sadrzaj[j][4] = voznje.getMusterija().getIme().substring(0,1).toUpperCase() + voznje.getMusterija().getIme().substring(1);
                sadrzaj[j][5] = voznje.getBrojKMpredjenih();
                sadrzaj[j][6] = voznje.getTrajanjVoznje();
                sadrzaj[j][7] = voznje.getStatusVoznje();
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

}
