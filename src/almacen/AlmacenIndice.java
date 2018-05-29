package almacen;

import java.io.File;
import java.util.TreeMap;
import java.util.Map.Entry;

import modelo.DAO;
import utiles.Utiles;

public class AlmacenIndice<T, K> {

	private StringBuilder pathDatos;
	private String pathIndice;
	private TreeMap<K, Integer> indice;

	public AlmacenIndice(String path) {
		super();
		this.pathDatos = new StringBuilder(path);
		File file = new File(pathDatos.toString());
		if (!file.exists()) {
			file.mkdirs();
		}
		this.pathIndice = pathDatos + "indice.data";
		if (Utiles.comprobarExiste(pathIndice)) {
			this.indice = (TreeMap<K, Integer>) new DAO().leer(pathIndice);
		} else {
			this.indice = new TreeMap<>();
		}
	}

	public boolean grabar(T t, K k) {
		this.pathDatos.append("clientes.data");
		assert k != null && t != null;
		boolean retorno = false;
		Entry<K, Integer> lastEntry = indice.lastEntry();
		Integer value = 0;
		if (lastEntry != null) {
			value = lastEntry.getValue() + 1;
		}
		if (indice.put(k, value) == null) {
			if (new DAO<>().grabar(pathDatos.toString(), t, true)) {
				retorno = true;
				new DAO<>().grabar(pathIndice, indice);
			} else {
				new DAO<>().grabar(pathIndice, indice);
			}
			System.out.println(indice);
		}
		return retorno;
	}

	public T leer(K k) {
		this.pathDatos.append("clientes.data");
		indice = (TreeMap<K, Integer>) new DAO<T>().leer(pathIndice.toString());
		Integer posicion = (Integer) indice.get(k);
		T retorno = null;
		if (posicion != null) {
			retorno = (T) new DAO<>().leer(pathDatos.toString(), posicion);
		}
		return retorno;
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
			if (nombreArch != null) {
				pathDatos.append(nombreArch + ".art");
				retorno = (T) new DAO<>().leer(pathDatos.toString());
			}
		}
		return retorno;
	}
}
