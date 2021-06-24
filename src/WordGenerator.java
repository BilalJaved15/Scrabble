import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class WordGenerator {
    String lettersStr;
    Pattern pattern;
    int orientationSpace;
    int wordCount;
    ArrayList<Word> words;

    static class Word implements Comparator<Word> {

        String word;
        int orientationSpace;
        int score;
        int[] CommonIndexesInOrientationSpace;

        private void calcWordScore() {
            score = 0;
            for (int i = 0; i < word.length(); i++) {
                score = score + Tile.getTileScoreByLetter(Character.toString(word.charAt(i)));
            }
        }

        public Word(String word, int orientationSpace, int[] commonIndexesInOrientationSpace) {
            this.orientationSpace = orientationSpace;
            this.CommonIndexesInOrientationSpace = commonIndexesInOrientationSpace;
            this.word = word;
            calcWordScore();
        }

        @Override
        public int compare(Word o1, Word o2) {
            return o1.score - o2.score;
        }
    }

    public WordGenerator(String lettersStr, Pattern pattern) {
        char[] lettersChars = lettersStr.toCharArray();
        Arrays.sort(lettersChars);
        this.lettersStr = new String(lettersChars);
        this.pattern = pattern;
        words = new ArrayList<Word>();
        fetchMatchingWords();
    }

    public ArrayList<Word> fetchMatchingWords() {
        try {
            File file = new File("dictionary.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                char[] dictChars = line.toCharArray();
                Arrays.sort(dictChars);
                String sortedDictStr = new String(dictChars);
                if (lettersContain(sortedDictStr) && !isSameWord(line)) {
                    if (pattern.isAMatch(line)) {
                        words.add(new Word(line, orientationSpace, pattern.midPartIndexes));
                    }
                }
                line = br.readLine();
            }
            br.close();
            fr.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return words;
    }

    public boolean lettersContain(String inpStr) {
        int commonLength = 0;
        if (!(inpStr.length() > lettersStr.length())) {
            int i = 0;
            int j = 0;
            while (inpStr.length() > i && lettersStr.length() > j) {
                if (inpStr.charAt(i) == lettersStr.charAt(j)) {
                    i++;
                    j++;
                    commonLength++;
                } else if (inpStr.charAt(i) > lettersStr.charAt(j)) {
                    while ((j < lettersStr.length()) && inpStr.charAt(i) > lettersStr.charAt(j)) {
                        j++;
                    }
                } else if (inpStr.charAt(i) < lettersStr.charAt(j)) {
                    while (i < inpStr.length() && inpStr.charAt(i) < lettersStr.charAt(j)) {
                        i++;
                    }
                }
            }
        } else {
            return false;
        }
        if (commonLength == inpStr.length()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSameWord(String inpStr) {
        String fixedPatternWords = new String();
        for (int i = 0; i < pattern.pattern.length(); i++) {
            if ((pattern.pattern.charAt(i)) >= 'A' && (pattern.pattern.charAt(i) <= 'Z')) {
                fixedPatternWords += pattern.pattern.charAt(i);
            }
        }
        if (fixedPatternWords.equals(inpStr)) {
            return true;
        } else {
            return false;
        }
    }

    public Word getHighestScoreWord() {
        if (words.size() > 0) {
            return words.get(words.size() - 1);
        } else {
            Word temp = new Word("", -1, new int[]{-1, -1});
            return temp;
        }
    }

}
