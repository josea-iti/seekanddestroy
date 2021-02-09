import java.util.*;

public interface SortIF<T> {

	public ArrayList<T> sort(ArrayList<T> vector, ComparatorIF<T> comparador, boolean sentido);

}
