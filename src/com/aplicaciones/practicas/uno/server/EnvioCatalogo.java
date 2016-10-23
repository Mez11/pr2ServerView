/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicaciones.practicas.uno.server;
import java.util.List;

import com.redes.p2.dao.ProductosDao;
import com.redes.p2.model.Productos;
import com.redes.p2.servidor.view.CatalogoDeProductos;

public class EnvioCatalogo {
	
    public static void main( String [] args ){
		ProductosDao miProducto = new ProductosDao( );
        List<Productos> list = null;
        CatalogoDeProductos catalogoDeProductos = new CatalogoDeProductos();
        
        System.out.println("Conectando a la base de datos");
        miProducto.inicializarConexion( );
        
        System.out.println("Obteniendo catalogos");
        list = miProducto.getProductos( );
        System.out.println( "Cargando vista de catalogos..." );
        catalogoDeProductos.init( list );
	}//end main
}//end EnvioCatalogo
