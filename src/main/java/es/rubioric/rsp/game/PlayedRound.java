package es.rubioric.rsp.game;

import es.rubioric.rsp.model.GameResult;
import es.rubioric.rsp.model.HandShape;
import java.util.Comparator;



/**
 *
 *<p>Entity POJO that represents a played round.</p>
 *
 *@author Ricardo Rubio
 *
 */
public class PlayedRound implements Comparable<PlayedRound> {

  private int id;

  private String sessionId;

  private HandShape playerOne;

  private HandShape playerTwo;

  private GameResult result;


  /**
   * <p>Constructor.
   * </p>
   *¶@param sessionId
   *¶@param playerOne
   *¶@param playerTwo
   *¶@param result
   */
  public PlayedRound(String sessionId, HandShape playerOne, HandShape playerTwo, 
                    GameResult result) {
    
    this.sessionId = sessionId;
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    this.result = result;
    
  }

  public String getSessionId() {
    return sessionId;
  }
  

  public HandShape getPlayerOne() {
    return playerOne;
  }

  
  public HandShape getPlayerTwo() {
    return playerTwo;
  }

  
  public GameResult getResult() {
    return result;
  }

  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  
  @Override
  public int compareTo(PlayedRound o) {
    return Comparator.comparing(PlayedRound::getId)
                     .compare(this, o);
  }
  

}
