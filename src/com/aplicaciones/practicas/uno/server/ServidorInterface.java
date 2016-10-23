package com.aplicaciones.practicas.uno.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.redes.p2.model.Productos;

public interface ServidorInterface {

	/**
	 * Bloqueante
	 * @return
	 */
	//Se esta escuchando el socket
	public Socket bind( ) throws IOException;
	public Productos readProducto( ObjectInputStream ois ) throws IOException, ClassNotFoundException;
}


//Esta es la interfaz pero de servidor 
//y SERVIDOR SE LLAMA EN RUNER sERVER