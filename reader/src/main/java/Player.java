import org.apache.commons.lang3.tuple.Triple;

import java.util.LinkedList;

public class Player {
    public Player(String name) {
        this.name = name;
    }
    private final String name;
    private int averageScore;
    private int gamesPlayed = 0;
    private LinkedList<Triple<String, Integer, Integer>> games;


    public String getName() {
        return name;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void insertGame(String nameOfCourse, Integer score, Integer place) {
        Triple<String, Integer, Integer> game =
                new Triple<String, Integer, Integer>() {
                    @Override
                    public String getLeft() {
                        return nameOfCourse;
                    }

                    @Override
                    public Integer getMiddle() {
                        return score;
                    }

                    @Override
                    public Integer getRight() {
                        return place;
                    }
                };

        games.addLast(game);
        averageScore = ((averageScore * gamesPlayed) + score) / gamesPlayed + 1;
        gamesPlayed++;
    }
}
