package com.redes.p2.cliente.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.redes.p2.cliente.BaseDatosCarrito;
import com.redes.p2.cliente.ReporteCarro;
import com.redes.p2.model.Productos;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CarritoCompra {

	private JFrame frmCarritoDeCompra;
	private JLabel lblCantidadEnEl;
	private JTextField textField;
	private JLabel lblIdDelProducto;
	private JTextField textField_1;
	private JButton btnModificar;
	private JButton btnFinalizarCompra;
	private JTable productosTable;
	private String[] columnNames = {"Id",
            "Nombre",
            "Precio",
            "Cantidad"};

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
				new DefaultTableModel( null, columnNames ) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 4037384032324381295L;
					Class<?>[] columnTypes = new Class[] {
							Integer.class, String.class, Double.class, Integer.class
					};
					public Class<?> getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
		});
		
		JScrollPane tableScroll = new JScrollPane( productosTable );
		tableScroll.setBounds(37, 28, 376, 92);
		frmCarritoDeCompra.getContentPane().add( tableScroll );
		
		JButton btnImprimirReporte = new JButton("Imprimir reporte");
		btnImprimirReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReporteCarro( ).generarReporte( );
			}
		});
		btnImprimirReporte.setBounds(209, 234, 162, 25);
		frmCarritoDeCompra.getContentPane().add(btnImprimirReporte);
		llenarTabla( );
		frmCarritoDeCompra.setVisible( true );
	}
	
	
	public void llenarTabla(){
		Object [] fila;
		DefaultTableModel modelo =(DefaultTableModel)productosTable.getModel( );
		List<Productos> list = BaseDatosCarrito.getCarrito( );
		
		if( !BaseDatosCarrito.hayProductos( ) ){
			return;
		}
		
		fila = new Object[4];
		for( int i = 0; i < list.size(); i++ ){
			System.out.println( "Agregando un producto.." );
			fila[0] = list.get( i ).getIdProductos( );
		    fila[1] = list.get(i).getNombre();
		    fila[2] = list.get(i).getPrecio();
		    fila[3] = list.get(i).getCantidadComprada( );
		    modelo.addRow( fila );
		}

	}//end mostrar
}//end class