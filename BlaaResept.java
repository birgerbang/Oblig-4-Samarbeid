public class BlaaResept extends Resept{
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public BlaaResept(Legemiddel legemiddel, Pasient pasient, int reit){
        super(legemiddel, pasient, reit);
    }

    @Override
    public String farge(){
        return "Blå resept";
    }

    @Override
    public int prisAaBetale(){
        double desimalPris = legemiddel.hentPris() * 0.25;
        return (int) Math.round(desimalPris);
        
    }

    @Override
    public String toString(){
        return ("Resept ID: " + hentId() + "\nLegemiddel: " + hentLegemiddel() + "\nLege som skrev ut denne resepten: " + hentUstskrivendeLege() + 
        "\nPasient ID: " + hentPasientId() + "\nReit: " + hentReit() + "\nResept farge: " + farge() + "\nPris å betale: " + prisAaBetale());
    }
}
