package dispecer.podaciAutomobila;

import automobili.Automobil;
import liste.Liste;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzmenaAutomobila extends PrikazAutomobila {

    private JButton btnEdit = new JButton();

    public IzmenaAutomobila(Liste ucitavanje) {
        super(ucitavanje);
        setTitle("Izmena automobila");
        initGui();
        initListeners();
    }

    private void initGui(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
        btnEdit.setIcon(deleteIcon);
        mainToolBar.add(btnEdit);
        add(mainToolBar, BorderLayout.NORTH);
    }

    private void initListeners() {
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = automobiliTabela.getSelectedRow();
                if (red == - 1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel)automobiliTabela.getModel();
                    int id = Integer.parseInt((String) tableModel.getValueAt(red, 0));
                    Automobil automobil = ucitavanje.nadjiAutomobil(id);
                    if (automobil != null){
                        int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete automobil:" + automobil.getId() + automobil.getProizvodjac().substring(1) + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION );
                        if (izbor == JOptionPane.YES_OPTION){
                            ProzorZaIzmenuAutomobila prozorZaIzmenuAutomobila = new ProzorZaIzmenuAutomobila(ucitavanje, automobil);
                            prozorZaIzmenuAutomobila.setVisible(true);
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran automobil!", "Greska", JOptionPane.ERROR_MESSAGE);

                    }
                }

            }
        });
    }

}
