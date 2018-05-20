package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import almacen.AlmacenRutasJose;
import modelo.Cliente;

public class AlmacenRutasJoseTest {
	AlmacenRutasJose<Cliente> instancia;
	ArrayList<Cliente> clientes = new ArrayList<>();
	@Before
	public void setUp() {
		instancia=new AlmacenRutasJose<>("Elementos");

		Cliente cliente1 = new Cliente(1, "pablo","dni1");
		clientes.add(cliente1);
		Cliente cliente2 = new Cliente(2, "pabloDos","dni2");
		clientes.add(cliente2);
		Cliente cliente3 = new Cliente(3, "pabloTres","dni3");
		clientes.add(cliente3);
		Cliente cliente4 = new Cliente(4, "pabloCuatro","dni4");
		clientes.add(cliente4);
		Cliente cliente5 = new Cliente(1, "pabloDos","dni5");
		clientes.add(cliente5);
	}

	@After
	public void tearDown() {
		for (Cliente cliente : clientes) {
			File archivo = new File("./data/Elementos/"+cliente.getNombre()+"/"+cliente.getNumero()+".data");
			archivo.delete();
		}
		for (Cliente cliente : clientes) {
			File directorio = new File("./data/Elementos/"+cliente.getNombre());
			directorio.delete();
			
		}
	}

	@Test
	public void testObtener() {
		testGrabar();
		for (Cliente cliente : clientes) {
			Cliente obtenido = instancia.obtener(String.valueOf(cliente.getNumero()), cliente.getNombre());
			assertTrue(cliente.equals(obtenido));
		}
	}

	@Test
	public void testGrabar() {
		int i=0;
		for (Cliente cliente : clientes) {
			instancia.grabar(cliente, String.valueOf(cliente.getNumero()),cliente.getNombre());
			i++;
			File archivo = new File("./data/Elementos/"+cliente.getNombre()+"/"+cliente.getNumero()+".data");
			assertTrue(archivo.exists());
		}
		Cliente cliente6 = new Cliente(3, "pabloTres","dni2"); //mismo nombre archivo, no sobreescribe
		assertFalse(instancia.grabar(cliente6, String.valueOf(cliente6.getNumero()),cliente6.getNombre()));
	}

}
