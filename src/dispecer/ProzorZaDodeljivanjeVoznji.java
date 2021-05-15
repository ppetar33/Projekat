package dispecer;

import enumi.StatusVoznje;
import liste.Liste;
import musterija.NarucivanjeVoznjePrekoTelefona;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProzorZaDodeljivanjeVoznji extends JFrame{
    /*
        Dodeljivanje vožnji kreiranih putem telefona vozačima (pri ovom koraku dispečer postavlja vozača I
        menja status vožnje sa KREIRANA-NA ČEKANJU na DODELJENA);
    */

    // sva polja osim gde se menja status voznje su readOnly ------ setEditable(false)

    // popuniti polja sa podacima kojima je status voznje kreirana

    private JLabel adresaPolaska = new JLabel("Adresa polaska");
    private JTextField tadresaPolaska = new JTextField(20);
    private JLabel adresaDolaska = new JLabel("Adresa destinacije");
    private JTextField tadresaDolaska = new JTextField(20);
    private JLabel musterija = new JLabel("Musterija");
    private JTextField tmusterija = new JTextField(20);
    private JLabel vozac = new JLabel("Vozac");
    private JTextField tvozac = new JTextField(20);
    private JLabel statusVoznje = new JLabel("Status voznje");
    private JComboBox<StatusVoznje> statusVoznjeJComboBox = new JComboBox<StatusVoznje>(StatusVoznje.values());
    private JButton dodeli = new JButton("Dodeli");
    private JButton btnCancel = new JButton("Odustani");

    private Liste ucitavanje;
    private NarucivanjeVoznjePrekoTelefona voznja;

    public ProzorZaDodeljivanjeVoznji(Liste ucitavanje, NarucivanjeVoznjePrekoTelefona voznja) {
        this.ucitavanje = ucitavanje;
        this.voznja = voznja;
        setTitle("Dodeli voznju vozacu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGUI();
        initListeners();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGUI(){
        MigLayout layout = new MigLayout("wrap 2");

        setLayout(layout);
        add(adresaPolaska);
        add(tadresaPolaska);
        add(adresaDolaska);
        add(tadresaDolaska);
        add(musterija);
        add(tmusterija);
        add(vozac);
        add(tvozac);
        add(statusVoznje);
        add(statusVoznjeJComboBox);
        add(new JLabel());
        add(dodeli, "split 2");
        add(btnCancel);

        tadresaPolaska.setEditable(false);
        tadresaDolaska.setEditable(false);
        tmusterija.setEditable(false);
        tvozac.setEditable(false);

        if(this.voznja != null){
            popunjavanjePolja();
        }
    }

    private void initListeners(){
        dodeli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {





//                JOptionPane.showMessageDialog(null,"Voznja uspesno dodeljena!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od dodeljivanja voznje","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                ProzorZaDodeljivanjeVoznji.this.setVisible(false);
                ProzorZaDodeljivanjeVoznji.this.dispose();
            }
        });
    }

    private void popunjavanjePolja(){
        tadresaPolaska.setText(voznja.getAdresaPolaska());
        tadresaDolaska.setText(voznja.getAdresaDestinacije());
        tmusterija.setText(voznja.getMusterija().getIme());
        tvozac.setText(voznja.getVozac().getIme());
    }
}
