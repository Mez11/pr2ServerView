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
		
		System.out.println( "Usando el puerto " + DEFAULT_PORT );
	
		
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
			//ña-ja positivo ña-ja gracias mil 
			//de nada mil amor, cualquier cosa aca ando
			//sipo estaras en casa oh iras a biblio
			//yo creo que en casa, ya es tarde para ir
			//eso si exito gracias te amo bye osi
			//te amo mil amor, bye bye peque mi cielo
			//Bie mi niñoo
			oosCliente = new ObjectOutputStream( cliente.getOutputStream() );
			oisCliente = new ObjectInputStream( cliente.getInputStream() );
			//Enviarle la lista de productos (catalogo)
			servidor.enviar( oosCliente, dao.getProductos( ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//fin main
}//fin clase