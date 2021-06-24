import java.util.StringTokenizer;

public class Pattern {
    String pattern;
    int[] midPartIndexes;

    public Pattern(String pattern) {
        this.pattern = pattern;
        midPartIndexes = new int[2];
    }

    boolean isAMatch(String inpStr) {
        if (pattern.equals("")){
            return true;
        }
        StringTokenizer prefixtokenizer = new StringTokenizer(pattern, ":");
        String strMaxPrefixLength = prefixtokenizer.nextToken();
        int maxPrefixLength = Integer.parseInt(strMaxPrefixLength);
        String midPart = pattern.replaceFirst(strMaxPrefixLength + ":", "");

        StringTokenizer postFixtokenizer = new StringTokenizer(((new StringBuilder()).append(midPart).reverse().toString()), ":");
        String strMaxPostfixLength  = postFixtokenizer.nextToken();
        midPart = new StringBuilder().append(midPart).reverse().toString().replaceFirst(strMaxPostfixLength + ":", "");
        midPart = new StringBuilder().append(midPart).reverse().toString();
        strMaxPostfixLength = new StringBuilder().append(strMaxPostfixLength).reverse().toString();
        int maxPostfixLength = Integer.parseInt(strMaxPostfixLength);

        String[] midParts = midPart.split(":");
        midPartIndexes = getMidPartIndex(midParts, inpStr);
        if (midPartIndexes[0] == -1){
            return false;
        }
        if (midPartIndexes[0] <= maxPrefixLength && inpStr.length() - midPartIndexes[1] <= maxPostfixLength) {
            return true;
        } else {
            return false;
        }
    }

    int[] getMidPartIndex(String[] midPart, String inpStr){
        int[] indexes = new int[2];
        indexes[0] = -1;
        indexes[1] = -1;
        boolean correctPattern = false;
        for (int i = 0, j = 0; i < inpStr.length() && !correctPattern; i++){
            if (inpStr.charAt(i) == midPart[j].charAt(0)){
                indexes[0] = i;
                j++;
                int k = i;
                correctPattern = true;
                while (k < inpStr.length() && correctPattern && j < midPart.length){
                    int nextFixedCharIndex = Integer.parseInt(midPart[j]) + 1;
                    j++;
                    k += nextFixedCharIndex;
                    if (k < inpStr.length() && inpStr.charAt(k) == midPart[j].charAt(0)){
                        j++;
                        indexes[1] = k;
                    }
                    else {
                        correctPattern = false;
                        indexes[0] = -1;
                        indexes[1] = -1;
                        j = 0;
                    }
                }
            }
        }
        if (correctPattern == true && indexes[1] == -1){
            indexes[1] = indexes[0];
        }
        return indexes;
    }
}
