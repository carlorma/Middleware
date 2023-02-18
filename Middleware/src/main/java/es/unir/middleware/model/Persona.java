package es.unir.middleware.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonDeserialize
@AllArgsConstructor
public class Persona {


	private String nif;
	private String nombre;
	private String apellidos;
	private String razonSocial;
	private String email;
	private String telefono;
    protected String codigoDestino;
	
}
