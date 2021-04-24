package dispecer.podaciVozaca;

import automobili.Automobil;
import enumi.Obrisan;
import enumi.StatusAutomobila;
import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrisanjeVozaca extends PrikazVozaca {

    private JButton btnDelete = new JButton();

    public BrisanjeVozaca(Liste ucitavanje,Vozac vozac) {
        super(ucitavanje,vozac);
        setTitle("Brisanje Vozaca");
        initGUI();
        initListeners();
    }

    private void initGUI(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
        btnDelete.setIcon(deleteIcon);
        mainToolBar.add(btnDelete);
        add(mainToolBar, BorderLayout.NORTH);
    }

    private void initListeners(){
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = vozaciTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel)vozaciTabela.getModel();
                    String korisnickoIme = tableModel.getValueAt(red, 0).toString();
                    Vozac vozac = ucitavanje.nadjiVozaca(korisnickoIme);
                    String modelAutomobila = tableModel.getValueAt(red,8).toString();
                    if(modelAutomobila.equals("Vozac nema automobil")){
                        int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete vozaca: " + vozac.getIme().substring(0, 1).toUpperCase() + vozac.getIme().substring(1) + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                        if( izbor == JOptionPane.YES_OPTION) {
                            tableModel.removeRow(red);
                            vozac.setObrisan(Obrisan.FALSE);
                            ucitavanje.dodavanjeKorisnika();
                        }
                    }else {
                        Automobil automobil = ucitavanje.nadjiAutomobilPoModeluAutomobila(modelAutomobila);
                        if (vozac != null) {
                            int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete vozaca: " + vozac.getIme().substring(0, 1).toUpperCase() + vozac.getIme().substring(1) + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                            if (izbor == JOptionPane.YES_OPTION) {
                                vozac.setObrisan(Obrisan.FALSE);
                                tableModel.removeRow(red);
                                if (automobil.getModel().equals(vozac.getAutomobili().getModel())) {
                                    automobil.setStatusAutomobila(StatusAutomobila.SLOBODAN);
                                } else if (automobil.getStatusAutomobila() == StatusAutomobila.SLOBODAN) {
                                    automobil.setStatusAutomobila(StatusAutomobila.SLOBODAN);
                                }
                                ucitavanje.dodavanjeKorisnika();
                                ucitavanje.snimanjeAutomobila("automobil.txt");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog vozaca!", "Greska", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }
}
