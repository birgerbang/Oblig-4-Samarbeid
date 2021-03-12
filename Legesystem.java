import java.io.File;
import java.io.FileNotFoundException;
import java.util.IllegalFormatException;
import java.util.Scanner;

public class Legesystem {

    Lenkeliste<Pasient> pasientliste = new Lenkeliste<Pasient>();
    Lenkeliste<Lege> legeliste = new Lenkeliste<Lege>();
    Lenkeliste<Legemiddel> legemiddelliste = new Lenkeliste<Legemiddel>();
    Lenkeliste<Resept> reseptliste = new Lenkeliste<Resept>();
    /**
     * En metode for å lese inn objekter fra fil. Følg filformatet i vedlegg 2. Bruk
s krivResept-metodene i legeobjektet for å opprette Resept objekter. Dersom et objekt er
ugyldig eller ikke følger filformatet i vedlegg 2, skal det ikke legges inn i systemet.
PS: Husk å behandle unntak som kan kastes.
     */
    public void lesFil(String filnavn){
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
                                HvitResept hvit = new HvitResept(getLegemiddel(resepter[0]), getLege(resepter[1]), getPasient(resepter[2]), Integer.parseInt(resepter[3]));
                                reseptliste.leggTil(hvit);
                                data = leser.nextLine();
                            }
                            else if(resepter[3]=="blaa"){
                                BlaaResept blaa = new BlaaResept(getLegemiddel(resepter[0]), getLege(resepter[1]), getPasient(resepter[2]), Integer.parseInt(resepter[3]));
                                reseptliste.leggTil(blaa);
                                data = leser.nextLine();
                            }
                            else if(resepter[3]=="millitaer"){
                                MilitaerResept millitaer = new MilitaerResept(getLegemiddel(resepter[0]), getLege(resepter[1]), getPasient(resepter[2]), Integer.parseInt(resepter[3]));
                                reseptliste.leggTil(millitaer);
                                data = leser.nextLine();
                            }
                            else if(resepter[3]=="p"){
                                PResept presept = new PResept(getLegemiddel(resepter[0]), getPasient(resepter[2]));
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
