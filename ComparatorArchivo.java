
public class ComparatorArchivo<T> extends ComparatorBase<T> {

	public ComparatorArchivo() {
		super ();
	}

	/**
	 * Compara dos elementos para indicar si el primero es menor, igual o mayor que el segundo elemento.
	 * @param e1 el primer  elemento.
	 * @param e2 el segundo elemento.
	 * @return el orden de los elementos. 
	 */
	@Override
	public int compare (T a1, T a2) {
		Archivo archivo1 = (Archivo) a1;
		Archivo archivo2 = (Archivo) a2;
		if (archivo1.getNombre().equals(archivo2.getNombre())) { 
			return ComparatorIF.EQUAL; 
		} else {
			int result = archivo1.getNombre().compareTo(archivo2.getNombre());
			if (result > 0) return ComparatorIF.GREATER;
			if (result < 0) return ComparatorIF.LESS;
			return 0;
		} 
	}
}
