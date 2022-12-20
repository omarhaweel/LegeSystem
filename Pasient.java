public class Pasient {
  int antallNarkotiskeResepter = 0;
  protected int pasientID;
  protected String navn;
  private static int counter = 0;
  protected String fnr;
  protected IndeksertListe<Resept> reseptListe = new IndeksertListe<>();

  public Pasient(String navn, String fnr) {
    this.navn = navn;
    counter++;
    this.pasientID = counter;
    //counter = pasientID;
    this.fnr = fnr; 
  }

  public int hentPasientID() {
    return pasientID;
  }

  public String hentPasientNavn() {
    return navn;
  }

  public void leggTilNyResept(Resept resept) {
    reseptListe.leggTil(resept);
  }

  public String hentFnr (){
    return fnr;
  }

  public String hentNavn() {
    return navn;
  }

  @Override
  public String toString() {
    String s = "\n..........Pasient..........";
    s += "\nPatient name: " + navn;
    s += "\nPatient ID: " + pasientID;

    return s;
  }

}
