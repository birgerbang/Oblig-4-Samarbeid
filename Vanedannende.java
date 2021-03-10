public class Vanedannende extends Legemiddel{
    public int styrke;

    public Vanedannende(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentVanedannendeStyrke(){
        return this.styrke;
    }
    @Override
    public String toString(){
        return "ID: " + hentID() + "\nNavn: " + hentNavn() + "\nPris: " + hentPris() + "\nVirkestoff: " + hentVirkestoff() + "\nStyrke: " + hentVanedannendeStyrke();
    }
    
}
