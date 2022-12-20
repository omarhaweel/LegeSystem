class Spesialist extends Lege implements Godkjenningsfritak {
  String KontrollID;

  public Spesialist(String navn, String KontrollID) {
    super(navn, KontrollID);
    //this.KontrollID = KontrollID;

  }

  @Override
  public String HentKontrollId() {
    return KontrollID;
  }

  @Override
  public String toString() {
    return super.toString() + "\n" + "KontrollID er :" + KontrollID;

  }
}
