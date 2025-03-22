package arrays;

import java.util.HashSet;

public class CountUniqueCharacters {
    public static void main(String[] args) {
//        countUniqueCharacters();
        countUniqueCharactersV1();
    }

    private static void countUniqueCharactersV1() {
        String str = "aaabbcccddaeef";
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append(charArray[0] + " ");
        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i] == charArray[i-1]){
                continue;
            }else{
                sb.append(charArray[i] + " ");
            }
        }

        System.out.println(sb);
    }

    private static void countUniqueCharacters() {
        String str = "aaabbcccddaeef";
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        HashSet<Character> uniSet = new HashSet<>();
        for (int i = 0; i < charArray.length; i++) {
            if (uniSet.add(charArray[i])){
                sb.append(charArray[i] + " ");
            }
        }

        System.out.println(sb.toString());
    }
}
