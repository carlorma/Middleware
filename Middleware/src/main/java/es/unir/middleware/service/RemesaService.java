package es.unir.middleware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.unir.middleware.model.Destinatarios;
import es.unir.middleware.model.EntregaDEH;
import es.unir.middleware.model.EntregaPostal;
import es.unir.middleware.model.Envio;
import es.unir.middleware.model.Envios;
import es.unir.middleware.model.Persona;
import es.unir.middleware.model.Remesa;
import es.unir.middleware.soap.ClienteSOAPNotifica;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.AltaRemesaEnvios;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.ResultadoAltaRemesaEnvios;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Service
public class RemesaService {
	@Autowired
	Conversor notificaConversor;
	@Autowired
	ObjectMapper objectMapper;
	
	ClienteSOAPNotifica notificaClient;
	
	public Remesa asignarEnvio(Remesa remesa, Envio envio) {
		if (remesa.getEnvios()==null) {
			remesa.setEnvios(new Envios());
		}
		remesa.getEnvios().getEnvio().add(envio);
		
		return remesa;
	}
	
	public ResultadoAltaRemesaEnvios invocarAltaRemesaEnvio(Remesa remesa) {
		//Transformar  Datos para envío a Notifica
		AltaRemesaEnvios altaRemesaEnvios=null;
		ResultadoAltaRemesaEnvios resultado=null;
		try {
			altaRemesaEnvios = notificaConversor.convierteAltaRemesaEnviosJsonToSOAP(remesa);
			//Invocación servicio web altaRemesaEnvio
			notificaClient = new ClienteSOAPNotifica();
			resultado=notificaClient.altaRemesaEnvio(altaRemesaEnvios);
			//Recuperar respuesta
			//Actualizar datos Remesa
			//int idEnvio=0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}

	public Envio getInfoEnvio(String idEnvio) {
		Envio envio = new Envio();
		envio.setIdEnvio("12");
		//notificaConversor.
		//Buscar información del envío
		return envio;
	}
	
	
	
}
