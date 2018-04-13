import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FicheroRaml implements IFicheroRaml {
    private String _path;
    private String _nombreDelFichero;

    private File _file;
    private FileReader _fileReader;
    private BufferedReader _bufferedReader;
    private ArrayList<String> _lineas ;
    private String _linea;
    private Iterator<String> _iterador;

    public FicheroRaml (String path,String fichero){
        asignarPropiedades(path,fichero);
        abrirFichero();
        leerFichero();
    }
    private Iterator<String> getIterador(){
        if (_iterador == null)
            _iterador = getLineas().iterator();
        return _iterador;
    }
    public boolean hayMasRegistros(){
        return getIterador().hasNext();
    }
    public String leerRegistro(){
        return getIterador().next();
    }
    public String getNombreDelFichero(){
        return getFicheroAProcesar();
    }
    public int getNumeroDeRegistros(){
        return _lineas.size();
    }
    private void asignarPropiedades(String path,String nombreDelFichero) {
        _nombreDelFichero = nombreDelFichero;
        _path = path;
    }
    private String getFicheroAProcesar(){
        return _path+_nombreDelFichero;
    }
    private ArrayList<String> getLineas(){
        if (_lineas == null)
            _lineas = new ArrayList<String>();
        return _lineas;
    }
    private void abrirFichero() {
        try {
            intentarAbrifFichero();
        } catch (FileNotFoundException e) {
            procesarErrorAlAbrirFichero(e);
        }
    }
    private void intentarAbrifFichero() throws FileNotFoundException{
        _file = new File(getFicheroAProcesar());
        _fileReader  =new FileReader(_file);
        _bufferedReader = new BufferedReader(_fileReader);
    }
    private void procesarErrorAlAbrirFichero(Exception e){
        System.out.println("Error al abrir el fichero: ["+e.getLocalizedMessage()+"]");
    }
    private void leerFichero(){
        try{
            leerLinea();
            while (hayMasLineas()){
                leerLinea();
                añadirLinea();
            }
        } catch (IOException e){
            procesarErrorAlLerrElDichero(e);
        }
    }
    private void leerLinea() throws IOException {
        _linea = _bufferedReader.readLine();
    }
    private void añadirLinea(){
        getLineas().add(_linea);
    }
    private boolean hayMasLineas(){
        return _linea != null ;
    }
    private void procesarErrorAlLerrElDichero(IOException e){
        System.out.println("Error al leer el fichero ["+e.getLocalizedMessage());
    }
    private void imprimirLineas(){
        for (String each : getLineas()){
            log(each);
        }
    }
    private void log(String mensaje){
        System.out.println("["+mensaje+"]");
    }
}


