package Principal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;


public class RecursosApiTest {
	private FicheroRamlMock _ficheroRamlDeCLientes;
	private FicheroRamlMock _ficheroRamlDeCuentas;
	private FicheroRamlMock _ficheroRamlDePrestamos;
	private FicheroRamlMock _ficheroRamlNull;

	private RecursosApi _recursosApiDeClientes;
	private RecursosApi _recursosApiDeCuentas;
	private RecursosApi _recursosApiDePrestamos;
	private RecursosApi _recursosApiDeNull;


	@Before
	public void setUp() {
		_ficheroRamlDeCLientes = new FicheroRamlMock("Clientes");
		_ficheroRamlDeCLientes.añadirLinea("/clientes");
		_ficheroRamlDeCLientes.añadirLinea("/clientes/{clienteId}");
		_ficheroRamlDeCLientes.añadirLinea("esta linea no es un recurso");
		_ficheroRamlDeCLientes.añadirLinea("/clientes/{clienteId}/detalle");
		_ficheroRamlDeCLientes.añadirLinea("esta linea no es un recurso");
		_ficheroRamlDeCLientes.añadirLinea(null);
		_ficheroRamlDeCLientes.añadirLinea("/");
		_ficheroRamlDeCLientes.añadirLinea("S");



		_ficheroRamlDeCuentas = new FicheroRamlMock("Cuentas");
		_ficheroRamlDeCuentas.añadirLinea("/Cuentas");
		_ficheroRamlDePrestamos = new FicheroRamlMock("Prestamos");
		_ficheroRamlNull = new FicheroRamlMock (null);


		_recursosApiDeClientes = new RecursosApi(_ficheroRamlDeCLientes);
		_recursosApiDeCuentas = new RecursosApi(_ficheroRamlDeCuentas);
		_recursosApiDePrestamos = new RecursosApi(_ficheroRamlDePrestamos);
		_recursosApiDeNull = new RecursosApi (_ficheroRamlNull);

	}

	@After
	public void tearDown() {
	}

	@Test
	public void hayMasRecursosTest() {
		assertTrue(_recursosApiDeClientes.hayMasRecursos());
	}
	@Test
	public void hayMasRecursosTestDos() {
		assertFalse(_recursosApiDePrestamos.hayMasRecursos());
	}
	@Test
	public void hayMasRecursosTestTres() {
		_recursosApiDeClientes.leerRecurso();
		_recursosApiDeClientes.leerRecurso();
		_recursosApiDeClientes.leerRecurso();
		_recursosApiDeClientes.leerRecurso();
		assertFalse(_recursosApiDeClientes.hayMasRecursos());
	}
	@Test
	public void leerRecurso() {
		assertEquals(new Registro("/clientes","/"), _recursosApiDeClientes.leerRecurso());
	}
	@Test
	public void leerRecursoDos() {
		_recursosApiDeClientes.leerRecurso();
		assertEquals(new Registro("/clientes/{clienteId}","/"), _recursosApiDeClientes.leerRecurso());
	}
	@Test
	public void leerRecursoTres() {
		_recursosApiDeClientes.leerRecurso();
		_recursosApiDeClientes.leerRecurso();
		_recursosApiDeClientes.leerRecurso();
		_recursosApiDeClientes.leerRecurso();
		assertNull(_recursosApiDeClientes.leerRecurso());
	}
	@Test
	public void leerRecursoCuatro() {
		assertNull(_recursosApiDePrestamos.leerRecurso());
	}

	@Test
	public void getNombreDelFicheroTest() {
		assertEquals("Cuentas", _recursosApiDeCuentas.getNombreDelFichero());
	}
	@Test
	public void getNombreDelFicheroTestDos() {
		assertEquals("Clientes", _recursosApiDeClientes.getNombreDelFichero());
	}
	@Test
	public void getNumeroDeRecursosTest() {
		assertEquals(4,_recursosApiDeClientes.getNumeroDeRecursos());
	}
	@Test
	public void getNumeroDeRecursosTestDos() {
		assertEquals(1,_recursosApiDeCuentas.getNumeroDeRecursos());
	}
	@Test
	public void getNumeroDeRecursosTestTres() {
		assertEquals(0,_recursosApiDePrestamos.getNumeroDeRecursos());
	}
	@Test
	public void getNumeroDeRecursosTestCuatro(){
		assertNull(_recursosApiDeNull.getNombreDelFichero());
	}

}

class FicheroRamlMock implements IFicheroRaml {
	private ArrayList<String> _lineas;
	private Iterator<String> _iterador;
	private String _nombre;

	protected  FicheroRamlMock(String nombre){
		_nombre = nombre;
		_lineas = new ArrayList<String>();
		_iterador = _lineas.iterator();
	}
	public String getNombreDelFichero(){
		return _nombre;
	}
	public int getNumeroDeRegistros(){
		return _lineas.size();
	}
	public boolean hayMasRegistros(){
		return _iterador.hasNext();
	}
	public String leerRegistro(){
		return _iterador.next();
	}
	protected void añadirLinea(String linea){
		_lineas.add(linea);
		_iterador = _lineas.iterator();
	}
}