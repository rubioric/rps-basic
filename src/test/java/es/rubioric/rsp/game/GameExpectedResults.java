package es.rubioric.rsp.game;

import static es.rubioric.rsp.model.GameResult.DRAW;
import static es.rubioric.rsp.model.GameResult.PLAYER_1_WINS;
import static es.rubioric.rsp.model.GameResult.PLAYER_2_WINS;
import static es.rubioric.rsp.model.HandShape.PAPER;
import static es.rubioric.rsp.model.HandShape.ROCK;
import static es.rubioric.rsp.model.HandShape.SCISSORS;

import es.rubioric.rsp.model.GameResult;
import es.rubioric.rsp.model.HandShape;
import java.util.HashMap;
import java.util.Map;



/**
 * 
 *
 *<p> 
 *  Enum with the expected results depending on each player's choice.
 *</p>
 *
 * @author Ricardo Rubio
 *
 */
public enum GameExpectedResults {

  ROCK_ROCK(ROCK.name() + "_" + ROCK.name(), ROCK, ROCK, DRAW),
  ROCK_PAPER(ROCK.name() + "_" + PAPER.name(), ROCK, PAPER, PLAYER_2_WINS),
  ROCK_SCISSORS(ROCK.name() + "_" + SCISSORS.name(), ROCK, SCISSORS, PLAYER_1_WINS),
  
  PAPER_ROCK(PAPER.name() + "_" + ROCK.name(), PAPER, ROCK, PLAYER_1_WINS),
  PAPER_PAPER(PAPER.name() + "_" + PAPER.name(), PAPER, PAPER, DRAW),
  PAPER_SCISSORS(PAPER.name() + "_" + SCISSORS.name(), PAPER, SCISSORS, PLAYER_2_WINS),
  
  SCISSORS_ROCK(SCISSORS.name() + "_" + ROCK.name(), SCISSORS, ROCK, PLAYER_2_WINS),
  SCISSORS_PAPER(SCISSORS.name() + "_" + PAPER.name(), SCISSORS, PAPER, PLAYER_1_WINS),
  SCISSORS_SCISSORS(SCISSORS.name() + "_" + SCISSORS.name(), SCISSORS, SCISSORS, DRAW) 
  ;
  
  
  private static class Holder {
    static Map<String, GameExpectedResults> EXPECTED_MAP = new HashMap<>();
  }
   
  private String index;
  private final HandShape playerOne;
  private final HandShape playerTwo;
  private final GameResult expectedResult;
    
  
  /**
   * 
   * <p>Constructor.
   * </p>
   *¶@param index
   *¶@param playerOne
   *¶@param playerTwo
   *¶@param expectedResult
   */
  private GameExpectedResults(String index, 
                             HandShape playerOne, HandShape playerTwo, 
                             GameResult expectedResult) {
    this.index = index;
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    this.expectedResult = expectedResult;
    
    Holder.EXPECTED_MAP.put(playerOne.name() + "_" + playerTwo.name(), this);
  }
  
  
  public HandShape getPlayerOne() {
    return playerOne;
  }

  public HandShape getPlayerTwo() {
    return playerTwo;
  }

  public GameResult getExpectedResult() {
    return expectedResult;
  }
  
}
