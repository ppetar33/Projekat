package main;

import gui.LoginProzor;
import ucitavanje.Ucitavanje;

public class TaxiSluzbaMain {
	
    private static final String KORISNICI_FAJL = "korisnici.txt";

	public static void main(String[] args){


//	    proba123
        Ucitavanje ucitavanje = new Ucitavanje();
        ucitavanje.ucitajZaposlene(KORISNICI_FAJL);

        LoginProzor lp = new LoginProzor(ucitavanje);
        lp.setVisible(true);

	}

}
