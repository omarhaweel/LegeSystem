import java.util.Iterator;

abstract class LenkeListe<T> implements Liste<T> {

  protected Node start;
  protected int iBruk = 0;

  @Override
  public void leggTil(T x) {
    Node ny = new Node(x);
    if (stoerrelse() == 0) {
      start = ny;
      iBruk++;
    } else {
      Node midl = start;
      for (int i = 0; i < stoerrelse() - 1; i++) {
        midl = midl.neste;
      }
      midl.neste = ny;
      iBruk++;
    }
  }

  public T fjern() throws UgyldigListeindeks {
    if (stoerrelse() == 0) {
      throw new UgyldigListeindeks(iBruk);
    } else {
      Node midl = start;

      start = midl.neste;
      iBruk--;
      return midl.data;
    }
  }

  public T hent() throws UgyldigListeindeks {
    if (stoerrelse() != 0) {
      return start.data;
    } else {
      throw new UgyldigListeindeks(stoerrelse());
    }

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

  public Iterator<T> iterator() {
    return new LenkelisteIterator();
  }

  public class Node {
    protected T data;
    protected Node neste;

    protected Node(T data) {
      this.data = data;
    }
  }

  class LenkelisteIterator implements Iterator<T> {
    private int pos = 0;
    private Node posNode = start;

    public T next() {
      pos++;
      Node tmp = posNode;

      for (int i = 0; i < (pos - 1); i++) { // kan bytes ut med posNode.next()
        tmp = tmp.neste;
      }
      return tmp.data;
    }

    public boolean hasNext() {
      return pos < stoerrelse();
    }

  }
}
