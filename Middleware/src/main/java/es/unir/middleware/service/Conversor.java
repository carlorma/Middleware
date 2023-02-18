package es.unir.middleware.service;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.axis.utils.BeanUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.unir.middleware.model.Destinatarios;
import es.unir.middleware.model.Documento;
import es.unir.middleware.model.EntregaPostal;
import es.unir.middleware.model.Envio;
import es.unir.middleware.model.Envios;
import es.unir.middleware.model.Opcion;
import es.unir.middleware.model.Persona;
import es.unir.middleware.model.Remesa;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.AltaRemesaEnvios;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.Destinatario;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.InfoEnvioV2;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class Conversor {
	
	public static final String CODIGO_ORGANISMO_EMISOR = "U01800001";
	private ObjectMapper objectMapper = new ObjectMapper();	
/**
 * Función encargada de convertir los datos de entrada de la petición rest de alta de remesa
 * de envíos a los datos de entrada de la petición equivalente al servicio SOAP
 * @param peticion
 * @return
 */
	public AltaRemesaEnvios convierteAltaRemesaEnviosJsonToSOAP(Remesa peticion) {
		AltaRemesaEnvios peticionWS = null;
		
		try {
			peticionWS = new AltaRemesaEnvios();
			peticionWS.setCodigoOrganismoEmisor(CODIGO_ORGANISMO_EMISOR);
			
			peticionWS.setTipoEnvio( peticion.getTipoEnvio());
			peticionWS.setConcepto(peticion.getConcepto());
			peticionWS.setDescripcion(peticion.getDescripcion());
			
			
			
			peticionWS.setFechaEnvioProgramado(DatatypeFactory.newInstance().newXMLGregorianCalendar(peticion.getFechaEnvioProgramado()));
			peticionWS.setProcedimiento(peticion.getProcedimiento());

			if (peticion.getDocumento() != null) {
				https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Documento doc=convierteDocumentoRestToMinisterio(peticion.getDocumento());
				peticionWS.setDocumento(doc);
			}

			if (peticion.getEnvios() != null) {
				peticionWS.setEnvios(convierteEnviosJsonToSOAP(peticion.getEnvios()));
			}

			if (peticion.getOpcionesRemesa() != null) {
				https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Opciones opcionesMinis =new https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Opciones();

				for (Opcion opcionPeticion : peticion.getOpcionesRemesa().getOpcion()) {
					 https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Opcion opcion = new  https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Opcion();
					opcion.setTipo(opcionPeticion.getTipo());
					opcion.setValue(opcionPeticion.getValue());
					
				}
				peticionWS.setOpcionesRemesa(opcionesMinis);
			}
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return peticionWS;
	}
	 
	
	private https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Envios convierteEnviosJsonToSOAP(Envios enviosRest) {
		https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Envios enviosMinis = new https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Envios();

		for (Envio envioRest : enviosRest.getEnvio()) {
			https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Envio envio = new https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Envio();
			envio=objectMapper.convertValue(envioRest, envio.getClass());
			enviosMinis.getEnvio().add(envio);
		}

		return enviosMinis;
		}

	private https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.EntregaPostal convierteEntregaPostalRestToMinisterio(EntregaPostal entregaPostal) {
		https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.EntregaPostal entregaMin=new https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.EntregaPostal();

		return entregaMin;
	}
	private  https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Destinatarios convierteDestinatariosRestToMinisterio(Destinatarios destinatarios) {
		https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Destinatarios destinatariosMinis = new https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Destinatarios();
		Destinatario destinatario= new Destinatario();
		https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Persona personaDest= null;
		for (Persona personaDestRest : destinatarios.getDestinatario()) {
			personaDest= new https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Persona();
			
			personaDest.setApellidos(personaDestRest.getApellidos());
			personaDest.setNombre(personaDestRest.getNombre());
			personaDest.setNif(personaDestRest.getNif());
			personaDest.setEmail(personaDestRest.getEmail());
			personaDest.setTelefono(personaDestRest.getTelefono());
			personaDest.setRazonSocial(personaDestRest.getRazonSocial());
			
		}

		return destinatariosMinis;
	}
	private https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Persona conviertePersonaRestToMinisterio(Persona persona) {
		https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Persona destinatarioMinis = new https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Persona();

		
		destinatarioMinis.setNif(persona.getNif());
		destinatarioMinis.setNombre(persona.getNombre());
		destinatarioMinis.setApellidos(persona.getApellidos());
		destinatarioMinis.setRazonSocial(persona.getRazonSocial());
		destinatarioMinis.setEmail(persona.getEmail());
		destinatarioMinis.setTelefono(persona.getTelefono());
		destinatarioMinis.setCodigoDestino(persona.getCodigoDestino());

		return destinatarioMinis;
	}
	private https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Documento convierteDocumentoRestToMinisterio(Documento documento) {
		https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Documento documentoMinis = new https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Documento();

		documentoMinis.setContenido(documento.getContenido());
		documentoMinis.setHash(documento.getHash());

		documentoMinis.setEnlaceDocumento(documento.getEnlaceDocumento());

		if (documento.getOpcionesDocumento() != null) {
			documentoMinis.setOpcionesDocumento(new https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Opciones());

			for (Opcion opcionPeticion : documento.getOpcionesDocumento().getOpcion()) {
//				https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Opcion opcion = new https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.Opcion();
//				opcion.setTipo(opcionPeticion.getTipo());
//				opcion.setValue(opcionPeticion.getValue());
//
//				documentoMinis.getOpcionesDocumento().setOpcion(opcion);
			}
		}
		return documentoMinis;
	}

	/**
	 * Función encargada de convertir los datos de entrada de la petición rest de información
	 * de envío a los datos de entrada de la petición equivalente al servicio SOAP
	 * @param env
	 * @return
	 */
	public InfoEnvioV2 convierteInfoEnvioV2JsonToSOAP(Envio env) {
		InfoEnvioV2 infoEnvioV2= new InfoEnvioV2();
		
		
		return infoEnvioV2;
	}
	
	
}
