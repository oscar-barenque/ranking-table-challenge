package org.example.ranking.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LeagueRankingManagerTest {

  private final LeagueRankingFactory leagueRankingFactory = new LeagueRankingFactory();

  private LeagueRankingManager manager;

  private static final String NOMINAL_EXPECTED = """
        1. Tarantulas, 6 pts
        2. Lions, 5 pts
        3. FC Awesome, 1 pt
        3. Snakes, 1 pt
        5. Grouches, 0 pts
        """;

  @BeforeEach
  void init() {
    manager = leagueRankingFactory.createManager();
  }

  @Test
  void testNominalCase() {
    manager.computeMatchScoreLine("Lions 3, Snakes 3");
    manager.computeMatchScoreLine("Tarantulas 1, FC Awesome 0");
    manager.computeMatchScoreLine("Lions 1, FC Awesome 1");
    manager.computeMatchScoreLine("Tarantulas 3, Snakes 1");
    manager.computeMatchScoreLine("Lions 4, Grouches 0");
    StringBuilder sb = new StringBuilder();
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    manager.printRankingTable(printStream);
    assertEquals(NOMINAL_EXPECTED, outputStream.toString());
  }

}