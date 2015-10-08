package StringsArrays;

/*Various string related functions*/
public class StringHelpers {

    /*Check to see if a string is all unique characters without using adtl. data structures*/
    public static boolean isUniqueChars(String toTest) {
        if (toTest.length() > 128) { //Extended ASCII
            return false;
        }
        boolean[] char_set = new boolean[128];

        for (int i = 0; i < toTest.length(); i++) {
            int val = toTest.charAt(i);
            if (char_set[val]) { //Character has already been found in string
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    /*Given two strings, determine if one is a permutation of the other.*/
    


    public static void main(String[] args) {
        System.out.println(StringHelpers.isUniqueChars("abcde"));
        System.out.println(StringHelpers.isUniqueChars("abcbe"));
    }
}
