package es.unir.middleware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.unir.middleware.model.Documento;
import es.unir.middleware.model.Envio;
import es.unir.middleware.model.Persona;
import es.unir.middleware.model.Remesa;
import es.unir.middleware.model.service.RemesaService;
import lombok.Data;


@Data
@RestController
@RequestMapping(value ="/notifica")
public class API_Rest {
	@Autowired
	RemesaService remesaService;
	
	Documento documento=new Documento();
	
	@GetMapping(value ="/remesa")
	public Remesa obtenerRemesa(String idRemesa) {
		Remesa remesa= new Remesa();
		remesa.setIdRemesa(idRemesa);
		remesa.setAplicacion(idRemesa);
		remesa.setConcepto(idRemesa);
		remesa.setDescripcion(idRemesa);
		documento.setContenido(null);
		remesa.setDocumento(null);
		remesa.setEnvios(null);
		remesa.setFechaEnvioProgramado(null);
		remesa.setOpcionesRemesa(null);
		remesa.setProcedimiento(idRemesa);
		remesa.setTipoEnvio(null);
		
		
		Persona dest = new Persona("38109346W","Carmen","Lorca Mateo","razon social","carmen.lorca@uv.es","321654987","codDest");
		Persona dest1= new Persona("73385978Q","David","Martínez Gil","razon social","vidito72@gmail.com","321654987","codDest");
		Persona dest2= new Persona("27154280C","Ade","Mateo Hernández","razon social","adelamateohernandez@gmail.com","321654987","codDest");
		Persona dest3= new Persona("44658978F","Irene","Martínez Lorca","razon social","IriMartinezLorca@gmail.com","321654987","codDest");
		String result ="";
	    

	    try {
			result = new ObjectMapper().writeValueAsString(remesa);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return remesa;
	}
	//Realizar envío de remesa con envíos
	@PostMapping(value ="/remesa")
	public Remesa enviaRemesa(Remesa remesaParam) {
		
		remesaService.invocarAltaRemesaEnvio(remesaParam);
		return remesaParam;
	}
	
	@GetMapping(value ="/remesa/envio")
	public Envio getInfoEnvio(String idEnvio) {
		return remesaService.getInfoEnvio(idEnvio);
	}
	
}
