package es.unir.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.unir.middleware.controller.API_Rest;


@WebMvcTest(API_Rest.class)
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
	  private MockMvc mvc;
    
    @LocalServerPort
    private int port;
    
//  @Test
//    public void getAllEmployeesAPI() throws Exception 
//    {
//      mvc.perform(MockMvcRequestBuilders
//      			.get("/remesas")
//      			.accept(MediaType.APPLICATION_JSON))
//          .andDo(print())
//          .andExpect(status().isOk())
//          .andExpect(MockMvcResultMatchers.jsonPath("$.idRemesa").exists())
//          .andExpect(MockMvcResultMatchers.jsonPath("$.idRemesa").isNotEmpty());
//    }

//    @Test
//    public void createEmployeeAPI() throws Exception 
//    {
//      mvc.perform( MockMvcRequestBuilders
//    	      .post("/remesas")
//    	      .content(provaEnviament)
//    	      .contentType(MediaType.APPLICATION_JSON)
//    	      .accept(MediaType.APPLICATION_JSON))
//          .andExpect(status().isCreated())
//          .andExpect(MockMvcResultMatchers.jsonPath("$.idRemesa").exists());
//    }
     
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
