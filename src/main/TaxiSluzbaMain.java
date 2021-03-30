package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.LoginProzor;

public class TaxiSluzbaMain {

	public static void main(String[] args){
		
		JFrame frame = new JFrame("Taxi sluzba");
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		LoginProzor.placeComponents(panel);

		frame.setVisible(true);

	}

}
