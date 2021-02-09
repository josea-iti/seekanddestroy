import java.util.ArrayList;

public class MergeSort<T> implements SortIF<T> {
	
	private ComparatorIF<T> comparador = null;
	
	public MergeSort() {
		comparador = null;
	}
	
	@Override
	public ArrayList<T> sort(ArrayList<T> vector, ComparatorIF<T> comparador, boolean sentido) {
		this.comparador = comparador;
		ArrayList<T> vectorOrdenado = new ArrayList<T>();
		if (!sentido) {
			vectorOrdenado = mergeSortAscendente(vector, vectorOrdenado, 0, vector.size() - 1);
		} else {
			vectorOrdenado = mergeSortDescendente(vector, vectorOrdenado, 0, vector.size() - 1);
		}
		return vectorOrdenado;
	}

	private ArrayList<T> mergeSortAscendente(ArrayList<T> vector, ArrayList<T> vt, int izq, int der) {
		
		if (izq < der) {
			int centro = (izq + der) / 2;
			mergeSortAscendente(vector, vt, izq, centro);
			mergeSortAscendente(vector, vt, centro + 1, der);
			mezclar(vector, vt, izq, centro + 1, der);
		}
		return vector;
		
	}
		
	private ArrayList<T> mergeSortDescendente(ArrayList<T> vector, ArrayList<T> vt, int izq, int der) {
		
		if (izq < der) {
			int centro = (izq + der) / 2;
			mergeSortDescendente(vector, vt, izq, centro);
			mergeSortDescendente(vector, vt, centro + 1, der);
			mezclar(vector, vt, izq, centro + 1, der);
		}
		return vector;
		
	}
		
	private void mezclar(ArrayList<T> vector, ArrayList<T> vt, int izq, int der, int fin) {
		
		int finIzq = der - 1;
		int posAux = izq;
		int numElem = fin - izq + 1;
		
		while (izq <= finIzq && der <= fin) {
			if (comparador.isLess(vector.get(izq), vector.get(der))) {
				vt.add(posAux++, vector.get(izq++));			
			} else {
				vt.add(posAux++, vector.get(der++));
			}
		}
		while (izq <= finIzq) {
			vt.add(posAux++, vector.get(izq++));
		}
		while (der <= fin) {
			vt.add(posAux++, vector.get(der++));
		}
		
		for (int i = 0; i < numElem; i++, fin--) {
			vector.set(fin, vt.get(fin));
		}
		
	}

//		ArrayList<T> v = new ArrayList<T>();
//		if (vector.size() < 2) {
//			return vector;
//		} else if (vector.size() == 2) {
//			if (this.comparador.isLess(this.vector.get(0), this.vector.get(1))) {
//				v.add(0, vector.get(0));
//				v.add(1, vector.get(1));
//			} else {
//				v.add(0, vector.get(1));
//				v.add(1, vector.get(0));
//		 	}
//			return v;
//		}
//		int middle = vector.size() / 2;
//		int index = 0;
//		ArrayList<T> U = new ArrayList<T>();
//		ArrayList<T> V = new ArrayList<T>();
//		for (T element: vector) {
//			if (index < middle) U.add(element);
//			if (index >= middle) V.add(element);
//			index = index + 1;
//		}
//		U = mergeSortAscendente(U);
//		V = mergeSortAscendente(V);
//		return fusionar(U, V, v);
//	}
//	
//	private ArrayList<T> fusionar(ArrayList<T> U, ArrayList<T> V) {
//		int i, j;
//		i = j = 0;
//		
//	}
//	@Override
//	public ArrayList<T> sort2(ArrayList<T> vector, ComparatorIF<T> comparator, boolean sentido) {
//		if (vector.size() <= 1) return vector;
//	}
//
//	private ArrayList<T> sortMerge(ArrayList<T> lLeft, ArrayList<T> lRight, ComparatorIF<T> comparator) {
//		ArrayList<T> result = new ArrayList<T>();
//		while (lLeft.size() > 0 || lRight.size() > 0) {
//			if (lLeft.size() > 0 && lRight.size() > 0) {
//				T eLeft = lLeft.get(0);
//				T eRight = lRight.get(0);
//				if (comparator.isGreater(eLeft, eRight)) {
//					result.add(eLeft);
//					lLeft = lLeft.getTail();
//				} else {
//					result.add(eRight);
//					lRight = lRight.getTail();
//				}
//			} else if (lLeft.size() > 0) {
//				T eLeft = lLeft.get(0);
//				result.add(eLeft);
//				lLeft = lLeft.getTail();
//			} else if (lRight.size() > 0) {
//				T eRight = lRight.get(0);
//				result.add(eRight);
//				lRight = lRight.getTail();
//			}
//		}
//		return result;
//	}
//	
//	
//	
//	fun mergesort (t:vector [1..n] de entero):vector [1...n] de entero
//	    var 
//	     U :vector [1...n] de entero
//	     V : vector [1..n] de entero
//	    fvar
//	    
//	    si trivial(n) entonces Insertar(T[i..n])
//	    sino
//	    	U[1...1 + (n/2)] <--  T[1.. [n/2]]
//	    	V[1...1 + (n/2)] <--  T[1 + [n/2] .. n]
//	    	Mergesort(U)
//	    	Mergesort(V)
//	    	Fusionar(U,V,T)
//	    fsi
//	  ffun
//	  
//	  fun Fusionar(U:vector [1..n+1] de entero, V:vector [1..m+1] de entero, T:vector [1..m+n] de entero)
//		var
//		  i,j: natural
//		fvar
//		
//		i,j <-- 1
//		U[m+1], V[n+1] <-- INFINITO
//		
//		para k <-- 1 hasta m+n hacer
//		    si U[i] < V[j] entonces
//		         T[k] <-- U[i]
//		         i <-- i + 1
//		    sino
//		         T[k] <-- V[j]
//		        j <-- j + 1
//		    fsi
//		 fpara
//      ffun
//      
//      
//      Quicksort
//      
//      fun Quicksort(T[i..j])
//          var 
//            l:natural
//          fvar
//          si trivial(j - i) entonces Insertar(T[i...j])
//          sino
//             Pivotar(T[i...j], l)
//             Quicksort(T[i ... l -1])
//             Quicksort(T[l + 1 .. j])
//          fsi
//       ffun
//       
//       fun Pivotar (T:vector[i..j] de entero, pivote: natural)
//       
//          var 
//             p, k: entero
//          fvar
//          
//          p <-- T[i]
//          k <-- i;
//	      l <-- j + 1
//	      repetir k <-- k + 1 hasta T[k] > p || k >= j
//	      repetir l <-- l - 1 hasta T[l] <= p
//	      mientras k < l hacer
//	           intercambiar[T, k, 1]
//	           repetir k <-- k + 1 hasta T[k] > p
//	           repetir l <-- l - 1 hasta T[l] <= p
//	      fmientras
//	      intercambiar(T, i, 1)
//	      pivote <-- 1
//	    ffun
//       
       
}
