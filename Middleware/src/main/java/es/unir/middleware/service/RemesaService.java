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
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.ResultadoInfoEnvioV2;
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
	
	public Remesa invocarAltaRemesaEnvio(Remesa remesa) throws Exception{
		//Transformar  Datos para envío a Notifica
		AltaRemesaEnvios altaRemesaEnvios=null;
		ResultadoAltaRemesaEnvios resultadoSOAP=null;
		Remesa resultado=null;
		try {
			altaRemesaEnvios = notificaConversor.convierteAltaRemesaEnviosJsonToSOAP(remesa);
			//Invocación servicio web altaRemesaEnvio
			resultadoSOAP=notificaClient.altaRemesaEnvio(altaRemesaEnvios);
			//Recuperar respuesta
			resultado= notificaConversor.convierteResultadoAltaRemesaSoapToRest(resultadoSOAP);
			//Actualizar datos Remesa
			//int idEnvio=0;
		} catch (Exception e) {
			throw e;
			
		}
		return resultado;
	}

	public Envio getInfoEnvio(String idEnvio) throws Exception{
		Envio envio = new Envio();
		envio.setIdEnvio("12");
		ResultadoInfoEnvioV2 resultadoSOAP= null;
		Envio resultado=null;
		try {
			resultadoSOAP= notificaClient.infoEnvioV2(idEnvio);
			resultado=notificaConversor.convierteResultadoInfoEnvioV2SoapToRest(resultadoSOAP);
		} catch (Exception e) {
			throw e;
		}
		return resultado;
	}

	public boolean comprobarUsuarioAutorizado(String idEnvio) {
		// Se comprobaría  en base de datos que el usuario que realiza la petición de información es el mismo que envió la remesa
		return true;
	}
	
	
	
}
