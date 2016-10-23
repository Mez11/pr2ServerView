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

public class AgregarProducto {

	private JFrame frmAgregarProducto;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblImagen;
	/**
	 * variable para guardar el archivo que el usuario haya elegido
	 */
	private File imageFile;
	
	
	/**
	 * Metodo que se ejecuta cuando
	 * el usuario presiona el boton de "Aceptar" 
	 */
	private void onAcceptPressed( CatalogoDeProductos principal ){
		//AGREGAR PRODUCTOS
        Productos producto = new Productos();
        ProductosDao dao = null;
        String nombre =textField_1.getText() ;
        String precio=textField_2.getText();
        String Existencias=textField_3.getText();
        String Descripcion=textField_4.getText();
        String Origen=textField_5.getText();
        if(nombre.replaceAll(" ", "").length()==0||precio.replaceAll(" ", "").length()==0||Existencias.replaceAll(" ", "").length()==0||Descripcion.replaceAll(" ", "").length()==0||Origen.replaceAll(" ", "").length()==0){
            JOptionPane.showMessageDialog( null, "Se detectaron campos vacios","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else{
	        producto.setNombre(nombre);
	        producto.setPrecio(Double.parseDouble(precio));
	        producto.setExistencias(Integer.parseInt(Existencias));
	        producto.setDescripcion(Descripcion);
	        producto.setOrigen(Origen);
	        //****EXTRAER LA IMAGEN*****
	        producto.setImagen( ImageUtils.extractImage( imageFile ) );
	        
	        dao = new ProductosDao();
	        dao.inicializarConexion();
	        dao.create(producto);
	        int res=JOptionPane.showConfirmDialog( null, "¿Desea agregar otro producto?",null,JOptionPane.YES_NO_OPTION);
	        frmAgregarProducto.dispose();
	        if( res == 0 ){
	            init( principal );
	        } else {
	        	principal.init( dao.getProductos( ) );
	        }
        }//end if-else
	}//end onAcceptPressed

	/**
	 * @wbp.parser.entryPoint
	 */
	public void init( CatalogoDeProductos principal ) {
		frmAgregarProducto = new JFrame();
		frmAgregarProducto.setTitle("Agregar producto");
		frmAgregarProducto.setBounds(100, 100, 450, 300);
		frmAgregarProducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAgregarProducto.getContentPane().setLayout(null);
				
		lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(181, 12, 70, 45);
		frmAgregarProducto.getContentPane().add( lblImagen );
		
		JButton btnEleguirImagen = new JButton("Eleguir imagen");
		btnEleguirImagen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//guardar el archivo elegido en la variable global
				imageFile =  ImageUtils.getImagen( lblImagen, frmAgregarProducto.getContentPane( ) );
			}
		});
		btnEleguirImagen.setBounds(146, 69, 144, 25);
		frmAgregarProducto.getContentPane().add(btnEleguirImagen);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(22, 103, 70, 15);
		frmAgregarProducto.getContentPane().add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 130, 70, 15);
		frmAgregarProducto.getContentPane().add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("Precio");
		lblNewLabel.setBounds(22, 157, 70, 15);
		frmAgregarProducto.getContentPane().add(lblNewLabel);
		
		JLabel lblExistencias = new JLabel("Existencias");
		lblExistencias.setBounds(22, 184, 96, 15);
		frmAgregarProducto.getContentPane().add(lblExistencias);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(22, 211, 106, 15);
		frmAgregarProducto.getContentPane().add(lblNewLabel_1);
		
		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(22, 238, 70, 15);
		frmAgregarProducto.getContentPane().add(lblOrigen);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(319, 206, 117, 25);
		frmAgregarProducto.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmAgregarProducto.dispose();
				ProductosDao dao = new ProductosDao( );
				dao.inicializarConexion();
				principal.init( dao.getProductos() );
			}
		});
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(319, 147, 117, 25);
		frmAgregarProducto.getContentPane().add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//cuando el usuario presione "aceptar",
				//llamar al metodo "onAcceptPressed"
				onAcceptPressed( principal );
			}
		});
		
		textField = new JTextField();
		textField.setBounds(110, 106, 124, 19);
		frmAgregarProducto.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
                
		textField_1 = new JTextField();
		textField_1.setBounds(110, 128, 124, 19);
		frmAgregarProducto.getContentPane().add(textField_1);
		textField_1.setColumns(10);
              
		
		textField_2 = new JTextField();
		textField_2.setBounds(110, 155, 124, 19);
		frmAgregarProducto.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(110, 182, 124, 19);
		frmAgregarProducto.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(110, 209, 124, 19);
		frmAgregarProducto.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(110, 236, 124, 19);
		frmAgregarProducto.getContentPane().add(textField_5);
		textField_5.setColumns(10);
        frmAgregarProducto.setVisible(true);
	}
}
