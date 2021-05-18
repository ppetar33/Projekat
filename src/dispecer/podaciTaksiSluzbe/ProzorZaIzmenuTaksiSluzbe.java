package dispecer.podaciTaksiSluzbe;

import liste.Liste;
import net.miginfocom.swing.MigLayout;
import taksiSluzba.TaksiSluzba;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProzorZaIzmenuTaksiSluzbe extends JFrame {

    private JLabel id = new JLabel("ID");
    private JTextField tid = new JTextField(20);
    private JLabel pib = new JLabel("PIB");
    private JTextField tpib = new JTextField(20);
    private JLabel naziv = new JLabel("Naziv");
    private JTextField tnaziv = new JTextField(20);
    private JLabel adresa = new JLabel("Adresa");
    private JTextField tadresa = new JTextField(20);
    private JLabel cenaStartaVoznje = new JLabel("Cena starta voznje");
    private JTextField tcenaStartaVoznje = new JTextField(20);
    private JLabel cenaVoznjePoKilometru = new JLabel("Cena voznje po kilometru");
    private JTextField tcenaVoznjePoKilometru = new JTextField(20);
    private JButton btnOK = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private Liste ucitavanje;
    private TaksiSluzba taksiSluzba;


    public ProzorZaIzmenuTaksiSluzbe(Liste ucitavanje, TaksiSluzba taksiSluzba) {
        this.ucitavanje = ucitavanje;
        this.taksiSluzba = taksiSluzba;
        setTitle("Izmena podatata taksi sluzbe");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGUI();
        initListeners();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(id);
        add(tid);
        add(pib);
        add(tpib);
        add(naziv);
        add(tnaziv);
        add(adresa);
        add(tadresa);
        add(cenaStartaVoznje);
        add(tcenaStartaVoznje);
        add(cenaVoznjePoKilometru);
        add(tcenaVoznjePoKilometru);
        add(new JLabel());
        add(btnOK, "split 2");
        add(btnCancel);

        if (this.taksiSluzba != null){
            popunjavanjeTextField();
        }
    }

    private void initListeners() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveraUnetihPodataka() == true){
                    int id = taksiSluzba.getId();
                    String pib = tpib.getText().trim();
                    String naziv = tnaziv.getText().trim();
                    String adresa = tadresa.getText().trim();
                    double cenaStartaVoznje = Double.parseDouble(tcenaStartaVoznje.getText().trim());
                    double cenaVoznjePoKilometru = Double.parseDouble(tcenaVoznjePoKilometru.getText().trim());
                    if (taksiSluzba == null){
                        taksiSluzba = new TaksiSluzba(id,pib,naziv,adresa,cenaStartaVoznje, cenaVoznjePoKilometru);
                    }else {
                        taksiSluzba.setId(id);
                        taksiSluzba.setPib(pib);
                        taksiSluzba.setNaziv(naziv);
                        taksiSluzba.setAdresa(adresa);
                        taksiSluzba.setCenaStartaVoznje(cenaStartaVoznje);
                        taksiSluzba.setCenaPoKilometru(cenaVoznjePoKilometru);
                    }
                    ucitavanje.snimiTaksiSluzbe("taksiSluzba.txt");
                    JOptionPane.showMessageDialog(null, "Taksi sluzba je uspesno izmenjena!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    ProzorZaIzmenuTaksiSluzbe.this.setVisible(false);
                    ProzorZaIzmenuTaksiSluzbe.this.dispose();
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od izmene","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                ProzorZaIzmenuTaksiSluzbe.this.setVisible(false);
                ProzorZaIzmenuTaksiSluzbe.this.dispose();
            }
        });
    }

    private void popunjavanjeTextField() {
        tid.setText(String.valueOf(taksiSluzba.getId()));
        tpib.setText(taksiSluzba.getPib());
        tnaziv.setText(taksiSluzba.getNaziv());
        tadresa.setText(taksiSluzba.getAdresa());
        tcenaStartaVoznje.setText(String.valueOf(taksiSluzba.getCenaStartaVoznje()));
        tcenaVoznjePoKilometru.setText(String.valueOf(taksiSluzba.getCenaPoKilometru()));
    }

    private boolean proveraUnetihPodataka(){
        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n";

        try {
            Integer.parseInt(tid.getText().trim());
        } catch (NumberFormatException e) {
            obavestenjeZaGresku += "Morate uneti ID kao broj!\n";
            ok = false;
        }

        if (tpib.getText().trim().equals("")){
            obavestenjeZaGresku += "Morate uneti PIB taksi sluzbe! \n";
            ok = false;
        }
        if (tnaziv.getText().trim().equals("")){
            obavestenjeZaGresku += "Morate uneti naziv taksi sluzbe! \n";
            ok = false;
        }
        if (tadresa.getText().trim().equals("")){
            obavestenjeZaGresku += "Morate uneti adresu taksi sluzbe! \n";
            ok = false;
        }
        try {
            Double.parseDouble(tcenaStartaVoznje.getText().trim());
        } catch (NumberFormatException e) {
            obavestenjeZaGresku += "Cena starta voznje mora biti broj!\n";
            ok = false;
        }
        try {
            Double.parseDouble(tcenaVoznjePoKilometru.getText().trim());
        } catch (NumberFormatException e) {
            obavestenjeZaGresku += "Cena voznje po kilometru mora biti broj!\n";
            ok = false;
        }
        if(ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Morate popuniti polja!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }


}