abstract public class Resept {
    Legemiddel legemiddel;
    Lege utskrivendeLege;
    Pasient pasient;
    int reit;
    int id = 0;
    static int teller;

    protected Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        teller++;
        id = teller;
    }

    protected Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        teller++;
        id = teller;
    }

    protected Resept(Legemiddel legemiddel, Pasient pasient, int reit){
        this.legemiddel = legemiddel;
        this.pasient = pasient;
        this.reit = reit;
        teller++;
        id = teller;
    }

    protected Resept(Legemiddel legemiddel, Pasient pasient){
        this.legemiddel = legemiddel;
        this.pasient = pasient;
        teller++;
        id = teller;
    }

    public int hentId(){
        return id;
    }

    public String hentLegemiddel(){
        return legemiddel.hentNavn();
    }

    public String hentUstskrivendeLege(){
        return utskrivendeLege.hentNavn();
    }

    public int hentPasientId(){
        return this.pasient.hentPasientId();
    }

    public int hentReit(){
        return this.reit;
    }

    public boolean bruk(){
        if (this.reit <= 0){
            return false;
        } else {
            this.reit -= 1;
            return true;
        }
    }

    abstract public String farge();

    abstract public int prisAaBetale();

    public String toString(){
        return ("Resept ID: " + hentId() + "\nLegemiddel: " + hentLegemiddel() + "\nLege som skrev ut denne resepten: " + hentUstskrivendeLege() + 
        "\nPasient ID: " + hentPasientId() + "\nReit: " + hentReit() + "\nResept farge: " + farge() + "\nPris å betale: " + prisAaBetale());
    }
}
