public class Spesialist extends Lege implements Godkjenningsfritak {
    String kontrollID;
    
    public Spesialist(String legeNavn, String kontrollID){
        super(legeNavn);
        this.kontrollID = kontrollID;
    }

    public String hentKontrollID(){
        return this.kontrollID;
    }

    @Override
    public String toString(){
        return "Legens navn: " + hentLegeNavn() + "\nKontroll ID: " + hentKontrollID();
    }
}
