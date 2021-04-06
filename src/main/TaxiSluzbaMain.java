package main;

import gui.LoginProzor;
import ucitavanje.Ucitavanje;

public class TaxiSluzbaMain {
	
    private static String KORISNICI_FAJL = "korisnici.txt";
    // da
	public static void main(String[] args){
		
        Ucitavanje ucitavanje = new Ucitavanje();
        ucitavanje.ucitajZaposlene(KORISNICI_FAJL);

        LoginProzor lp = new LoginProzor(ucitavanje);
        lp.setVisible(true);

	}

}
