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
		 clienteUno = new Cliente(1, "uno", "asd1");
		 clienteDos = new Cliente(2, "dos", "asd2");
		 clienteTres = new Cliente(3, "tres", "asd3");
		 pedidoUno = new Pedido( "pUno");
		 pedidoDos = new Pedido( "pDos");
		 pedidoTres = new Pedido( "pTres");
		 pedidoCuatro = new Pedido( "pCuatro");
		 pedidoCinco = new Pedido( "pCinco");
		 pedidoSeis = new Pedido( "pSeis");

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


		inst.grabar(clienteUno.getID(), pedidoUno);
		inst.grabar(clienteDos.getID(), pedidoDos);
		inst.grabar(clienteUno.getID(), pedidoTres);
		inst.grabar(clienteTres.getID(), pedidoCuatro);
		inst.grabar(clienteUno.getID(), pedidoCinco);
		inst.grabar(clienteDos.getID(), pedidoSeis);
	}

	@Test
	public void testLeer() {
		testGrabar();
		assertEquals(pedidoUno, inst.leer(clienteUno.getID(), pedidoUno.getNumero()));
		assertEquals(pedidoDos, inst.leer(clienteDos.getID(), pedidoDos.getNumero()));
		assertEquals(pedidoTres, inst.leer(clienteUno.getID(), pedidoTres.getNumero()));
		assertEquals(pedidoCuatro, inst.leer(clienteTres.getID(), pedidoCuatro.getNumero()));
		assertEquals(pedidoCinco, inst.leer(clienteUno.getID(), pedidoCinco.getNumero()));
		assertEquals(pedidoSeis, inst.leer(clienteDos.getID(), pedidoSeis.getNumero()));
	}

}
