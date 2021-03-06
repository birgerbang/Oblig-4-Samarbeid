public class PResept extends HvitResept{
    static int rabatt = 108;
    int Preit = 3;
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
        super(legemiddel, utskrivendeLege, pasient);
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
        if (Preit <= 0){
            return false;
        } else {
            Preit -= 1;
            return true;
        }
    }

    @Override
    public String toString(){
        return ("Resept ID: " + hentId() + "\nLegemiddel: " + hentLegemiddel() + "\nLege som skrev ut denne resepten: " + hentUtskrivendeLege() + 
        "\nPasient ID: " + hentPasientId() + "\nReit: " + hentReit() + "\nResept farge: " + farge() + "\nPris å betale: " + prisAaBetale() + "\n");
    }
}

