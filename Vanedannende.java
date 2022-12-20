
class Vanedannende extends Legemiddel {
  private int VanedannendeStyrke;

  public Vanedannende(int Pris, String navn, double MengdeVirkestoff, int VanedannendeStyrke) {
    super(Pris, navn, MengdeVirkestoff);

    this.VanedannendeStyrke = VanedannendeStyrke;

  }

  @Override
  public int hentStyrke() {
    return VanedannendeStyrke;// + "\n" + "Vanedannende styrke er" + VanedannendeStyrke;
  }

  @Override
    public String toString() {
        String s = "\n..........Vanedannende legemiddel..........";
        s += "\nID: " + hentId();
        s += "\nNavn: " + hentNavn();
        s += "\nPris: " + HentPris();
        s += "\nVirkestoff: " + hentVirkestoff();
        s += "\nStyrke: " + VanedannendeStyrke + " %";
        return s;
    }
}
