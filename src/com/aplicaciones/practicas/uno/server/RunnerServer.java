package com.aplicaciones.practicas.uno.server;

import java.io.IOException;

import com.redes.p2.Operaciones;
import com.redes.p2.dao.ProductosDao;

public class RunnerServer {
	private static final int DEFAULT_PORT = 4070;
	
	public static void main( String args[] ){
		Servidor servidor = new Servidor( );
		ProductosDao dao;
		int codigoOperacion;
		/**Conexion con laBD*/
		System.out.println( "Usando el puerto " + DEFAULT_PORT );
		
		//Servidor servidor = new Servidor();
		if( !servidor.init( DEFAULT_PORT ) ){
			System.out.println( "Previos errores, el envio no puede iniciar @.#.." );
			return;
		}
		try {
			dao = new ProductosDao();//inicializar el objeto
			dao.inicializarConexion( );//inicializar la conexion
			while( true ){
				//Aceptar a un cliente
				System.out.println( "A la espera de un cliente..." );
				servidor.bind( );
				//Inicializar la BD
				while( true ){
					//Obtener el codigo de operaciones
					codigoOperacion = servidor.getCodigoOperacion( );
					if( codigoOperacion == Operaciones.OP_CATALOGO ){
						//Debemos enviar el catalogo del dao
						servidor.enviar( dao.getProductos( ) );
					}
					else if( codigoOperacion == Operaciones.OP_COMPRA ){
						//Implementar la logica de confirmacion compra
					}
					else if( codigoOperacion == Operaciones.OP_CERRAR_CONEXION ){
						servidor.cerrar( );
						break;
					}
					else{
						System.out.println( "Codigo de operacion desconocido: " + codigoOperacion );
					}
				}//fin ciclo para escuchar codigos de operacion
			}//Fin ciclo escuchar clientes
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//fin main
}//fin clase