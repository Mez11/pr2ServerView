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
		//Aqui es donde se necesita mas codigo...
		//La operacion de "lectura"... 
		//¿Como decirle al seridor que lo que queremos es obtener 
		//Ok ,,,eso yo lo hago ,,,por que ya he abusadoooooo demasiadoooooo de ti solo me pudes poner los pasos
		//haha ntp amor :*
		//Estuve pensando en el baño, mientras me lavaba los dientes, y segun yo, las operaciones 
		//que se necesitan del servidor (llamense, funciones remotas) que se necesitan, son las siguientes:
		//1.-Lectura de catalogo (Es decir, traer desde el servidor la lista de todos los productos existentes en la BD)
		//2.-Compra (Es decir, informarle al servidor cuantos productos compro el usuario al presionar el boton "Finalizar compra" )

		//Para esto, no es muy facil explicarlo asi, pero hare mi mejor intento
		//Recuerdas tramas?
		//Osea datagramas con inetAdress 
		//no, tramas. O sea, como IEEE802, y ethernet y asi
		//ah...ethernet si
		//ah, bueno, si recuerdas, es una "barra" donde hay muchas cosas, pero una de esas cosas
		//es, si no mal recuerdo, un fragmento que indica la longitud del mensaje, otro para el mensaje, 
		//oitro para el checksum y asi, no?
		//Si seria ver el dibuji y asi
		//ah ok, no es muy importante saber exactamente el acomodo de dicha trama, pero me interesa
		//que recuerdes esos conceptos, porque vamos a aplicar algo parecido.
		//El punto es, que en la trama, lo que se envia de una compu a otra son un conjunto de bits en forma serial,
		//y la compu que recibe esa trama sabe como interpretar esos datos porque sabe que del 0 al 4tyo bit, por ejemplo,
		//estan dispuestos tales datos (el protocolo, por ejemplo)

		//Aqui realizaremos algo similar.
		//Por ejemplo, la comunicacion entre cliente y servidor puede ser de la siguiente forma:
		// | CLIENTE |          | SERVIDOR |
		// |    x    | -------->|          |  <-Lanzar peticion de coneccion (del cliente al servidor). Esto se hace en "new Socket ( host, port )
		// |    x    | -------->|          | <-Cliente le envia al servidor el CODIGO DE OPERACION
		// |         | <--------|    x     | <- El servidor le envia una respuesta al cliente. Dicha respuesta depende de la operacion a realizar
		// |         |    ...   |          | <- Realizar el proceso anterior (codigo operacion y respuesta) cuantas veces quiera el usuario
		// |    x    | -------->|          | <- Cerrar la conexion del cliente hacia el servidor
		// +---------+          +----------+

		//Lo que se trata de expresar en el dibujito, es que el primer mensaje que debe haber
		//(despues de que la conexion haya sido exitosa) es enviar el CODIGO DE OPERACION del cliente al servidor.
		//El codigo de operacion puede ser, por ejemplo:
		//COD_OPERACION = 1 <- Para obtener todos los productos e la BD_Operacion. esto se refiere a la List que proviene del server
		List<Productos> list = new ArrayList<Productos>();
		//COD_OPERACION = 2 <- Para informar al servidor los productos que fueron comprados (y la cantidad comprada)

		//Despues de que el cliente envie el codigo de operacion, el servidor emitira una respuesta con base en dicha informacion
		//Por ejemplo, para COD_OPERACION = 1, el cliente debe enviar un ArrayList con todos los productos de la BD  (SELECT *)
		//Lista de todos los productos
		List<Productos> list1 = new ArrayList<>();

		// public List<Productos>  getProductos(){

		/*   try {

					   Productos aux=null;//Inicializar auxiliar 
					   while(rs.next()){
						   aux=new Productos();
						   aux.setIdProductos(rs.getInt("idProductos"));
						   aux.setNombre(rs.getString("nombre"));
						   aux.setPrecio(rs.getDouble("precio"));
						   aux.setExistencias(rs.getInt("existencias"));
						   aux.setDescripcion(rs.getString("descripcion"));
						   aux.setOrigen(rs.getString("origen"));
						   aux.setImagen(rs.getBytes("imagen"));
						   list.add(aux);
					   }
				} catch (SQLException e) {
					e.printStackTrace();
				} 
				return list;*/
	}//end
	//Dicho de otra forma, en un ejemplo muy basico, la interaccion entre ambos quedaria descrita por el siguiente codigo:

	//Servidor.java (clase imaginaria creada por mi)
	//1-Aceptar la conexion
	Socket server =new ServerSocket().accept();
	//2-Leer codigo de operacion

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


//int codigoOperacion = flujoEntradaCliente.readInt( );
////saber que hacer
//( if codigoOperacion == 1 ){
//    //Enviar el listado de productos
//    flujoSalidaCliente.writeObject( productosDao.getAll( ) );
//}
//else if( codigoOperacion == 2 ){
//    //recibir los productos comprados (carrito de compras)
//    List<Productos> productosComprados = (List<Productos>)flujoEntradaCliente.readObject( );
//    //Hacer cosas para actualizar el inventario... (codigo no explicado)++++++++++++ok
//}
//+++++++++++++++++++++++++
//Cliente.java (clase imaginaria creada por mi)
//1-Solicitar una conexion

Socket conexionAlServer = new Socket( Host, port );
//Enviar un codigo de operacion (en este ejemplo, para obtener todos los productos)

//flujoSalidaServidor.writeInt( 1 );
//recibir los productos
//List<Productos> productos = (List<Productos> )flujoEntradaServidor.readObject( );
//Hacer operaciones varias...
//Y despues de esas operaciones varias, volver a solicitar los productos de la BD del servidor
//flujoSalidaServidor.writeInt( 1 );
//recibir los productos
//List<Productos> productos = (List<Productos> )flujoEntradaServidor.readObject( );

//En los codigos de arriba, algunas cosas se han obviado (tal como obtener el flujo de entrada/salida del cliente/servidor)
//(y ponerlo en un Obje3ctOutputStream o un ObjectINputStream segun corresponda)
//7oh.........si lo estado analizando...cielos...ok.......
//Ese codigo obviamente no compila, pero es a grandes rasgos el procedimiento a seguir para el cliente/servidor


//Socket cliente = servidorSocket.accept( );
//2-Leer codigo de operacion

//int codigoOperacion = flujoEntradaCliente.readInt( );
////saber que hacer
//( if codigoOperacion == 1 ){
//    //Enviar el listado de productos
//    flujoSalidaCliente.writeObject( productosDao.getAll( ) );
//}
//else if( codigoOperacion == 2 ){
//    //recibir los productos comprados (carrito de compras)
//    List<Productos> productosComprados = (List<Productos>)flujoEntradaCliente.readObject( );
//    //Hacer cosas para actualizar el inventario... (codigo no explicado)++++++++++++ok
//}







