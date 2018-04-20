package Principal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistroTest {

	Registro registroDeTresCampos ;
	Registro registroDeUnCampo;
	Registro registroDeUnCampoBis;
	Registro registroNulo;
	Registro registroSeparadorNulo;
	Registro registroNuloYSeparadorNulo;
	Registro registroConSeparadorAlComienzo;
	Registro registroConSeparadorAlFinal;

	@Before
	public void setUp() throws Exception {
		try {
			registroDeTresCampos = new Registro("campo1;campo2;campo3", ";");
			registroDeUnCampo = new Registro("campo", ";");
			registroDeUnCampoBis = new Registro ("campo1;campo2","/");
			registroConSeparadorAlComienzo = new Registro ("/campo1/campo2/campo3","/");
			registroConSeparadorAlFinal = new Registro ("campo1/campo3/campo3/","/");
			registroNulo = new Registro (null,";");
			registroSeparadorNulo = new Registro ("campo1;campo2",null) ;
			registroNuloYSeparadorNulo = new Registro (null,null);
		} catch (Exception e){
			System.out.println(e.getLocalizedMessage());
			throw  e;
		}
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test 	public void getCampoTest() {
		assertEquals("campo1",registroDeTresCampos.getCampo(0));
	}
	@Test	public void getCampoTest_2() {
		assertEquals("campo2",registroDeTresCampos.getCampo(1));
	}
	@Test	public void getCampoTest_3() {
		assertEquals("Sin valor",registroDeTresCampos.getCampo(3));
	}
	@Test	public void getCampoTest_4() {
		assertEquals("campo",registroDeUnCampo.getCampo(0));
	}
	@Test	public void getCampoTest_5() {
		assertEquals("Sin valor",registroDeUnCampo.getCampo(1));
	}
	@Test	public void getCampoTest_6() {
		assertEquals("campo1;campo2",registroDeUnCampoBis.getCampo(0));
	}
	@Test	public void getCampoTest_7() {
		assertEquals("Sin valor",registroDeUnCampoBis.getCampo(1));
	}
	@Test	public void getCampoTest_8() {
		assertEquals("campo1",registroConSeparadorAlComienzo.getCampo(0));
	}
	@Test	public void getCampoTest_9() {
		assertEquals("campo2",registroConSeparadorAlComienzo.getCampo(1));
	}
	@Test	public void getCampoTest_10() {
		assertEquals("campo3",registroConSeparadorAlFinal.getCampo(2));
	}
	@Test	public void getCampoTest_11() {
		assertEquals("Sin valor",registroConSeparadorAlFinal.getCampo(3));
	}
	@Test	public void getCampoTest_12() {
		assertEquals("Sin valor",registroNulo.getCampo(0));
	}
	@Test	public void getCampoTest_13() {
		assertEquals("Sin valor",registroNulo.getCampo(1));
	}
	@Test	public void getCampoTest_14() {
		assertEquals("campo1;campo2",registroSeparadorNulo.getCampo(0));
	}
	@Test	public void getCampoTest_15() {
		assertEquals("Sin valor",registroSeparadorNulo.getCampo(1));
	}
	@Test	public void getCampoTest_16() {
		assertEquals("Sin valor",registroNuloYSeparadorNulo.getCampo(0));
	}
	@Test	public void getCampoTest_17() {
		assertEquals("Sin valor",registroNuloYSeparadorNulo.getCampo(1));
	}
	@Test	public void getRegistroTest() {
		assertEquals("campo1;campo2;campo3",registroDeTresCampos.getRegistro());
	}
	@Test	public void getRegistroTest_2() {
		assertEquals("campo",registroDeUnCampo.getRegistro());
	}
	@Test	public void getRegistroTest_2Bis() {
		assertEquals("campo1;campo2",registroDeUnCampoBis.getRegistro());
	}
	@Test	public void getRegistroTest_3() {
		assertNull(registroNulo.getRegistro());
	}
	@Test	public void getRegistroTest_4() {
		assertNull(registroNuloYSeparadorNulo.getRegistro());
	}
	@Test	public void getRegistroTest_5() {
		assertEquals("campo1;campo2",registroSeparadorNulo.getRegistro());
	}

	@Test	public void toStringTest() {
		assertEquals("[campo1][campo2][campo3]",registroDeTresCampos.toString());
	}
	@Test	public void toStringTest_2() {
		assertEquals("[campo]",registroDeUnCampo.toString());
	}
	@Test	public void toStringTest_3() {
		assertEquals("[campo1;campo2]",registroDeUnCampoBis.toString());
	}
	@Test	public void toStringTest_4() { assertEquals("[campo1][campo2][campo3]",registroConSeparadorAlComienzo.toString()); }
	@Test	public void toStringTest_5() { assertEquals("[campo1][campo3][campo3]",registroConSeparadorAlFinal.toString()); }
	@Test	public void toStringTest_6() { assertEquals("[Sin valor]",registroNulo.toString()); }
	@Test	public void toStringTest_7() { assertEquals("[campo1;campo2]",registroSeparadorNulo.toString()); }
	@Test	public void toStringTest_8() { assertEquals("[Sin valor]",registroNuloYSeparadorNulo.toString()); }

	@Test	public void equalsTest() {
		assertNotEquals((new Object()),registroDeTresCampos);
	}
	@Test	public void equalsTest_2() {
		assertEquals(new Registro ("campo1;campo2;campo3",";"),registroDeTresCampos);
	}
	@Test	public void equalsTest_3() {
		assertEquals(new Registro ("campo1/campo2/campo3","/"),registroDeTresCampos);
	}
	@Test	public void equalsTest_4() {
		assertNotEquals(new Registro ("campo1;campo2;campo3","/"),registroDeTresCampos);
	}
	@Test	public void equalsTest_5() {
		assertNotEquals(new Registro ("campo1;campo2;campo3","/"),registroNulo);
	}


}
