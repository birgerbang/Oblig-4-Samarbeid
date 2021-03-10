abstract public class Legemiddel { // abstrakt klasse for at man ikke skal kunne lage et objekt som bare er et legemiddel
    String navn;
    int pris;
    double virkestoff;
    static int teller;
    int id = 0;

    protected Legemiddel(String navn, int pris, double virkestoff){
        teller ++;
        this.id = teller;
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
    }

    protected int hentID(){
        return this.id;
    }

    public String hentNavn(){
        return this.navn;
    }

    public int hentPris(){
        return this.pris;
    }

    public double hentVirkestoff(){
        return this.virkestoff;
    }

    public void settNyPris(int nyPris){
        this.pris = nyPris;
    }

    public String toString(){
        return "ID: " + hentID() + "\nNavn: " + hentNavn() + "\nPris: " + hentPris() + "\nVirkestoff: " + hentVirkestoff();
    }
}
