package dispecer;


import podaci.Liste;

public class BrisanjeAutomobila extends PrikazAutomobila{
//brisanje je moguće samo za automobile koji nisu dodeljeni ni jednom vozaču

    
    public BrisanjeAutomobila(Liste ucitavanje) {
        super(ucitavanje);
        setTitle("Brisanje Automobila");
        initGUI();
        initListeners();
    }

    private void initListeners() {
    }

    //private void initGUI(){
        
    //}
    
}
