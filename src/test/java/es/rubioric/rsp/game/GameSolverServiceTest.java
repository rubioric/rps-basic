package es.rubioric.rsp.game;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.rubioric.rsp.model.GameResult;
import es.rubioric.rsp.model.HandShape;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest
class GameSolverServiceTest {

  private static final String SESSIONID = "sessionid";
  
  @Autowired
  GameSolverService service;

  @Autowired
  PlayedRoundRepository repo;
  
  @BeforeEach
  void setup() {
    repo.removeAll();
    repo.resetIndexes();
  }
  
  @Test
  void testEmptyList() throws Exception {
    assertTrue(service.findAllBySessionId("").isEmpty());  
  }
  
  
  @Test
  void testRegisterPlayedRound() throws Exception {
    
    for (GameExpectedResults round : GameExpectedResults.values()) {
      service.registerPlayedRound(SESSIONID, round.getPlayerOne(), round.getPlayerTwo());
    }
        
    List<PlayedRound> playedRounds = service.findAllBySessionId(SESSIONID);
    
    assertNotNull(playedRounds); 
    assertEquals(GameExpectedResults.values().length, playedRounds.size()); 
    
    int i = 0;
    for (GameExpectedResults round : GameExpectedResults.values()) {
      
      PlayedRound playedRound = playedRounds.get(i);
      
      assertEquals(round.getPlayerOne(), playedRound.getPlayerOne()); 
      assertEquals(round.getPlayerTwo(), playedRound.getPlayerTwo()); 
      assertEquals(round.getExpectedResult(), playedRound.getResult());
      
      i++;
    }
        
  }
  
  @Test
  void testSummarizeTotalScores() throws Exception {
    
    for (GameExpectedResults round : GameExpectedResults.values()) {
      service.registerPlayedRound(SESSIONID, round.getPlayerOne(), round.getPlayerTwo());
    }
    
    
    Map<GameResult, Long> mapTotals = 
        Arrays.stream(GameExpectedResults.values())
              .collect(groupingBy(GameExpectedResults::getExpectedResult, counting()));
          
    TotalScores totalScores = service.summarizeTotalScores();
    
    assertNotNull(totalScores); 
    assertEquals(totalScores.getTotalPlayedRounds(), GameExpectedResults.values().length); 
    assertEquals(totalScores.getTotalWinsPlayerOne(), mapTotals.get(GameResult.PLAYER_1_WINS));
    assertEquals(totalScores.getTotalWinsPlayerTwo(), mapTotals.get(GameResult.PLAYER_2_WINS));
    assertEquals(totalScores.getTotalDraws(), mapTotals.get(GameResult.DRAW));
        
  }
  
  @Test
  void testSummarizeTotalScoresWithNoPlayedRounds() throws Exception {
    
    TotalScores totalScores = service.summarizeTotalScores();
    
    assertNotNull(totalScores); 
    assertEquals(0L, totalScores.getTotalPlayedRounds()); 
    assertEquals(0L, totalScores.getTotalWinsPlayerOne());
    assertEquals(0L, totalScores.getTotalWinsPlayerTwo());
    assertEquals(0L, totalScores.getTotalDraws());
        
  }
  
  @Test
  void testSummarizeTotalScoresWithNoPlayer2Wins() throws Exception {
    
    service.registerPlayedRound(SESSIONID, HandShape.ROCK, HandShape.SCISSORS);
    
    TotalScores totalScores = service.summarizeTotalScores();
    
    assertNotNull(totalScores); 
    assertEquals(1L, totalScores.getTotalPlayedRounds()); 
    assertEquals(1L, totalScores.getTotalWinsPlayerOne());
    assertEquals(0L, totalScores.getTotalWinsPlayerTwo());
    assertEquals(0L, totalScores.getTotalDraws());
        
  }
  
  @Test
  void testRetrieveRandomHandShape() throws Exception { 
    assertNotNull(service.retrieveRandomHandShape());
  }
}
