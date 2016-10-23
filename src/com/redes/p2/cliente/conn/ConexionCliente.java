package com.redes.p2.cliente.conn;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.aplicaciones.practicas.uno.server.Sender;
import com.redes.p2.model.Productos;

public class ConexionCliente {

	private JFrame frmConexionCliente;
	private JTextField tfDireccion;
	private static final String HOST = "127.0.0.1";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConexionCliente window = new ConexionCliente();
					window.frmConexionCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConexionCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConexionCliente = new JFrame();
		frmConexionCliente.setTitle("IniciarConexion");
		frmConexionCliente.setBounds(100, 100, 450, 300);
		frmConexionCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConexionCliente.getContentPane().setLayout(null);

		JLabel lblDireccionDelServidor = new JLabel("Direccion del servidor");
		lblDireccionDelServidor.setBounds(42, 33, 200, 15);
		frmConexionCliente.getContentPane().add(lblDireccionDelServidor);

		tfDireccion = new JTextField();
		tfDireccion.setBounds(270, 31, 114, 19);
		frmConexionCliente.getContentPane().add(tfDireccion);
		tfDireccion.setColumns(10);
		tfDireccion.setText("4070");

		JButton btnConectarse = new JButton("Conectarse");
		btnConectarse.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				int port= Integer.parseInt(tfDireccion.getText());
				//Esto no puede ir aqui
				//Sender es para el servidor, no para el cliente ok
				//Sender miCliente = new Sender();
				// miCliente.open(HOST, port);
				/**Partes de sockets @_&**/
				//Socket  esto si 
				try {
					//Si el socket se declara aqui, 
					//se perdera toda comunicacion con el servidor al 
					//terminar el metodo ok entonces iran global}
					//Acui pero creo q muere
					Socket	socket = new Socket(HOST, port);
					ObjectOutputStream  os = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream());


					//ler lista del servidor
					List< Productos> list = (List<Productos>)in.readObject();
					//"list" ya contiene los datos recibidos del server...
					//MIsas tengo una duda---yo asociaba write cuando es del cliente al servidot
					//y read del servidor al cliente pero ahorita estoy viendo que se 
					//usa read para leer cosas del servidor ...pero desde el cliente
					
					//es lo que te habia comentado 
					//Todo depende de con que "vista" estes trabajando
					//El cliente tiene las operaciones de "read" y "write",
					//así como el servidor tiene sus operaciones de "read" y "write"
					//El cliente puede leer(recibir) datos del servidor y 
					//escribir (mandar) datos al servidor
					
					//asi como el servidor puede leer(recibir) datos del cliente y 
					//puede escribir(mandar) datos al cliente
					//YA entiendo es como en servlet se podia hacer request y response solo 
					//la diferencia es con get oh set aTRIBUTE OH parameter ok ya entiendo mas
					//¿?
					//Ah...yo me entiendo ja mi relacion q hice jaa
					//ok... podría decirse que la comunicación entre cliente y servidor
					//es bi-direccional. un servidor le puede mandar un "request" al cliente
					//y un cliente le puede mandar un "request" al servidor
					
					
					System.out.println("Se realizo la conexion de cliente");
					System.out.println( "datos recibidos:" );
					for( Productos producto : list ){
						System.out.println( producto.getNombre( ) );
					}
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmConexionCliente.dispose();
			}
		});
		btnConectarse.setBounds(174, 142, 117, 25);
		frmConexionCliente.getContentPane().add(btnConectarse);
	}
}
