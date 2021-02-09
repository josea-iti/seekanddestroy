import java.text.SimpleDateFormat;

public class Archivo {

	private String nombre;
	private long longitud;
    private long fecha;
    private String ruta;
	
	public Archivo () {
		this.nombre = "";
		this.longitud = 0;
		this.fecha = 0;
		this.ruta = "";
	}

	public Archivo (String nombre, long longitud, long fecha, String ruta) {
		this();
		this.nombre = nombre;
		this.longitud = longitud;
        this.fecha = fecha;
        this.ruta = ruta;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public int hashCode() {
		return (this.ruta + this.nombre).hashCode();
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (o != null && o instanceof Archivo) {
			Archivo q = (Archivo)o;
			if (this.nombre.equals(q.nombre)) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public String toString() {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return this.nombre + ";" + 
               this.longitud + ";" + 
               sd.format(this.fecha) + ";" +
               this.ruta + ";" +
               this.hashCode();
	}
}
