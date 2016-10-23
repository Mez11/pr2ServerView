package com.redes.p2.model;

import java.io.Serializable;

public class Productos implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6054418405462278963L;
	/**Parametros provenientes de la BD**/
	private int idProductos;
	private String nombre;
	private Double precio;
	private int existencias;
	private String descripcion;
	private String origen;
	private byte[] imagen;

	public int getIdProductos() {
		return idProductos;
	}
	public void setIdProductos(int idProductos) {
		this.idProductos = idProductos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public int getExistencias() {
		return existencias;
	}
	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	

}
