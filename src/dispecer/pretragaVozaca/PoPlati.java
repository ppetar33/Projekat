package dispecer.pretragaVozaca;

import net.miginfocom.swing.MigLayout;
import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoPlati extends JFrame {

    public JLabel pretragaPoPlati = new JLabel("Unesi platu");
    private JTextField tpretragaPoPlati = new JTextField(20);
    private JButton btnOK = new JButton("Pretrazi");
    private JButton cancel = new JButton("Odustani");

    private Liste ucitavanje;
    private Vozac vozac;

    public PoPlati(Liste ucitavanje, Vozac vozac){
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
        setTitle("Pretraga Vozaca Po Plati");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initGui();
        initListeners();
        pack();
        setLocationRelativeTo(null);
    }
    private void initGui(){
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(pretragaPoPlati);
        add(tpretragaPoPlati);
        add(new JLabel());
        add(btnOK, "split 2");
        add(cancel);
    }

    private void initListeners(){
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija() == true){

                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od pretrage","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                PoPlati.this.setVisible(false);
                PoPlati.this.dispose();
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String poruka = "Napravili ste gresku!\n";
        if(tpretragaPoPlati.getText().equals("")){
            poruka += "Polje ne sme biti prazno!\n";
            ok = false;
        }
        if( ok == false){
            JOptionPane.showMessageDialog(null,poruka,"Greska",JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}