package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AlmacenableTest2Jose.class,AlmacenClienteTest.class,AlmacenProveedoresTest.class,
	AlmacenRutasJoseTest.class,AlmacenPedidoTest.class,AlmacenArticuloTest.class })
public class AllTests {

}
