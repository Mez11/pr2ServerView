package com.redes.p2.servidor;

import java.io.IOException;

public interface ServidorInterface {

	/**
	 * Bloqueante
	 * @return
	 */
	//Se esta escuchando el socket
	public void bind( ) throws IOException;
}


//Esta es la interfaz pero de servidor 
//y SERVIDOR SE LLAMA EN RUNER sERVER