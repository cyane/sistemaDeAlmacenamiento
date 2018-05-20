package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import almacen.AlmacenCliente;
import modelo.Cliente;

public class AlmacenClienteTest {
	ArrayList<Cliente> clientes = new ArrayList<>();
	AlmacenCliente<Cliente,Object> inst;
	
	@Before
	public void setUp() {
		inst = new AlmacenCliente<>();
		Cliente cliente1 = new Cliente(0, "pablo","dni1");
		clientes.add(cliente1);
		Cliente cliente2 = new Cliente(1, "pabloDos","dni2");
		clientes.add(cliente2);
		Cliente cliente3 = new Cliente(2, "pabloTres","dni3");
		clientes.add(cliente3);
		Cliente cliente4 = new Cliente(3, "pabloCuatro","dni4");
		clientes.add(cliente4);
		Cliente cliente5 = new Cliente(4, "pabloDos","dni5");
		clientes.add(cliente5);
	}


	@After
	public void tearDown() {
		File archivo = new File("./data/clientes/clientes.data");
		archivo.delete();
		File archivo2 = new File("./data/clientes/indice.data");
		archivo2.delete();
		File archivo3 = new File("./data/clientes");
		archivo3.delete();
	}

	@Test
	public void testGrabar() {
		for (Cliente cliente : clientes) {
			inst.grabar(cliente, cliente.getNumero());
		}
	}

	@Test
	public void testObtener() {
		testGrabar();
		for (Cliente cliente : clientes) {
			assertTrue(cliente.equals(inst.obtener(cliente.getNumero())));
		}
	}

}
