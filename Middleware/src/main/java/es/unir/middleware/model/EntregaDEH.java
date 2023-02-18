package es.unir.middleware.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonDeserialize
@AllArgsConstructor
public class EntregaDEH {
    protected boolean obligado;
    protected String codigoProcedimiento;
}
