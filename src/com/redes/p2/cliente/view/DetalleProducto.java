package com.redes.p2.cliente.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.redes.p2.cliente.BaseDatosCarrito;
import com.redes.p2.cliente.ConexionConServidor;
import com.redes.p2.model.Productos;
import com.redes.p2.servidor.dao.ProductosDao;

public class DetalleProducto {
	

	private JFrame frmDetalleDelProducto;
	private JTextField idTf;
	private JTextField nombreTf;
	private JTextField precioTf;
	private JTextField descripcionTf;
	private JTextField origenTf;
	private JTextField existenciasTf;
	
	/**
	 * Create the application. por eso tenia comentatdo lo del constructor ya que asi no sale error al instanciar botton
	 * Pero si sigue comentado, no se puede probar 
	 */
	public DetalleProducto( Productos producto, CatalogoProductos principal ) {
		init( producto, principal ); //MIsas ya se por que 
		//yo ya se...
		//recuerdas cuando te dije que seria mejor tener un proyecto para el cliente,
		//Y otro proyecto para el servidor...?
		//si por esto
		//sipo...
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void init(Productos producto, CatalogoProductos principal) {
		
		frmDetalleDelProducto = new JFrame();
		frmDetalleDelProducto.setTitle("Detalle del producto");
		frmDetalleDelProducto.setBounds(100, 100, 450, 300);
		frmDetalleDelProducto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDetalleDelProducto.getContentPane().setLayout(null);
		
		/**Etiquetas de producto*/
	
		JLabel lblNombre = new JLabel("nombre");
		lblNombre.setBounds(12, 43, 70, 15);
		frmDetalleDelProducto.getContentPane().add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("precio");
		lblNewLabel.setBounds(12, 70, 70, 15);
		frmDetalleDelProducto.getContentPane().add(lblNewLabel);
		
		JLabel lblDescripcion = new JLabel("descripcion");
		lblDescripcion.setBounds(12, 97, 88, 15);
		frmDetalleDelProducto.getContentPane().add(lblDescripcion);
		
		JLabel lblOrigen = new JLabel("origen");
		lblOrigen.setBounds(12, 122, 70, 15);
		frmDetalleDelProducto.getContentPane().add(lblOrigen);
		
		JLabel lblExistencias = new JLabel("existencias");
		lblExistencias.setBounds(12, 149, 88, 15);
		frmDetalleDelProducto.getContentPane().add(lblExistencias);
		
		JLabel lblCantidadAComprar = new JLabel("cantidad a comprar");
		lblCantidadAComprar.setBounds(12, 176, 143, 15);
		frmDetalleDelProducto.getContentPane().add(lblCantidadAComprar);
		//Boton Cancelar y Aceptar
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(38, 234, 117, 25);
		frmDetalleDelProducto.getContentPane().add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ProductosDao dao = new ProductosDao( );
				dao.inicializarConexion();
				frmDetalleDelProducto.dispose( );
				principal.init( dao.getProductos() );
			}
		});

		JButton btnComprar = new JButton("comprar");
		btnComprar.setBounds(246, 234, 117, 25);
		frmDetalleDelProducto.getContentPane().add(btnComprar);
		 
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAcceptPressed( producto, principal );
			}
		});
		
		
		
	   //TextFields
		idTf = new JTextField( Integer.toString( producto.getIdProductos( ) ) );
		idTf.setEditable(false);
		idTf.setBounds(146, 20, 114, 19);
		//idTf.setBounds(115, 123, 124, 19);
		frmDetalleDelProducto.getContentPane().add(idTf);
		idTf.setColumns(10);
		
		
		nombreTf = new JTextField(producto.getNombre());
		nombreTf.setBounds(146, 41, 114, 19);
		frmDetalleDelProducto.getContentPane().add(nombreTf);
		nombreTf.setColumns(10);
		
		precioTf = new JTextField(producto.getPrecio().toString());
		precioTf.setBounds(146, 68, 114, 19);
		frmDetalleDelProducto.getContentPane().add(precioTf);
		precioTf.setColumns(10);
		
		descripcionTf= new JTextField(producto.getDescripcion());
		descripcionTf.setBounds(146, 95, 114, 19);
		frmDetalleDelProducto.getContentPane().add(descripcionTf);
		descripcionTf.setColumns(10);
		
		origenTf = new JTextField(producto.getIdProductos());
		origenTf.setBounds(146, 120, 114, 19);
		frmDetalleDelProducto.getContentPane().add(origenTf);
		origenTf.setColumns(10);
		
		existenciasTf = new JTextField(producto.getExistencias());
		existenciasTf.setBounds(146, 147, 114, 19);
		frmDetalleDelProducto.getContentPane().add(existenciasTf);
		existenciasTf.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(186, 174, 28, 20);
		frmDetalleDelProducto.getContentPane().add(spinner);
	}
	/**Metodo para el boton Comprar**/
	
	private void onAcceptPressed( Productos producto, CatalogoProductos principal ){
		Productos aux = new Productos();

		String nombre = nombreTf.getText() ;
		String precio = precioTf.getText();
		String Existencias = existenciasTf.getText();
		String Descripcion = descripcionTf.getText();
		String Origen = origenTf.getText();
		/**Validacion de campos vacios @.@.*/
		if( nombre.replaceAll(" ", "").length() == 0 ||
				precio.replaceAll(" ", "").length()==0||
				Existencias.replaceAll(" ", "").length()==0||
				Descripcion.replaceAll(" ", "").length()==0||
				Origen.replaceAll(" ", "").length()==0)
		{
			JOptionPane.showMessageDialog( null, "Se detectaron campos vacios","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		else{
			aux.setIdProductos( Integer.parseInt( idTf.getText( ) ) );
			aux.setNombre(nombre);
			aux.setPrecio(Double.parseDouble(precio));
			aux.setExistencias(Integer.parseInt(Existencias));
			aux.setDescripcion(Descripcion);
			aux.setOrigen(Origen);
			
			//Agregar el producto al carrito de compras
			BaseDatosCarrito.agregar( aux );

			frmDetalleDelProducto.dispose( );
			try {
				principal.init( ConexionConServidor.getCatalogoRemoto( ) );
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}//end if-else isValid
	}//end onAccptPressed
	
	
}//End class
