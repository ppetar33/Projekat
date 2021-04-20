package dispecer;

import osobe.Vozac;
import podaci.Liste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PretragaVozaca extends JFrame{

    private Container c;
    private JLabel pretragaPoImenu;
    private JTextField tpretragaPoImenu;
    private JButton btnOK;

    private Liste ucitavanje;
    private Vozac vozac;

    public PretragaVozaca(Liste ucitavanje, Vozac vozac){
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
        setTitle("Pretraga Vozaca Po Imenu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        c = getContentPane();
        c.setLayout(null);

        pretragaPoImenu = new JLabel("Pretraga po imenu");
        pretragaPoImenu.setFont(new Font("Arial", Font.PLAIN, 18));
        pretragaPoImenu.setSize(180, 20);
        pretragaPoImenu.setLocation(50, 20);
        c.add(pretragaPoImenu);

        tpretragaPoImenu = new JTextField();
        tpretragaPoImenu.setFont(new Font("Arial", Font.PLAIN, 15));
        tpretragaPoImenu.setSize(190, 35);
        tpretragaPoImenu.setLocation(50, 55);
        c.add(tpretragaPoImenu);

        btnOK = new JButton("Potvrdi");
        btnOK.setFont(new Font("Arial", Font.PLAIN, 19));
        btnOK.setSize(180, 40);
        btnOK.setLocation(50, 90);
        btnOK.setBackground(Color.BLUE);
        c.add(btnOK);

        initListeners();
    }

    private void initListeners(){
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"OK","Uspesno",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

}
