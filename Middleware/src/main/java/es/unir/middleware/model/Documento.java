package es.unir.middleware.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
@JsonDeserialize
public class Documento {
    protected byte[] contenido;
    
    protected String hash;
    protected String enlaceDocumento;
    protected String metadatos;
    protected Opciones opcionesDocumento;

    
}
