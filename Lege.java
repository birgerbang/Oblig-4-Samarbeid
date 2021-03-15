public class Lege implements Comparable<Lege> {
    String legeNavn;
    Lenkeliste<Resept> utskrevedeResepter;
    int antallNarkotiskeResepter;

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

    public Lenkeliste<Resept> hentUtskrevedeResepter(){
        return utskrevedeResepter;
    }

    public boolean skrevetUtNarkotisk(){
        for (Resept e : utskrevedeResepter){
            if (e.legemiddel instanceof Narkotisk){
                antallNarkotiskeResepter++;
            }
        }
        if (antallNarkotiskeResepter > 0){
            return true;
        } else {
            return false;
        }
    }

    public int hentAntallNarkotiskeResepter(){
        return antallNarkotiskeResepter;
    }
    
    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }
        HvitResept nyHvitResept = new HvitResept(legemiddel, this, pasient, reit);
        pasient.leggTilResept(nyHvitResept);
        utskrevedeResepter.leggTil(nyHvitResept);
        return nyHvitResept;
    }

    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }
        MilitaerResept nyMilitaerResept = new MilitaerResept(legemiddel, this, pasient, reit);
        pasient.leggTilResept(nyMilitaerResept);
        utskrevedeResepter.leggTil(nyMilitaerResept);
        return nyMilitaerResept;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }
        PResept nyPResept = new PResept(legemiddel, this, pasient);
        pasient.leggTilResept(nyPResept);
        utskrevedeResepter.leggTil(nyPResept);
        return nyPResept;
    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }
        BlaaResept nyBlaaResept = new BlaaResept(legemiddel, this, pasient, reit);
        pasient.leggTilResept(nyBlaaResept);
        utskrevedeResepter.leggTil(nyBlaaResept);
        return nyBlaaResept;
    }
}