import java.util.ArrayList;

public class InsertSort<T> implements SortIF <T> {
	
	public InsertSort() {
		
	}

	@Override
	public ArrayList<T> sort(ArrayList<T> vector, ComparatorIF<T> comparador, boolean sentido) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ListIF<T> sort (ComparatorIF<T> comparator) {
//		if (this.isEmpty ()) {return this;}
//		ListIF<T> list = new ListDynamic<T>();
//		IteratorIF<T> itp = this.getIterator();
//		while (itp.hasNext()) {
//			list = this.sortInsert(list, itp.getNext(), comparator); 
//		}
//		return list;
//	}
//	private ListIF<T> sortInsert(ListIF<T> lista, T element, ComparatorIF<T> comparator) {
//		if (lista.isEmpty()) {
//			lista.insert(element);
//			return lista;
//		}
//		if (comparator.isLess(element, lista.getFirst())) { 
//			lista.insert(element);
//			return lista;
//		}
//		StackIF<T> pila = new StackDynamic<T>();
//		pila.push(lista.getFirst());
//		lista = lista.getTail();
//		boolean insertado = false;
//		while (!lista.isEmpty() && !insertado) {
//			T s = lista.getFirst();
//			if (comparator.isLess(element, s)) {
////				lista = lista.getTail();
//				lista.insert(element);
//				insertado = true;
//			} else {
//				pila.push(s);
//				lista = lista.getTail();
//			}
//		}
//		if (!insertado) {
//			lista.insert(element);
//		}
//		while (!pila.isEmpty()) {
//			lista.insert(pila.getTop());
//			pila.pop();
//		}
//		return lista;
//	}
//
//	@Override
//	public ListIF<T> sort (ComparatorIF<T> comparator) {
//		if (isEmpty()) return this;
//		else return ((ListDynamic<T>)tail.sort(comparator)).sortInsert(first, comparator);
//	}
//   /**
//    * Inserta un elemento en orden en una lista ordenada.
//    * @param  element El elemento a insertar
//    * @param  comparator El comparador de elementos.
//    * @return la lista ordenada.
//    */
///*
//	private ListIF<T> sortInsert(T element, ComparatorIF<T> comparator) {
//		if (isEmpty()) return this.insert(element);
//		else if (comparator.isLess(element, first)) 
//				 return this.insert(element);
//			 else return ((ListDynamic<T>) tail).sortInsert(element, comparator).insert(first);
//	}
//*/
//
	
	
}
