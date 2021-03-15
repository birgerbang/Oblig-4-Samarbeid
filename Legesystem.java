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
    public void hovedmeny() throws UlovligUtskrift{
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

    public void leggTil() throws UlovligUtskrift{
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
                                    if(inn.hasNextLine()){
                                        p = inn.nextLine();
                                        if(p.equals("")){
                                            leggTil();
                                        }
                                    }
                                }
                                else{
                                    System.out.println("Feil! Husk at personnummeret skal ha 11 siffer. ");
                                    System.out.println("Hva er pasientens personnummer? ");
                                    if (inn.hasNextLine()){
                                        personnummer = inn.nextLine();
                                        if(personnummer.length()==11){
                                            Pasient pasienten = new Pasient(navn, personnummer);
                                            pasientliste.leggTil(pasienten);
                                            System.out.println("Pasienten ble lagt til, press enter for aa gaa tilbake");
                                            if(inn.hasNextLine()){
                                                p = inn.nextLine();
                                                if(p.equals("")){
                                                    leggTil();
                                                }
                                            }
                                        }
                                        else{
                                            System.out.println("Feil! Pasienten kunne ikke bli lagt til. Press enter for aa gaa tilbake ");
                                            if(inn.hasNextLine()){
                                                p = inn.nextLine();
                                                if(p.equals("")){
                                                    leggTil();
                                                }
                                                else{
                                                    leggTil();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                else if(p.equals("3")){
                    Lege legen = null;
                    while(legen == null && p!=""){
                        System.out.println("Hva er den utskrivende legens navn? : ");
                        if (inn.hasNextLine()){
                            String navn = inn.nextLine();
                            if (navn!= null){
                                legen = getLege(navn);
                                if (legen != null){
                                    break;
                                }
                                else{
                                    System.out.println("Legen ble ikke funnet. har du skrevet feil? press enter for å gaa tilbake, [1] for å forsoke igjen");
                                    if (inn.hasNextLine()){
                                        p = inn.nextLine();
                                    }
                                }
                            }
                        }
                    }
                    Legemiddel legemiddelet = null;
                    while(legemiddelet == null && p!=""){
                        System.out.println("Hvilket legemiddel skal skrives ut? : ");
                        if (inn.hasNextLine()){
                            String navn = inn.nextLine();
                            if (navn!= null){
                                legemiddelet = getLegemiddel(navn);
                                if (legemiddelet != null){
                                    break;
                                }
                                else{
                                    System.out.println("Legemiddelet ble ikke funnet. har du skrevet feil? \n press enter for å gaa tilbake og legge til legemiddel, [1] for å forsoke igjen");
                                    if (inn.hasNextLine()){
                                        p = inn.nextLine();
                                    }
                                }
                            }
                        }
                    }
                    Pasient pasienten = null;
                    while(pasienten == null && p!=""){
                        System.out.println("Pasientnummeret til pasienten? : ");
                        if (inn.hasNextLine()){
                            String nummer = inn.nextLine();
                            if (nummer!= null){
                                pasienten = getPasient(nummer);
                                if (pasienten != null){
                                    break;
                                }
                                else{
                                    System.out.println("Pasienten ble ikke funnet. har du skrevet feil nummer? \n press enter for å gaa tilbake og legge til pasienten, [1] for å forsoke igjen");
                                    if (inn.hasNextLine()){
                                        p = inn.nextLine();
                                    }
                                }
                            }
                        }
                    }
                    String type = null;
                    while(type == null && p!=""){
                        System.out.println("Hvilken type skal resepten vaere?, [b] for blaa, [h] for hvit, [p] for PResept, [m] for militaer: ");
                        if (inn.hasNextLine()){
                            String input = inn.nextLine();
                            if (input.equals("b")){
                                type = input;
                            }
                            else if (input.equals("h")){
                                type = input;
                            }
                            else if (input.equals("p")){
                                type = input;
                            }
                            else if (input.equals("m")){
                                type = input;
                            }
                            else{
                                System.out.println("Dette er ikke en gyldig resepttype. Prov igjen. (eller press enter for aa gi opp)");
                            }
                        }
                    }
                    if (type.equals("p")){
                        legen.skrivPResept(legemiddelet, pasienten);
                        System.out.println("Resepten ble lagt til, press enter for aa gaa tilbake.");
                        if (inn.hasNextLine()){
                            p = inn.nextLine();
                        }
                    }
                    else{
                        int reit = 0;
                        while(reit == 0 && p!=""){
                            System.out.println("Hvor mange reit skal resepten ha? : ");
                            if (inn.hasNextLine()){
                                String nummer = inn.nextLine();
                                if (nummer!= null){
                                    try {
                                        int numb = Integer.parseInt(nummer);
                                        reit = numb;
                                    }catch (NumberFormatException e) {
                                        System.out.println("Dette er ikke et nummer. har du skrevet feil? feil nummer? \n press enter for å gi opp, eller [1] for å forsoke igjen");
                                        if (inn.hasNextLine()){
                                            p = inn.nextLine();
                                        }
                                    }
                                }
                            }
                        }
                        if(type.equals("h")){
                            legen.skrivHvitResept(legemiddelet, pasienten, reit);
                        }
                        else if(type.equals("m")){
                            legen.skrivMilitaerResept(legemiddelet, pasienten, reit);
                        }
                        else if(type.equals("b")){
                            legen.skrivBlaaResept(legemiddelet, pasienten, reit);
                        }
                    }
                }
                else if(p.equals("4")){
                    String typen = null;
                    int pris = 0;
                    double virkestoff = 0; 
                    int styrke = -1;
                    String navn = null;

                    System.out.println("Hva er navnet på legemiddelet?: ");
                    if(inn.hasNextLine()){
                        navn = inn.nextLine();
                    }
                    while(typen == null && p!=""){
                        System.out.println("Hva slags type legemiddel? [l] for vanlig, [v] for vanedannende eller [n] for narkotisk: ");
                        if (inn.hasNextLine()){
                            String inputs = inn.nextLine();
                            if (inputs!= null){
                                if (inputs.equals("l")){
                                    typen = inputs;
                                }
                                else if (inputs.equals("v")){
                                    typen = inputs;
                                }
                                else if (inputs.equals("n")){
                                    typen = inputs;
                                }
                                else{
                                    System.out.println("Dette er ikke et gyldig legemiddeltype. Prov igjen. (eller press enter for aa gi opp)");
                                }
                            }
                        }
                    }
                    while(pris == 0 && p!=""){
                        System.out.println("Hvor mye koster legemiddelet? : ");
                        if (inn.hasNextLine()){
                            String nummer = inn.nextLine();
                            if (nummer!= null){
                                try {
                                    int numb = Integer.parseInt(nummer);
                                    pris = numb;
                                }catch (NumberFormatException e) {
                                    System.out.println("Dette er ikke et nummer. har du skrevet feil? feil nummer? \n press enter for å gi opp, eller [1] for å forsoke igjen");
                                    if (inn.hasNextLine()){
                                        p = inn.nextLine();
                                    }
                                }
                            }
                        }
                    }
                    while(virkestoff==0 && p!=""){
                        System.out.println("Hvor mye virkestoff er det i legemiddelet? : ");
                        if (inn.hasNextLine()){
                            String nummer = inn.nextLine();
                            if (nummer!= null){
                                try {
                                    double numb = Double.parseDouble(nummer);
                                    virkestoff = numb;
                                }catch (NumberFormatException e) {
                                    System.out.println("Dette er ikke et gyldig nummer. har du skrevet feil? feil nummer? \n press enter for å gi opp, eller [1] for å forsoke igjen");
                                    if (inn.hasNextLine()){
                                        p = inn.nextLine();
                                    }
                                }
                            }
                        }
                    }
                    if(typen.equals("l")){
                        Vanlig vanlige = new Vanlig(navn, pris, virkestoff);
                        legemiddelliste.leggTil(vanlige);
                        System.out.println("Legemiddelet ble lagt til");

                    }
                    else{
                        while(styrke==-1){
                            System.out.println("Hva er styrken på legemiddelet? oppgi styrke i hel eller decimaltall:  ");
                            if (inn.hasNextLine()){
                                String nummer = inn.nextLine();
                                if (nummer!= null){
                                    try {
                                        int numb = Integer.parseInt(nummer);
                                        styrke = numb;
                                    }catch (NumberFormatException e) {
                                        System.out.println("Dette er ikke en gyldig styrke. har du skrevet feil? feil nummer? \n press enter for å gi opp, eller [1] for å forsoke igjen");
                                        if (inn.hasNextLine()){
                                            p = inn.nextLine();
                                        }
                                    }
                                }   
                            }
                        }
                        if(typen.equals("v")){
                            Vanedannende vane = new Vanedannende(navn, pris, virkestoff, styrke);
                            legemiddelliste.leggTil(vane);
                        }
                        else if(typen.equals("n")){
                            Narkotisk narko = new Narkotisk(navn, pris, virkestoff, styrke);
                            legemiddelliste.leggTil(narko);
                        }
                        System.out.println("Legemiddelet ble lagt til. press enter for aa gaa tilbake ");
                        if (inn.hasNextLine()){
                            p = inn.nextLine();
                        }
                    }
                }
            hovedmeny();
            }
        inn.close();
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

    public void visStatistikk() throws UlovligUtskrift{
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