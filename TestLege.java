class TestLege {
  public static void main(String[] args) {
    Lege le = new Spesialist("rick", "dsd");
    Narkotisk leg = new Narkotisk(2, "sd", 43, 3);
    Pasient pas = new Pasient("sick", "1112131");
    try {
      le.skrivBlaaResept(leg, pas, 2);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    System.out.println(le.hentUtReseptListe());

  }
}
