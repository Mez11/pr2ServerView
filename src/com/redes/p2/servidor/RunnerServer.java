package com.redes.p2.servidor;

import java.io.IOException;
import java.util.List;

import com.redes.p2.Operaciones;
import com.redes.p2.model.Productos;
import com.redes.p2.servidor.dao.ProductosDao;

public class RunnerServer {
	private static final int DEFAULT_PORT = 4070;
	
	public static void main( String args[] ){
		Servidor servidor = new Servidor( );
		ProductosDao dao;
		/**Conexion con laBD*/
		System.out.println( "Usando el puerto " + DEFAULT_PORT );
		
		//Servidor servidor = new Servidor();
		if( !servidor.init( DEFAULT_PORT ) ){
			System.out.println( "Previos errores, el envio no puede iniciar @.#.." );
			return;
		}
		dao = new ProductosDao();//inicializar el objeto
		dao.inicializarConexion( );//inicializar la conexion
		while( true ){
			procesarCliente( servidor, dao );
		}//Fin ciclo escuchar clientes
	}//fin main
	
	private static void procesarCliente( Servidor servidor, ProductosDao dao ){
		try {
			//Aceptar a un cliente
			System.out.println( "A la espera de un cliente..." );
			servidor.bind( );
			System.out.println( "Cliente recibido" );
			//Inicializar la BD
			while( true ){
				if( !procesarOperacion( servidor, dao ) ){
					break;
				}
			}//fin ciclo para escuchar codigos de operacion
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}//fin try-catch
	}
	
	private static boolean procesarOperacion( Servidor servidor, ProductosDao dao ) throws IOException, ClassNotFoundException{
		int codigoOperacion;
		//Obtener el codigo de operaciones
		System.out.println( "Obteniendo el codigo de operacion..." );
		codigoOperacion = servidor.getCodigoOperacion( );
		System.out.println( "Codigo recibido: " + codigoOperacion );
		if( codigoOperacion == Operaciones.OP_CATALOGO ){
			System.out.println( "Enviando lista de productos..." );
			//Debemos enviar el catalogo del dao
			servidor.enviar( dao.getProductos( ) );
		}
		else if( codigoOperacion == Operaciones.OP_COMPRA ){
			//Implementar la logica de confirmacion compra
			System.out.println( "Recibiendo compra @.@...." );
			List<Productos> carrito = servidor.getCarrito( );
			for( Productos producto : carrito ){
				System.out.println( "Producto en carrito: " + producto.getNombre( ) );
			}
		}
		else if( codigoOperacion == Operaciones.OP_CERRAR_CONEXION ){
			System.out.println("Cerrando conexion @.@.!!");
			servidor.cerrar( );
			//break;
			return false;
		}
		else{
			System.out.println( "Codigo de operacion desconocido: " + codigoOperacion );
		}
		//el while siga ejecutandose
		return true;
	}
	
}//fin clase