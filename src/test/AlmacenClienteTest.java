package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import almacen.AlmacenCliente;
import almacen.AlmacenIndice;
import modelo.Cliente;

public class AlmacenClienteTest<K> {
	ArrayList<Cliente> clientes = new ArrayList<>();
	
	@Before
	public void setUp() {
		Cliente cliente1 = new Cliente( "dni1","uno" ,"asd","123");
		clientes.add(cliente1);
		Cliente cliente2 =  new Cliente( "dni2","dos" ,"asd","123");
		clientes.add(cliente2);
		Cliente cliente3 =  new Cliente( "dni3","tres" ,"asd","123");
		clientes.add(cliente3);
		Cliente cliente4 =  new Cliente( "dni4","cuatro" ,"asd","123");
		clientes.add(cliente4);
		Cliente cliente5 =  new Cliente( "dni5","dos" ,"asd","123");
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
			new AlmacenIndice<>( "./data/clientes/").grabar(cliente, cliente.getDniCif());
		}
		// se pueden sobreescribir archivos
		assertFalse(new AlmacenIndice<>( "./data/clientes/").grabar(new Cliente("dni3", "tres", "asd","123"), "dni3"));
	}

	@Test
	public void testleer() {
		testGrabar();
		for (Cliente cliente : clientes) {
			assertTrue(cliente.equals(new AlmacenIndice<>( "./data/clientes/").leer((K)cliente.getDniCif())));
		}
	}

}
