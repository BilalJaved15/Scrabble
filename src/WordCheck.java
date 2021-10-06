
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bilal Javed
 */
public class WordCheck {

    public static boolean isWord(String word) {
        boolean isFound = false;
        try {
            File file = new File("assets/dictionary.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while (line != null) {
                if (line.equals(word)) {
                    return true;
                }
                line = br.readLine();
            }
            br.close();
            fr.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
