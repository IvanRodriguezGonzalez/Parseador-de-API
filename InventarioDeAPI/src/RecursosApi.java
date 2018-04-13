import java.util.ArrayList;
import java.util.Iterator;

public class RecursosApi implements IRecursosApi {
	private IFicheroRaml _fichero ;
	private IRegistro _registro;
	private ArrayList<IRegistro> _recursos;
	private Iterator<IRegistro> _iteratorDeRecursos;

	public RecursosApi(IFicheroRaml fichero){
		asignarPropiedades(fichero);
		procesarFichero();
	}
	private void  asignarPropiedades(IFicheroRaml fichero){
		_fichero = fichero;
	}
	private void procesarFichero(){
		while (hayMasRegistros()){
			leerRegistro();
			añadirRecurso();
		}
	}
	private boolean hayMasRegistros(){
		return _fichero.hayMasRegistros();
	}
	private void leerRegistro(){
		_registro =  new Registro(_fichero.leerRegistro(),"/");
	}
	private void añadirRecurso(){
		if (esUnRecurso()){
			getRecursos().add(_registro);
		}
	}
	private boolean esUnRecurso(){
		if (_registro.getRegistro() == null) return false;
		if (_registro.getRegistro().trim().length() <1) return false;
		if (_registro.getRegistro().substring(0,1).equals("/"))
			return true;
		else
			return false;
	}
	private ArrayList<IRegistro> getRecursos(){
		if (_recursos == null)
			_recursos = new ArrayList<IRegistro>();
		return _recursos;
	}

	public boolean hayMasRecursos(){
	    return getIteratorDeRecursos().hasNext();
    }
	public IRegistro leerRecurso(){
	    return getIteratorDeRecursos().next();
    }
    public String getNombreDelFichero(){
		return _fichero.getNombreDelFichero();
	}
	public int getNumeroDeRecursos(){
    	return getRecursos().size();
    }

	private Iterator<IRegistro> getIteratorDeRecursos(){
		if (_iteratorDeRecursos == null)
			_iteratorDeRecursos = getRecursos().iterator();
		return _iteratorDeRecursos;
	}
	private void imprimirRegistros(){
		for (IRegistro each:getRecursos()){
			log(each);
		}
	}
	private void log(Object objeto){
		System.out.println(objeto);
	}
}
