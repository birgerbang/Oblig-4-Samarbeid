public class Spesialist extends Lege implements Godkjenningsfritak {
    String kontrollID;
    
    public Spesialist(String legeNavn, String kontrollID){
        super(legeNavn);
        this.kontrollID = kontrollID;
        utskrevedeResepter = new Lenkeliste<Resept>();
    }

    public String hentKontrollID(){
        return this.kontrollID;
    }

    @Override
    public String toString(){
        return "Legens navn: " + hentNavn() + "\nKontroll ID: " + hentKontrollID();
    }

    @Override
    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        HvitResept nyHvitResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(nyHvitResept);
        return nyHvitResept;
    }

    @Override
    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        MilitaerResept nyMilitaerResept = new MilitaerResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(nyMilitaerResept);
        return nyMilitaerResept;
    }

    @Override
    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
        PResept nyPResept = new PResept(legemiddel, this, pasient);
        utskrevedeResepter.leggTil(nyPResept);
        return nyPResept;
    }

    @Override
    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        BlaaResept nyBlaaResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(nyBlaaResept);
        return nyBlaaResept;
    }
}
