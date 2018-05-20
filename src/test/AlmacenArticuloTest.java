package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import almacen.AlmacenArticulo;
import modelo.Articulo;

public class AlmacenArticuloTest {

	private AlmacenArticulo inst;
	Articulo articuloUno = new Articulo(0, "pan");
	Articulo articuloDos = new Articulo(1, "ajo");
	Articulo articuloTres = new Articulo(2,"sal");
	@Before
	public void setUp() throws Exception {
		inst = new AlmacenArticulo();
	}

	@After
	public void tearDown() throws Exception {
		File dir = new File("./data/articulos");
		File[] list = dir.listFiles();
		for (int i = 0; i < list.length; i++) {
			list[i].delete();
		}
		dir.delete();
	}

	@Test
	public void testGrabar() {
		inst.grabar(articuloDos, articuloDos.getNumero(), articuloDos.getNombre());
		inst.grabar(articuloTres, articuloTres.getNumero(), articuloTres.getNombre());
		inst.grabar(articuloUno, articuloUno.getNumero(), articuloUno.getNombre());
	}

	@Test
	public void testLeer() {
		testGrabar();
		assertTrue(articuloUno.equals(inst.leer(articuloUno.getNombre())));
		assertTrue(articuloTres.equals(inst.leer(articuloTres.getNombre())));
		assertTrue(articuloDos.equals(inst.leer(articuloDos.getNombre())));
	}

}
