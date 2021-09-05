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

import es.rubioric.rsp.model.HandShape;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 *<p>
 *   Game Controller class.
 *   Handles the three different views of the application.
 *   - Home
 *   - Show Session Recorded Rounds
 *   - Shot Total Scores
 *</p>
 *
 * @author Ricardo Rubio
 *
 */
@Controller
public class GameController {
      
  @Autowired
  GameSolverService solverService;

  
  /**
   * 
   * <p>Show Home Page.
   * </p>
   *¶@return Home Page view
   */
  @GetMapping(REQUEST_MAPPING_HOME)
  public String showHome() {
    return VIEW_HOME;
  }
  
  
  /**
   * 
   * <p>Show Played Rounds.
   * </p>
   *¶@param model Model that stores the list of played rounds.
   *¶@param session HTTP Session
   *¶@return Show Played Rounds view
   */
  @GetMapping(REQUEST_MAPPING_SHOW_PLAYED_ROUNDS)
  public String showPlayedRounds(Model model, HttpSession session) {

    String sessionId = session.getId();

    List<PlayedRound> playedRoundsBySession = solverService.findAllBySessionId(sessionId);

    model.addAttribute(MODEL_ATT_PLAYED_ROUNDS, playedRoundsBySession);

    return VIEW_SHOW_PLAYED_ROUNDS;
  }

  
  /**
   * 
   * <p>Plays a new round and store it associated to the session id.
   * </p>
   * ¶@param model Model that stores the list of recorded rounds.
   * ¶@param session HTTP Session
   * ¶@return Show Played Rounds' view
   */
  @GetMapping(REQUEST_MAPPING_PLAY_NEW_ROUND)
  public String playNewRound(Model model, HttpSession session) {

    String sessionId = session.getId();

    HandShape playerOne = solverService.retrieveRandomHandShape();
    HandShape playerTwo = HandShape.ROCK;

    solverService.registerPlayedRound(sessionId, playerOne, playerTwo);

    return "redirect:" + REQUEST_MAPPING_SHOW_PLAYED_ROUNDS;
  }

  /**
   * 
   * <p>Show summarized numbers of played rounds.
   * </p>
   *¶@param model Model that stores the POJO containing summarized totals.
   *¶@return Show Total Score' view
   */
  @GetMapping(REQUEST_MAPPING_SHOW_TOTAL_SCORES)
  public String showTotalScores(Model model) {

    TotalScores totalScores = solverService.summarizeTotalScores();

    model.addAttribute(MODEL_ATT_TOTAL_SCORES, totalScores);

    return VIEW_SHOW_TOTAL_SCORES;
  }
  
  
}
