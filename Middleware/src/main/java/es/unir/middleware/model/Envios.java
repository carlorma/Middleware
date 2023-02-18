package es.unir.middleware.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonDeserialize
@AllArgsConstructor
public class Envios {
	 protected List<Envio> envio;
	 
	 public Envios() {
		envio= new ArrayList<Envio>();
	}
}
