package almacen;

import java.io.File;
import java.util.Map.Entry;
import java.util.TreeMap;

import modelo.DAO;
import utiles.Utiles;

public class AlmacenCliente<T, K> {

	private StringBuilder pathDatos;
	private String pathIndice;
	private TreeMap<K, Integer> indice;

	public AlmacenCliente(String path) {
		super();
		this.pathDatos=new StringBuilder(path); //   "./data/clientes/"
	
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

	public boolean grabar(T t, K k) {
		this.pathDatos.append("clientes.data");
		assert k != null && t != null;
		boolean retorno = false;
		// miro el ultimo indice. siempre hay un mapa aqui
		Entry<K, Integer> lastEntry = indice.lastEntry();
		Integer value = 0;
		// si es el primer elemento lastentry sera null
		if (lastEntry != null) {
			value = lastEntry.getValue() + 1;
		}
		if (indice.put(k, value) == null) {
			System.out.println("es null");
			if (new DAO<>().grabar(pathDatos.toString(), t, true)) {
				retorno = true;
				new DAO<>().grabar(pathIndice, indice);
			}else{
				//Si no se graba bien actualizamos el indice con la version grabada
				new DAO<>().grabar(pathIndice, indice);
			}
			System.out.println(indice);
		}
		return retorno;
	}
//		boolean retorno = false;
//		if (k != null) {
//			boolean grabar = new DAO<>().grabar(pathDatos.toString(), t, true);
//			if (grabar) {
//				try {
//					indice.put(k, indice.lastEntry().getValue() + 1);
//				} catch (NullPointerException e) {
//					indice.put(k, 0);
//				}
//				retorno = new DAO<>().grabar(pathIndice.toString(), indice);
//			}
//		}
//		return retorno;
//	}

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
	
}
