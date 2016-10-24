package com.redes.p2.cliente.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.redes.p2.cliente.ConexionConServidor;
import com.redes.p2.model.Productos;

public class ConexionCliente {

	private JFrame frmConexionCliente;
	private JTextField tfDireccion;
	private static final String HOST = "127.0.0.1";
	//private static final int port = 4070;
	
	
	//Creacion de socket de @.@. conexion
	/*Socket	socket = new Socket(HOST ,port);
	ObjectOutputStream  os = new ObjectOutputStream(socket.getOutputStream());
	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
	//ler lista del servidor
	List< Productos> list = (List<Productos>)in.readObject();*/


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
			public void actionPerformed(ActionEvent e) {
				int port= Integer.parseInt(tfDireccion.getText());
				
				/**Partes de sockets @_&**/
				
				try {
					ConexionConServidor.inicializarCliente(HOST, port);
					//leer lista del servidor
					List< Productos> list = ConexionConServidor.getCatalogoRemoto( );
					//"list" ya contiene los datos recibidos del server...
					//El cliente tiene las operaciones de "read" y "write",
					//así como el servidor tiene sus operaciones de "read" y "write"
					//El cliente puede leer(recibir) datos del servidor y 
					//escribir (mandar) datos al servidor
					System.out.println("Se realizo la conexion de cliente");
					System.out.println( "datos recibidos:" );
					for( Productos producto : list ){
						System.out.println( producto.getNombre( ) );
					}
				} catch (IOException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				frmConexionCliente.dispose();
			}
		});
		btnConectarse.setBounds(174, 142, 117, 25);
		frmConexionCliente.getContentPane().add(btnConectarse);
	}
}
