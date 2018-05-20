package modelo;

import java.io.Serializable;

public class Articulo implements Serializable{
	private int numero;
	private String nombre;

	public Articulo(int numero, String nombre) {
		super();
		this.numero = numero;
		this.nombre = nombre;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

	@Override
	public boolean equals(Object obj) {
		boolean retorno = super.equals(obj);
		if (!retorno) {
			Articulo articulo = (Articulo) obj;
			retorno = this.numero==articulo.getNumero();
		}
		return retorno;
	}
}
