class Vanlig extends Legemiddel {

  public Vanlig(int Pris, String navn, double MengdeVirkestoff) {
    super(Pris, navn, MengdeVirkestoff);

  }

  @Override
  public String toString() {
      String s = "\n..........Vanlig legemiddel..........";
      s += "\nID: " + id;
      s += "\nNavn: " + navn;
      s += "\nPris: " + Pris;
      s += "\nVirkestoff: " + MengdeVirkestoff;
      return s;

  }
}
