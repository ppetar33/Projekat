package vozac.Statistika;

import liste.Liste;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DnevnaMesecnaGodisnjaStatistika extends JFrame {

    private JLabel datum = new JLabel("Unesi datum: ");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancel = new JButton("Cancel");

    private Liste ucitavanje;

    public DnevnaMesecnaGodisnjaStatistika(Liste ucitavanje, String[] days){
        this.ucitavanje = ucitavanje;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initGUI();
        initActions(days);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGUI(){
        MigLayout layout = new MigLayout("wrap 2");

        setLayout(layout);
        add(datum);
        add(datumUnos);
        add(new JLabel());
        add(btnOk,"split 2");
        this.getRootPane().setDefaultButton(btnOk);
        add(btnCancel);
    }

    private void initActions(String[] days){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija() == true){
                    String unosDatuma = datumUnos.getText().trim();

                }
            }
        });
    }

    public boolean validacija() {
        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n";
        if(datumUnos.getText().equals("")){
            obavestenjeZaGresku += "\nMorate uneti datum! \n";
            ok = false;
        }
        if (ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Neispravni podaci!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
