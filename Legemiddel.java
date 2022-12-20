abstract class Legemiddel {
  protected Boolean requiresSpesialst = false;
  protected int Pris;
  protected String navn;
  protected double MengdeVirkestoff;
  private static int count = 1;
  protected int id = 0;

  public Legemiddel(int Pris, String navn, double MengdeVirkestoff) {
    this.Pris = Pris;
    this.navn = navn;
    this.MengdeVirkestoff = MengdeVirkestoff;
    id = count++;

  }

  public String hentNavn() {
    return navn;
  }

  @Override
    public String toString() {
        String s = "\n..........Legemiddel..........";
        s += "\nID: " + id;
        s += "\nNavn: " + navn;
        s += "\nPris: " + Pris;
        s += "\nVirkestoff: " + MengdeVirkestoff;
        return s;
    }

  public int HentPris() {
    return Pris;
  }

  public double hentVirkestoff() {
    return MengdeVirkestoff;
  }

  public int hentId() {
    return id;
  }

  public void settNyPris(int nyPris) {
    Pris = nyPris;
  }

  public int hentStyrke() {
    return 0;
  }
}
