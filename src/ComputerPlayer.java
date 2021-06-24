import java.util.ArrayList;
import java.util.Collections;

public class ComputerPlayer extends Player {
    Board board;

    public Word performMove(Board board) {
        this.board = new Board();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.board.set(i, j, board.get(i, j));
            }
        }
        String rackLetters = "";
        ArrayList<WordGenerator.Word> bestRowWords = new ArrayList<WordGenerator.Word>();
        ArrayList<WordGenerator.Word> bestColWords = new ArrayList<WordGenerator.Word>();
        for (int i = 0; i < 7; i++) {
            if (!this.rack.get(i).equals("BL")) {
                rackLetters += this.rack.get(i).tileLetter;
            }
        }
        WordGenerator.Word bestRowWord;
        WordGenerator.Word bestColWord;
        for (int i = 0; i < 15; i++) {
            String rowPattern = "";
            String availableLetters = rackLetters;
            int gapCount = 0;
            boolean isPlacementPossible = false;
            for (int j = 0; j < 15; j++) {
                if (!board.isBlankPlace(i, j)) {
                    availableLetters += board.get(i, j);
                    rowPattern += (gapCount) + ":" + board.get(i, j) + ":";
                    gapCount = 0;
                    isPlacementPossible = true;
                } else {
                    gapCount++;
                }
            }
            rowPattern += gapCount;
            if (isPlacementPossible) {
                WordGenerator wordGenerator = new WordGenerator(availableLetters, new Pattern(rowPattern));
                bestRowWord = wordGenerator.getHighestScoreWord();
                bestRowWord.orientationSpace = i;
                if (bestRowWord.CommonIndexesInOrientationSpace[0] != -1) {
                    bestRowWords.add(bestRowWord);
                }
            } else if (!isPlacementPossible && i == 7) {
                WordGenerator wordGenerator = new WordGenerator(availableLetters, new Pattern(""));
                bestRowWord = wordGenerator.getHighestScoreWord();
                bestRowWord.orientationSpace = i;
                Word generatedWord = generateFirstWord(bestRowWord);
                return generatedWord;
            }
        }

        for (int i = 0; i < 15; i++) {
            String colPattern = "";
            String availableLetters = rackLetters;
            int gapCount = 0;
            boolean isPlacementPossible = false;
            for (int j = 0; j < 15; j++) {
                if (!board.isBlankPlace(j, i)) {
                    availableLetters += board.get(j, i);
                    colPattern += (gapCount) + ":" + board.get(j, i) + ":";
                    gapCount = 0;
                    isPlacementPossible = true;
                } else {
                    gapCount++;
                }
            }
            colPattern += gapCount;
            if (isPlacementPossible) {
                WordGenerator wordGenerator = new WordGenerator(availableLetters, new Pattern(colPattern));
                bestColWord = wordGenerator.getHighestScoreWord();
                bestColWord.orientationSpace = i;
                if (bestColWord.CommonIndexesInOrientationSpace[0] != -1) {
                    bestColWords.add(bestColWord);
                }
            }
        }
        WordGenerator.Word temp = new WordGenerator.Word("", -1, new int[]{-1, -1});
        Collections.sort(bestRowWords, temp);
        Collections.sort(bestColWords, temp);
        bestColWord = bestColWords.get(bestColWords.size() - 1);
        bestRowWord = bestRowWords.get(bestRowWords.size() - 1);

        int i = 2, j = 2;

        Word generatedRowWord = generateBoardRowWord(bestRowWord);
        placeWord(generatedRowWord);
        boolean isTurnValid = validateTurnEffect(generatedRowWord);

        while (isTurnValid == false && (bestRowWords.size() - i >= 0)) {
            removeWord(generatedRowWord);
            bestRowWord = bestRowWords.get(bestRowWords.size() - i);
            generatedRowWord = generateBoardRowWord(bestRowWord);
            placeWord(generatedRowWord);
            isTurnValid = validateTurnEffect(generatedRowWord);
            i++;
        }
        removeWord(generatedRowWord);

        Word generatedColWord = generateBoardColWord(bestColWord);
        placeWord(generatedColWord);
        isTurnValid = validateTurnEffect(generatedColWord);
        while (isTurnValid == false && (bestColWords.size() - j >= 0)) {
            removeWord(generatedColWord);
            bestColWord = bestColWords.get(bestColWords.size() - i);
            generatedColWord = generateBoardColWord(bestColWord);
            placeWord(generatedColWord);
            isTurnValid = validateTurnEffect(generatedColWord);
            j++;
        }

        if (bestRowWord.score > bestColWord.score) {
            return generatedRowWord;
        } else {
            return generatedColWord;
        }
    }

    private void placeWord(Word word) {
        for (int i = 0; i < word.noOfLetters; i++) {
            if (word.letters[i].isMyOwnTile == true) {
                board.set(word.letters[i].row, word.letters[i].col, word.letters[i].tile.tileLetter);
            }
        }
    }

    private void removeWord(Word word) {
        for (int i = 0; i < word.noOfLetters; i++) {
            if (word.letters[i].isMyOwnTile == true) {
                board.set(word.letters[i].row, word.letters[i].col, Board.getDefaultBoardPos(word.letters[i].row, word.letters[i].col));
            }
        }
    }

    private Word generateBoardRowWord(WordGenerator.Word bestRowWord) {
        Word word = new Word();
        int startCol = -1;
        int endCol = -1;
        int row = bestRowWord.orientationSpace;
        String inpStr = bestRowWord.word;
        int[] commonPartIndexes = bestRowWord.CommonIndexesInOrientationSpace;
        if (commonPartIndexes[0] == -1) {
            System.out.println(inpStr);
            System.out.println(bestRowWord.orientationSpace);
            System.out.println(bestRowWord.score);
        }
        for (int i = 0; i < 15; i++) {
            if (board.get(row, i).equals(new String(inpStr.charAt(commonPartIndexes[0]) + ""))) {
                if (board.get(row, i + commonPartIndexes[1] - commonPartIndexes[0]).equals(new String(inpStr.charAt(commonPartIndexes[1]) + ""))) {
                    startCol = i - commonPartIndexes[0];
                    endCol = (i + commonPartIndexes[1] - commonPartIndexes[0]) + ((inpStr.length() - 1) - commonPartIndexes[1]);
                }
            }
        }
        for (int i = 0, j = startCol; i < inpStr.length(); i++, j++) {
            if (board.get(row, j).equals(new String(inpStr.charAt(i) + ""))) {
                word.addLetter(row, j, new String(inpStr.charAt(i) + ""), false);
            } else {
                word.addLetter(row, j, new String(inpStr.charAt(i) + ""), true);
            }
        }
        return word;
    }

    private boolean validateTurnEffect(Word word) {
        TurnSummaryLogic temp = new TurnSummaryLogic(word, board);
        for (int i = 0; i < temp.words.size(); i++) {
            String currWord = new String();
            for (int j = 0; j < temp.words.get(i).noOfLetters; j++) {
                currWord += temp.words.get(i).letters[j].tile.tileLetter;
            }
            boolean isValid = WordCheck.isWord(currWord);
            if (isValid == false) {
                return false;
            }
        }
        return true;
    }

    private Word generateBoardColWord(WordGenerator.Word bestColWord) {
        Word word = new Word();
        int startRow = -1;
        int endRow = -1;
        int col = bestColWord.orientationSpace;
        String inpStr = bestColWord.word;
        int[] commonPartIndexes = bestColWord.CommonIndexesInOrientationSpace;
        for (int i = 0; i < 15; i++) {
            if (board.get(i, col).equals(new String(inpStr.charAt(commonPartIndexes[0]) + ""))) {
                if (board.get(i + commonPartIndexes[1] - commonPartIndexes[0], col).equals(new String(inpStr.charAt(commonPartIndexes[1]) + ""))) {
                    startRow = i - commonPartIndexes[0];
                    endRow = (i + commonPartIndexes[1] - commonPartIndexes[0]) + ((inpStr.length() - 1) - commonPartIndexes[1]);
                }
            }
        }
        for (int i = 0, j = startRow; i < inpStr.length(); i++, j++) {
            if (board.get(j, col).equals(new String(inpStr.charAt(i) + ""))) {
                word.addLetter(j, col, new String(inpStr.charAt(i) + ""), false);
            } else {
                word.addLetter(j, col, new String(inpStr.charAt(i) + ""), true);
            }
        }
        return word;
    }

    private Word generateFirstWord(WordGenerator.Word bestPossibleWord) {
        Word word = new Word();
        String inpStr = bestPossibleWord.word;
        int midIndex = inpStr.length() / 2;
        int startCol = 7 - midIndex;
        for (int i = 0, j = startCol; i < inpStr.length(); i++, j++) {
            word.addLetter(7, j, new String(inpStr.charAt(i) + ""), true);
        }
        return word;
    }
}
