
class BlaaResept extends Resept {
  final String farge = "blaa";
  final String type = "blaa";

  public BlaaResept(Legemiddel legemiddel, Lege utskrivende, Pasient pasient, int Reit) {
    super(legemiddel, utskrivende, pasient, Reit);
  }

  public int prisAaBetale() {
    return (legemiddel.HentPris() * 25 / 100);
  }

  public String farge() {
    return farge;
  }

  @Override
  public String hentType() {
    return type;
  }

  @Override
  public int hentId() {
    return id;
  }

  @Override
  public String toString() {
    String s = "\n..........Blaa Resept..........";
    s += "\nDrug name: " + legemiddel.hentNavn();
    s += "\nPrescription by Doctor: " + utskrivende.hentNavn();
    s += "\nPatient ID: " + pasient.hentNavn();
    s += "\nRemaining Possible Prescriptions: " + Reit;
    s += "\nResept color: " + farge();
    s += "\nPris a betale: " + prisAaBetale() + " kr.";
    return s;
  }

}
