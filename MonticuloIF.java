public interface MonticuloIF<T> {
	
	public void crearMonticuloVacio();
	public boolean monticuloVacio();
	public void flotar(int i);
	public void hundir(int i);
	public void insertar(T e);
	public T primero();
	public T obtenerCima();
	
}
