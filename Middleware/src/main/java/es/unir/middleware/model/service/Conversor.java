package es.unir.middleware.model.service;

import java.util.GregorianCalendar;

import org.springframework.stereotype.Service;

import es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaremesaenvios.AltaRemesaEnvios;
import es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.infoenviov2.InfoEnvioV2;
import es.unir.middleware.model.Destinatarios;
import es.unir.middleware.model.Documento;
import es.unir.middleware.model.EntregaPostal;
import es.unir.middleware.model.Envio;
import es.unir.middleware.model.Envios;
import es.unir.middleware.model.Opcion;
import es.unir.middleware.model.Persona;
import es.unir.middleware.model.Remesa;

@Service
public class Conversor {
	
	public static final String CODIGO_ORGANISMO_EMISOR = "U01800001";
	
/**
 * Función encargada de convertir los datos de entrada de la petición rest de alta de remesa
 * de envíos a los datos de entrada de la petición equivalente al servicio SOAP
 * @param peticion
 * @return
 */
	public AltaRemesaEnvios convierteAltaRemesaEnviosJsonToSOAP(Remesa peticion) {
		AltaRemesaEnvios peticionWS = new AltaRemesaEnvios();
		peticionWS.setCodigoOrganismoEmisor(CODIGO_ORGANISMO_EMISOR);
		
		peticionWS.setTipoEnvio( TipoEnvio.fromValue(peticion.getTipoEnvio()));
		peticionWS.setConcepto(peticion.getConcepto());
		peticionWS.setDescripcion(peticion.getDescripcion());
		
		
		peticionWS.setFechaEnvioProgramado(GregorianCalendar.getInstance().getTime());
		peticionWS.setProcedimiento(peticion.getProcedimiento());

		if (peticion.getDocumento() != null) {
			es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Documento doc=convierteDocumentoRestToMinisterio(peticion.getDocumento());
			peticionWS.setDocumento(doc);
		}

		if (peticion.getEnvios() != null) {
			peticionWS.setEnvios(convierteEnviosJsonToSOAP(peticion.getEnvios()));
		}

		if (peticion.getOpcionesRemesa() != null) {
			peticionWS.setOpcionesRemesa(new es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Opciones());

			for (Opcion opcionPeticion : peticion.getOpcionesRemesa().getOpcion()) {
				 es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Opcion opcion = new  es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Opcion();
				opcion.setTipo(opcionPeticion.getTipo());
				opcion.set_value(opcionPeticion.getValue());

				peticionWS.getOpcionesRemesa().setOpcion(opcion);
			}
		}

		return peticionWS;
	}
	 
	
	private es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Envios convierteEnviosJsonToSOAP(Envios envios) {
		es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Envios enviosMinis = new es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Envios();

		for (Envio envioPeticion : envios.getEnvio()) {
			es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Envio envio = new es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Envio();

			envio.setReferenciaEmisor(envioPeticion.getReferenciaEmisor());

			if (envioPeticion.getTitular() != null) {
				envio.setTitular(conviertePersonaRestToMinisterio(envioPeticion.getTitular()));
			}

			if (envioPeticion.getDestinatarios() != null) {
				envio.setDestinatarios(convierteDestinatariosRestToMinisterio(envioPeticion.getDestinatarios()));
			}

			if (envioPeticion.getEntregaPostal() != null) {
				envio.setEntregaPostal(convierteEntregaPostalRestToMinisterio(envioPeticion.getEntregaPostal()));
			}

			if (envioPeticion.getEntregaDEH() != null) {
				es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.EntregaDEH entregaDEH = new es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.EntregaDEH();

				entregaDEH.setObligado(envioPeticion.getEntregaDEH().isObligado());
				entregaDEH.setCodigoProcedimiento(envioPeticion.getEntregaDEH().getCodigoProcedimiento());

				envio.setEntregaDEH(entregaDEH);
			}

			enviosMinis.setEnvio(envio);
		}

		return enviosMinis;
		}

	private es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.EntregaPostal convierteEntregaPostalRestToMinisterio(EntregaPostal entregaPostal) {
		es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.EntregaPostal entregaMin=new es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.EntregaPostal();

		return entregaMin;
	}
	private  es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Destinatarios convierteDestinatariosRestToMinisterio(Destinatarios destinatarios) {
		es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Destinatarios destinatariosMinis = new es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Destinatarios();

		for (Persona destinatarioPeticion : destinatarios.getDestinatario()) {
			destinatariosMinis.setDestinatario(conviertePersonaRestToMinisterio(destinatarioPeticion));
		}

		return destinatariosMinis;
	}
	private es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Persona conviertePersonaRestToMinisterio(Persona persona) {
		es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Persona destinatarioMinis = new es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Persona();

		
		destinatarioMinis.setNif(persona.getNif());
		destinatarioMinis.setNombre(persona.getNombre());
		destinatarioMinis.setApellidos(persona.getApellidos());
		destinatarioMinis.setRazonSocial(persona.getRazonSocial());
		destinatarioMinis.setEmail(persona.getEmail());
		destinatarioMinis.setTelefono(persona.getTelefono());
		destinatarioMinis.setCodigoDestino(persona.getCodigoDestino());

		return destinatarioMinis;
	}
	private es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Documento convierteDocumentoRestToMinisterio(Documento documento) {
		es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Documento documentoMinis = new es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Documento();

		documentoMinis.setContenido(documento.getContenido());
		documentoMinis.setHash(documento.getHash());

		documentoMinis.setEnlaceDocumento(documento.getEnlaceDocumento());

		if (documento.getOpcionesDocumento() != null) {
			documentoMinis.setOpcionesDocumento(new es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Opciones());

			for (Opcion opcionPeticion : documento.getOpcionesDocumento().getOpcion()) {
				es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Opcion opcion = new es.gob.administracionelectronica.notifica.ws.notificaws_v2._1_0.altaRemesaEnvios.Opcion();
				opcion.setTipo(opcionPeticion.getTipo());
				opcion.set_value(opcionPeticion.getValue());

				documentoMinis.getOpcionesDocumento().setOpcion(opcion);
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
