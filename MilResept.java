
class MilResept extends HvitResept {
  final String type = "militaer";

  public MilResept(Legemiddel legemiddel, Lege utskrivende, Pasient pasient) {
    super(legemiddel, utskrivende, pasient, 3);
  }

  public int prisAaBetale() {
    return 0;
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
    String s = "\n..........Militaer Resept..........";
    s += "\nDrug name: " + legemiddel.hentNavn();
    s += "\nPrescription by Doctor: " + utskrivende.hentNavn();
    s += "\nPatient ID: " + pasient.hentNavn();
    s += "\nRemaining Possible Prescriptions: " + Reit;
    s += "\nResept color: " + farge();
    s += "\nPris a betale: " + prisAaBetale() + " kr.";
    return s;
  }

}
