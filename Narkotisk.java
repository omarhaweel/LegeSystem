public class Narkotisk extends Legemiddel {
  private int Styrke;

  public Narkotisk(int Pris, String navn, double MengdeVirkestoff, int Styrke) {
    super(Pris, navn, MengdeVirkestoff);
    this.Styrke = Styrke;
    super.requiresSpesialst = true;

  }

  public int hentStyrke() {
    return Styrke;
  }

  @Override
  public String toString() {
      String s = "\n..........Narkotisk legemiddel..........";
      s += "\nID: " + hentId();
      s += "\nNavn: " + hentNavn();
      s += "\nPris: " + HentPris();
      s += "\nVirkestoff: " + hentVirkestoff();
      s += "\nStyrke: " + Styrke + " %";
      return s;
  }
}
