package es.unir.middleware.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonDeserialize
public class Opciones {
	protected List<Opcion> opcion;
}
