package com.redes.p2.cliente.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.redes.p2.cliente.BaseDatosCarrito;
import com.redes.p2.model.Productos;

public class CatalogoProductos {

	private JFrame frmCatalogoDeProductos;
	

	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void init( List<Productos> productosList  ) {
		frmCatalogoDeProductos = new JFrame();
		frmCatalogoDeProductos.setTitle("Catalogo de productos @.@");
		frmCatalogoDeProductos.setBounds(100, 100, 450, 300);
		frmCatalogoDeProductos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if( !productosList.isEmpty() ){		
			System.out.println( "recibida lista" );
			ProductoPanel prueba = new ProductoPanel( productosList.get( 0 ), this );
			prueba.setBounds(26, 12, 204, 178);
			prueba.setVisible( true );
			frmCatalogoDeProductos.getContentPane().setLayout(null);
			//prueba.setBounds( 35, 39, 228, 283 );
			frmCatalogoDeProductos.getContentPane( ).add( prueba );
		}
		JButton btnVerCarrito = new JButton("Ver Carrito");
		btnVerCarrito.setBounds(319, 19, 91, 27);
		frmCatalogoDeProductos.getContentPane().add(btnVerCarrito);
		
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
		//IMPLEMENTACION TEMPORAL
		new DetalleProducto( crearProductoDePrueba(), this );
		this.dispose( );
	}

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
