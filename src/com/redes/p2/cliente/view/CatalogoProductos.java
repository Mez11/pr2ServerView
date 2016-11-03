package com.redes.p2.cliente.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.redes.p2.cliente.BaseDatosCarrito;
import com.redes.p2.model.Productos;

public class CatalogoProductos {

	private JFrame frmCatalogoDeProductos;
	private List<Productos> productosList;
	private int numeroDeProductos;
	private int productoActual;
	private ProductoPanel productoPanel;
	
	private void mostrarProducto( Productos producto ){
		
	}

	private void inicializarParametrosLista(  ){
		//guardar el tamaño de la lista (la lista va de 0 a n - 1)
		numeroDeProductos = productosList.size( ) - 1;
		//establecer el producto actual (empezamos en el cero)
		productoActual = 0;
		//Mostrar el primer producto en el catalogo principal
		mostrarProducto( productosList.get( productoActual ) );
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void init( List<Productos> productosList  ) {
		//Validacion: Si no hay productos, no hacer nada
		if( productosList == null ||  productosList.isEmpty() ){
			JOptionPane.showMessageDialog( null, "No hay productos para mostrar", "Nada por hacer", JOptionPane.WARNING_MESSAGE );
			return;
		}
		//setter
		this.productosList = productosList;
		//init de ventanas
		frmCatalogoDeProductos = new JFrame();
		frmCatalogoDeProductos.setTitle("Catalogo de productos @.@");
		frmCatalogoDeProductos.setBounds(100, 100, 450, 300);
		frmCatalogoDeProductos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCatalogoDeProductos.getContentPane().setLayout(null);
		
		inicializarParametrosLista( );
		//Se inicializa con el primer producto
		productoPanel = new ProductoPanel( productosList.get( productoActual ), this );
		productoPanel.setBounds(26, 12, 204, 178);
		productoPanel.setVisible( true );
		frmCatalogoDeProductos.getContentPane( ).add( productoPanel );
		
		JButton btnVerCarrito = new JButton("Ver Carrito");
		btnVerCarrito.setBounds(273, 19, 137, 27);
		frmCatalogoDeProductos.getContentPane().add(btnVerCarrito);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				siguienteProducto( );
			}
		});
		btnSiguiente.setBounds(273, 58, 142, 27);
		frmCatalogoDeProductos.getContentPane().add(btnSiguiente);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anteriorProducto( );
			}
		});
		btnAnterior.setBounds(273, 97, 142, 27);
		frmCatalogoDeProductos.getContentPane().add(btnAnterior);
		
		JButton btnVerDetalle = new JButton("Comprar @.@.!!");
		btnVerDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDetalle( );
			}
		});
		btnVerDetalle.setBounds(36, 191, 142, 27);
		frmCatalogoDeProductos.getContentPane().add(btnVerDetalle);
		
		btnVerCarrito.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				onSeeCar(); //Instancia de clase
			}
		});
		
		
		frmCatalogoDeProductos.setVisible( true );
		
	}

	/*private void onComprar( ){
		new DetalleProducto();
		this.dispose( );
	}*/
	
	private Productos crearProductoDePrueba( ){
		Productos aux = new Productos( );
		aux.setIdProductos( 10 );
		aux.setNombre( "Soy un producto de prueba" );
		aux.setExistencias( 10 );
		aux.setPrecio( 20.3 );
		aux.setCantidadComprada( 2 );
		return aux;
	}
	
	private void mostrarDetalle(  ){
		new DetalleProducto( productosList.get( productoActual ), this );
		this.dispose( );
	}
	
	private void siguienteProducto( ){
		if( productoActual == numeroDeProductos ){
			JOptionPane.showMessageDialog( null, "No hay más productos", "Productos", JOptionPane.INFORMATION_MESSAGE );
		}
		else{			
			productoActual++;
			mostrarProducto( productosList.get( productoActual ) );
		}//fin if-else
	}//fin siguienteProducto
	
	private void anteriorProducto( ){
		if( productoActual == 0 ){
			JOptionPane.showMessageDialog( null, "No hay más productos", "Productos", JOptionPane.INFORMATION_MESSAGE );
		}
		else{			
			productoActual--;
			mostrarProducto( productosList.get( productoActual ) );
		}//fin if-else
	}//fin siguienteProducto

	private void onSeeCar(){
		BaseDatosCarrito.agregar( crearProductoDePrueba( ) );
		new CarritoCompra();
		this.dispose();
		frmCatalogoDeProductos.dispose();
	}

	public void dispose() {
		frmCatalogoDeProductos.dispose();
	}
}
