public class BlankTileAssignerLogic {
    public BlankTileAssignerLogic(Word word, MatchRoundGUI refg) {
        for (int i = 0; i < word.noOfLetters; i++) {
            if (word.letters[i].tile.tileLetter.equals("BL")) {
                String currLetter = refg.board[word.letters[i].col][word.letters[i].row].getText();
                BlankTileAssignerGUI temp = new BlankTileAssignerGUI(word.letters[i].col, word.letters[i].row, refg);
                /*while(refg.board[word.letters[i].col][word.letters[i].row].getText() == currLetter){
                    System.out.print("");
                }*/
            }
        }
    }
}
