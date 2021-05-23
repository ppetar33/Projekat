package vozac.aukcija;

import dispecer.dodeljivanjeVoznje.DodeljivanjeVoznje;
import dispecer.dodeljivanjeVoznje.ProzorZaDodeljivanjeVoznji;
import enumi.StatusVoznje;
import liste.Liste;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// potrebno je prikazati sve voznje koje su kreirane
// vozac bira neku od kreirane voznje
// izabere voznju
// iskoci prozor da unese koliko mu treba vremena do neke adrese

public class AukcijeVoznje extends DodeljivanjeVoznje {

    private JButton btnUcestvujUaukciji = new JButton("Ucestvuj u aukciji");
    private JButton btnOsvezi = new JButton("Osvezi tabelu");

    public AukcijeVoznje(Liste ucitavanje, NarucivanjeVoznjePrekoTelefona voznja) {
        super(ucitavanje, voznja);
        setTitle("Aukcija");
        initGUI();
        initListeners();
    }

    private void initGUI(){
        add(btnUcestvujUaukciji, BorderLayout.NORTH);
        add(btnOsvezi, BorderLayout.SOUTH);
    }

    private void initListeners(){
        btnUcestvujUaukciji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    DefaultTableModel tableModel = (DefaultTableModel) voznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);
                    NarucivanjeVoznjePrekoTelefona trazenaVoznja = ucitavanje.nadjiVoznjuNarucenuPrekoTelefonaPoId(id);
                    ProzorZaUnosPodataka prozorZaUnosPodataka = new ProzorZaUnosPodataka(ucitavanje, trazenaVoznja);
                    prozorZaUnosPodataka.setVisible(true);
                }
            }
        });
        btnOsvezi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AukcijeVoznje.this.setVisible(false);
                AukcijeVoznje.this.dispose();
                AukcijeVoznje aukcijeVoznje = new AukcijeVoznje(ucitavanje,voznja);
                aukcijeVoznje.setVisible(true);
            }
        });
    }

}
