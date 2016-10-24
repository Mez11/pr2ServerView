package com.redes.p2.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.redes.p2.dao.ProductosDao;
import com.redes.p2.model.Productos;

public class ConexionConServidor {

	//Hacer variable estatica para guardar al socket...
	private static Socket cliente;
	private static ObjectOutputStream salida;
	private static ObjectInputStream entrada;

	//Y acceder a este a traves de metodos estaticos...

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
	public static List<Productos> getCatalogoRemoto( ){
		List<Productos> list = new ArrayList<Productos>();
		List<Productos> list1 = new ArrayList<>();
	}//end
	
	
	Socket server =new ServerSocket().accept();
	
	ObjectInputStream ios = new ObjectInputStream(server.getInputStream());
	ObjectOutputStream  os = new ObjectOutputStream(server.getOutputStream());

	List<Productos> list1 = (List<Productos>) ios.readObject();//ConexionConServidor.inicializarCliente(HOST, port);



	if( list1 == 1){
		ObjectOutputStream.writeObject(ProductosDao.getAll());
	}else { if (list1 == 2){
		List<Productos> productosComprados = (List<Productos>) entrada.readObject( );



		// List<Productos> list=(List<Productos>) in.readObject( );
	}
	//Actualizacion de inventario @.@.
	}
}//End class


Socket conexionAlServer = new Socket( Host, port );