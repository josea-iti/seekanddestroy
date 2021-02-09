import java.util.*;

public class HeapSort<T> implements SortIF<T> {
	
	private MonticuloIF<T> monticulo = null;

	public HeapSort() {
		monticulo = null;
	}

	@Override
	public ArrayList<T> sort(ArrayList<T> vector, ComparatorIF<T> comparador, boolean orden) {
		if (vector.size() < 2) return vector;
		monticulo = null;
		if (orden) {
			monticulo = new MonticuloMinimos<T>(vector, comparador);
		} else {
			monticulo = new MonticuloMaximos<T>(vector, comparador);
		}
		return ordenarVector();
	}

	private ArrayList<T> ordenarVector() {
		ArrayList<T> v = new ArrayList<T>();;
		while (!monticulo.monticuloVacio()) {
			v.add(monticulo.obtenerCima());
		}
		return v;
	}

}
