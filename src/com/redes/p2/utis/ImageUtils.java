package com.redes.p2.utis;

import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageUtils {
	/**Arreglo de imagenes para extraer y gaudar la imagen*/
	public static byte[] extractImage( File imageFile ){
		//donde vamos a guardar los bytes de la imagen
		byte [] imageBytes = null;
		
		//validar que haya una imagen
		if( imageFile == null ){
			return null;
		}
		
		//extraer los bytes de la imagen
		try {
			imageBytes = Files.readAllBytes( imageFile.toPath( ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return imageBytes;
	}//end extractImage

	/**
	 * Metodo que despliega la imagen especificada en el JLabel,
	 * modificando su tamaño para que no se vea rara
	 * @param imageArray byteArray que contiene los bytes de la imagen
	 * @param lblImagen JLabel donde desplegar la imagen
	 */
	public static void displayPrettyImage( byte[] imageArray, JLabel lblImagen ){
		BufferedImage originalImage = null;
		ByteArrayInputStream bais = null;
		Image resizedImage = null;
		ImageIcon image = null;
		
		if( imageArray == null ){
			System.err.println( "imagearray is null" );
			return;
		}
		
		try {
			//Inicializar el flujo con base en los bytes
			bais = new ByteArrayInputStream( imageArray );
			//Leer la imagen
			originalImage = ImageIO.read( bais );
			//Escalar la imagen
			//(Modificar su tamaño de tal forma que ocupe perfectamente el JLabel
			//en el que esta contenida)
			resizedImage = originalImage.getScaledInstance( 
					lblImagen.getWidth(), lblImagen.getHeight( ), Image.SCALE_SMOOTH );			
			//desplegar la imagen en el JLabel
			image = new ImageIcon( resizedImage );
			lblImagen.setIcon( image );
		} catch (IOException e) {
			e.printStackTrace();
		}//end try-catch
	}//end onSelectedImage
	
	
	public static File getImagen( JLabel lblImagen, Container container ){
		File selectedImage = null;
		JFileChooser chooser = new JFileChooser( );
		FileNameExtensionFilter filtroImagen= new FileNameExtensionFilter("JPG, PNG","jpg","png");
		chooser.setFileFilter(filtroImagen);
		int r=chooser.showOpenDialog(null);
		
		if(r==JFileChooser.APPROVE_OPTION){
			selectedImage = chooser.getSelectedFile( );
			//llamamos al manejador del evento "Imagen seleccionada",
			//enviandole el archivo que el usuario elijio
			onSelectedImage( selectedImage, lblImagen, container );
		}
		return selectedImage;
	}
	
	/**
	 * Metodo a ejecutar cuando el usuario elije una imagen
	 * @param selectedFile Archivo que el usuario escogio a traves
	 * del {@link JFileChooser}
	 */
	private static void onSelectedImage( File selectedFile, JLabel lblImagen, Container container ){
		BufferedImage originalImage = null;
		Image resizedImage = null;
		ImageIcon image = null;
		
		try {
			
			//Leer la imagen
			originalImage = ImageIO.read( selectedFile );
			//Escalar la imagen
			//(Modificar su tamaño de tal forma que ocupe perfectamente el JLabel
			//en el que esta contenida)
			resizedImage = originalImage.getScaledInstance( 
					lblImagen.getWidth(), lblImagen.getHeight( ), Image.SCALE_SMOOTH );			
			//desplegar la imagen en el JLabel
			image = new ImageIcon( resizedImage );
			paintModel( image, lblImagen, container );
		} catch (IOException e) {
			e.printStackTrace();
		}//end try-catch
	}//end onSelectedImage
	
	public static void paintModel( ImageIcon img, JLabel lblImagen, Container container ){
		lblImagen.removeAll( );
		lblImagen.setIcon( img );
		container.add( lblImagen );
	}
}
