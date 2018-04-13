import java.io.File;
import java.util.ArrayList;

public class DirectorioRaml {
    private String _path;
    private ArrayList<String> _ficherosDeLaCarpeta = new ArrayList<String>();
    private File _directorio;

    public DirectorioRaml(String carpeta) {
        asignarPropiedades(carpeta);
        obtenerListaDeFicherosRaml();
    }
    private void asignarPropiedades(String path) {
        _path = path;
    }
    private void obtenerListaDeFicherosRaml() {
        _directorio = new File(_path);
        for (File each : _directorio.listFiles()) {
            if (esRaml(each.getName()))
                _ficherosDeLaCarpeta.add(each.getName());
        }
    }
    private boolean esRaml(String fichero) {
        if (fichero.length() < 4) return false;
        if (fichero.substring(fichero.length() - 4, fichero.length()).equals("raml"))
            return true;
        else
            return false;
    }
    private void imprimirFicherosDeLaCarpeta() {
        for (String each : _ficherosDeLaCarpeta) {
            log(each);
        }
    }
    private void log(Object objeto) {
        System.out.println(objeto);
    }
    public void procesarFicherosRaml() {
        FicheroDeRecursos ficheroDeRecursos = new FicheroDeRecursos(_path +"resultado.txt");
        ficheroDeRecursos.crearFichero();
        for (String each : _ficherosDeLaCarpeta) {
            log("[Procesando....]");
            log (each);
            IFicheroRaml ficheroRaml = new FicheroRaml(_path,each);
            log ("Nº líneas: "+String.valueOf(ficheroRaml.getNumeroDeRegistros()));
            RecursosApi recursosApi = new RecursosApi(ficheroRaml);
            log ("Nº de recursos: "+String.valueOf(recursosApi.getNumeroDeRecursos()));
            ficheroDeRecursos.incluirRecursosApi(recursosApi);
        }
        ficheroDeRecursos.cerrarFichero();
    }
}