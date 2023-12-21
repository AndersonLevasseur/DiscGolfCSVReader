package DiscGolfCSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CSVReader {
    public static HashMap<String, Player> playas = new HashMap<>(50);

    public static void main(String[] args) throws IOException {
        for (String arg : args) {
            File file = new File(arg);
            try {
                readCSV(new Scanner(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        printToCSV();
    }

    public static void readCSV(Scanner csv) {
        PlayerComp newComp = new PlayerComp();
        PriorityQueue<String[]> players = new PriorityQueue<>(newComp);
        while (csv.hasNextLine()) {
            // Split the line into tokens based on the comma delimiter
            String[] tokens = csv.nextLine().split(",");

            //identify what line it is
            switch (tokens[0]) {
                case "PlayerName":
                    //first line in file, ignore?
                    break;
                case "Par":
                    final int size = players.size();
                    for (int i = 0; i < size; i++) {
                        readline(players.poll(), i + 1);
                    }
                    break;
                default:
                    players.add(tokens);
            }
        }
    }

    public static void readline(String[] line, Integer place) {
        Player currPlay;
        if (!playas.containsKey(line[0])) {
            Player nPlaya = new Player(line[0]);
            playas.put(line[0], nPlaya);
            currPlay = nPlaya;
        } else {
            currPlay = playas.get(line[0]);
        }

        currPlay.insertGame(line[1] + " " + line[2],
                Integer.parseInt(line[5]), place);
    }

    public static void printToCSV() throws IOException {
        File CSV = new File("./Player.csv");
        FileWriter csvWriter = new FileWriter(CSV);
        csvWriter.write("NAME,COURSE,SCORE,PLACE,GAMES-PLAYED\n");
        csvWriter.close();
        playas.forEach((key, value) -> {
            try {
                value.printToCSV(CSV);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
