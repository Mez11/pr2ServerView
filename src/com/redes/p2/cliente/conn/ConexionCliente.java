package com.redes.p2.cliente.conn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.aplicaciones.practicas.uno.server.Sender;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
			public void actionPerformed(ActionEvent e) {
				int port= Integer.parseInt(tfDireccion.getText());
				Sender miCliente = new Sender();
				  miCliente.open(HOST, port);
                  frmConexionCliente.setVisible(false);
                  frmConexionCliente.dispose();
				
				//System.out.println( "Aqui conectate..." );
			}
		});
		btnConectarse.setBounds(174, 142, 117, 25);
		frmConexionCliente.getContentPane().add(btnConectarse);
	}
}
