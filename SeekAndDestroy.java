
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class SeekAndDestroy {

    private String RUTAFICHEROS = "";
    private String FICHERO_LISTADO = "fichero_listado.csv";
    private String FICHERO_ORDENADO = "fichero_ordenado.csv";
    private String FICHERO_DUPLICADOS = "fichero_duplicados.csv";
    private File ruta_inicial = null;
    private long contador_ficheros = 0;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private ArrayList<Archivo> vector = new ArrayList<Archivo>();
    private ArrayList<Archivo> vector_ordenado = new ArrayList<Archivo>();
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
            fd.setRutaInicial(ruta);
            fd.procesar();
        }
        System.exit(0);
    }

    void setRutaInicial(File ruta) {
        this.ruta_inicial = ruta;
    }

    void procesar() {
        contador_ficheros = 0;
        listar_ficheros(ruta_inicial);
        ordenar_ficheros();
        cargar_duplicados();
    }

    void listar_ficheros(File ruta) {
        long millis = System.currentTimeMillis();
        contador_ficheros += 1;
        grabar(FICHERO_LISTADO, Long.toString(millis), contador_ficheros > 1);

        File[] archivos = ruta.listFiles();
//      File[] archivos = ruta.listFiles(filtro);
        if (archivos.length > 0) {
            for (int i=0; i < archivos.length; i++) {
                if (archivos[i].isDirectory()) {
                    listar_ficheros(archivos[i]);
                } else {
//                    String linea = archivos[i].getName() + ";" + 
//                                   archivos[i].length() + ";" + 
//                                   sdf.format(archivos[i].lastModified()) + ";" +
//                                   archivos[i].getAbsolutePath();
//                    contador_ficheros += 1;
//                    grabar(FICHERO_LISTADO, linea, contador_ficheros > 1);
                    Archivo a = new Archivo(archivos[i].getName(), archivos[i].length(), archivos[i].lastModified(), archivos[i].getAbsolutePath());
                    vector.add(a);
                }
            }
        }
    }

    void ordenar_ficheros() {

        long millis = System.currentTimeMillis();
        contador_ficheros += 1;
        grabar(FICHERO_LISTADO, Long.toString(millis), contador_ficheros > 1);



        ComparatorIF<Archivo> comparador = new ComparatorArchivo<Archivo>();
        orden = true;
        SortIF<Archivo> algoritmo = new HeapSort<Archivo>();
        vector_ordenado = algoritmo.sort(vector, comparador, orden);

        millis = System.currentTimeMillis();
        contador_ficheros += 1;
        grabar(FICHERO_LISTADO, Long.toString(millis), contador_ficheros > 1);


//        contador_ficheros = 0;
//		for (Archivo a: vector_ordenado) {
//            contador_ficheros += 1;
//            grabar(FICHERO_ORDENADO, a.toString(), contador_ficheros > 1);
//		}
    }

    void cargar_duplicados() {

        Archivo anterior = new Archivo();

        contador_ficheros = 0;
		for (Archivo a: vector_ordenado) {
            if (a.equals(anterior)) {
                contador_ficheros += 1;
                grabar(FICHERO_DUPLICADOS, anterior.toString(), contador_ficheros > 1);
                contador_ficheros += 1;
                grabar(FICHERO_DUPLICADOS, a.toString(), contador_ficheros > 1);
            }
            anterior = a;
		}

        long millis = System.currentTimeMillis();
        contador_ficheros += 1;
        grabar(FICHERO_DUPLICADOS, Long.toString(millis), contador_ficheros > 1);
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
