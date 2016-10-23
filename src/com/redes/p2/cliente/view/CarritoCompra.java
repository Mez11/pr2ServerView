package com.redes.p2.cliente.view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import com.redes.p2.dao.ProductosDao;
import com.redes.p2.model.Productos;

import javax.swing.JButton;


public class CarritoCompra {

	private JFrame frmCarritoDeCompra;
	private JTable table;
	private JLabel lblCantidadEnEl;
	private JTextField textField;
	private JLabel lblIdDelProducto;
	private JTextField textField_1;
	private JButton btnModificar;
	private JButton btnFinalizarCompra;
	private JTable productosTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarritoCompra window = new CarritoCompra();
					window.frmCarritoDeCompra.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CarritoCompra() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmCarritoDeCompra = new JFrame();
		frmCarritoDeCompra.setTitle("Carrito de compra");
		frmCarritoDeCompra.setBounds(100, 100, 450, 300);
		frmCarritoDeCompra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCarritoDeCompra.getContentPane().setLayout(null);
		
		JLabel lblProdutosEnCarrito = new JLabel("Produtos en carrito");
		lblProdutosEnCarrito.setBounds(27, 12, 146, 15);
		frmCarritoDeCompra.getContentPane().add(lblProdutosEnCarrito);
		
		/**Tabla Carrito**/
		table = new JTable();
		table.setBounds(33, 54, 348, 71);
		/**JTable @.@. llenado**/
		
		/***/
		
		String[] columnNames = {"Id",
		                        "Nombre",
		                        "Precio",
		                        "Cantidad"};
		Object[][] data = {


		     { new Integer(1),"Sombrero" ,new Double(10.0),new Integer(1),"Agregar,Modificar,Eliminar"},
		     {new Integer(2),"Sombrero" ,new Double(10.0),new Integer(1),"Agregar,Modificar,Eliminar"},

		     { new Integer(1),"Sombrero" ,new Double(10.0),new Integer(1) },
		     {new Integer(2),"Sombrero" ,new Double(10.0),new Integer(1) }

		};

//Then the Table is constructed using these data and columnNames:
	
	JTable table = new JTable(data, columnNames);
//	JTable(Object[][] ,data, Object[][] ,columnNames);


	//JScrollPane scrollPane = new JScrollPane(table);
	//table.setFillsViewportHeight(true);


	//container.setLayout(new BorderLayout());
	//container.add(table.getTableHeader(), BorderLayout.PAGE_START);
	//container.add(table, BorderLayout.CENTER);
		
		/**Añadiendo a contentPanel*/
		frmCarritoDeCompra.getContentPane().add(table);
		
		lblCantidadEnEl = new JLabel("Cantidad en el carrito");
		lblCantidadEnEl.setBounds(27, 143, 162, 15);
		frmCarritoDeCompra.getContentPane().add(lblCantidadEnEl);
		
		textField = new JTextField();
		textField.setBounds(209, 141, 114, 19);
		frmCarritoDeCompra.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblIdDelProducto = new JLabel("id del producto");
		lblIdDelProducto.setBounds(37, 170, 146, 15);
		frmCarritoDeCompra.getContentPane().add(lblIdDelProducto);
		
		textField_1 = new JTextField();
		textField_1.setBounds(209, 172, 114, 19);
		frmCarritoDeCompra.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(331, 165, 105, 25);
		frmCarritoDeCompra.getContentPane().add(btnModificar);
		
		btnFinalizarCompra = new JButton("Finalizar compra");
		btnFinalizarCompra.setBounds(35, 234, 162, 25);
		frmCarritoDeCompra.getContentPane().add(btnFinalizarCompra);
		
		productosTable = new JTable();
		
		
		productosTable.setModel(
				new DefaultTableModel( data, columnNames ) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JScrollPane tableScroll = new JScrollPane( productosTable );
		tableScroll.setBounds(37, 28, 376, 92);
		frmCarritoDeCompra.getContentPane().add( tableScroll );
	}
	
	//Metodo para mostrar la tabla desde la BD
	
	public void mostrar(){
		  
		  DefaultTableModel modelo =(DefaultTableModel)table.getModel();
		 ProductosDao dao =new ProductosDao();

		 
	/*	ArrayList<Productos> list = ArrayList dao.getProductos();
		  Object[] fila = new Object [modelo.getColumnCount()];
		  for(int i=0; i<list.size();i++){
		    fila[0] = list.get(i).getIdProductos();
		    fila[1] = list.get(i).getNombre();
		    fila[2] = list.get(i).getPrecio();
		     modelo.addRow(fila);*/
		    
		  }
		}

	
//}
