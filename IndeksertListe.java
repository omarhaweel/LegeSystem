public class IndeksertListe<T> extends LenkeListe<T> {

  public void leggTil(int pos, T x) {
    Node node = new Node(x);
    if (pos < 0 || pos > iBruk) {
      throw new UgyldigListeindeks(pos);

    }

    if (iBruk == 0 && pos == 0) {
      start = node;

    } else if (iBruk > 0 && pos == 0) {
      node.neste = start;
      start = node;

    }

    else {
      Node midl = start;
      for (int i = 0; i < pos - 1; i++) {
        midl = midl.neste;

      }
      node.neste = midl.neste;
      midl.neste = node;
    }
    iBruk++;

  }

  public void sett(int pos, T x) {
    Node node = new Node(x);
    Node peker = start;

    if (pos < 0 || pos >= iBruk) {
      throw new UgyldigListeindeks(pos);
    } else if (pos == 0) {
      start.data = node.data;
    } else {
      for (int i = 0; i < pos; i++) {
        peker = peker.neste;
      }
      peker.data = node.data;

    }
  }

  public T hent(int pos) {
    Node peker = start;

    if (pos < 0 || pos > iBruk) {
      throw new UgyldigListeindeks(pos);
    } else if (pos == 0) {
      return start.data;
    } else {
      for (int i = 0; i < pos; i++) {
        peker = peker.neste;
      }
      return peker.data;
    }
  }

  public T fjern(int pos) {
    Node peker = start;
    T svar;

    if (pos < 0 || pos >= iBruk) {
      throw new UgyldigListeindeks(pos);
    }

    else if (pos == 0) {
      svar = start.data;
      start = start.neste;

    } else {
      for (int i = 0; i < pos - 1; i++) {
        peker = peker.neste;
      }
      svar = peker.neste.data;
      peker.neste = peker.neste.neste;
    }
    iBruk--;
    return svar;

  }

  public int stoerrelse() {
    return iBruk;
  }

  public String toString() {
    String str = " ";
    Node node = start;
    for (int i = 0; i < this.stoerrelse(); i++) {
      str += node.data + "\n ";
      node = node.neste;
    }
    return str;
  }

}
