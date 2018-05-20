package almacen;
import java.io.File;
import java.io.FileOutputStream;

import modelo.DAO;

public class AlmacenRutasJose<T> {

	// La ruta comun a todos los archivos
	private StringBuilder pathComun = new StringBuilder("./data/");
	private int lenghtDir;

	// Directorio se refiere a la carpeta de un determinado grupo de valores
	public AlmacenRutasJose(String directorio) {
		super();
		pathComun.append(directorio);
		File file = new File(pathComun.toString());
		if (!file.exists()) {
			file.mkdirs();
		}
		this.lenghtDir = pathComun.length();
	}

	public T obtener(String claveElemento, String claveGrupo) {
		this.pathComun.delete(lenghtDir, pathComun.length());
		pathComun.append("/" + claveGrupo);
		String ruta = pathComun.toString();
		pathComun.append("/" + claveElemento + ".data");
		File archivo = new File(pathComun.toString());
		T retorno= (T) new DAO<>().leer(pathComun.toString());
		this.pathComun.delete(lenghtDir, pathComun.length());
		return retorno;
	};

	public boolean grabar(T t, String claveElemento, String claveGrupo) {
		boolean retorno = true;
		pathComun.append("/" + claveGrupo);
		String ruta = pathComun.toString();
		pathComun.append("/" + claveElemento + ".data");
		File elemento = new File(ruta);
		File archivo = new File(pathComun.toString());
		if (!elemento.exists()) {
			elemento.mkdirs();
		} else if (archivo.exists()) {
			retorno = false;
		}
		if (retorno) {
			retorno = new DAO<>().grabar(pathComun.toString(), t);
		}
		this.pathComun.delete(lenghtDir, pathComun.length());
		return retorno;
	};

}
