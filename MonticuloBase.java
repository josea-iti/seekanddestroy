import java.util.*;

public abstract class MonticuloBase<T> implements MonticuloIF<T> {

    protected ArrayList<T> vector;
    protected int MAX;
    protected int c;
	protected ComparatorIF<T> comparador;
	
    public MonticuloBase() {
        crearMonticuloVacio();
        this.comparador = null;
    }

    public MonticuloBase(int maximo, ComparatorIF<T> comparador) {
    	this();
		try {
			if (maximo < 1) {
				throw new IndexOutOfBoundsException();
			}
			if (comparador == null) {
				throw new NullPointerException();
			}
	    	this.MAX = maximo;
	        this.vector = new ArrayList<T>(this.MAX);
			for (int i = 0; i < this.MAX; i++) {
				this.vector.add(i, null);
			}
			this.comparador = comparador;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Tamanyo del monticulo incorrecto.");
		} catch (NullPointerException e) {
			System.out.println("Falta criterio de comparacion.");
		}
    }
    
    public MonticuloBase(ArrayList<T> vectorAOrdenar, ComparatorIF<T> comparador) {
    	this();
		try {
			if (vectorAOrdenar == null || vectorAOrdenar.size() < 1 || comparador == null) {
				throw new NullPointerException();
			}
	    	this.vector = vectorAOrdenar;
	    	this.MAX = this.c = this.vector.size();
			this.comparador = comparador;
	    	ordenarVector();
		} catch (NullPointerException e) {
			System.out.println("Falta vector o criterio de comparacion.");
		}
    }

    @Override
	public void crearMonticuloVacio() {
        this.vector = null;
        this.c = 0;
        this.MAX = 0;
	}

	@Override
	public boolean monticuloVacio() {
		return this.c == 0;
	}
	
	@Override
	public T primero() {
		if (this.c > 0) {
			return this.vector.get(0);
		}
		return null;
	}

	@Override
	public T obtenerCima() {
		if (this.c > 0) {
			T e = this.vector.get(0);
			this.vector.set(0, this.vector.get(this.c - 1));
			this.c--;
			hundir(0);
			return e;
		}
		return null;
	}
	
	@Override
    public abstract void flotar(int i);
	
	@Override
    public abstract void hundir(int i);
	
	@Override
	public void insertar(T element) {
		try {
//			if (this.c == this.MAX) {
//				throw new IndexOutOfBoundsException();
//			}
			this.c++;
			this.vector.add(element);
			flotar(this.c - 1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Tamanyo del monticulo incorrecto.");
		}
	}
    
	protected void intercambiar(int a, int b) {
		T p = vector.get(a);
		vector.set(a, vector.get(b));
		vector.set(b, p);
	}

	private void ordenarVector() {
    	for (int i = ((this.c / 2) - 1); i > -1; i--) {
    		hundir(i);
    	}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (T elem: this.vector) {
			sb.append("[");
			sb.append(elem.toString());
			sb.append("]");
		}
		return sb.toString();
	}
}
