package com.redes.p2.cliente.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.redes.p2.model.Productos;
import com.redes.p2.utis.ImageUtils;

public class ProductoPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6789727953580545184L;
	private JLabel lblNewLabel;
	private JLabel lblNombre;
	private JLabel lblPrecio;
	private JLabel lblExistencia;
	
	public void llenarCampos( Productos producto ){
		if( producto.getImagen() != null ){
			ImageUtils.displayPrettyImage( producto.getImagen(), lblNewLabel );
		} else {
			lblNewLabel.setText( "Imagen no disponible" );
		}
		lblNombre.setText( "Nombre: " + producto.getNombre( )  );
		lblPrecio.setText( "Precio: " + producto.getPrecio( ) );
		lblExistencia.setText( "Existencias: " + producto.getExistencias( ) );
	}//end llenarCampos
	
	/**
	 * Clase para visualizar un producto en especifico
	 * @param producto El producto a desplegar
	 */
	public ProductoPanel( CatalogoProductos principal ) {
		setLayout(null);
		
		lblNewLabel = new JLabel( );
		lblNewLabel.setBounds(35, 25, 94, 93);
		add(lblNewLabel);
		
		lblNombre = new JLabel( );
		lblNombre.setBounds(25, 130, 196, 15);
		add(lblNombre);
		
		lblPrecio = new JLabel( );
		lblPrecio.setBounds(25, 149, 196, 15);
		add(lblPrecio);
		
		lblExistencia = new JLabel( );
		lblExistencia.setBounds(25, 163, 196, 15);
		add(lblExistencia);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DetalleProducto miProducto= new DetalleProducto();
			}
		});
		btnComprar.setBounds(25, 227, 117, 25);
		add(btnComprar);		
	}//end constructor
}//end class