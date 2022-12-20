class Lege implements Comparable<Lege> {
  int narkotiskReseptCounter = 0;
  String navn;
  IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>();
  String kontrollID;
  public Lege(String navn, String kontrollID) {
    this.navn = navn;
    this.kontrollID = kontrollID;//"0";
  }

  @Override
  public String toString() {
      String s = "\n..........Lege..........";
      s += "\nLegenavn: " + navn;
      s += "\nKontroll ID: " + kontrollID;
      return s;
  }

  public String hentNavn() {
    return navn;
  }

  public String hentKontrollID(){
    return kontrollID;
  }

  public int hentNarkotiskReseptCounter() {
    return narkotiskReseptCounter;
  }

  public int compareTo(Lege annen) {
    if (annen.navn.compareTo(this.navn) < 0)
      return -1;
    if (annen.navn.compareTo(this.navn) > 0)
      return 1;
    else
      return 0;
  }

  public IndeksertListe hentUtReseptListe() {
    return this.utskrevneResepter;
  }

  public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
    HvitResept ny = new HvitResept(legemiddel, this, pasient, reit);

    if (legemiddel instanceof Narkotisk) {
      throw new UlovligUtskrift(this, legemiddel);
    }
    utskrevneResepter.leggTil(ny);

    // HvitResept hvitt = (HvitResept) utskrevneResepter.hent(ny.hentId());
    return ny;

  }

  public MilResept skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
    MilResept ny = new MilResept(legemiddel, this, pasient);
    if (legemiddel instanceof Narkotisk) {
      throw new UlovligUtskrift(this, legemiddel);
    }
    utskrevneResepter.leggTil(ny);

    // MilResept mil = (MilResept) utskrevneResepter.hent(ny.hentId());
    return ny;

  }

  public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
    PResept ny = new PResept(legemiddel, this, pasient, reit);
    if (legemiddel instanceof Narkotisk) {
      throw new UlovligUtskrift(this, legemiddel);
    }
    utskrevneResepter.leggTil(ny);

    // PResept pr = (PResept) utskrevneResepter.hent(ny.hentId());
    return ny;
  }

  public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
    BlaaResept ny = new BlaaResept(legemiddel, this, pasient, reit);

    if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
      throw new UlovligUtskrift(this, legemiddel);
    }

    utskrevneResepter.leggTil(ny);

    // BlaaResept blaa = (BlaaResept) utskrevneResepter.hent(ny.hentId());
    return ny;

  }

}
