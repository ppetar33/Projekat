package ucitavanje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import osobe.Musterija;

public class Ucitavanje {

    private ArrayList<Musterija> musterije;

    public Ucitavanje() {
        this.musterije = new ArrayList<Musterija>();
    }
    public ArrayList<Musterija> getVozaci() {
        return musterije;
    }

    public void ucitajZaposlene(String imeFajla) {
    	try {
			File korisniciFajl = new File("src/fajlovi/korisnici.txt");
			BufferedReader br = new BufferedReader(new FileReader(korisniciFajl));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] split = line.split("\\,");
				String korisnickoIme = split[0];
				String lozinka = split[1];
				String ime = split[2];
				String prezime = split[3];
				String jmbgString = split[4];
				double jmbg = Double.parseDouble(jmbgString);
				String adresa = split[5];
				String pol = split[6];
				String brojTelefonaString = split[7];
				int brojTelefona = Integer.parseInt(brojTelefonaString);
				String tip = split[11];
				if(tip.equals("MUSTERIJA")) {
					String prazan01 = split[8];
					String prazan02 = split[9];
					String prazan03 = split[10];
				}else if(tip.equals("DISPECAR")) {
					String plataString = split[8];
					int plata = Integer.parseInt(plataString);
					String brojPozivnogTelefonaString = split[9];
					int brojPozivnogTelefona = Integer.parseInt(brojPozivnogTelefonaString);
					String deoGradaNaKojemRadi = split[10];
				}else if(tip.equals("VOZAC")) {
					String plataString = split[8];
					int plata = Integer.parseInt(plataString);
					String brojKarticeString = split[9];
					int brojKartice = Integer.parseInt(brojKarticeString);
					String automobil = split[10];
				}
				
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public Musterija login(String korisnickoIme, String lozinka) {
        for (Musterija musterija : musterije) {
            if (musterija.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)
                    && musterija.getLozinka().equals(lozinka)) {
                return musterija;
            }
        }
        return null;
    }
}
