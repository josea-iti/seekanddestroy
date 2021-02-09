/**
 * Clase abstracta para la gestion de los distintos tipos de comparadores.
 * Implementa la interfaz ComparatorIF.
 */
public abstract class ComparatorBase<T> implements ComparatorIF<T> {

    public static int LESS = -1;
    public static int EQUAL = 0;
    public static int GREATER = 1;
	
    public abstract int compare(T e1, T e2);

    public boolean isLess(T e1, T e2) {
      	return compare(e1, e2) == LESS;
	}

    public boolean isEqual(T e1, T e2) {
       	return compare(e1, e2) == EQUAL;
	}
        
    public boolean isGreater(T e1, T e2) {
     	return compare(e1, e2) == GREATER;
    }
}
