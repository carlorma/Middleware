package es.unir.middleware.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.unir.middleware.model.Destinatarios;
import es.unir.middleware.model.Documento;
import es.unir.middleware.model.EntregaPostal;
import es.unir.middleware.model.Envio;
import es.unir.middleware.model.Envios;
import es.unir.middleware.model.Opcion;
import es.unir.middleware.model.Opciones;
import es.unir.middleware.model.Persona;
import es.unir.middleware.model.Remesa;
import es.unir.middleware.service.RemesaService;
import https.administracionelectronica_gob_es.notifica.ws.notificaws_v2._1_0.altaremesaenvios.AltaRemesaEnvios;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;


@Data
@RestController
@RequestMapping(value ="/notifica")
public class MiddlewareApiRest {
	@Autowired
	RemesaService remesaService;
	@Autowired
	ObjectMapper objectMapper;
	Documento documento=new Documento();
	
	
	@Operation(summary = "Permite enviar los datos de una remesa de notificaciones", 
			responses = {
					@ApiResponse(responseCode = "201", description = "Remesa enviada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
					@ApiResponse(responseCode = "400", description = "Los datos de la remesa son incorrectos", content = @Content),
					@ApiResponse(responseCode = "401", description = "No est?? autorizado para el env??o de remesas"),
			        @ApiResponse(responseCode = "500", description = "Error interno del servicio")})
	@PostMapping(value ="/remesas" )
	public Remesa enviaRemesa(AltaRemesaEnvios remesaParam, HttpServletResponse response) {
		Remesa remesaResult=null;
		try {
			remesaResult=null;//remesaService.invocarAltaRemesaEnvio(remesaParam);
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return remesaResult;
	}
	
	
	@Operation(summary = "Permite obtener informaci??n de un env??o", 
			responses = {
		        @ApiResponse(responseCode = "200", description = "Obtenci??n de informaci??n del env??o correcta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		        @ApiResponse(responseCode = "404", description = "El env??o no existe"),
		        @ApiResponse(responseCode = "401", description = "No est?? autorizado a consultar los datos del env??o"),
		        @ApiResponse(responseCode = "500", description = "Error interno del servicio")})
	@GetMapping(value ="/envios{idEnvio}")
	public Envio getInfoEnvio(String idEnvio, HttpServletResponse response) {
		Envio envioResult=null;
		try {
			if (!remesaService.comprobarUsuarioAutorizado(idEnvio)){
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			}
			//envioResult=remesaService.getInfoEnvio(idEnvio);
			envioResult=new Envio();
			envioResult.setIdEnvio("13");
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return envioResult;
	}
	
	@Operation(summary = "Permite actualizar informaci??n de un env??o con la informaci??n que llega del Adviser", 
			responses = {
		        @ApiResponse(responseCode = "201", description = "Env??o Actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		        @ApiResponse(responseCode = "404", description = "El env??o no existe"),
		        @ApiResponse(responseCode = "401", description = "No est?? autorizado a actualizar los datos del env??o"),
		        @ApiResponse(responseCode = "500", description = "Error interno del servicio")})
	@PutMapping(value ="/envios{idEnvio}")
	public Envio actualizaEnvio(String idEnvio, HttpServletResponse response) {
		Envio envioResult=null;
		try {
			if (!remesaService.comprobarUsuarioAutorizado(idEnvio)){
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			}
			envioResult=remesaService.getInfoEnvio(idEnvio);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return envioResult;
	}
	
	
}
