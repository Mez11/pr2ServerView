package com.redes.p2.servidor.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.redes.p2.dao.ProductosDao;
import com.redes.p2.model.Productos;
import com.redes.p2.utis.ImageUtils;

public class EditarProducto  {
	

	private JFrame frmEditarProducto;
	private JTextField idTf;
	private JTextField nombreTf;
	private JTextField precioTf;
	private JTextField existenciasTf;
	private JTextField descripcionTf;
	private JTextField origenTf;
	private JLabel lblImagen;
	//Variable para guardar el archivo que el usuario haya elgido
	private File imageFile;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void init( Productos producto, CatalogoDeProductos principal ) {
		frmEditarProducto = new JFrame();
		frmEditarProducto.setTitle("Editar producto");
		frmEditarProducto.setBounds(100, 100, 450, 300);
		frmEditarProducto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditarProducto.getContentPane().setLayout(null);
		
		
		lblImagen = new JLabel( );
		lblImagen.setBounds(138, 12, 122, 62);
		ImageUtils.displayPrettyImage( producto.getImagen(), lblImagen);
		frmEditarProducto.getContentPane().add(lblImagen);
		
		JButton btnElegirImagen = new JButton("Eleguir imagen");
		btnElegirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imageFile = ImageUtils.getImagen( lblImagen, frmEditarProducto.getContentPane( ) );
			
			}
		});
		btnElegirImagen.setBounds(102, 86, 167, 25);
		frmEditarProducto.getContentPane().add(btnElegirImagen);
		/**Etiquetas de atributos..@.@.*/
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(29, 117, 70, 15);
		frmEditarProducto.getContentPane().add(lblId);
		
		JLabel lblNombre  = new JLabel("Nombre");
		lblNombre .setBounds(12, 144, 70, 15);
		frmEditarProducto.getContentPane().add(lblNombre );
		
		JLabel lblNewLabel_1 = new JLabel("Precio");
		lblNewLabel_1.setBounds(22, 175, 70, 15);
		frmEditarProducto.getContentPane().add(lblNewLabel_1);
		
		JLabel lblExistencias = new JLabel("Existencias");
		lblExistencias.setBounds(12, 202, 103, 15);
		frmEditarProducto.getContentPane().add(lblExistencias);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(250, 123, 92, 15);
		frmEditarProducto.getContentPane().add(lblDescripcion);
		
		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(257, 144, 70, 15);
		frmEditarProducto.getContentPane().add(lblOrigen);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(165, 234, 117, 25);
		frmEditarProducto.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductosDao dao = new ProductosDao( );
				dao.inicializarConexion();
				frmEditarProducto.dispose( );
				principal.init( dao.getProductos() );
			}
		});
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(307, 234, 117, 25);
		frmEditarProducto.getContentPane().add(btnAceptar);
		//Editar  
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onAcceptPressed( producto, principal );
			}
		});
		//Etiqueta de ID
		idTf = new JTextField( Integer.toString( producto.getIdProductos( ) ) );
		idTf.setEditable(false);
		idTf.setBounds(115, 123, 124, 19);
		frmEditarProducto.getContentPane().add(idTf);
		idTf.setColumns(10);
		
		nombreTf = new JTextField( producto.getNombre( ) );
		nombreTf.setBounds(115, 142, 124, 19);
		frmEditarProducto.getContentPane().add(nombreTf);
		nombreTf.setColumns(10);
		
		precioTf = new JTextField( producto.getPrecio( ).toString( ) );
		precioTf.setBounds(115, 173, 124, 19);
		frmEditarProducto.getContentPane().add(precioTf);
		precioTf.setColumns(10);
		
		existenciasTf = new JTextField( Integer.toString( producto.getExistencias( ) ) );
		existenciasTf.setBounds(115, 200, 124, 19);
		frmEditarProducto.getContentPane().add(existenciasTf);
		existenciasTf.setColumns(10);
		
		descripcionTf = new JTextField( producto.getDescripcion( ) );
		descripcionTf.setBounds(349, 121, 87, 19);
		frmEditarProducto.getContentPane().add(descripcionTf);
		descripcionTf.setColumns(10);
		
		origenTf = new JTextField( producto.getOrigen( ) );
		origenTf.setBounds(345, 142, 91, 19);
		frmEditarProducto.getContentPane().add(origenTf);
		origenTf.setColumns(10);
		frmEditarProducto.setVisible(true);
	}
	/**Metodo para el boton aceptar**/
	
	private void onAcceptPressed( Productos producto, CatalogoDeProductos principal ){
		ProductosDao dao = null;
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
			aux.setImagen( ImageUtils.extractImage( imageFile ) );

			dao = new ProductosDao();
			dao.inicializarConexion();
			dao.update( aux );

			frmEditarProducto.dispose();
			principal.init( dao.getProductos( ) );
		}//end if-else isValid
	}//end onAccptPressed
	
}//end class
