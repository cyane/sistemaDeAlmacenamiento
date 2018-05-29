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

		Cliente cliente1 = new Cliente( "dni1","uno" ,"asd","123");
		clientes.add(cliente1);
		Cliente cliente2 = new Cliente( "dni2","uno" ,"asd","123");
		clientes.add(cliente2);
		Cliente cliente3 = new Cliente( "dni3","uno" ,"asd","123");
		clientes.add(cliente3);
		Cliente cliente4 = new Cliente( "dni4","uno" ,"asd","123");
		clientes.add(cliente4);
		Cliente cliente5 = new Cliente( "dni5","uno" ,"asd","123");
		clientes.add(cliente5);
	}

	@After
	public void tearDown() {
		for (Cliente cliente : clientes) {
			File archivo = new File("./data/Elementos/"+cliente.getRazonSocial()+"/"+cliente.getDniCif()+".data");
			archivo.delete();
		}
		for (Cliente cliente : clientes) {
			File directorio = new File("./data/Elementos/"+cliente.getRazonSocial());
			directorio.delete();
			
		}
	}

	@Test
	public void testObtener() {
		testGrabar();
		for (Cliente cliente : clientes) {
			Cliente obtenido = instancia.obtener(cliente.getRazonSocial(), cliente.getDniCif());
			assertTrue(cliente.equals(obtenido));
		}
	}

	@Test
	public void testGrabar() {
		int i=0;
		for (Cliente cliente : clientes) {
			instancia.grabar(cliente, String.valueOf(cliente.getDniCif()),cliente.getRazonSocial());
			i++;
			File archivo = new File("./data/Elementos/"+cliente.getRazonSocial()+"/"+cliente.getDniCif()+".data");
			assertTrue(archivo.exists());
		}
		Cliente cliente6 = new Cliente( "dni1","uno" ,"asd","123");; //mismo nombre archivo, no sobreescribe
		assertFalse(instancia.grabar(cliente6, String.valueOf(cliente6.getDniCif()),cliente6.getRazonSocial()));
	}

}
