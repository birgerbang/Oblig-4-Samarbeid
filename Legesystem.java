import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParsePosition;
import java.util.IllegalFormatException;
import java.util.Scanner;

public class Legesystem {
    Lenkeliste<Pasient> pasientliste = new Lenkeliste<Pasient>();
    SortertLenkeliste<Lege> legeliste = new SortertLenkeliste<Lege>();
    Lenkeliste<Legemiddel> legemiddelliste = new Lenkeliste<Legemiddel>();
    Lenkeliste<Resept> reseptliste = new Lenkeliste<Resept>();

    public static void main(String [] args) throws NumberFormatException, UlovligUtskrift{
        Legesystem hovedsystem = new Legesystem();
        hovedsystem.hovedmeny();
    }
    public void hovedmeny(){
        Scanner input = new Scanner(System.in);
        String n = null;
        System.out.println(" Velg 1 for aa skrive ut oversikt over pasienter, leger, legemidler og resepter. \n Velg 2 for aa opprette og legge til nye elementer i systemet \n velg 3 for aa bruke en gitt resept fra listen til en pasient \n velg 4 for aa skrive ut forskjellige former for statistikk \n velg 5 for aa skrive alle data til fil. \n press enter for aa avslutte.");
        if (input.hasNextLine()){
            while(n!=""){
                n = input.nextLine();
                if(n.startsWith("1")){
                    skrivUtAlt();
                }
                if(n.equals("2")){
                    leggTil();
                }
                if(n.equals("3")){
                    brukResept();
                }
                if(n.equals("4")){
                    visStatistikk();
                }
                if(n.equals("")){
                    System.out.println("Takk for i dag");
                    input.close();
                }
            }
            input.close();
        }
    }
    public void leggTil(){
        Scanner inn = new Scanner(System.in);
        String p = null;
        System.out.println(" Velg 1 for aa legge til lege \n velg 2 for aa legge til pasient \n velg 3 for aa legge til resept \n velg 4 for aa legge til legemiddel. \n enter for aa gaa tilbake.");
        if (inn.hasNextLine()){
            while(p!=""){
                p = inn.nextLine();
                if(p.equals("1")){
                    System.out.println("Er legen en spesialist? [1] hvis ja, [2] hvis nei.");
                    if (inn.hasNextLine()){
                        p = inn.nextLine();
                        if(p.equals("1")){
                            System.out.println("Skriv inn kontroll-ID: ");
                            if (inn.hasNextLine()){
                                String kontrollID = inn.nextLine();
                                if(kontrollID!= null){
                                    System.out.println("Skriv inn legens navn: ");
                                    if (inn.hasNextLine()){
                                        Spesialist spess = new Spesialist(inn.nextLine(), kontrollID);
                                        legeliste.leggTil(spess);
                                        System.out.println("Spesialisten ble lagt til, press enter for aa gaa tilbake");
                                        if(inn.hasNextLine()){
                                            p = inn.nextLine();
                                            if(p.equals("")){
                                                leggTil();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    else if (p.equals("2")){
                        System.out.println("Skriv inn legens navn: ");
                        if (inn.hasNextLine()){
                            Lege legen = new Lege(inn.nextLine());
                            legeliste.leggTil(legen);
                            System.out.println("Legen ble lagt til, press enter for aa gaa tilbake");
                            if(inn.hasNextLine()){
                                p = inn.nextLine();
                                if(p.equals("")){
                                    leggTil();
                                }
                            }
                        }
                    }
                }
                else if(p.equals("2")){
                    System.out.println("Hva er pasientens navn: ");
                    if (inn.hasNextLine()){
                        String navn = inn.nextLine();
                        if (navn!= null){
                            System.out.println("Hva er pasientens personnummer?");
                            if (inn.hasNextLine()){
                                String personnummer = inn.nextLine();
                                if(personnummer.length()==11){
                                    Pasient pasienten = new Pasient(navn, personnummer);
                                    pasientliste.leggTil(pasienten);
                                    System.out.println("Pasienten ble lagt til, press enter for aa gaa tilbake");
                                }
                            }
                        }
                    }
                }
                else if(p.equals("4")){
                 
                }
                else if(p.equals("4")){
                    
                }
            }
            hovedmeny();
            }
        }
        inn.close();
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
                    if (data.startsWith("# Legemidler")){
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
                    if (data.startsWith("# Leger")){
                        data = leser.nextLine();
                        while(!data.startsWith("#")){
                            String[] leger = data.split(",");
                            if(!leger[1].equals("0")){
                                Spesialist spes = new Spesialist(leger[0], leger[1]);
                                legeliste.leggTil(spes);
                            }
                            else{
                                Lege lege = new Lege(leger[0]);
                                legeliste.leggTil(lege);
                            }
                            data = leser.nextLine();
                        }
                    }
                    if (data.startsWith("# Resepter")){
                        data = leser.nextLine();
                        while(!data.startsWith("#")){
                            String[] resepter = data.split(",");

                            if(resepter[3].equals("hvit")){
                                System.out.println(resepter[1]);
                                HvitResept hvit = getLege(resepter[1]).skrivHvitResept(getLegemiddel(resepter[0]), getPasient(resepter[2]), Integer.parseInt(resepter[4]));
                                reseptliste.leggTil(hvit);
                            }
                            else if(resepter[3].equals("blaa")){
                                BlaaResept blaa = getLege(resepter[1]).skrivBlaaResept(getLegemiddel(resepter[0]), getPasient(resepter[2]), Integer.parseInt(resepter[4]));
                                reseptliste.leggTil(blaa);
                            }
                            else if(resepter[3].equals("millitaer")){
                                MilitaerResept millitaer = getLege(resepter[1]).skrivMilitaerResept(getLegemiddel(resepter[0]), getPasient(resepter[2]), Integer.parseInt(resepter[4]));
                                reseptliste.leggTil(millitaer);
                            }
                            else if(resepter[3].equals("p")){
                                PResept presept = getLege(resepter[1]).skrivPResept(getLegemiddel(resepter[0]), getPasient(resepter[2]));
                                reseptliste.leggTil(presept);
                            }
                            data = leser.nextLine();
                        }
                    }      
                }catch (IllegalFormatException f){
                System.err.println("Element fra fil ikke lagt til, feil formatering");
                }
            }
            leser.close();
        } catch (FileNotFoundException e) {
        System.err.println("Error: kan ikke lese " + filnavn + "");
        System.exit(1);
        }
    }

    public void visStatistikk(){
        Scanner nyInput = new Scanner(System.in);
        System.out.println("Trykk på et tall for å skrive ut statistikk: \n1 - Totalt antall resepter på vanedannende legemidler \n2 - Totalt antall utskrevne resepter på narkotiske legemidler \n3 - Statistikk om mulig misbruk av narkotika");
        String n = null;
        int antall;
        if (nyInput.hasNextLine()){
            while (n != ""){
                n = nyInput.nextLine();
                if (n.equals("1")){
                    antall = 0;
                    System.out.println("\nVanedannende resepter: \n");
                    for (Resept e : reseptliste){
                        if (e.legemiddel instanceof Vanedannende){
                            antall++;
                        }
                    }
                    System.out.println("\nTotalt antall utskrevde resepter på vanedannende legemidler: " + antall + "\n");
                    System.out.println("Press enter for å gå tilbake til hovedmenyen");
                }
                else if (n.equals("2")){
                    antall = 0;
                    System.out.println("\nNarkotiske resepter: \n");
                    for (Resept e : reseptliste){
                        if (e.legemiddel instanceof Narkotisk){
                            antall++;
                        }
                    }
                    System.out.println("\nTotalt antall utskrevde resepter på narkotiske legemidler: " + antall + "\n");
                    System.out.println("Press enter for å gå tilbake til hovedmenyen");
                }
                else if (n.equals("3")){
                    for (Lege e : legeliste){
                        if (e.skrevetUtNarkotisk()){
                            System.out.println(e + " har skrevet ut " + e.antallNarkotiskeResepter + " resepter av narkotiske legemidler \n");
                        }
                    }
                    for (Pasient e : pasientliste){
                        if (e.harNarkotiskResept()){
                            System.out.println("\n" + e + " har " + e.gyldigeNarkotiskeResepter + " gyldige narkotiske resepter");
                        }
                    }
                    System.out.println("Press enter for å gå tilbake til hovedmenyen");
                } 
                else if (n.equals("")){
                    hovedmeny();
                }
            } nyInput.close();
        } 
    }

    public void brukResept(){
        Scanner input = new Scanner(System.in);
        String n = null;
        int index = 0;
        System.out.println("Hvilken pasient vil du se resepter for?");
        for (Pasient e : pasientliste){
            System.out.println(index + ": " + e.hentPasientNavn() + " (fnr: " + e.fodselsnummer + ")\n");
            index++;
        }
        index = 0;
        if (input.hasNextLine()){
            while (n != ""){
                n = input.nextLine();
                int i = Integer.parseInt(n);
                Pasient pasient = pasientliste.hent(i);
                pasient.hentPasientNavn();
                System.out.println("\nValgt pasient: " + pasient.hentPasientNavn() + " (fnr: " + pasient.fodselsnummer + ")\n");
                System.out.println("Hvilken resept vil du bruke?");
                for (Resept e : reseptliste){
                    System.out.println(index + ": " + e.hentLegemiddel() + " (" + e.hentReit() + " reit)\n");
                    index++;
                }
                Resept resept = reseptliste.hent(i);
                if (resept.reit > 0){
                    System.out.println("\nKunne ikke bruke resept på " + resept.hentLegemiddel() + ". Ingen gjenværende reit.");
                } else {
                    resept.bruk();
                }
            }
        }
    }

    public Lege getLege(String legeNavn){
        for (Lege e : legeliste){
            if (e.hentNavn().equals(legeNavn)){
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
