package es.unir.middleware;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
class MiddlewareApplicationTests {

	String provaEnviament="{\"idRemesa\":\"5\",\"tipoEnvio\":null,\"concepto\":\"5\",\"descripcion\":\"5\",\"fechaEnvioProgramado\":1676745751031,\"proced"
			+ "imiento\":\"5\",\"documento\":null,\"envios\":{\"envio\":[{\"idEnvio\":\"idEnvio\",\"referenciaEmisor\":\"Referencia del emisor\",\"tit"
			+ "ular\":{\"nif\":\"14578449Q\",\"nombre\":\"Irene\",\"apellidos\":\"Martinez Lorca\",\"razonSocial\":null,\"email\":\"irenemartinezlorca"
			+ "@gmail.com\",\"telefono\":\"222222222\",\"codigoDestino\":null},\"destinatarios\":{\"destinatario\":[{\"nif\":\"38109346W\",\"nombre\":\"Car"
			+ "men\",\"apellidos\":\"Lorca Mateo\",\"razonSocial\":null,\"email\":\"Carmen.lorca@gmail.com\",\"telefono\":\"321654987\",\"codigo"
			+ "Destino\":null},{\"nif\":\"73385978Q\",\"nombre\":\"David\",\"apellidos\":\"Martinez Gil\",\"razonSocial\":null,\"email\":\"vidito72"
			+ "@gmail.com\",\"telefono\":\"111111111\",\"codigoDestino\":null}]},\"entregaPostal\":{},\"entregaDEH\":null}]},\"opcionesRemesa\":{\"op"
			+ "cion\":[{\"value\":\"valorOpcion1\",\"tipo\":\"Tip_opcion1\"},{\"value\":null,\"tipo\":\"Tip_opcion2\"}]},\"aplicacion\":\"5\"}";
	@Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    private int port;
    
    @Test
    public void testHelloWorld() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + 8080 + "/notificaciones/remesas", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello, world!", response.getBody());
    }
}
