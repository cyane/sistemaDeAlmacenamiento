package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AlmacenClienteTest.class,AlmacenProveedoresTest.class,
	AlmacenPedidoTest.class,AlmacenArticuloTest.class })
public class AllTests {

}
