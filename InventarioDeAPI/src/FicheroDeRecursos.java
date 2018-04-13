import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FicheroDeRecursos {
    private IRecursosApi _recursosApi;
    private String _fichero;

    private FileWriter _fileWriter;
    private PrintWriter _printWriter;

    public FicheroDeRecursos( String fichero){
        asignarPropiedades(fichero);
      //  crearFichero();
       // escribirRecursos();
      //  cerrarFichero();
    }
    private void asignarPropiedades(String fichero) {
        _fichero = fichero;
    }
    public void crearFichero(){
        try {
            intentarCrearFichero();
        } catch (IOException e){
            procesarErrorAlCraerFichero(e);
        }
    }
    private void intentarCrearFichero() throws IOException{
        _fileWriter = new FileWriter(_fichero);
        _printWriter = new PrintWriter(_fileWriter);
    }
    private void procesarErrorAlCraerFichero(IOException e){
        log (e.getLocalizedMessage());
    }
    public void cerrarFichero(){
        try {
            intentarCerrarFichero();
        } catch (IOException e){
            procesarErrorAlCerrarFichero(e);
        }
    }
    private void intentarCerrarFichero() throws IOException {
        if (_fileWriter != null)
            _fileWriter.close();
    }
    private void procesarErrorAlCerrarFichero(IOException e){
        procesarErrorAlCraerFichero(e);
    }
    private void log (Object objeto){
        System.out.println(objeto);
    }

    public void incluirRecursosApi(IRecursosApi recursosApi){
        _recursosApi = recursosApi;
        _printWriter.println("****************************************************************************");
        _printWriter.println(_recursosApi.getNombreDelFichero());
        _printWriter.println("Nº de recursos: "+String.valueOf(_recursosApi.getNumeroDeRecursos()));
        _printWriter.println("****************************************************************************");
        String mensaje = "";
        while (_recursosApi.hayMasRecursos()){
            mensaje = _recursosApi.leerRecurso().toString();
            _printWriter.println(mensaje);
        }
    }
}
