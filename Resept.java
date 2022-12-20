
abstract class Resept {
  private static int count = 1;
  protected int id = 0;
  Legemiddel legemiddel;
  Lege utskrivende;
  Pasient pasient;
  int Reit;

  public Resept(Legemiddel legemiddel, Lege utskrivende, Pasient pasient, int Reit) {
    this.legemiddel = legemiddel;
    this.utskrivende = utskrivende;
    this.Reit = Reit;
    this.pasient = pasient;
    id = count++;
  }

  public int hentId() {
    return id;
  }

  public int hentReit() {
    return Reit;
  }

  public Legemiddel hentLegemiddel() {
    return legemiddel;
  }

  public Lege hentLege() {
    return utskrivende;
  }

  public Pasient hentPasient() {
    return pasient;
  }

  public boolean bruk() {
    if (Reit == 0) {
      return false;
    } else {
      Reit--;
      return true;
    }
  }

  @Override
  public String toString() {
    String s = "\n..........P Resept..........";
    s += "\nDrug name: " + legemiddel.hentNavn();
    s += "\nPrescription by Doctor: " + utskrivende.hentNavn();
    s += "\nPatient ID: " + pasient.hentNavn();
    s += "\nRemaining Possible Prescriptions: " + Reit;
    s += "\nResept color: " + farge();
    s += "\nPris a betale: " + prisAaBetale() + " kr.";
    return s;
  }

  abstract public String farge();

  abstract public int prisAaBetale();

  abstract public String hentType();
}
