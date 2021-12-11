package org.turistmontoro.datos;

public class ListaCarta {

	public ListaCarta(int idImagen, int idImagen2, String descripcion) {
		super();
		this.idImagen = idImagen;
		this.idImagen2 = idImagen2;
		this.descripcion = descripcion;
	}
	private int idImagen;
	private int idImagen2;
	private String descripcion;
	
	
	public int getIdImagen() {
		return idImagen;
	}
	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}
	public int getIdImagen2() {
		return idImagen2;
	}
	public void setIdImagen2(int idImagen2) {
		this.idImagen2 = idImagen2;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
