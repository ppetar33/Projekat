package dispecer;

import java.io.*;


public class PrikazVozaca {
	
	public static void main(String[] args){
		
		ucitajVozaca();
		
	}

	public static void ucitajVozaca() {
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
				String tip = split[10];
				if(tip.equals("MUSTERIJA")) {
					String prazan01 = split[8];
					String prazan02 = split[9];
				}else if(tip.equals("DISPECAR")) {
					String plataString = split[8];
					int plata = Integer.parseInt(plataString);
					String brojPozivnogTelefonaString = split[9];
					int brojPozivnogTelefona = Integer.parseInt(brojPozivnogTelefonaString);
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
}
