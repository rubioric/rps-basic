package es.rubioric.rsp.game;


/**
 *
 *<p>POJO Bean that stores the summarized total scores.
 *</p>
 *
 * @author Ricardo Rubio
 *
 */
public class TotalScores {

  private long totalPlayedRounds;

  private long totalWinsPlayerOne;

  private long totalWinsPlayerTwo;

  private long totalDraws;

  /**
   * 
   * <p>Constructor.
   * </p>
   */
  public TotalScores() {
    super();
  }
  
  /**
   * 
   * <p>Constructor.
   * </p>
   *¶@param totalPlayedRounds
   *¶@param totalWinsPlayerOne
   *¶@param totalWinsPlayerTwo
   *¶@param totalDraws
   */
  public TotalScores(long totalPlayedRounds, 
                     long totalWinsPlayerOne, 
                     long totalWinsPlayerTwo, 
                     long totalDraws) {
    
    this.totalPlayedRounds = totalPlayedRounds;
    this.totalWinsPlayerOne = totalWinsPlayerOne;
    this.totalWinsPlayerTwo = totalWinsPlayerTwo;
    this.totalDraws = totalDraws;
  }

  

  public long getTotalPlayedRounds() {
    return totalPlayedRounds;
  }

  public long getTotalWinsPlayerOne() {
    return totalWinsPlayerOne;
  }

  public long getTotalWinsPlayerTwo() {
    return totalWinsPlayerTwo;
  }

  public long getTotalDraws() {
    return totalDraws;
  }

  public void setTotalPlayedRounds(long totalPlayedRounds) {
    this.totalPlayedRounds = totalPlayedRounds;
  }

  public void setTotalWinsPlayerOne(long totalWinsPlayerOne) {
    this.totalWinsPlayerOne = totalWinsPlayerOne;
  }

  public void setTotalWinsPlayerTwo(long totalWinsPlayerTwo) {
    this.totalWinsPlayerTwo = totalWinsPlayerTwo;
  }

  public void setTotalDraws(long totalDraws) {
    this.totalDraws = totalDraws;
  }

}
