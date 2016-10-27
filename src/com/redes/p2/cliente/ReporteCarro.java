package com.redes.p2.cliente;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lowagie.text.pdf.codec.Base64.InputStream;
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
    	
    	return (InputStream)fis;
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
			JasperPrint printer = JasperFillManager.fillReport( carrito, null, source );
			exporter.setConfiguration( config );
			exporter.setExporterInput( new SimpleExporterInput( printer ) );
			exporter.setExporterOutput( new SimpleOutputStreamExporterOutput( response.getOutputStream( ) ) );
			exporter.exportReport( );
		} catch (JRException e) {
				e.printStackTrace();
		}//end catch
    }//final generarReporte
    
 }//final de clase
    
