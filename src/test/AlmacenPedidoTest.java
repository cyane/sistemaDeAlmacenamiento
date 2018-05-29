package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import almacen.AlmacenPedido;
import modelo.Cliente;
import modelo.Pedido;

public class AlmacenPedidoTest {
	AlmacenPedido inst;
	Cliente clienteUno,clienteDos, clienteTres ;
	Pedido pedidoUno,pedidoDos, pedidoTres, pedidoCuatro, pedidoCinco,pedidoSeis;
	@Before
	public void setUp() throws Exception {
		inst = new AlmacenPedido();
		Cliente clienteUno = new Cliente( "dni1","uno" ,"asd","123");
		Cliente clienteDos = new Cliente( "dni2","uno" ,"asd","123");
		Cliente clienteTres = new Cliente( "dni3","uno" ,"asd","123");
		 pedidoUno = new Pedido(1, clienteUno);
		 pedidoDos = new Pedido(2, clienteDos);
		 pedidoTres = new Pedido(3, clienteUno);
		 pedidoCuatro = new Pedido(4, clienteDos);
		 pedidoCinco = new Pedido(5, clienteTres);
		 pedidoSeis = new Pedido(6, clienteUno);

	}

	@After
	public void tearDown() throws Exception {
		File dir = new File("./data/pedidos");
		File[] list = dir.listFiles();
		for (int i = 0; i < list.length; i++) {
			File[] files = list[i].listFiles();
			for (int j = 0; j < files.length; j++) {
				files[j].delete();
			}
			list[i].delete();
		}
		dir.delete();
	}

	@Test
	public void testGrabar() {
		assertTrue(inst.grabar(pedidoUno.getCliente().getDniCif(), pedidoUno));
		assertTrue(inst.grabar(pedidoDos.getCliente().getDniCif(), pedidoDos));
		assertTrue(inst.grabar(pedidoTres.getCliente().getDniCif(), pedidoTres));
		assertTrue(inst.grabar(pedidoCuatro.getCliente().getDniCif(), pedidoCuatro));
		assertTrue(inst.grabar(pedidoCinco.getCliente().getDniCif(), pedidoCinco));
		assertTrue(inst.grabar(pedidoSeis.getCliente().getDniCif(), pedidoSeis));
	}

	@Test
	public void testLeer() {
		testGrabar();
		Pedido pedido = inst.leer(pedidoUno.getCliente().getDniCif(), pedidoUno.getNumero());
		System.out.println(pedidoUno.getCliente()+" "+pedidoUno.getNumero()+" "+pedidoUno.getFecha());
		System.out.println(pedido.getCliente()+" "+pedido.getNumero()+" "+pedido.getFecha());
		assertEquals(pedidoUno, inst.leer(pedidoUno.getCliente().getDniCif(), pedidoUno.getNumero()));
		assertEquals(pedidoDos, inst.leer(pedidoDos.getCliente().getDniCif(), pedidoDos.getNumero()));
		assertEquals(pedidoTres, inst.leer(pedidoTres.getCliente().getDniCif(), pedidoTres.getNumero()));
		assertEquals(pedidoCuatro, inst.leer(pedidoCuatro.getCliente().getDniCif(), pedidoCuatro.getNumero()));
		assertEquals(pedidoCinco, inst.leer(pedidoCinco.getCliente().getDniCif(), pedidoCinco.getNumero()));
		assertEquals(pedidoSeis, inst.leer(pedidoSeis.getCliente().getDniCif(), pedidoSeis.getNumero()));
	}

}
