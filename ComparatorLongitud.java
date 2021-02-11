
public class ComparatorLongitud<T> extends ComparatorBase<T> {

	public ComparatorLongitud() {
		super ();
	}

	/**
	 * Compara dos elementos para indicar si el primero es menor, igual o mayor que el segundo elemento.
	 * @param e1 el primer  elemento.
	 * @param e2 el segundo elemento.
	 * @return el orden de los elementos. 
	 */
	@Override
	public int compare (T e1, T e2) {
		Archivo archivo1 = (Archivo) e1;
		Archivo archivo2 = (Archivo) e2;
		long long1 = archivo1.getLongitud();
		long long2 = archivo2.getLongitud();
		if (long1 == long2) return ComparatorIF.EQUAL;
		if (long1 > long2) return ComparatorIF.GREATER;
		if (long1 < long2) return ComparatorIF.LESS;
		return 0;
	}
}
