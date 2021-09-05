package es.rubioric.rsp.game;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import es.rubioric.rsp.model.GameResult;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;



/**
*
*<p>Repository for saving/retrieving played rounds.</p>
*
*@author Ricardo Rubio
*
*/
@Repository
public class PlayedRoundRepository {

  private SortedSet<PlayedRound> registry = new TreeSet<>((g1, g2) -> g1.compareTo(g2));

  private AtomicInteger indexId = new AtomicInteger(0);

  /**
   * 
   * <p>Saves a played round.
   * </p>
   * ¶@param playedRound
   */
  public void save(PlayedRound playedRound) {

    int id = indexId.getAndAdd(1);
    playedRound.setId(id);

    registry.add(playedRound);
  }

  
  /**
   * 
   * <p>Retrieves a list of played rounds associated with the provided session id.
   * </p>
   * ¶@param sessionId
   * ¶@return a list of played rounds associated with the provided session id.
   *          An empty list is returned if no rounds are found.
   */
  public List<PlayedRound> findAllBySessionId(String sessionId) {

    Predicate<PlayedRound> isSameSessionId = g -> g.getSessionId().equals(sessionId);

    return registry.stream().filter(isSameSessionId).collect(Collectors.toList());
  }

  /**
   * 
   * <p>Removes all stored played rounds.
   * </p>
   */
  public void removeAll() {
    registry.clear();
  }
  
  /**
   * 
   * <p>Reset indexes.
   * </p>
   */
  public void resetIndexes() {
    indexId = new AtomicInteger(0);
  }
  
  /**
   * 
   * <p>Retrieves a POJO containing total scores.
   * </p>
   * ¶@return a POJO containing total scores.
   */
  public TotalScores summarizeTotalScores() {
    
    TotalScores totalScores = new TotalScores();
    
    if (!registry.isEmpty()) {
      Map<GameResult, Long> map = 
          registry.stream()
                  .collect(groupingBy(PlayedRound::getResult, counting()));
  
      totalScores.setTotalPlayedRounds(registry.size());
      
      Long totalWinsPlayerOne = map.get(GameResult.PLAYER_1_WINS);
      Long totalWinsPlayerTwo = map.get(GameResult.PLAYER_2_WINS);
      Long totalDraws         = map.get(GameResult.PLAYER_1_WINS);
      
      totalScores.setTotalWinsPlayerOne(totalWinsPlayerOne != null ? totalWinsPlayerOne : 0L); 
      totalScores.setTotalWinsPlayerTwo(totalWinsPlayerTwo != null ? totalWinsPlayerTwo : 0L);
      totalScores.setTotalDraws(totalDraws != null ? totalDraws : 0L);
    }
    
    return totalScores;
  }
}
