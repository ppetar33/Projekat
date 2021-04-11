package dispecer;

import osobe.Dispecar;
import ucitavanje.Ucitavanje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MogucnostiDispecera extends JFrame {

	private JMenuBar dispecerMenu = new JMenuBar();
	private JMenu funkcionalnosti = new JMenu("Manipulacija vozacem");
	private JMenuItem dodavanjeVozaca = new JMenuItem("Dodavanje vozaca");
	private JMenuItem prikazVozaca = new JMenuItem("Prikaz vozaca");
	private JMenuItem izmenaVozaca = new JMenuItem("Izmena vozaca");
	private JMenuItem brisanjeVozaca = new JMenuItem("Brisanje vozaca");
	private Ucitavanje ucitavanje;
	private Dispecar prijavljeniDispecar;

	public MogucnostiDispecera(Ucitavanje ucitavanje, Dispecar prijavljeniDispecar){
		this.ucitavanje = ucitavanje;
		this.prijavljeniDispecar = prijavljeniDispecar;
		setTitle("Dispecer, ime: " + prijavljeniDispecar.getIme().substring(0, 1).toUpperCase() + prijavljeniDispecar.getIme().substring(1));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 500);
		initGUI();
		initListeners();
	}

	private void initGUI(){
		setJMenuBar(dispecerMenu);
		dispecerMenu.add(funkcionalnosti);
		funkcionalnosti.add(dodavanjeVozaca);
		funkcionalnosti.add(prikazVozaca);
		funkcionalnosti.add(izmenaVozaca);
		funkcionalnosti.add(brisanjeVozaca);
	}

	private void initListeners(){
		dodavanjeVozaca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyFrame f = new MyFrame();
				f.setVisible(true);
			}
		});
	}

}
