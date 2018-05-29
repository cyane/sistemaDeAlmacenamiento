package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import almacen.AlmacenProveedores;
import modelo.Proveedor;

public class AlmacenProveedoresTest {

	private AlmacenProveedores inst;
	private ArrayList<Proveedor> proveedores;

	@Before
	public void setUp() throws Exception {
		inst = new AlmacenProveedores();
		proveedores=new ArrayList<Proveedor>();
		Proveedor proveedorUno = new Proveedor( "proveedorUno");
		proveedores.add(proveedorUno);
		Proveedor proveedorDos = new Proveedor( "proveedorDos");
		proveedores.add(proveedorDos);
		
	}

	@After
	public void tearDown() throws Exception {
		File archivo = new File("./data/proveedores/proveedores.data");
		File directorio = new File("./data/proveedores");
		archivo.delete();
		directorio.delete();
	}

	@Test
	public void testGrabar() {
		for (Proveedor proveedor : proveedores) {
			inst.grabar(proveedor);
		}
	}

	@Test
	public void testObtener() {
		testGrabar();
		TreeSet<Proveedor> test = new TreeSet<>();
		Proveedor proveedorUno = new Proveedor("proveedorUno");
		test.add(proveedorUno);
		Proveedor proveedorDos = new Proveedor("proveedorDos");
		test.add(proveedorDos);
		TreeSet<Proveedor> obtener = inst.obtener();
		assertEquals(obtener, test);
	}

}
