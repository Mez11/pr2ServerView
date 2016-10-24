package com.redes.p2.cliente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.redes.p2.model.Productos;

public class BaseDatosCarrito {
	 //Se crea un ArrayList producto para guardar productos de carro
    
	/**
	 * Lista de productos.
	 * Este ArrayList representa nuestra "BD" del
	 * carrito de compras.
	 * Es <code>static</code> ya que solo puede haber una "instancia" 
	 * de esta lista al mismo tiempo para cualquier clase.
	 * Es decir, no se puede "instanciar" (variable de clase).
	 */
	private static List<Productos> carrito;
    
	public static boolean hayProductos( ){
		if( carrito == null || carrito.isEmpty() ){
			//si la lista es null o esta vacia, no hay elementos
			return false;
		}
		//en caso contrario, hay al menos un elemento.
		return true;
	}//fin hayProductos
    
    //ALTA
    public static void agregar( Productos productoAAgregar ){
    	//Verificamos que el producto a agregar sea correcto
    	if( productoAAgregar == null || productoAAgregar.getIdProductos() <= 0 ){
    		//Necesitamos que el producto no sea null
    		//Y que al menos el ID del producto sea valido.
    		System.err.println( "El producto a agregar no es valido" );
    		return;
    	}
    	//Si la lista no se ha inicializado...
    	if( carrito == null ){
    		//Inicializarla
    		carrito = new ArrayList<>( );
    	}
    	carrito.add( productoAAgregar );
    }//fin agregar
    
    //Identacion...
    //BAJA...
    public static void eliminar( Productos productos ){
    	Productos productoActual = null;
    	//comprobar si hay elementos
    	if( !hayProductos() ){
    		//si no hay productos, no hay nada por hacer...
    		return;
    	}
    	
    	
    	//Para eliminar datos de una lista, hay una operacion especial.
    	//No se trabaja sobre la lista en si, sino sobre un objeto que se llama
    	//"Iterador" i?Nterador es como un indice 
    	//Mm... podria verse asi, si.
    	
    	Iterator<Productos> iterator = carrito.iterator( );
    	//Recorrer la lista
    	//iterator.hasNext es un metodo booleano
    	//devuelve "true" si hay elementos restantes
    	while( iterator.hasNext() ){
    		//iterator.next() Nos da el objeto en la posicion actual
    		productoActual = iterator.next( );
    		//Validar si el ID del producto actual es igual al producto especificado
    		if( productoActual.getIdProductos() == productos.getIdProductos() ){
    			//Si se encontro, eliminarlo:
    			iterator.remove( );
    		}//fin comprobacion ID
    	}//Fin del while
    } //end quitar
    
    //CONSULTA por ID...
    public static Productos getProductoById(Integer id){
    	//validar si carrito tiene productos
    	if( !hayProductos( ) ){
    		//si no hay productos, no hay nada por hacer...
    		return null;
    	} //End if  
    	//obtener el elemento con el ID especificado
    	for( Productos producto : carrito ){
    		if( producto.getIdProductos() == id.intValue() ){
    			return producto;
    		}
    	}
    	//si se llega a este punto,
    	//el producto especificado no se encontro :(
    	return null;
    }//fin getProductoById
    
    //CONSULTA de todos los productos del carrito
    public static List<Productos> getCarrito( ){
    	return carrito;
    }
    
    //CAMBIO
    public static void cambiar( Productos producto ){
    	if( !hayProductos() ){
    		System.err.println( "No hay productos en el carrito" );
    		return;
    	}
    	//Por tiempo, vamos a hacerlo bien cochino
    	//esto es, en vez de cambiar el producto dentro de la lista,
    	//Eliminaremos el producto original, y agregaremos el producto modificado
    	eliminar( producto );
    	agregar( producto );
    }
}//fin clase
