public class Prioritetskoe <T extends Comparable<T>> extends LenkeListe<T> {
    @Override
    public void leggTil(T x) {
        Node tmp = start;

        if (stoerrelse() == 0) {
            Node ny = new Node(x);
            start = ny;
            iBruk++;
            return;
        } else {

            Node ny = new Node(x);
            if ((start.data.compareTo(x)) < 0) {
                ny.neste = start;
                start = ny;
                iBruk++;
            } else if ((start.data.compareTo(x)) > 0) {
                while (tmp.neste != null && (tmp.neste.data.compareTo(x) > 0)) {
                    tmp = tmp.neste;
                }
                ny.neste = tmp.neste;
                tmp.neste = ny;
                iBruk++;
            } else {
                while (tmp.neste != null) {
                    tmp = tmp.neste;

                }
                ny.neste = tmp.neste;
                tmp.neste = ny;
                iBruk++;
            }
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

    @Override
    public T hent() throws UgyldigListeindeks {
        if (stoerrelse() != 0) {
            return start.data;
        } else {
            throw new UgyldigListeindeks(stoerrelse());
        }

    }

    @Override
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

}

