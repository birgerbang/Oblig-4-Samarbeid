import java.io.File;
import java.io.FileNotFoundException;
import java.util.IllegalFormatException;
import java.util.Scanner;

public class Legesystem {
    Lenkeliste<Pasient> pasientliste = new Lenkeliste<Pasient>();
    Lenkeliste<Lege> legeliste = new Lenkeliste<Lege>();
    Lenkeliste<Legemiddel> legemiddelliste = new Lenkeliste<Legemiddel>();
    Lenkeliste<Resept> reseptliste = new Lenkeliste<Resept>();

    public static void main(String [] args) throws NumberFormatException, UlovligUtskrift{
        Legesystem hovedsystem = new Legesystem();
        hovedsystem.lesFil("tekstTest.txt");
        Scanner input = new Scanner(System.in);
        String n = null;
        System.out.println(" Velg 1 for aa skrive ut en fullstendig oversikt over pasienter, leger, legemidler og resepter. \n Velg 2 for aa opprette og legge til nye elementer i systemet \n velg 3 for aa bruke en gitt resept fra listen til en pasient \n velg 4 for aa skrive ut forskjellige former for statistikk \n velg 5 for aa skrive alle data til fil. \n press enter for aa avslutte.");
        if (input.hasNextLine()){
            while(n != ""){
                n = input.nextLine();
                if(n.startsWith("1")){
                    System.out.println("Genialt egon");
                    hovedsystem.skrivUtAlt();
                }
            }
            input.close();
        }
    }

    public void skrivUtAlt(){
        for (Pasient e : pasientliste){
            System.out.println(e.toString());
        }
        for (Lege e : legeliste){
            System.out.println(e.toString());
        }
        for (Legemiddel e : legemiddelliste){
            System.out.println(e.toString());
        }
        for (Resept e : reseptliste){
            System.out.println(e.toString());
        }
    }
    /**
     * En metode for å lese inn objekter fra fil. Bruk
        skrivResept-metodene i legeobjektet for å opprette Resept objekter. Dersom et objekt er
        ugyldig eller ikke følger filformatet i vedlegg 2, skal det ikke legges inn i systemet.
        PS: Husk å behandle unntak som kan kastes.
     * @throws UlovligUtskrift
     * @throws NumberFormatException
     */
    public void lesFil(String filnavn) throws NumberFormatException, UlovligUtskrift{
        try{
            File fil = new File(filnavn);
            Scanner leser = new Scanner(fil);
            while (leser.hasNextLine()) {
                String data = leser.nextLine();
                try{
                    if(data.startsWith("# Pasienter")){
                        data = leser.nextLine();
                        while(!data.startsWith("#")){
                            String[] pasient = data.split(",");
                            Pasient nypasient = new Pasient(pasient[0], pasient[1]);
                            pasientliste.leggTil(nypasient);
                            data = leser.nextLine();
                        }
                    }
                    else if (data.startsWith("# Legemidler")){
                        data = leser.nextLine();
                        while(!data.startsWith("#")){
                            String[] legemiddel = data.split(",");
                            if(legemiddel[1]=="narkotisk"){
                                Narkotisk narko = new Narkotisk(legemiddel[0], Integer.parseInt(legemiddel[2]), Double.parseDouble(legemiddel[3]),Integer.parseInt(legemiddel[4]));
                                legemiddelliste.leggTil(narko);
                            }
                            else if(legemiddel[1]=="vanedannende"){
                                Vanedannende vane = new Vanedannende(legemiddel[0], Integer.parseInt(legemiddel[2]), Double.parseDouble(legemiddel[3]),Integer.parseInt(legemiddel[4]));
                                legemiddelliste.leggTil(vane);
                            }
                            else if(legemiddel[1]=="vanlig"){
                                Vanlig vanlig = new Vanlig(legemiddel[0], Integer.parseInt(legemiddel[2]), Double.parseDouble(legemiddel[3]));
                                legemiddelliste.leggTil(vanlig);
                            }
                            data = leser.nextLine();
                        }
                    }
                    else if (data.startsWith("# Leger")){
                        data = leser.nextLine();
                        while(!data.startsWith("#")){
                            String[] leger = data.split(",");
                            if(leger[1]!="0"){
                                Spesialist spes = new Spesialist(leger[0], leger[1]);
                                legeliste.leggTil(spes);
                            }
                            else{
                                Lege lege = new Lege(leger[0]);
                                legeliste.leggTil(lege);
                                data = leser.nextLine();
                            }
                        data = leser.nextLine();
                        }
                    }
                    else if (data.startsWith("# Resepter")){
                        data = leser.nextLine();
                        while(!data.startsWith("#")){
                            String[] resepter = data.split(",");
                            if(resepter[3]=="hvit"){
                                HvitResept hvit = getLege(resepter[1]).skrivHvitResept(getLegemiddel(resepter[0]), getPasient(resepter[2]), Integer.parseInt(resepter[3]));
                                reseptliste.leggTil(hvit);
                                data = leser.nextLine();
                            }
                            else if(resepter[3]=="blaa"){
                                BlaaResept blaa = getLege(resepter[1]).skrivBlaaResept(getLegemiddel(resepter[0]), getPasient(resepter[2]), Integer.parseInt(resepter[3]));
                                reseptliste.leggTil(blaa);
                                data = leser.nextLine();
                            }
                            else if(resepter[3]=="millitaer"){
                                MilitaerResept millitaer = getLege(resepter[1]).skrivMilitaerResept(getLegemiddel(resepter[0]), getPasient(resepter[2]), Integer.parseInt(resepter[3]));
                                reseptliste.leggTil(millitaer);
                                data = leser.nextLine();
                            }
                            else if(resepter[3]=="p"){
                                PResept presept = getLege(resepter[1]).skrivPResept(getLegemiddel(resepter[0]), getPasient(resepter[2]));
                                reseptliste.leggTil(presept);
                                data = leser.nextLine();
                            }   
                        }
                    }      
                }catch (IllegalFormatException f){
                System.out.println("Element fra fil ikke lagt til, feil formatering");
                }
            }
            leser.close();
        } catch (FileNotFoundException e) {
        System.out.println("Error: kan ikke lese " + filnavn + "");
        System.exit(1);
        }
    }

    public Lege getLege(String legenavn){
        for (Lege e : legeliste){
            if (e.legeNavn == legenavn){
                return e;
            }
        }
        return null;
    }

    public Legemiddel getLegemiddel(String legemiddelnavn){
            for (Legemiddel e : legemiddelliste){
            if (e.navn == legemiddelnavn){
                return e;
            }
        }
        return null;
    }   
    public Pasient getPasient(String pasientnummer){
        for (Pasient e : pasientliste){
            if (e.id == Integer.parseInt(pasientnummer)){
                return e;
            }
        }
        return null;
    }
}
