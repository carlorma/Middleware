package es.unir.middleware.soap;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import es.unir.middleware.service.RemesaService;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.AltaRemesaEnvios;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.ResultadoAltaRemesaEnvios;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.InfoEnvioV2;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.ResultadoInfoEnvioV2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteSOAPNotifica extends WebServiceGatewaySupport{
	public static final String CODIGO_ORGANISMO_EMISOR = "U01800001";
	public ClienteSOAPNotifica() {
		// TODO Auto-generated constructor stub
	}
	

	public ResultadoAltaRemesaEnvios altaRemesaEnvio(AltaRemesaEnvios peticion ) throws Exception{
		ResultadoAltaRemesaEnvios respuesta=null;
		try {
			getWebServiceTemplate().marshalSendAndReceive("http://localhost:8088/mockNotificaWs/altaRemesaEnvios", respuesta);
		}catch (Exception e) {
			logger.error("Error invocando al método SOAP altaRemesaEnvios",e);
		}

		return respuesta;
		
	}

	public ResultadoInfoEnvioV2 infoEnvioV2(InfoEnvioV2 peticion ) throws Exception{
		
		ResultadoInfoEnvioV2 respuesta=null;
		getWebServiceTemplate().marshalSendAndReceive("http://localhost:8088//mockNotificaWs/infoEnvioV2", respuesta);
		/*
		  NotificaWsV2Service servicio = new NotificaWsV2ServiceLocator();
		  NotificaWsV2PortType ws= new NotificaWsV2BindingStub(new URL(servicio.getNotificaWsV2PortAddress()), servicio);
		 
		ResultadoInfoEnvioV2 respuesta=ws.infoEnvioV2(peticion);
		/*ResultadoAltaRemesaEnvios respuesta= (ResultadoAltaRemesaEnvios)getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8088/mockNotificaWs",peticion,
						new SoapActionCallback(
				                "http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));
				                */
		return respuesta;
	}

}
