public class PResept extends HvitResept{
    static int rabatt = 108;
    int PReit = 3;
    
    public PResept(Legemiddel legemiddel, Pasient pasient){
        super(legemiddel, pasient);
    }

    @Override
    public int prisAaBetale(){
        int nyPris = legemiddel.hentPris() - rabatt;
        if (nyPris < 0){
            return 0;
        } else {
            return nyPris;
        }
    }

    @Override
    public boolean bruk(){
        if (PReit <= 0){
            return false;
        } else {
            PReit -= 1;
            return true;
        }
    }

    @Override
    public String toString(){
        return ("Resept ID: " + hentId() + "\nLegemiddel: " + hentLegemiddel() + "\nLege som skrev ut denne resepten: " + hentUstskrivendeLege() + 
        "\nPasient ID: " + hentPasientId() + "\nReit: " + hentReit() + "\nResept farge: " + farge() + "\nPris å betale: " + prisAaBetale());
    }
}
