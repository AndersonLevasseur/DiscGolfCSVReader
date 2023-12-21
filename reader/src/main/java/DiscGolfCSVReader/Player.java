package DiscGolfCSVReader;

import org.apache.commons.lang3.tuple.Triple;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Player {
    private final String name;
    private float totalScore;
    private float totalPlace;
    private int gamesPlayed = 0;
    private final LinkedList<Triple<String, Integer, Integer>> games =
            new LinkedList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAverageScore() {
        return (int) (totalScore/gamesPlayed);
    }

    public int getAveragePlace() {
        return (int) (totalPlace/gamesPlayed);
    }
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void insertGame(String nameOfCourse, Integer score, Integer place) {
        Triple<String, Integer, Integer> game =
                new Triple<>() {
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
        totalScore += score;
        totalPlace += place;
        gamesPlayed++;
    }

    /**
     * Should look like
     * Name         AVG_SCORE AVG_PLACE GAMES_PLAYED
     * COURSE  SCORE     PLACE
     *
     * @param CSV
     * @throws IOException
     */
    public void printToCSV(File CSV) throws IOException {
        FileWriter writer = new FileWriter(CSV, true);
        String header =
                getName() + ",AVG," + getAverageScore() + "," + getAveragePlace() + "," + getGamesPlayed();
        writer.write(header + "\n");
        String game;
        for (int i = 0; i < gamesPlayed; i++) {
            Triple<String, Integer, Integer> currGame = games.remove();
            game = "," + currGame.getLeft() + "," + currGame.getMiddle() + "," + currGame.getRight();
            writer.write(game + "\n");
        }

        writer.close();
    }
}
