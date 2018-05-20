package utiles;

import java.io.File;

public class Utiles {

	public static boolean comprobarExiste(String ruta){
		File archivo = new File(ruta);
		return archivo.exists();
		
	}
}
