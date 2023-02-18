package es.unir.middleware.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonDeserialize
@AllArgsConstructor
public class Envio {
	private String idEnvio;
	protected String referenciaEmisor;
    protected Persona titular;
    protected  Destinatarios destinatarios;
    protected EntregaPostal entregaPostal;
    protected EntregaDEH entregaDEH;
	
    
}
