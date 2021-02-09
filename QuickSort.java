import java.util.ArrayList;

public class QuickSort<T> implements SortIF<T> {
	
	private ComparatorIF<T> comparador = null;
	// Creciente = true;
	// Decreciente = false;
	private boolean orden = false;
	
	public QuickSort() {
	}

	@Override
	public ArrayList<T> sort(ArrayList<T> vector, ComparatorIF<T> comparador, boolean orden) {
		
		if (vector.size() < 2) return vector;
		
		this.comparador = comparador;
		this.orden = orden;
		
		quicksort(vector, 0, vector.size());
		
		return vector;
	}
	
	private void quicksort(ArrayList<T> vector, int i, int j) {
		if (j - i > 1) {
			int k = pivote(vector, i, j);
			quicksort(vector, i, k);
			quicksort(vector, k + 1, j);
		}
	}
	
	private int pivote(ArrayList<T> vector, int i, int j) {
		T tmp;
		T p = vector.get(i);
		int k = i;
		int l = j;
		
		if (orden) {
			do {k = k + 1;} while (k < j && comparador.isGreater(p, vector.get(k)));
			do {l = l - 1;} while (comparador.isLess(p, vector.get(l)));
		} else {
			do {k = k + 1;} while (k < j && comparador.isLess(p, vector.get(k)));
			do {l = l - 1;} while (comparador.isGreater(p, vector.get(l)));
		}
		
		while (k < l) {
			tmp = vector.get(k);
			vector.set(k, vector.get(l));
			vector.set(l, tmp);
			if (orden) {
				do {k = k + 1;} while (comparador.isGreater(p, vector.get(k)));
				do {l = l - 1;} while (comparador.isLess(p, vector.get(l)));
			} else {
				do {k = k + 1;} while (comparador.isLess(p, vector.get(k)));
				do {l = l - 1;} while (comparador.isGreater(p, vector.get(l)));
			}
		}
		tmp = vector.get(i);
		vector.set(i, vector.get(l));
		vector.set(l, tmp);
		return l;
	}
}
