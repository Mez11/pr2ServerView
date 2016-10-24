package com.redes.p2.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.redes.p2.Operaciones;
import com.redes.p2.model.Productos;

public class ConexionConServidor {
	//Hacer variable estatica para guardar al socket...
	private static Socket cliente;
	private static ObjectOutputStream salida;
	private static ObjectInputStream entrada;

	public static void inicializarCliente( String host, int port ) throws IOException{
		ConexionConServidor.cliente = new Socket( host, port);
		salida = new ObjectOutputStream( cliente.getOutputStream( ) );
		entrada = new ObjectInputStream( cliente.getInputStream( ) );
	}

	public static ObjectInputStream getEntrada() {
		return entrada;
	}

	public static ObjectOutputStream getSalida() {
		return salida;
	}

	@SuppressWarnings("unchecked")
	public static List<Productos> getCatalogoRemoto( ) throws IOException, ClassNotFoundException{
		//notificarle al servidor que esperamos recibir
		salida.writeInt( Operaciones.OP_CATALOGO );
		return (List<Productos>)entrada.readObject( );
	}//end
	
	public static void enviarCompra() throws IOException{
		//notificar al servidor  que operacion realizar
		salida.writeInt( Operaciones.OP_COMPRA );
		salida.writeObject( BaseDatosCarrito.getCarrito( ) );
	}
	
	public static void cerrar() throws IOException{
		salida.close();
		entrada.close();
		cliente.close();
	}
	
}//End class
