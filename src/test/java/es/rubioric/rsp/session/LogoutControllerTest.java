package es.rubioric.rsp.session;

import static es.rubioric.rsp.RequestMappingConstants.REQUEST_MAPPING_LOGOUT;
import static es.rubioric.rsp.RequestMappingConstants.REQUEST_MAPPING_SHOW_PLAYED_ROUNDS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;



/**
 * 
 *
 *<p> 
 *  Logout Controller Test.
 *</p>
 *
 * @author Ricardo Rubio
 *
 */
@WebMvcTest(LogoutController.class)
class LogoutControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private HttpSession session;
  
  @Test
  void testLogout() throws Exception {
    
    mockMvc.perform(get(REQUEST_MAPPING_LOGOUT))
           .andExpect(redirectedUrl(REQUEST_MAPPING_SHOW_PLAYED_ROUNDS));  ;    
  }
}
