package com.aplicaciones.practicas.uno.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.redes.p2.model.Productos;

public class Servidor implements ServidorInterface {
	private ServerSocket server;
	
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
	public Socket bind() throws IOException {
		return server.accept( ); //Accepts a client
	}
	
	
	
	public void enviar( ObjectOutputStream oos, List<Productos> productos ) throws IOException{
		oos.writeObject( productos );
	}
	
	public void enviar( ObjectOutputStream oos, Productos producto ) throws IOException{
		oos.writeObject( producto );
	}
		
	@Override
	public Productos readProducto( ObjectInputStream ois ) throws IOException, ClassNotFoundException {
		Productos productos = null;
		productos = (Productos)ois.readObject( ); 
		return productos;
	}

}
