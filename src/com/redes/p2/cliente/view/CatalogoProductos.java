package com.redes.p2.cliente.view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class CatalogoProductos {

	private JFrame frmCatalogoDeProductos;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CatalogoProductos window = new CatalogoProductos();
					window.frmCatalogoDeProductos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CatalogoProductos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCatalogoDeProductos = new JFrame();
		frmCatalogoDeProductos.setTitle("Catalogo de productos @.@");
		frmCatalogoDeProductos.setBounds(100, 100, 450, 300);
		frmCatalogoDeProductos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmCatalogoDeProductos.getContentPane().setLayout(springLayout);
		
		JLabel lblImagen = new JLabel("Imagen");
		springLayout.putConstraint(SpringLayout.NORTH, lblImagen, 12, SpringLayout.NORTH, frmCatalogoDeProductos.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblImagen, 34, SpringLayout.WEST, frmCatalogoDeProductos.getContentPane());
		frmCatalogoDeProductos.getContentPane().add(lblImagen);
		
		JLabel lblNombre = new JLabel("Nombre");
		springLayout.putConstraint(SpringLayout.SOUTH, lblImagen, -6, SpringLayout.NORTH, lblNombre);
		springLayout.putConstraint(SpringLayout.NORTH, lblNombre, 74, SpringLayout.NORTH, frmCatalogoDeProductos.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNombre, 10, SpringLayout.WEST, frmCatalogoDeProductos.getContentPane());
		frmCatalogoDeProductos.getContentPane().add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio");
		springLayout.putConstraint(SpringLayout.NORTH, lblPrecio, 16, SpringLayout.SOUTH, lblNombre);
		springLayout.putConstraint(SpringLayout.WEST, lblPrecio, 10, SpringLayout.WEST, frmCatalogoDeProductos.getContentPane());
		frmCatalogoDeProductos.getContentPane().add(lblPrecio);
		
		JLabel lblExistencias = new JLabel("Existencias");
		springLayout.putConstraint(SpringLayout.NORTH, lblExistencias, 17, SpringLayout.SOUTH, lblPrecio);
		springLayout.putConstraint(SpringLayout.WEST, lblExistencias, 0, SpringLayout.WEST, lblNombre);
		frmCatalogoDeProductos.getContentPane().add(lblExistencias);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, lblImagen, 0, SpringLayout.EAST, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, lblNombre);
		springLayout.putConstraint(SpringLayout.WEST, textField, 26, SpringLayout.EAST, lblNombre);
		frmCatalogoDeProductos.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField);
		frmCatalogoDeProductos.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 6, SpringLayout.EAST, lblExistencias);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_2, 0, SpringLayout.SOUTH, lblExistencias);
		frmCatalogoDeProductos.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnComprar = new JButton("Comprar");
		springLayout.putConstraint(SpringLayout.NORTH, btnComprar, 38, SpringLayout.SOUTH, textField_2);
		springLayout.putConstraint(SpringLayout.WEST, btnComprar, 0, SpringLayout.WEST, textField_2);
		//Instanciar a metodo DetalleProducto
		
		frmCatalogoDeProductos.getContentPane().add(btnComprar);
		
		JButton btnVerCarrito = new JButton("Ver Carrito");
		springLayout.putConstraint(SpringLayout.NORTH, btnVerCarrito, 19, SpringLayout.NORTH, frmCatalogoDeProductos.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnVerCarrito, -38, SpringLayout.EAST, frmCatalogoDeProductos.getContentPane());
		frmCatalogoDeProductos.getContentPane().add(btnVerCarrito);
	}

}
