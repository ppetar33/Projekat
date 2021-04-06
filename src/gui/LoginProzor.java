package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;
import ucitavanje.Ucitavanje;

public class LoginProzor extends JFrame{

	private JLabel lblPoruka;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorisnickoIme;
	private JLabel lblSifra;
	private JPasswordField pfSifra;
	private JButton btnOK;
	private JButton btnCancel;
    private Ucitavanje ucitavanje;

    //dadadadad
    public LoginProzor(Ucitavanje ucitavanje) {
        this.ucitavanje = ucitavanje;
		setTitle("Prijava");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		initActions();
        pack();
    }
    private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		setLayout(layout);

		this.lblPoruka = new JLabel("Dobrodošli. Molimo da se prijavite.");
		this.lblKorisnickoIme = new JLabel("Korisničko ime");
		this.txtKorisnickoIme = new JTextField(20);
		this.lblSifra = new JLabel("Šifra");
		this.pfSifra = new JPasswordField(20);
		this.btnOK = new JButton("OK");
		this.btnCancel = new JButton("Cancel");

		this.getRootPane().setDefaultButton(btnOK);
		
		add(lblPoruka, "span 2");
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(pfSifra);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		
    }
    private void initActions() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String korisnickoIme = txtKorisnickoIme.getText().trim();
                String sifra = new String(pfSifra.getPassword()).trim();

                Musterija prijavljen = ucitavanje.login(korisnickoIme, sifra);
                if (prijavljen == null) {
                    JOptionPane.showMessageDialog(null, "Neispravni login podaci", "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                	JOptionPane.showMessageDialog(null, "Uspesno ste se prijavili", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Uspesno ste izasli iz aplikacije", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
				LoginProzor.this.setVisible(false);
				LoginProzor.this.dispose();
			}
		});
    }
}
