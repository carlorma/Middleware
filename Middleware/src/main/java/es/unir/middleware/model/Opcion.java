package es.unir.middleware.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonDeserialize
public class Opcion {
	
    protected String value;
    protected String tipo;
}
