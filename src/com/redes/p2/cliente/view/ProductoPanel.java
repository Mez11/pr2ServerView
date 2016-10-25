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
	
	/**
	 * Clase para visualizar un producto en especifico
	 * @param producto El producto a desplegar
	 */
	public ProductoPanel( Productos producto, CatalogoProductos principal ) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel( );
		lblNewLabel.setBounds(35, 25, 94, 93);
		if( producto.getImagen() != null ){
			ImageUtils.displayPrettyImage( producto.getImagen(), lblNewLabel );
		} else {
			lblNewLabel.setText( "Imagen no disponible" );
		}
		add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre: " + producto.getNombre( ) );
		lblNombre.setBounds(25, 130, 196, 15);
		add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio: " + producto.getPrecio( ) );
		lblPrecio.setBounds(25, 149, 196, 15);
		add(lblPrecio);
		
		JLabel lblExistencia = new JLabel("Existencias: " + producto.getExistencias( ) );
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
		
	}
	

	

}
