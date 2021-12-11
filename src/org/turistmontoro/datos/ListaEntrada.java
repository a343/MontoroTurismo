package org.turistmontoro.datos;

public class ListaEntrada {
	private int id;
	private int idImagen;
	private String titulo;
	private String descripcion;
	private String descripcionLarga;
	private String telefono;
	private String web;
	private String direccion;
	private String table_name;

	public ListaEntrada() {

	}

	public ListaEntrada(int idImagen, String titulo, String descripcion,
			String descripcionLarga, String telefono, String web,
			String direccion) {
		super();
		this.idImagen = idImagen;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.descripcionLarga = descripcionLarga;
		this.telefono = telefono;
		this.web = web;
		this.direccion = direccion;
	}

	public ListaEntrada(int id, int idImagen, String titulo,
			String descripcion, String descripcionLarga, String telefono,
			String web, String direccion, String table_name) {
		super();
		this.id = id;
		this.idImagen = idImagen;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.descripcionLarga = descripcionLarga;
		this.telefono = telefono;
		this.web = web;
		this.direccion = direccion;
		this.table_name = table_name;
	}

	public int getIdImagen() {
		return idImagen;
	}

	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

}