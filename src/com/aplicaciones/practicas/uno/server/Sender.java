package com.aplicaciones.practicas.uno.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketOptions;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.redes.p2.dao.ProductosDao;
import com.redes.p2.model.Productos;
import com.redes.p2.servidor.view.CatalogoDeProductos;

/**
 *  Esta clase realiza el envio  al 
 *  servidor
 */
public class Sender implements Runnable {
	private Socket socket;
	/**Flujo de entreda**/
	private DataInputStream dis; 
	/**Flujo de salida **/
	private DataOutputStream dos;
	/**Archivo**/
	private File file;
//private List<Product> products;
	/**Interfaz que mostrara el estado del envio del archivo**/
	/*Lista*/
	private List<Productos> productos;
	
    private ObjectInputStream ois;
	
	/**
	 *  Se sierra el socket sin enviar un archivo al servidor
	 **/
	public void abruptClose( ){
		if( socket == null ){
			return;
		}
		
		try {
			/**Desborda el maximo de 4 bytes**/
			dos.writeInt( -1 );
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			close( );
		}
	}
	
	/**
	 *  Se sierran los flujos de los sockets
	 *  Si no envia una excepcion
	 */
	public void close( ){
		if( socket != null ){
			try {
				dos.close( );
				dis.close( );
				socket.close( );
				
				socket = null;
				dis = null;
				dos = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public DataInputStream getDis() {
		return dis;
	}
	
	public DataOutputStream getDos() {
		return dos;
	}
	

	/**
	 * Se obtiene el host asociado con el puerto
	 *  Si el  socket es <code>null</code>
	 */
	public String getHost( ){
		if( socket == null || socket.getInetAddress() == null ){
			return null;
		}
		return socket.getInetAddress( ).getHostAddress( );
	}
	
	public boolean getKeepAlive() throws SocketException {
		return socket.getKeepAlive();
	}
	
	public boolean getOOBInline() throws SocketException {
		return socket.getOOBInline();
	}
	
	/**
	 *  Obten el numero del puerto asociado con el socket
	 * Regresa -1 si el socket es nulo
	 */
	public int getPort( ){
		if( socket == null ){
			return -1;
		}
		return socket.getPort( );
	}

	public int getReceiveBufferSize() throws SocketException {
		return socket.getReceiveBufferSize();
	}



	public int getSendBufferSize() throws SocketException {
		return socket.getSendBufferSize();
	}
	
	
	
	public Socket getSocket() {
		return socket;
	}

	/**
     * Cierra el socket
     */
	public int getSoLinger() throws SocketException {
		return socket.getSoLinger();
	}

	public int getSoTimeout() throws SocketException {
		return socket.getSoTimeout();
	}

	public boolean getTcpNoDelay() throws SocketException {
		return socket.getTcpNoDelay();
	}

	/**
	 *Conoexion de cliente y socket inicializada
	 */
	public boolean init( int numOfFiles ){
		if( socket == null ){
			return false;
		}
		
		
		return true;
		
	}
	
	/**
	 * Abre el puerto de comunicacion
	 */
	public boolean open( String host, int port ){
		try {
			socket = new Socket( host, port );
			dis = new DataInputStream( socket.getInputStream() );
			dos = new DataOutputStream( socket.getOutputStream() );
                        
                        System.out.println("Conectando CLIENTE");
                        
                       // List list = null;
                        List<Productos> list = null;
                        ois= new ObjectInputStream(socket.getInputStream());
                        list= new ArrayList<Productos>();
                        ois.readObject();
                        //EnvioCatalogo
                        ProductosDao miProducto = new ProductosDao( );
                       // List<Productos> list = null;
                        CatalogoDeProductos catalogoDeProductos = new CatalogoDeProductos();
                        
                        System.out.println("Conectando a la base de datos");
                        miProducto.inicializarConexion( );
                        
                        System.out.println("Obteniendo catalogos");
                        list = miProducto.getProductos( );
                        System.out.println( "Cargando vista de catalogos..." );
                        catalogoDeProductos.init( list );
                        
                        
                        
                        //list=(List) ois.readObject();
                        //CatalogoProductos miCatalago = new CatalogoProductos(list);
                        System.out.println(list);
                        // while(socket != null ){
                            
                     //  }
		} catch (UnknownHostException e) {
			System.err.println( "El host es  " + host + " no se sabe el numero de puerto"  );
			e.printStackTrace( );
			socket = null;
			return false;
		}catch( ConnectException e ){
			System.err.println( "El host es( " + host + " ) el numero de puerto es( " + port + "  ) el puerto no es valido" );
			e.printStackTrace( );
			return false;
		} catch (IOException e) {
			e.printStackTrace( );
			socket = null;
			return false;
		} catch (ClassNotFoundException ex) {
                Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		return true;
	}

	/**
	 * Inicializa la conexion con el servidor
	 */
	public boolean open( String host, int port, int numOfFiles ){
		return open( host, port ) && init( numOfFiles );
	}

	@Override
	public void run() {
		
	}

	/**
	 * Envia un solo archivo al servidor
	 */
	
	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

	public void setFile(File file) {
		this.file = file;
	}
	public void setKeepAlive(boolean on) throws SocketException {
		socket.setKeepAlive(on);
	}
	public void setOOBInline(boolean on) throws SocketException {
		socket.setOOBInline(on);
	}
	public void setReceiveBufferSize(int size) throws SocketException {
		socket.setReceiveBufferSize(size);
	}
	public void setSendBufferSize(int size) throws SocketException {
		socket.setSendBufferSize(size);
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public void setSoLinger(boolean on, int linger) throws SocketException {
		socket.setSoLinger(on, linger);
	}
	public void setSoTimeout(int timeout) throws SocketException {
		socket.setSoTimeout(timeout);
	}
	public void setTcpNoDelay(boolean on) throws SocketException {
		socket.setTcpNoDelay(on);
	}
}
