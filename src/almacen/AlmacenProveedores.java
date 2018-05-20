package almacen;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

import modelo.DAO;
import modelo.Proveedor;
import utiles.Utiles;

public class AlmacenProveedores{

	private ArrayList<Proveedor> lista; //esto tendria que ser un set, pero no tira (?)
	private StringBuilder pathComun = new StringBuilder("./data/proveedores");
	
	public AlmacenProveedores() {
		super();
		this.lista=new ArrayList<>();
		if (!Utiles.comprobarExiste(pathComun.toString())) {
			File file = new File(pathComun.toString());
			file.mkdirs();
		}
		this.pathComun.append("/proveedores.data");
	}

	public boolean grabar(Proveedor t){
		boolean retorno = false;
		lista.add(t);
		retorno = new DAO<>().grabar(pathComun.toString(), lista);
		return retorno;
	}
	
	public ArrayList<Proveedor> obtener(){
		ArrayList<Proveedor> retorno=null;
		if (Utiles.comprobarExiste(pathComun.toString())) {
			retorno = (ArrayList<Proveedor>) new DAO().leer(pathComun.toString());
		}
		return retorno;
	}
	


}
