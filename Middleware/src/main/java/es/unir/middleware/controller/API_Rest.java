package es.unir.middleware.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import lombok.Data;


@Data
@RestController
@RequestMapping(value ="/notifica")
public class API_Rest {
	@Autowired
	RemesaService remesaService;
	@Autowired
	ObjectMapper objectMapper;
	Documento documento=new Documento();
	
	//Realizar envío de remesa con envíos
	@PostMapping(value ="/remesas" )
	public Remesa enviaRemesa(Remesa remesaParam) {
		
		remesaService.invocarAltaRemesaEnvio(remesaParam);
		return remesaParam;
	}
	
	//Obtener información de envío
	@GetMapping(value ="/remesas/envio{idEnvio}")
	public Envio getInfoEnvio(String idEnvio) {
		return remesaService.getInfoEnvio(idEnvio);
	}
	
	private Remesa crearNuevaRemesa(String idRemesa) {
		Remesa remesa= new Remesa();
		remesa.setIdRemesa(idRemesa);
		remesa.setAplicacion(idRemesa);
		remesa.setConcepto(idRemesa);
		remesa.setDescripcion(idRemesa);
		documento.setContenido(null);
		remesa.setDocumento(null);
		
		Envios envios = new Envios();
		List<Envio> listadeEnvios= new ArrayList<Envio>();
		
		Envio envioRest= new Envio();
		envioRest.setIdEnvio("idEnvio");
		envioRest.setEntregaPostal(new EntregaPostal());
		envioRest.setReferenciaEmisor("Referencia del emisor");
		Destinatarios destinatarios = new Destinatarios();
		List<Persona> listadeDestinatarios= new ArrayList<Persona>();
		Persona personaDest= new Persona();
		personaDest.setApellidos("Lorca Mateo");
		personaDest.setNombre("Carmen");
		personaDest.setNif("38109346W");
		personaDest.setTelefono("321654987");
		personaDest.setEmail("Carmen.lorca@gmail.com");
		listadeDestinatarios.add(personaDest);
		personaDest= new Persona();
		personaDest.setApellidos("Martinez Gil");
		personaDest.setNombre("David");
		personaDest.setNif("73385978Q");
		personaDest.setTelefono("111111111");
		personaDest.setEmail("vidito72@gmail.com");
		listadeDestinatarios.add(personaDest);
		destinatarios.setDestinatario(listadeDestinatarios);
		envioRest.setDestinatarios(destinatarios);
		Persona personaTit= new Persona();
		personaTit.setApellidos("Martinez Lorca");
		personaTit.setNombre("Irene");
		personaTit.setNif("14578449Q");
		personaTit.setTelefono("222222222");
		personaTit.setEmail("irenemartinezlorca@gmail.com");
		envioRest.setTitular(personaTit);
		listadeEnvios.add(envioRest);
		envios.setEnvio(listadeEnvios);
		remesa.setEnvios(envios);
		GregorianCalendar c=new GregorianCalendar();
		remesa.setFechaEnvioProgramado(c);
		
		Opciones opcionesRemesa= new Opciones();
		List<Opcion> listadeOpciones= new ArrayList<Opcion>();
		Opcion opcion= new Opcion();
		opcion.setTipo("Tip_opcion1");
		opcion.setValue("valorOpcion1");
		listadeOpciones.add(opcion);
		opcion= new Opcion();
		opcion.setTipo("Tip_opcion2");
		listadeOpciones.add(opcion);
		opcionesRemesa.setOpcion(listadeOpciones);
		remesa.setOpcionesRemesa(opcionesRemesa);
		remesa.setProcedimiento(idRemesa);
		remesa.setTipoEnvio(null);
		return remesa;
	}
}
