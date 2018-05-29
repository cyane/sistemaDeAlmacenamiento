package almacen;

import java.io.File;
import java.util.TreeMap;

import modelo.DAO;
import utiles.Utiles;

public class AlmacenArticulo<T,K> {

	private StringBuilder pathDatos;
	private String pathIndice;
	private TreeMap<K, Integer> indice;

	public AlmacenArticulo(String path) {//  "./data/articulos/"
		super();
		this.pathDatos=new StringBuilder(path);
		File file = new File(pathDatos.toString());
		if (!file.exists()) {
			file.mkdirs();
		}
		this.pathIndice = pathDatos + "indice.data";
		if (Utiles.comprobarExiste(pathIndice)) {
			this.indice = (TreeMap<K, Integer>) new DAO().leer(pathIndice);
		}else{
			this.indice = new TreeMap<>();
		}
	}

	public boolean grabar(T t, Integer numero, String nombre) {
		boolean retorno = false;
		this.pathDatos.append(numero + ".art");
		boolean grabar = new DAO<>().grabar(pathDatos.toString(), t);
		if (Utiles.comprobarExiste(pathIndice)) {// si el indice existe se tendria que pillar
			indice = (TreeMap<K, Integer>) new DAO().leer(pathIndice);
		}
		if (grabar) {
			indice.put((K) nombre, numero);
			retorno = new DAO<>().grabar(pathIndice, this.indice);
			
		}
		return retorno;
	}

	public T leer(String nombre) {
		T retorno = null;
		if (Utiles.comprobarExiste(pathIndice)) {
			indice = (TreeMap<K, Integer>) new DAO().leer(pathIndice);
			Integer nombreArch = indice.get(nombre);
			if (nombreArch!=null) {
				pathDatos.append(nombreArch + ".art");
				retorno = (T) new DAO<>().leer(pathDatos.toString());
				
			}
			
		}
		return retorno;
	}

	public TreeMap obtenerIndice() {
		if (Utiles.comprobarExiste(pathIndice)) {
			return (TreeMap) new DAO<>().leer(pathIndice);
		}
		return null;
	}
}
