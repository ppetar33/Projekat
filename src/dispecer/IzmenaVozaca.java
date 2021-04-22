package dispecer;

import osobe.Vozac;
import podaci.Liste;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzmenaVozaca extends PrikazVozaca{

    private JButton btnEdit = new JButton();

    public IzmenaVozaca(Liste ucitavanje,Vozac vozac) {
        super(ucitavanje,vozac);
        setTitle("Izmena vozaca" + this.vozac.getIme().substring(0,1) + this.vozac.getIme().substring(1));
        initGui();
        initListeners();
    }

    private void initGui(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
        btnEdit.setIcon(deleteIcon);
        mainToolBar.add(btnEdit);
        add(mainToolBar, BorderLayout.NORTH);
    }

    private void initListeners(){
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = vozaciTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel)vozaciTabela.getModel();
                    String korisnickoIme = tableModel.getValueAt(red, 0).toString();
                    Vozac vozac = ucitavanje.nadjiVozaca(korisnickoIme);
                    if( vozac != null){
                        int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da izmenite vozaca: " + vozac.getIme().substring(0,1).toUpperCase() + vozac.getIme().substring(1) + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                        if ( izbor == JOptionPane.YES_OPTION ){
                            ProzorZaIzmenuVozaca prozorZaIzmenuVozaca = new ProzorZaIzmenuVozaca(ucitavanje,vozac);
                            prozorZaIzmenuVozaca.setVisible(true);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog vozaca!", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
