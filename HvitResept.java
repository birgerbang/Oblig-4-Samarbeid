public class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
        super(legemiddel, utskrivendeLege, pasient);
    }
/*
    public HvitResept(Legemiddel legemiddel, Pasient pasient, int reit){
        super(legemiddel, pasient, reit);
    }
*/
    @Override
    public String farge(){
        return "Hvit resept";
    }
    
    @Override
    public int prisAaBetale(){
        return legemiddel.hentPris();
    }

    @Override
    public String toString(){
        return ("Resept ID: " + hentId() + "\nLegemiddel: " + hentLegemiddel() + "\nLege som skrev ut denne resepten: " + hentUtskrivendeLege() + 
        "\nPasient ID: " + hentPasientId() + "\nReit: " + hentReit() + "\nResept farge: " + farge() + "\nPris å betale: " + prisAaBetale() + "\n");
    }
}
