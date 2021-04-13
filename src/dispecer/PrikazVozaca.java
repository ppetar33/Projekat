package dispecer;

import osobe.Musterija;
import osobe.Pol;
import osobe.Vozac;

import java.io.*;
import java.util.ArrayList;

/*
	napraviti prozor za prikaz (izgled kao na slici 4 u specifikaciji projekta)
*/

public class PrikazVozaca {

	private ArrayList<Musterija> musterije;

	public PrikazVozaca(){
		this.musterije = new ArrayList<Musterija>();
	}

	public static void ucitajKorisnike() {
		try {
			File korisniciFajl = new File("../src/fajlovi/korisnici.txt");
			BufferedReader br = new BufferedReader(new FileReader(korisniciFajl));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] split = line.split(",");
				String korisnickoIme = split[0];
				String lozinka = split[1];
				String ime = split[2];
				String prezime = split[3];
				String jmbg = split[4];
				String adresa = split[5];
				Pol pol = Pol.valueOf(split[6].toUpperCase());
				String brojTelefona= split[7];
				String tip = split[11];
				if(tip.equals("VOZAC")) {
					String plataString = split[8];
					double plata = Double.parseDouble(plataString);
					String brojKarticeString = split[9];
					int brojKartice = Integer.parseInt(brojKarticeString);
					String automobil = split[10];
					Vozac vozac = new Vozac(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plata, brojKartice, automobil);
					System.out.println(vozac);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
