
class HvitResept extends Resept {
  final String farge = "hvit";
  final String type = "hvit";

  public HvitResept(Legemiddel legemiddel, Lege utskrivende, Pasient pasient, int Reit) {
    super(legemiddel, utskrivende, pasient, Reit);
  }

  public String farge() {
    return farge;
  }

  public int prisAaBetale() {
    return legemiddel.HentPris();
  }

  @Override
  public int hentId() {
    return id;
  }

  @Override
  public String hentType() {
    return type;
  }

  @Override
  public String toString() {
    String s = "\n..........Hvit Resept..........";
    s += "\nDrug name: " + legemiddel.hentNavn();
    s += "\nPrescription by Doctor: " + utskrivende.hentNavn();
    s += "\nPatient ID: " + pasient.hentNavn();
    s += "\nRemaining Possible Prescriptions: " + Reit;
    s += "\nResept color: " + farge();
    s += "\nPris a betale: " + prisAaBetale() + " kr.";
    return s;
  }
}
