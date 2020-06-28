package realpac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore {

    private String[] name;
    private int[] score;
    private final File file = new File("C:\\Users\\chjal\\Documents\\NetBeansProjects\\RealPac\\src\\realpac\\HighScore.txt");

    public HighScore() {
        name = new String[15];
        score = new int[15];
    }

    public String[] getName() {
        return name;
    }

    public int getLowestScore() {
        return (score[8]);
    }

    public void init() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String[] info = new String[2];
        String nextLine;

        int j = 0;
        while ((nextLine = br.readLine()) != null && j < 10) {
            if (!nextLine.equals("")) {
                info = nextLine.split(";");//; would be the delimiter
                name[j] = info[0];
                score[j] = Integer.parseInt(info[1].trim());

                j++;
            }
        }
    }

    public void sortAndAdd(String newName, int newScore) throws FileNotFoundException, IOException {
      init();
        score[9] = newScore;
        name[9] = newName;
        //int biggest, biggestIndex;
        for (int i = 0; i < score.length; i++) {
//            biggest = score[i];
//            biggestIndex = i;
            for (int k = i; k < score.length; k++) {
                if (score[k] > score[i]) {
                    int tmpInt = score[k];
                    String tmpString = name[k];
                    score[k] = score[i];
                    name[k] = name[i];
                    score[i] = tmpInt;
                    name[i] = tmpString;
                }
            }
        }

//        for (int k = 10; k < score.length; k++) {
//            score[k] = null;
//            name[k] = null;
//        }
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        for (int i = 0; i < score.length; i++) {
            try {
                fileWriter = new FileWriter(file, true);

                bw = new BufferedWriter(fileWriter);
                bw.newLine();
                bw.write(name[i] + ";" + score[i]);

                bw.close();
            } catch (IOException ex) {
            }

        }
    }
}
