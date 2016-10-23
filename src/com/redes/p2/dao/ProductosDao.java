package com.redes.p2.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.redes.p2.model.Productos;

public class ProductosDao {
	
	
/**Realizacion de Consultas SQL**/
	private  static final String SQL_INSERT=
				"insert into Productos"
				+"(nombre,precio,existencias,descripcion,origen,imagen)"
				+"values (?,?,?,?,?,?)";

		private static final String SQL_UPDATE=
				"update Productos set nombre =?,"
				+"precio =?,existencias =?,"
				+"descripcion=? ,origen=?, imagen=? where idProductos =?";
		
		private static final String SQL_DELETE=
				"delete from Productos where idProductos =?";
		
		private static final String SQL_SELECT=
				"select * from Productos where idProductos =?";
		
		
		
		private static final String SQL_SELECT_ALL=
				"select * from Productos";
		private Connection con;
		
		/**
		 * Trae todos los productos
		 * @return
		 */
	   public List<Productos>  getProductos(){
		   List<Productos> list = null; 
			   try {
				   PreparedStatement r = con.prepareStatement( SQL_SELECT_ALL); //Inicializa conexion y trae sentencia de Query
				   ResultSet rs =r.executeQuery();
				   list = new ArrayList<Productos> ();//INicializar lista
				   Productos aux=null;//Inicializar auxiliar 
				   while(rs.next()){
					   aux=new Productos();
					   aux.setIdProductos(rs.getInt("idProductos"));
					   aux.setNombre(rs.getString("nombre"));
					   aux.setPrecio(rs.getDouble("precio"));
					   aux.setExistencias(rs.getInt("existencias"));
					   aux.setDescripcion(rs.getString("descripcion"));
					   aux.setOrigen(rs.getString("origen"));
					   aux.setImagen(rs.getBytes("imagen"));
					   list.add(aux);
				   }
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			return list;
		}//end
		
	   public Productos getEventoById(Integer id){
	
		   List<Productos> list = null;
		   try {
			   PreparedStatement rt = con.prepareStatement(SQL_SELECT);
			   rt.setInt(1,id );
			   ResultSet rs = rt.executeQuery();
			   Productos aux = null;
			   list = new ArrayList<Productos>( );
			   while(rs.next()){
				   aux= new Productos();
				   aux.setIdProductos(rs.getInt("id"));
				   aux.setNombre(rs.getString("nombre"));
				   aux.setPrecio(rs.getDouble("precio"));
				   aux.setExistencias(rs.getInt("existencias"));
				   aux.setDescripcion(rs.getString("descripcion"));
				   aux.setOrigen(rs.getString("origen"));
				   aux.setImagen(rs.getBytes("foto"));
				   list.add(aux);
			   }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    if( list == null || list.isEmpty() ){
		    	return null;
		    }
		    return list.get( 0 );
	   }
	   
	   public void delete( Productos producto ){
		   PreparedStatement ps;
		   try {
			   ps = con.prepareStatement( SQL_DELETE );
			   ps.setInt( 1, producto.getIdProductos( ) );
			   ps.execute( );
		   } catch (SQLException e) {
			   e.printStackTrace();
		   }
	   }
	   
	   
		public void inicializarConexion(){
			try {
				Class.forName("com.mysql.jdbc.Driver");//El paquete de Class 
				
				con=(Connection)
						DriverManager.getConnection("jdbc:mysql://localhost:3306/Productos","root","helado11");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		/**
		 * crear productos
		 * @param e
		 * @throws SQLException
		 */
		public void create (Productos  e) {
			PreparedStatement ps = null; 
			if( con == null ){
				System.out.println( "conn es nulo. Ya ha llamado al metodo iniciarConexion() ?");
				return;
			}
			try {
				ps = con.prepareStatement( SQL_INSERT );//LLama a la conexion como a Query
				ps.setString(1,e.getNombre());
				ps.setDouble(2,e.getPrecio());
				ps.setInt(3,e.getExistencias());
				ps.setString(4,e.getDescripcion());
				ps.setString(5,e.getOrigen());
				ps.setBytes(6,e.getImagen());
				
				ps.executeUpdate( );	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//Metodo para borrar cosas
		
		public void erase (Productos i){
			
			PreparedStatement ds=null;
		    try {
				ds =con.prepareStatement( SQL_DELETE );
				ds.setInt(1,i.getIdProductos());
				ds.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			
		}
		
		//Metodo para actualizar productos
		public void update (Productos e){
			
			PreparedStatement ds=null;
			
			
			try {
				ds=con.prepareStatement(SQL_UPDATE);
				ds.setString(1,e.getNombre());
				ds.setDouble(2,e.getPrecio());
				ds.setInt(3,e.getExistencias());
				ds.setString(4,e.getDescripcion());
				ds.setString(5,e.getOrigen());
				ds.setBytes(6,e.getImagen());
				ds.setInt(7,e.getIdProductos());
				
				ds.executeUpdate( );	
				
				
				
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
			
	}//end class
	


			

	

