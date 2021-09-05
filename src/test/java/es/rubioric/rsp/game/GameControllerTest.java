package es.rubioric.rsp.game;

import static es.rubioric.rsp.RequestMappingConstants.REQUEST_MAPPING_HOME;
import static es.rubioric.rsp.RequestMappingConstants.REQUEST_MAPPING_PLAY_NEW_ROUND;
import static es.rubioric.rsp.RequestMappingConstants.REQUEST_MAPPING_SHOW_PLAYED_ROUNDS;
import static es.rubioric.rsp.RequestMappingConstants.REQUEST_MAPPING_SHOW_TOTAL_SCORES;
import static es.rubioric.rsp.model.ModelAttributeConstants.MODEL_ATT_PLAYED_ROUNDS;
import static es.rubioric.rsp.model.ModelAttributeConstants.MODEL_ATT_TOTAL_SCORES;
import static es.rubioric.rsp.view.ViewConstants.VIEW_HOME;
import static es.rubioric.rsp.view.ViewConstants.VIEW_SHOW_PLAYED_ROUNDS;
import static es.rubioric.rsp.view.ViewConstants.VIEW_SHOW_TOTAL_SCORES;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


/**
 *
 *<p>
 *   Game Controller Test class.
 *</p>
 *
 * @author Ricardo Rubio
 *
 */
@WebMvcTest(GameController.class)
class GameControllerTest {

  private static final String TEST_SESSION_ID = "TEST_SESSION_ID";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GameSolverService service;
  
  @MockBean
  private HttpSession session;
  
  @BeforeEach
  void setup() {
    given(session.getId()).willReturn(TEST_SESSION_ID);
    given(service.findAllBySessionId(TEST_SESSION_ID)).willReturn(new ArrayList<PlayedRound>());
    
    TotalScores totalScores = new TotalScores(0, 0, 0, 0);
    given(service.summarizeTotalScores()).willReturn(totalScores);   
  }
  
    
  @Test
  void testHomePage() throws Exception {
    
    mockMvc.perform(get(REQUEST_MAPPING_HOME))
           .andExpect(status().isOk())
           .andExpect(view().name(VIEW_HOME));    
  }
  
  @Test
  void testShowPlayedRoundsPage() throws Exception {
       
    mockMvc.perform(get(REQUEST_MAPPING_SHOW_PLAYED_ROUNDS))
           .andExpect(status().isOk())
           .andExpect(model().attributeExists(MODEL_ATT_PLAYED_ROUNDS))
           .andExpect(model().attribute(MODEL_ATT_PLAYED_ROUNDS, is(notNullValue())))
           .andExpect(view().name(VIEW_SHOW_PLAYED_ROUNDS));    
  }
  
  @Test
  void testPlayRoundPage() throws Exception {
       
    mockMvc.perform(get(REQUEST_MAPPING_PLAY_NEW_ROUND))
           .andExpect(redirectedUrl(REQUEST_MAPPING_SHOW_PLAYED_ROUNDS));    
  }
  
  @Test
  void testShowTotalsPage() throws Exception {
       
    mockMvc.perform(get(REQUEST_MAPPING_SHOW_TOTAL_SCORES))
           .andExpect(status().isOk())
           .andExpect(model().attributeExists(MODEL_ATT_TOTAL_SCORES))
           .andExpect(model().attribute(MODEL_ATT_TOTAL_SCORES, is(notNullValue())))
           .andExpect(view().name(VIEW_SHOW_TOTAL_SCORES));    
  }
}
