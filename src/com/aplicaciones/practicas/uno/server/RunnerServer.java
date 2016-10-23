package com.aplicaciones.practicas.uno.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.redes.p2.dao.ProductosDao;

public class RunnerServer {
	private static final int DEFAULT_PORT = 4070;
	private static Servidor servidor;
	
	public static void main( String args[] ){
		/**Representacion del cliente*/
		Socket cliente = null;
		/**Conexion con laBD*/
		ProductosDao dao = null;
		/**Flujos de E/S del cliente*/
		ObjectOutputStream oosCliente = null;
		ObjectInputStream oisCliente = null;
		Servidor servidor =  new Servidor();
		//holi holi...ja desacomede la vista 
		//La vista del cliente? del IDE gracias @.@. 
		//Entonces, aqui ya esta iniciado el servidr; no?
		//Sipoo ya funciona ja aunque no le llega a las clase de CLiente
		//Como inicias a client?
		System.out.println( "Usando el puerto " + DEFAULT_PORT );
	
		//Servidor servidor = new Servidor();
		if( !servidor.init( DEFAULT_PORT ) ){
			System.out.println( "Previos errores, el envio no puede iniciar @.#.." );
			return;
		}
		try {
			//Aceptar a un cliente
			cliente = servidor.bind( );
			//Inicializar la BD
			//Aqui se instancia el DAO:cool ya veo @.@.
			dao = new ProductosDao();
			dao.inicializarConexion( );
			//Obtener sus flujos
			//Aqui solo se obtienen los flujos de E/S d el cliente si...@.@...
			
			oosCliente = new ObjectOutputStream( cliente.getOutputStream() );
			oisCliente = new ObjectInputStream( cliente.getInputStream() );
			//Enviarle la lista de productos (catalogo)
			servidor.enviar( oosCliente, dao.getProductos( ) );
			} catch (IOException e) {
			e.printStackTrace();
		}
	}//fin main
}//fin clase