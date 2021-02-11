
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class SeekAndDestroy {

    private String RUTAFICHEROS = "";
    private String FICHERO_LISTADO = "testing/fichero_listado.csv";
    private String FICHERO_ORDENADO = "testing/fichero_ordenado.csv";
    private String FICHERO_DUPLICADOS = "testing/fichero_duplicados.csv";
    private File ruta_inicial = null;
    private long contador_ficheros = 0;
    private SimpleDateFormat sdf = null;
    private ArrayList<Archivo> vector = new ArrayList<Archivo>();
    private ArrayList<Archivo> vector_ordenado = new ArrayList<Archivo>();
    private ArrayList<Archivo> vector_duplicados = new ArrayList<Archivo>();
    private ArrayList<Archivo> vector_final = new ArrayList<Archivo>();
    private SortIF<Archivo> algoritmo = null;
    private ComparatorIF<Archivo> comparador_xNombre = null;
    private ComparatorIF<Archivo> comparador_xLongitud = null;
    private boolean orden = true; // true=ascendiente ; false=descenciente

    public static void main(String[] args) {
        SeekAndDestroy fd = new SeekAndDestroy();
        File ruta;
        if (args.length != 0) {
            ruta = new File(args[0]);
        } else {
            ruta = new File("/media/josea/TOSHIBA EXT");
        }
        if (ruta.exists()) {
            fd.setEstadoInicial(ruta);
            fd.procesar();
        }
        System.exit(0);
    }

    void setEstadoInicial(File ruta) {
        this.ruta_inicial = ruta;

        this.algoritmo = new HeapSort<Archivo>();
        this.comparador_xNombre = new ComparatorNombre<Archivo>();
        this.comparador_xLongitud = new ComparatorLongitud<Archivo>();

        this.sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    }

    void procesar() {

        contador_ficheros = 0;
        long millis = System.currentTimeMillis();
        contador_ficheros += 1;
        grabar(FICHERO_LISTADO, Long.toString(millis), contador_ficheros > 1);

/*
 *  Leer ficheros en la ruta solicitada.
 */
        listar_ficheros(ruta_inicial);

/*
 * Ordenar ficheros leidos por nombre.
 */
        ordenar_xNombre();

/*
 * Localiza ficheros duplicados y los separa en un array.
 */
        localizar_duplicados();

/*
 * Ordenar ficheros duplicados por longitud.
 */
        ordenar_xLongitud();

/*
 * Salvar ficheros duplicados en un fichero csv.
 */
        cargar_duplicados();

        millis = System.currentTimeMillis();
        contador_ficheros += 1;
        grabar(FICHERO_LISTADO, Long.toString(millis), contador_ficheros > 1);

    }

    void listar_ficheros(File ruta) {
        File[] archivos = ruta.listFiles();
//      File[] archivos = ruta.listFiles(filtro);
        if (archivos.length > 0) {
            for (int i=0; i < archivos.length; i++) {
                if (archivos[i].isDirectory()) {
                    listar_ficheros(archivos[i]);
                } else {
                    Archivo a = new Archivo(archivos[i].getName(), archivos[i].length(), archivos[i].lastModified(), archivos[i].getAbsolutePath());
                    vector.add(a);
                }
            }
        }
    }

    void ordenar_xNombre() {
        orden = true;
        vector_ordenado = algoritmo.sort(vector, comparador_xNombre, orden);
    }

    void localizar_duplicados() {
        Archivo anterior = new Archivo();
        boolean tmp = false;
		for (Archivo a: vector_ordenado) {
            if (a.equals(anterior)) {
                if (!tmp) {
                    vector_duplicados.add(anterior);
                    tmp = true;
                }
                vector_duplicados.add(a);
            } else {
                tmp = false;
            }
            anterior = a;
		}
    }

    void ordenar_xLongitud() {
        orden = false;
        vector_final = algoritmo.sort(vector_duplicados, comparador_xLongitud, orden);
    }
    
    void cargar_duplicados() {
		for (Archivo a: vector_final) {
            contador_ficheros += 1;
            grabar(FICHERO_DUPLICADOS, a.toString(), contador_ficheros > 1);
		}
    }
    
    private void grabar(String nombre_fichero, String linea, boolean append) {
        BufferedWriter bwFicheros = null;
        try {
            bwFicheros = new BufferedWriter(new FileWriter(RUTAFICHEROS + nombre_fichero, append));
            bwFicheros.write(linea);
            bwFicheros.newLine();
            bwFicheros.flush(); }
        catch (Exception ex) {
            ex.printStackTrace(); 
        } 
        finally { 
            if (bwFicheros != null) 
                try { bwFicheros.close(); } 
                catch (Exception ex2) {	}
        }
    }
    

    FileFilter filtro = new FileFilter() {
        @Override
        public boolean accept(File arch) {
            return arch.isFile();
        }
    };
    
}
