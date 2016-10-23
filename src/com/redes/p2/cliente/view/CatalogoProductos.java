package com.redes.p2.cliente.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.redes.p2.model.Productos;

public class CatalogoProductos {

	private JFrame frmCatalogoDeProductos;
	private JTextField  nombreTf;
	private JTextField  precioTf;
	private JTextField  existenciasTf;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	}*/

	/**
	 * Create the application.
	 */
	//public CatalogoProductos() {
		//initialize();
	//}

	/**
	 * Initialize the contents of the frame.
	 */
	//detalleProducto para que es?
	//Es para poder instanciar DetalleProducto en Boton Comprar
	//Lo quitare :)
	//Metodo "init" debe ser publico para que pueda ser llamado
	//desde otras clases
	public void init(List<Productos> productos  ) {
		frmCatalogoDeProductos = new JFrame();
		frmCatalogoDeProductos.setTitle("Catalogo de productos @.@");
		frmCatalogoDeProductos.setBounds(100, 100, 450, 300);
		frmCatalogoDeProductos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmCatalogoDeProductos.getContentPane().setLayout(springLayout);
		
		if(!productos.isEmpty()){
			//ProductoPanel produc = new ProductoPanel(productos.containsAll(productos));
		}
		
		
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
		
		 nombreTf = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, lblImagen, 0, SpringLayout.EAST, nombreTf);
		springLayout.putConstraint(SpringLayout.NORTH, nombreTf, 0, SpringLayout.NORTH, lblNombre);
		springLayout.putConstraint(SpringLayout.WEST, nombreTf, 26, SpringLayout.EAST, lblNombre);
		frmCatalogoDeProductos.getContentPane().add(nombreTf);
		nombreTf.setColumns(10);
		
		precioTf = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, precioTf, 6, SpringLayout.SOUTH, precioTf);
		springLayout.putConstraint(SpringLayout.EAST, precioTf, 0, SpringLayout.EAST, precioTf);
		frmCatalogoDeProductos.getContentPane().add(precioTf);
		precioTf.setColumns(10);
		
		existenciasTf = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, existenciasTf, 6, SpringLayout.EAST, lblExistencias);
		springLayout.putConstraint(SpringLayout.SOUTH, existenciasTf, 0, SpringLayout.SOUTH, lblExistencias);
		frmCatalogoDeProductos.getContentPane().add(existenciasTf);
		existenciasTf.setColumns(10);
		
		JButton btnComprar = new JButton("Comprar");
		springLayout.putConstraint(SpringLayout.NORTH, btnComprar, 38, SpringLayout.SOUTH, existenciasTf);
		springLayout.putConstraint(SpringLayout.WEST, btnComprar, 0, SpringLayout.WEST, existenciasTf);
		//Instanciar a metodo DetalleProducto
		frmCatalogoDeProductos.getContentPane().add(btnComprar);
		btnComprar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				onComprar( ); //Instancia de clase
			}
		});
		
		
		JButton btnVerCarrito = new JButton("Ver Carrito");
		springLayout.putConstraint(SpringLayout.NORTH, btnVerCarrito, 19, SpringLayout.NORTH, frmCatalogoDeProductos.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnVerCarrito, -38, SpringLayout.EAST, frmCatalogoDeProductos.getContentPane());
		frmCatalogoDeProductos.getContentPane().add(btnVerCarrito);
		
		btnVerCarrito.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				onSeeCar(); //Instancia de clase
			}
		});
		
		
		
		
	}

	private void onComprar( ){
		new DetalleProducto( ); //Aqui pero m...realmente no lo uso 
		this.dispose( );
	}
	
	private void onSeeCar(){
		new CarritoCompra();
		this.dispose();
		frmCatalogoDeProductos.dispose();
	}

	public void dispose() {
		frmCatalogoDeProductos.dispose();
	}
	
	
}
