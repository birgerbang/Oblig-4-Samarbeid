public class Lege implements Comparable<Lege> {
    String legeNavn;
    Lenkeliste<Resept> utskrevedeResepter;

    public Lege(String legeNavn){
        this.legeNavn = legeNavn;
        utskrevedeResepter = new Lenkeliste<Resept>();
    }

    public String hentNavn(){
        return this.legeNavn;
    }

    public String toString(){
        return "Legens navn: " + hentNavn();
    }

    @Override
    public int compareTo(Lege x){
        return hentNavn().compareTo(x.hentNavn());
    }

    public Lenkeliste hentUtskrevedeResepter(){
        return utskrevedeResepter;
    }
    
    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk){
            throw UlovligUtskrift(this.Lege, legemiddel);
        }
        return new HvitResept(legemiddel, pasient, reit);
    }

    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        return new MilitaerResept(legemiddel, pasient, reit);
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
        return new PResept(legemiddel, pasient);
    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        return new BlaaResept(legemiddel, pasient, reit);
    }
}