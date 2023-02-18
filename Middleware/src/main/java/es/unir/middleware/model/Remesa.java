package es.unir.middleware.model;

import java.math.BigInteger;
import java.util.GregorianCalendar;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonDeserialize
@AllArgsConstructor
public class Remesa {
	
private String idRemesa;
private BigInteger tipoEnvio= new BigInteger("1");
private String concepto;
private String descripcion;
private GregorianCalendar fechaEnvioProgramado;
private String procedimiento;
private Documento documento;
private Envios envios;
private Opciones opcionesRemesa;
private String aplicacion;




public Remesa() {
	envios= new Envios();
}

}
