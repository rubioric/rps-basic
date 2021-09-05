package es.rubioric.rsp.game;


import es.rubioric.rsp.model.GameResult;
import es.rubioric.rsp.model.HandShape;
import java.security.SecureRandom;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 *<p>
 *   Game Solver Service.
 *   Allow the controller access to the repository layer.
 *   Generates random HandShape.
 *   Checks which player has wond a played round.
 *</p>
 *
 * @author Ricardo Rubio
 *
 */
@Service
public class GameSolverService {
   
  private static final SecureRandom SECURE_RANDOM = new SecureRandom();
  
  @Autowired
  PlayedRoundRepository repository;
  
  public List<PlayedRound> findAllBySessionId(String sessionId) {
    return repository.findAllBySessionId(sessionId);
  }
  
  public TotalScores summarizeTotalScores() {
    return repository.summarizeTotalScores();
  }


  /**
   * <p>Registers a played round calculating the result 
   * depending on the provided choice for each player.
   * </p>
   * ¶@param sessionId Session id.
   * ¶@param playerOne Player One's choice.
   * ¶@param playerTwo Player Two's choice.
   */
  public void registerPlayedRound(String sessionId, HandShape playerOne, HandShape playerTwo) {
    
    GameResult res = GameResult.DRAW;

    if (!playerOne.equals(playerTwo)) {
      res = checkPlayerOneBeats(playerOne, playerTwo) ? GameResult.PLAYER_1_WINS 
                                                      : GameResult.PLAYER_2_WINS;
    }

    PlayedRound gr = new PlayedRound(sessionId, playerOne, playerTwo, res);

    repository.save(gr);
    
  }
  
  /**
   * <p>Checks which player's choice has won the round.
   * </p>
   * ¶@param playerOne Player One's choice.
   * ¶@param playerTwo Player Two's choice.
   * ¶@return true, if Player One's choice wins; false, otherwise.
   */
  private boolean checkPlayerOneBeats(HandShape playerOne, HandShape playerTwo) {

    boolean res = false;

    switch (playerOne) {
      case ROCK:
        res = (playerTwo == HandShape.SCISSORS);
        break;
      case PAPER:
        res = (playerTwo == HandShape.ROCK);
        break;
      case SCISSORS:
        res = (playerTwo == HandShape.PAPER);
        break;
      default:
        break;
    }

    return res;
  }
  
  
  /**
   * <p>Returns random HandShape object..
   * </p>
   * ¶@return random HandShape object.
   */
  public HandShape retrieveRandomHandShape() {
    return HandShape.values()[SECURE_RANDOM.nextInt(HandShape.values().length)];
  }
}
