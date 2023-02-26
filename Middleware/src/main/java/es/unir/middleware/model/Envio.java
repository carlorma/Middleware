package es.unir.middleware.model;

import java.math.BigInteger;
import java.util.GregorianCalendar;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.Certificacion;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.CodigoDIR;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.Datados;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.Destinatarios;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.Documento;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.EntregaDEH;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.EntregaPostal;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.Opciones;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.Persona;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.infoenviov2.Procedimiento;
import lombok.Data;

@Data
@JsonDeserialize
public class Envio {
	private String idEnvio;
	private String referenciaEmisor;
    private Persona titular;
    private Destinatarios destinatarios;
    private EntregaPostal entregaPostal;
    private EntregaDEH entregaDEH;
    private String estado;
    private String identificador;
    private String concepto;
    private String descripcion;
    private CodigoDIR codigoOrganismoEmisor;
    private CodigoDIR codigoOrganismoEmisorRaiz;
    private String tipoEnvio;
    private GregorianCalendar fechaCreacion;
    private GregorianCalendar fechaPuestaDisposicion;
    private GregorianCalendar fechaCaducidad;
    private BigInteger retardo;
    private Procedimiento procedimiento;
    private Documento documento;
    private Datados datados;
    private Certificacion certificacion;
    private Opciones opcionesEnvio;
    
	
    
}
