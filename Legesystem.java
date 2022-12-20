import java.util.*;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;

public class Legesystem {
  public static void main(String[] args) throws Exception {
    IndeksertListe<Pasient> pasienterList = new IndeksertListe<>();
    IndeksertListe<Legemiddel> Legemidler = new IndeksertListe<>();
    Prioritetskoe<Lege> Leger = new Prioritetskoe<>();
    IndeksertListe<Resept> Resepter = new IndeksertListe<>();

    String innFil = "legedata.txt";
    try {
      Scanner file = new Scanner(new File(innFil));

      String line = "";

      while (file.hasNextLine()) {

        if (file.nextLine().equals("# Pasienter (navn, fnr)")) {
          for (int i = 0; i < 4; i++) {
            line = file.nextLine();
            // System.out.println(line);
            String[] biter = line.split(",");
            String navn = biter[0];
            String fnr = biter[1];
            Pasient p = new Pasient(navn, fnr);
            pasienterList.leggTil(p);
          }
          // just for testingSystem.out.println("-----------------------" +
          // pasienterList.toString());
        }

        // Leser inn legemidler fra fil
        if (file.nextLine().equals("# Legemidler (navn,type,pris,virkestoff,[styrke])")) {
          for (int i = 0; i < 4; i++) {
            line = file.nextLine();
            String[] biter = line.split(",");
            String navn = biter[0];
            String type = biter[1];
            int pris = Integer.parseInt(biter[2]);
            double virkestoff = Double.parseDouble(biter[3]);

            if (type.equals("narkotisk")) {
              int styrke = Integer.parseInt(biter[4]);
              Narkotisk n = new Narkotisk(pris, navn, virkestoff, styrke);
              Legemidler.leggTil(n);
            }
            if (type.equals("vanedannende")) {
              int styrke = Integer.parseInt(biter[4]);
              Vanedannende v = new Vanedannende(pris, navn, virkestoff, styrke);
              Legemidler.leggTil(v);
            }
            if (type.equals("vanlig")) {
              Vanlig vad = new Vanlig(pris, navn, virkestoff);
              Legemidler.leggTil(vad);
            }
          }
          // System.out.println(Legemidler.toString());

        }

        // Leser inn Leger fra fil
        if (file.nextLine().equals("# Leger (navn,kontrollid / 0 hvis vanlig lege)")) {
          for (int i = 0; i < 4; i++) {
            line = file.nextLine();
            // System.out.println(line);
            String[] biter = line.split(",");
            String navn = biter[0];
            String kontrollid = biter[1];

            if (kontrollid.equals("0")) {
              Lege l = new Lege(navn, kontrollid);
              Leger.leggTil(l);
            }
            if (kontrollid.equals("0") == false) {
              Spesialist s = new Spesialist(navn, kontrollid);
              Leger.leggTil(s);
            }
          }
          // System.out.println(Leger.toString());
        }

        // Leser inn Resepter fra fil
        if (file.nextLine().equals("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])")) {
          for (int i = 0; i < 4; i++) {
            line = file.nextLine();
            // System.out.println(line);
            String[] biter = line.split(",");
            int legemiddelNummer = Integer.parseInt(biter[0]);
            String legeNavn = biter[1];
            int pasientID = Integer.parseInt(biter[2]);

            Pasient pasient = pasienterList.hent(pasientID - 1);

            Legemiddel legemiddel = null;

            for (Legemiddel lm : Legemidler) {
              if (lm.hentId() == legemiddelNummer) {
                legemiddel = lm;
              }
            }

            //Legemiddel legemiddel = Legemidler.hent(legemiddelNummer);

            //Lege utskrivende = Leger.hent();

            Lege utskrivende = null;

            for (Lege lg : Leger) {
              if (lg.hentNavn().equals(legeNavn)) {
                utskrivende = lg;
              }
            }

            //Lege utskrivende = new Lege(navn)
            String type = biter[3];

            if (type.equals("hvit")) {
              int reit = Integer.parseInt(biter[4]);
              
              try {
                HvitResept h = utskrivende.skrivHvitResept(legemiddel, pasient, reit);
                Resepter.leggTil(h);
                pasient.leggTilNyResept(h);
              } catch (Exception e) {
                System.out.println(e.getMessage());
              }

            }
            if (type.equals("blaa")) {
              int reit = Integer.parseInt(biter[4]);
              try {
                if (legemiddel instanceof Narkotisk) {
                  utskrivende.narkotiskReseptCounter++;
                  pasient.antallNarkotiskeResepter++;
                }
                BlaaResept b = utskrivende.skrivBlaaResept(legemiddel, pasient, reit);
                Resepter.leggTil(b);
                pasient.leggTilNyResept(b);
              } catch (Exception e) {
                System.out.println(e.getMessage());
              }

            }
            if (type.equals("militaer")) {
              try {
                MilResept m = utskrivende.skrivMilResept(legemiddel, pasient);
                Resepter.leggTil(m);
                pasient.leggTilNyResept(m);
              } catch (Exception e) {
                System.out.println(e.getMessage());
              }

            }
            if (type.equals("p")) {
              int reit = Integer.parseInt(biter[4]);
              try {
                PResept p = utskrivende.skrivPResept(legemiddel, pasient, reit);
                Resepter.leggTil(p);
                pasient.leggTilNyResept(p);
              } catch (Exception e) {
                System.out.println(e.getMessage());
              }

            }
          }
          // System.out.println(Resepter.toString());
        }

      }
      file.close();
    } catch (

    FileNotFoundException e) {
      System.out.println("Fant ikke filen!");
    }

    // start meny utføring ;

    Scanner in = new Scanner(System.in);

    String input = "";

    //Level 1 menu
    

    while (!input.equals("Exit")) {
      System.out.println("[Hovedmeny]");
      System.out.println("A: Oversikt");
      System.out.println("B: Opprett nytt objekt");
      System.out.println("C: Bruk resept");
      System.out.println("D: Skriv ut statistikk");
      System.out.println("E: Skriv data til fil");
      System.out.println("Exit: Avslutt program");

      input = (String) in.nextLine();

      // in.close()

      if (input.equals("A")) { //Level 2
        // print out Oversikt;
        System.out.println("********************** LEGER *******************************");
        System.out.println();
        System.out.println(Leger);
        System.out.println("*********************** LEGEMIDLER ******************************");
        System.out.println();
        System.out.println(Legemidler);
        System.out.println("************************* PASIENTER ****************************");
        System.out.println();
        System.out.println(pasienterList);
        System.out.println("*************************** RESEPTER **************************");
        System.out.println();
        System.out.println(Resepter);
        System.out.println();
        System.out.println("*****************************************************");

      } 

      else if (input.equals("B")) { // Level 2
        while (!input.equals("Exit")) {
          System.out.println("[Gjoor et valg]");
          System.out.println("A: Legg til lege");
          System.out.println("B: Legg til pasient");
          System.out.println("C: Legg til resept");
          System.out.println("D: Legg til legemiddel");
          System.out.println("Exit: Avslutt program");
          input = (String) in.nextLine();

          if (input.equals("A")) { // Level 3
            System.out.println("Skriv inn navnet pa legen du onsker a legge til: ");
            String navn = in.nextLine();
            System.out.println("Er den legen en spesialist? Svar med ja eller nei: ");
            String svar = in.nextLine();
            if (svar.equals("ja")) {
              System.out.println("Legg til en gyldig kontroll ID: ");
              String kontrollID = in.nextLine();
              Spesialist nySpesialist = new Spesialist(navn, kontrollID);
              Leger.leggTil(nySpesialist);
            } else {
              Lege nyLege = new Lege(navn, "0");
              Leger.leggTil(nyLege);
            }

            break;
          }

          else if (input.equals("B")) { //  Level 3
              System.out.println("Skriv inn navnet pa pasienten du onsker a legge til: ");
              String navn = in.nextLine();
              System.out.println("Skriv inn fodselsnummeret til pasienten: ");
              String fnr = in.nextLine();

              int testIfPatient = 0;
              for (Pasient p : pasienterList) {
                if (fnr.equals(p.hentFnr())) {
                  System.out.println("Pasienten er allerede registrert. Du kan ikke legge denne pasienten.");
                  testIfPatient++;
                } 
              }
              
              if (testIfPatient == 0) {
                Pasient pasient = new Pasient(navn, fnr);
                pasienterList.leggTil(pasient);
                System.out.println("Pasient lagt til.");
              }

              break;
          }

          else if (input.equals("C")) { // Level 3


            // Sporr om en lege
            System.out.println("Hvilken lege vil du legge til?");

            int counter = 1;

            for (Lege l : Leger) {
              System.out.println(counter + ": " + l.hentNavn() + ", " + l.hentKontrollID());
              
              //l.hentKontrollID() + " " + l.hentNavn());
              counter++;
              
            }
                
            int velgLege = in.nextInt(); 
            
            Lege lege = Leger.hent(velgLege-1);

            //System.out.println(lege.hentKontrollID());
            System.out.println("Du valgte: " + lege.hentNavn());

            //for (Lege lg : Leger) {
            //  System.out.println(lg.hentNavn());
            //}

            // Sporr om en pasient
            System.out.println("Hvilken pasient vil du legge til?");

            counter = 1;
            for (Pasient p : pasienterList) {
              System.out.println(counter + ": " + p.hentFnr() + " " + p.hentPasientNavn());
              counter++;
            }
                
            int velgPasient = in.nextInt();

            Pasient pasient = pasienterList.hent(velgPasient-1);

            System.out.println("Du valgte: " + pasient.hentNavn());

            // Sporr om et legemiddel
            System.out.println("Hvilket legemiddel vil du legge til?");

            counter = 1;
            for (Legemiddel lm : Legemidler) {
              System.out.println(counter + ": " + lm.hentId() + " " + lm.hentNavn());
              counter++;
            }
                
            int velgLegemiddel = in.nextInt();

            //System.out.println(velgLegemiddel);

            Legemiddel legemiddel = Legemidler.hent(velgLegemiddel-1);

            System.out.println("Du valgte: " + legemiddel.hentNavn());

            //System.out.println(legemiddel.hentId());
            //System.out.println(legemiddel.hentNavn());


            //for (Legemiddel l1 : Legemidler) {
            //  System.out.println(l1.hentId());
            //  System.out.println(l1.hentNavn());
            //}

            // Sporr om reit
            System.out.println("Hvor mange ganger skal resepten vaere gyldig?");
            int gyldigGanger = in.nextInt();

            System.out.println("vanlig hvit (h), militaer (m), p-resept (p) eller blaa (b)");
            String mystisk = in.nextLine();
            String navnResept = in.nextLine();
            

            if (navnResept.equals("h")) {
              HvitResept hvitResept = lege.skrivHvitResept(legemiddel, pasient, gyldigGanger);
              Resepter.leggTil(hvitResept);
              System.out.println("Lagt til hvitResept");
            }

            if (navnResept.equals("p")) {
              PResept pResept = lege.skrivPResept(legemiddel, pasient, gyldigGanger);
              Resepter.leggTil(pResept);
              System.out.println("Lagt til P-resept");
            }

            if (navnResept.equals("m")) {
              MilResept milResept = lege.skrivMilResept(legemiddel, pasient);
              Resepter.leggTil(milResept);
              System.out.println("Lagt til militaerresept");
            }
            
            if (navnResept.equals("b")) {
              BlaaResept blaaResept = lege.skrivBlaaResept(legemiddel, pasient, gyldigGanger);
              Resepter.leggTil(blaaResept);
              System.out.println("Lagt til blaaresept");
              if (legemiddel instanceof Narkotisk) {
                lege.narkotiskReseptCounter++;
                pasient.antallNarkotiskeResepter++;
              }
            }
            
            break;
          } 

          else if (input.equals("D")) { // Level 3

            System.out.println("Skriv inn navnet pa legemiddelet du onsker a legge til:");
            String navn = in.nextLine();

            System.out.println("Skriv inn prisen pa legemiddelet du onsker a legge til:");
            int pris = in.nextInt();

            System.out.println("Skriv inn mengde virkestoff for legemiddelet du onsker a legge til: ");
            double mengdeVirkestoff = in.nextDouble();

            System.out.println("Skriv inn typet til legemiddelet du onsker a legge til. Er det et narkotisk, vanedannende eller vanlig?");
            String mysteriousRelicThatFixesTheCode = in.nextLine();
            String typeLegemiddel = in.nextLine();

            if (typeLegemiddel.equals("vanlig")) {
              Vanlig vanlig = new Vanlig(pris, navn, mengdeVirkestoff);
              Legemidler.leggTil(vanlig);
            }
            if (typeLegemiddel.equals("narkotisk")) {
              System.out.println("Skriv inn styrke: ");
              int styrke = in.nextInt();
              Narkotisk narkotisk = new Narkotisk(pris, navn, mengdeVirkestoff, styrke);
              Legemidler.leggTil(narkotisk);
            }
            if (typeLegemiddel.equals("vanedannende")) {
              System.out.println("Skriv inn nivaa: ");
              int nivaa = in.nextInt();
              Vanedannende vanedannede = new Vanedannende(pris, navn, mengdeVirkestoff,nivaa);
              Legemidler.leggTil(vanedannede);
            }

            break;
          }
        }
      }
          
      else if (input.equals("C")) { // Level 2
        int counter = 1;
        System.out.println("Hvilken resept vil du bruke?");
        for (Resept r : Resepter) {
          Legemiddel legemiddel = r.hentLegemiddel();
          String legemiddelnavn = legemiddel.hentNavn();
          System.out.println(counter + ": " + legemiddelnavn + " " + "(" + r.hentReit() + " reit)");
          counter++;
        }
        
        int valgtReseptNummer = in.nextInt();
        String mysteriousRelicThatFixesTheCode3 = in.nextLine();

        Resept resept = Resepter.hent(valgtReseptNummer);
        resept.bruk();

        System.out.println("");
        System.out.println("Legemiddel " + resept.hentLegemiddel().hentNavn() + " kan brukes " + resept.hentReit() + " ganger igjen");
        System.out.println("");
      }

      else if (input.equals("D")) { // Level 2
        int vaneCounter = 0;
        int narkoCounter = 0;
        int vanligCounter = 0;
        int unikeLegeCounter = 0;
        int unikePasientCounter = 0;
        Koe<Lege> unikeLeger = new Koe<Lege>();
        Koe<Pasient> unikePasienter = new Koe<Pasient>();

        for (Resept r : Resepter) {
          if (r.hentLegemiddel() instanceof Vanedannende) {
            vaneCounter++;
          }

          if (r.hentLegemiddel() instanceof Narkotisk) {
            narkoCounter++;
          }

          if (r.hentLegemiddel() instanceof Vanlig) {
            vanligCounter++;
          }

          Lege lege = r.hentLege();
          Pasient pasient = r.hentPasient();

          if (lege.narkotiskReseptCounter > 0 && r.hentLegemiddel() instanceof Narkotisk) {
            for (Lege l : unikeLeger) {
              if (lege.equals(l)) {
                unikeLegeCounter++;
              }
            }
            if (unikeLegeCounter == 0) {
              unikeLeger.leggTil(lege);
              System.out.println(lege.hentNavn() + " har skrevet ut " + lege.narkotiskReseptCounter + " narkotiske legemidler");
            }

            for (Pasient p : unikePasienter) {
              if (pasient.equals(p)) {
                unikePasientCounter++;
              }
            }
            if (unikePasientCounter == 0) {
              unikePasienter.leggTil(pasient);
              System.out.println(pasient.hentNavn() + " har resept for " + pasient.antallNarkotiskeResepter + " narkotiske legemidler");
            }

          }          

        }
          System.out.println("Antall resepter med vanedannende legemidler: " + vaneCounter); 
          System.out.println("Antall resepter med narkotiske legemidler: " + narkoCounter); 
          System.out.println("Antall resepter med vanlige legemidler: " + vanligCounter);  
      }

      else if (input.equals("E")) { // Level 2
        System.out.println("Skrev ut info til fil");
        try { 
          FileWriter myFile = new FileWriter("legedata.txt");
          myFile.write("# Pasienter (navn, fnr)");
          myFile.write("\n");
          for (Pasient p : pasienterList) {
            myFile.write(p.hentNavn() + "," + p.hentFnr());
            myFile.write("\n");
          }
          myFile.write("# Legemidler (navn,type,pris,virkestoff,[styrke])");
          myFile.write("\n");
          for (Legemiddel lm : Legemidler) {
            if (lm instanceof Narkotisk) {
              Narkotisk lmNarkotisk = (Narkotisk) lm;
              myFile.write(lmNarkotisk.hentNavn() + "," + "narkotisk" + "," + lmNarkotisk.HentPris() + "," + lmNarkotisk.hentVirkestoff() + "," + lmNarkotisk.hentStyrke());
            }

            else if (lm instanceof Vanedannende) {
              Vanedannende lmVanedannende = (Vanedannende) lm;
              myFile.write(lmVanedannende.hentNavn() + "," + "vanedannende" + "," + lmVanedannende.HentPris() + "," + lmVanedannende.hentVirkestoff() + "," + lmVanedannende.hentStyrke());
            }

            else {
              myFile.write(lm.hentNavn() + "," + "vanlig" + "," +  lm.HentPris() + "," + lm.hentVirkestoff());
            }
            myFile.write("\n");
          }
          myFile.write("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
          myFile.write("\n");
          for (Lege lg : Leger) {
            myFile.write(lg.hentNavn() + "," + lg.hentKontrollID());
            myFile.write("\n");
          }
          myFile.write("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
          myFile.write("\n");

          for (Resept r : Resepter) {
            if (r instanceof MilResept) {
              myFile.write(r.hentLegemiddel().hentId() + "," + r.hentLege().hentNavn() + "," + r.hentPasient().hentPasientID() + "," + r.hentType());
            }
            else {
              myFile.write(r.hentLegemiddel().hentId() + "," + r.hentLege().hentNavn() + "," + r.hentPasient().hentPasientID() + "," + r.hentType() + "," + r.hentReit());
            }
      
            myFile.write("\n");
          }
          myFile.close();
        } 
        
        catch (IOException e) {
          System.out.println("Noe gikk feil");
          e.printStackTrace();
        }
      }
      
      else if (input.equals("Exit")) {
        in.close();
        break;
      }

      else { // Level 2
        System.out.println("------------------------------");
        System.out.println("[Ikke valid input]");
        System.out.println("------------------------------");
      }
    }
  }
}