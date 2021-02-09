import java.util.ArrayList;

public class MonticuloMaximos<T> extends MonticuloBase<T> {

	public MonticuloMaximos() {
		super();
	}

	public MonticuloMaximos(ArrayList<T> vector, ComparatorIF<T> comparador) {
		super(vector, comparador);
	}
	
	@Override
	public void hundir(int i) {
		int hi = 0;
		int hd = 0;
		int p = 0;
		do {
			hi = (2 * i) + 1;
			hd = (2 * i) + 2;
			p = i;
			if (hd <= (this.c - 1) && this.comparador.isGreater(this.vector.get(hd), this.vector.get(i))) {
				i = hd;
			}
			if (hi <= (this.c - 1) && this.comparador.isGreater(this.vector.get(hi), this.vector.get(i))) {
				i = hi;
			}
			if (p != i) intercambiar(p, i);
		} while (p != i);
	}

	@Override
	public void flotar(int i) {
		int padre = i / 2;
		if (i % 2 == 0) {padre--;}
		while (i > 0 && this.comparador.isLess(this.vector.get(padre), this.vector.get(i))) {
			intercambiar(i, padre);
			i = padre;
			padre = i / 2;
			if (i % 2 == 0) {padre--;}
		}
	}

}
