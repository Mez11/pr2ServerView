package com.redes.p2.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.redes.p2.model.Productos;

public class Servidor implements ServidorInterface {
	private ServerSocket server;
	private Socket cliente;
	private ObjectInputStream entradaDeCliente;
	private ObjectOutputStream salidaACliente;
	
	public boolean init( int port ){
		boolean pudoConectarse = false;
		try {
			server = new ServerSocket( port );
			pudoConectarse = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pudoConectarse;
	}

	@Override
	public void bind() throws IOException {
		cliente = server.accept( );
		entradaDeCliente = new ObjectInputStream( cliente.getInputStream() );
		salidaACliente = new ObjectOutputStream( cliente.getOutputStream() );
		System.out.println( "Aceptada conexion de " + cliente.getInetAddress( ).getHostAddress( ) );
	}
	
	public int getCodigoOperacion( ) throws IOException{
		return entradaDeCliente.readInt( );
	}
	
	public void enviar( List<Productos> productos ) throws IOException{
		salidaACliente.writeObject( productos );
		salidaACliente.flush( );
	}
		
	@SuppressWarnings("unchecked")
	public List<Productos> getCarrito( ) throws ClassNotFoundException, IOException{
		return (List<Productos>) entradaDeCliente.readObject( );
	}

	public void cerrar() throws IOException {
		entradaDeCliente.close( );
		salidaACliente.close();
		cliente.close();
	}

}
