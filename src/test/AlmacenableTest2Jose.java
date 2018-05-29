package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modelo.Cliente;
import modelo.DAO;

public class AlmacenableTest2Jose {
	DAO<Cliente> instancia;
	Cliente cliente = new Cliente( "dni1","uno" ,"asd","123");
	private static final String VARIOS_DATA = "varios.data";
	private static final String UNICO_DATA = "unico.data";

	@Before
	public void setUp() throws Exception {
		// TODO instanciacion de Almacenable
		instancia=new DAO<>();
	}

	@After
	public void tearDown() throws Exception {
		File file=new File(UNICO_DATA);
		file.delete();
		file=new File(VARIOS_DATA);
		file.delete();
	}

	@Test
	public void testLeerObjetoUnioo() {
		assertTrue(instancia.grabar(UNICO_DATA, cliente));
		Cliente cli = (Cliente) instancia.leer(UNICO_DATA);
		assertEquals(cli, cliente);
	}

	@Test
	public void testLeerObjetoPosicional() {
		instancia.grabar(VARIOS_DATA, cliente);
		Cliente dos = new Cliente( "dos","dos" ,"asd","123");
		instancia.grabar(VARIOS_DATA, dos);
		assertEquals(dos, instancia.leer(VARIOS_DATA, 1));
		instancia.grabar(VARIOS_DATA, dos);
		instancia.grabar(VARIOS_DATA, dos);
		Cliente tres = new Cliente( "res","tres" ,"asd","123");
		instancia.grabar(VARIOS_DATA, tres);
		assertEquals(tres, instancia.leer(VARIOS_DATA, 4));
	}

	@Test
	public void testGrabarObjetoUnico() {
		assertTrue(instancia.grabar(UNICO_DATA, cliente));
	}

	@Test
	public void testGrabarObjetoPosicionalAlFinal() {
		instancia.grabar(VARIOS_DATA, cliente);
		Cliente dos = new Cliente( "cuatro","uno" ,"asd","123");
		assertTrue(instancia.grabar(VARIOS_DATA, dos));
		
	}

}
