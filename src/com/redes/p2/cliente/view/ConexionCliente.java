package com.redes.p2.cliente.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.redes.p2.cliente.ConexionConServidor;

public class ConexionCliente {

	private JFrame frmConexionCliente;
	private JTextField tfDireccion;
	private JTextField tfIp;
	
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 4070;
	
	
	
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

		JLabel lblDireccionDelServidor = new JLabel("Direccion del  puerto servidor");
		lblDireccionDelServidor.setBounds(42, 33, 210, 15);
		frmConexionCliente.getContentPane().add(lblDireccionDelServidor);
		//Ip
		JLabel lblNewLabel = new JLabel("Ip del host");
		lblNewLabel.setBounds(45, 60, 145, 15);
		frmConexionCliente.getContentPane().add(lblNewLabel);
		
		tfIp = new JTextField();
		tfIp.setBounds(270, 58, 114, 19);
		frmConexionCliente.getContentPane().add(tfIp);
		tfIp.setColumns(10);
		tfIp.setText("127.0.0.1"); 
		

		tfDireccion = new JTextField();
		tfDireccion.setBounds(270, 31, 114, 19);
		frmConexionCliente.getContentPane().add(tfDireccion);
		tfDireccion.setColumns(10);
		tfDireccion.setText( Integer.toString( PORT ) );

		JButton btnConectarse = new JButton("Conectarse");
		btnConectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onConnectar( );
			}
		});
		btnConectarse.setBounds(174, 142, 117, 25);
		frmConexionCliente.getContentPane().add(btnConectarse);
		
		
	}
	
	private void onConnectar( ){
		int port= Integer.parseInt(tfDireccion.getText());
		
		/**Partes de sockets @_&**/
		
		try {
			ConexionConServidor.inicializarCliente(HOST, port);
			//leer lista del servidor y enviarsela a catalogoProductos
			new CatalogoProductos( ).init( ConexionConServidor.getCatalogoRemoto( ) );
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		frmConexionCliente.dispose();
	}
}//fin clase
