
class PResept extends HvitResept {
  final String type = "p";

  public PResept(Legemiddel legemiddel, Lege utskrivende, Pasient pasient, int Reit) {
    super(legemiddel, utskrivende, pasient, Reit);
  }

  public int prisAaBetale() {
    return (legemiddel.HentPris() - 108);
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
    String s = "\n..........P Resept..........";
    s += "\nDrug name: " + legemiddel.hentNavn();
    s += "\nPrescription by Doctor: " + utskrivende.hentNavn();
    s += "\nPatient ID: " + pasient.hentNavn();
    s += "\nRemaining Possible Prescriptions: " + Reit;
    s += "\nResept color: " + farge();
    s += "\nPris a betale: " + prisAaBetale() + " kr.";
    return s;
  }
}
