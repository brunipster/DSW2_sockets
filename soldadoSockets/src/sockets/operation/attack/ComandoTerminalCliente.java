package sockets.operation.attack;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import sockets.operation.dto.MensajeSecretoDTO;
import sockets.operation.dto.RespuestaCoronelDTO;

public class ComandoTerminalCliente {
	public static final int PUERTO_ULTRASECRETO = 5544;
	// Canal de comunicación
	private ServerSocket servidor = null;

	public static void main(String[] args) {

		try {
			
			System.out.println("SOLDADO DELTA1 - INTENTANDO COMUNICARSE ...");
			// establecer comunicacion  con la torre de control
			Socket socket = new Socket("localhost",PUERTO_ULTRASECRETO);
			//localhost: 127.0.0.1
			System.out.println("CONEXION EXITOSA CON LA TORRE DE CONTROL");
			
			//creamos el flujo de salida
			ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
			
			System.out.println("PREPARANDO ENVIO DE MENSAJE..");
			
			List<MensajeSecretoDTO> lst = new ArrayList<>();
			
			MensajeSecretoDTO mensaje1 = new MensajeSecretoDTO();
			mensaje1.setIdSoldado("Delta1");
			mensaje1.setMensaje("LTN54.454.545");
			mensaje1.setCoordenadas("Objetivo en la mira");
			lst.add(mensaje1);
			
			MensajeSecretoDTO mensaje2 = new MensajeSecretoDTO();
			mensaje2.setIdSoldado("Delta2");
			mensaje2.setMensaje("LTN54.454.545");
			mensaje2.setCoordenadas("Necesito Apoyo");
			lst.add(mensaje2);
			
			MensajeSecretoDTO mensaje3 = new MensajeSecretoDTO();
			mensaje3.setIdSoldado("Delta3");
			mensaje3.setMensaje("LTN54.454.545");
			mensaje3.setCoordenadas("Soldado Caido!");
			lst.add(mensaje3);
			
			//Enviar mensajes
			flujoSalida.writeObject(lst);
			System.out.println("MENSAJE ENVIADO!");
			
			//creamos el flujo de entrada
			ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
			RespuestaCoronelDTO respuesta = (RespuestaCoronelDTO)flujoEntrada.readObject();
			System.out.println("id del coronel :" + respuesta.getIdCoronel());
			System.out.println("Rpta coronel   :" + respuesta.getRespuesta());
			//cierro el canal de comunicación
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
