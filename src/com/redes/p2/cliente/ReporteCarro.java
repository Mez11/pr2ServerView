package com.redes.p2.cliente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.redes.p2.model.Productos;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class ReporteCarro {
	private static final String RUTA_JASPER ="";
	private static final String RUTA_ARCHIVO_SALIDA ="";
	
	public ReporteCarro(){
		super();
	}
	
    public List<Productos> getCarrito() {
		return BaseDatosCarrito.getCarrito( );
    }
    
    private Map<String,Object> getEmptyMap( ){
    	return new HashMap<String, Object>();
    }
    
    private InputStream getReporte( ){
    	FileInputStream fis = null;
    	
    	try {
			fis = new FileInputStream( RUTA_JASPER );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	return fis;
    }
	
    private OutputStream getArchivoSalida( ){
    	FileOutputStream fos = null;
    	
    	try {
			fos = new FileOutputStream( new File( RUTA_ARCHIVO_SALIDA ) );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return fos;
    }
    
    public  void generarReporte( ){
    	List<Productos> carrito = getCarrito( );
		
    	if( carrito == null ){
    		System.err.println( "El reporte es nulo" );
    		return;
    	}//end if
    	
		//cosos de reporte
		JRPdfExporter exporter = new JRPdfExporter( );
		SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration( );
		//Obtener el recurso
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource( BaseDatosCarrito.getCarrito() );
		try {
			//Comunicacion de la fuente de datos llenando el templete
			JasperPrint printer = JasperFillManager.fillReport( getReporte( ), null, source );
			exporter.setConfiguration( config );
			exporter.setExporterInput( new SimpleExporterInput( printer ) );
			exporter.setExporterOutput( new SimpleOutputStreamExporterOutput( getArchivoSalida( ) ) );
			exporter.exportReport( );
		} catch (JRException e) {
				e.printStackTrace();
		}//end catch
    }//final generarReporte
    
 }//final de clase
    
