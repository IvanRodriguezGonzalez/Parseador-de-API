import java.util.ArrayList;

public class Registro implements IRegistro{
    private String _registroDeTrabajo;
    private String _registroOriginal;
    private String _separadorDeCampos;
    private ArrayList<String> _camposDelRegistro;

    protected Registro(String registro, String separadorDeCampos){
        inicializarRegistro(registro,separadorDeCampos);
        extraerCamposDelRegistro();
    }

    private void inicializarRegistro(String registro, String separadorDeCampos){
        _registroDeTrabajo = registro;
        _registroOriginal = registro;
        _separadorDeCampos = separadorDeCampos;
    }

    private void extraerCamposDelRegistro(){
        eliminarElPrimerCaracterSiEsElSeparador();
        _camposDelRegistro = new ArrayList<String>();
        while (elRegistroTieneMasCampos()) {
            _camposDelRegistro.add(extraerPrimercampoDelRegistro());
        }
    }
    private void eliminarElPrimerCaracterSiEsElSeparador(){
        if (_registroDeTrabajo == null) return;
        if (_registroDeTrabajo.indexOf(_separadorDeCampos)== 0)
           _registroDeTrabajo =  _registroDeTrabajo.substring(1, _registroDeTrabajo.length());
    }
    private String extraerPrimercampoDelRegistro(){
        String result="";
        int posicionFinalDelPrimerCampo = _registroDeTrabajo.indexOf(_separadorDeCampos);
        if (_registroDeTrabajo.length() ==0) return "";
           if (posicionFinalDelPrimerCampo == -1) {
            result = _registroDeTrabajo;
            _registroDeTrabajo = "";
        } else{
            result = _registroDeTrabajo.substring(0, posicionFinalDelPrimerCampo);
            _registroDeTrabajo = _registroDeTrabajo.substring(posicionFinalDelPrimerCampo + 1, _registroDeTrabajo.length());
        }
        return result;
    }
    private boolean elRegistroTieneMasCampos(){
        if (_registroDeTrabajo == null) return false;
        if (_registroDeTrabajo.length() > 0 )
            return true;
        else
            return false;
    }
    public String getCampo(int numeroDeCampo) {
        if (numeroDeCampo > _camposDelRegistro.size()-1)
            return "Sin valor";
        else
            return _camposDelRegistro.get(numeroDeCampo);
    }
    public String getRegistro(){
        return _registroOriginal;
    }
    public String toString(){
        String result = "";
        for (String each:_camposDelRegistro){
            result +="["+each+"]";
        }
        return result;
    }
}