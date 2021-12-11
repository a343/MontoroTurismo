package org.turistmontoro.datos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ajdiaz
 */
public class Oferta {
    private int id;
    private String username;
    private String descripcion;
    private String titulo;
    private String fechaFin;
    private int idImagen;

    public Oferta(int id, String username, String descripcion, String titulo, String fechaFin) {
        this.id = id;
        this.username = username;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.fechaFin = fechaFin;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;

    }



	public int getIdImagen() {
		return idImagen;
	}



	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}
}
    
    
    