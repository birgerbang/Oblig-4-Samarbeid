public class MilitaerResept extends HvitResept {
    public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }
/*
    public MilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit){
        super(legemiddel, pasient, reit);
    }
*/
    @Override
    public int prisAaBetale(){
        return 0;
    }

    @Override
    public String toString(){
        return ("Resept ID: " + hentId() + "\nLegemiddel: " + hentLegemiddel() + "\nLege som skrev ut denne resepten: " + hentUtskrivendeLege() + 
        "\nPasient ID: " + hentPasientId() + "\nReit: " + hentReit() + "\nResept farge: " + farge() + "\nPris å betale: " + prisAaBetale() + "\n");
    }
}
