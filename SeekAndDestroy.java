import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class SeekAndDestroy {

    private String RUTAFICHEROS = "";
    private String FICHERO_LISTADO = "fichero_listado.csv";
    private String FICHERO_ORDENADO = "fichero_ordenado.csv";
    private String FICHERO_LIMPIO = "fichero_limpio.csv";
    private File ruta_inicial = null;
    private long contador_ficheros = 0;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private ComparatorIF<String> comparador = new ComparatorInteger<String>();
    private ArrayList<String> vector = new ArrayList<String>();
    private boolean orden = true;     //true=ascendiente ; false=descenciente

    public static void main(String[] args) {
        SeekAndDestroy fd = new SeekAndDestroy();
        if (args.length != 0) {
            File ruta = new File(args[0]);
            if (ruta.exists()) {
                fd.setRutaInicial(ruta);
                fd.procesar();
            }
        }
		System.exit(0);
    }
    
    void setRutaInicial(File ruta) {
        this.ruta_inicial = ruta;
    }

    void procesar() {
        listar_ficheros(ruta_inicial);
        ordenar_ficheros();
        quitar_NO_repetidos();
        quitar_repetidos();
    }

    void listar_ficheros(File ruta) {
        File[] archivos = ruta.listFiles();
//      File[] archivos = ruta.listFiles(filtro);
        if (archivos.length > 0) {
            for (int i=0; i < archivos.length; i++) {
                if (archivos[i].isDirectory()) {
                    listar_ficheros(archivos[i]);
                } else {
                    String linea = archivos[i].getName() + ";" + 
                                   archivos[i].length() + ";" + 
                                   sdf.format(archivos[i].lastModified()) + ";" +
                                   archivos[i].getAbsolutePath();
                    contador_ficheros += 1;
                    grabar(FICHERO_LISTADO, linea, contador_ficheros > 1);
                    vector.add(linea);
                }
            }
        }
    }

    void ordenar_ficheros() {

        /*   TO  DO   */
 
        /*
        SortIF<String> algoritmo = new HeapSort<String>();
        ArrayList<String> vectorOrdenado = algoritmo.sort(vector, comparador, orden);
        contador_ficheros = 0;
		for (String s: vectorOrdenado) {
            contador_ficheros += 1;
            grabar(FICHERO_ORDENADO, s, contador_ficheros > 1);
		}
        */

    }

    void quitar_NO_repetidos() {

        /*   TO  DO   */
 
    }
    
    void quitar_repetidos() {

        /*   TO  DO   */
 
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
