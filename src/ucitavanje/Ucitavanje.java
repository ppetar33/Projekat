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
            File prodavciFile = new File("src/fajlovi/" + imeFajla);
            BufferedReader br = new BufferedReader(new FileReader(prodavciFile));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                String korisnickoIme = split[0];
                String lozinka = split[1];
                String ime = split[2];
                String prezime = split[3];
                String jmbg = split[4];
//                int jmbg = Integer.parseInt(split[4]);
                String adresa = split[5];
                String pol = split[6];
                String brojTelefona = split[7];
//                int brojTelefona = Integer.parseInt(brojTelefonaString);
                Musterija musterija = new Musterija(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
                musterije.add(musterija);
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
