import java.util.ArrayList;
import java.util.List;


public class StringSearching implements StringSearchingInterface {
    /**
     * Knuth-Morris-Pratt algorithm that relies on the failure table (also
     * called failure function).
     * Make sure to implement the function before implementing this method.
     * Works better with small alphabets.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern a CharSequence you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    @Override
    public List<Integer> kmp(CharSequence pattern, CharSequence text) {
        if (pattern != null && pattern.length() != 0 && text != null) {
            List<Integer> list = new ArrayList<>();
            int[] fail = buildFailureTable(pattern);
            int i = 0;
            int j = 0;
            while (i < text.length()) {
                if (pattern.charAt(j) == text.charAt(i)) {
                    if (j == pattern.length() - 1) {
                        list.add(i - pattern.length() + 1);
                        j = fail[j - 1];
                    } else {
                        i++;
                        j++;
                    }
                } else if (j > 0) {
                    j = fail[j - 1];
                } else {
                    i++;
                }
            }
            return list;
        } else {
            if (pattern == null) {
                throw new IllegalArgumentException("Pattern can't be null");
            } else if (pattern.length() == 0) {
                throw new IllegalArgumentException("Pattern length cant be 0");
            } else {
                throw new IllegalArgumentException("Text can't be null");
            }
        }
    }
    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * algorithm.
     *
     * The table built should be the length of the input text.
     *
     * Note that a given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param text a CharSequence you are building last table for
     * @return integer array of size text.length that you are building a failure
     * function for
     */
    @Override
    public int[] buildFailureTable(CharSequence text) {
        if (text != null) {
            int m = text.length();
            int[] fail = new int[m];
            int j = 1;
            int k = 0;
            while (j < m) {
                if (text.charAt(j) == text.charAt(k)) {
                    fail[j] = k + 1;
                    j++;
                    k++;
                } else if (k > 0) {
                    k = fail[k - 1];
                } else {
                    j++;
                }
            }
            return fail;
        } else {
            throw new IllegalArgumentException("Pattern cant be null");
        }
    }
    /**
     * Boyer Moore algorithm that relies on last table. Make sure to implement
     * the table before implementing this method. Works better with large
     * alphabets.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern a CharSequence you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    @Override
    public List<Integer> boyerMoore(CharSequence pattern, CharSequence text) {
        if (pattern != null && pattern.length() != 0 && text != null) {
            if (text.length() == 0 || pattern.length() > text.length()) {
                return new ArrayList<>();
            }
            List<Integer> list = new ArrayList<>();
            int n = text.length();
            int m = pattern.length();
            int[] last = buildLastTable(pattern);
            int i = m - 1;
            int k = m - 1;
            while (i < n) {
                char a = text.charAt(i);
                char b = pattern.charAt(k);
                if (a == b) {
                    if (k == 0) {
                        list.add(i);
                        k = m - 1;
                        i += (m);
                    } else {
                        i--;
                        k--;
                    }
                } else {
                    i += m - Math.min(k, 1 + last[a]);
                    k = m - 1;
                }
            }
            return list;
        } else {
            if (pattern == null) {
                throw new IllegalArgumentException("Pattern can't be null");
            } else if (pattern.length() == 0) {
                throw new IllegalArgumentException("Pattern length cant be 0");
            } else {
                throw new IllegalArgumentException("Text can't be null");
            }
        }
    }
    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table[x].
     * Each entry should be -1 if x is not in the pattern or the last index of x
     * where x is a particular character in your pattern.
     *
     * Ex. octocat
     *
     * table[o] = 3
     * table[c] = 4
     * table[t] = 6
     * table[a] = 5
     * table[everything else] = -1
     *
     * HINT: Characters auto cast to their corresponding int in Unicode (UTF-16)
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a CharSequence you are building last table for
     * @return integer array of size (Character.MAX_VALUE + 1) containing the
     * mapping for all characters in Unicode
     */
    @Override
    public int[] buildLastTable(CharSequence pattern) {
        if (pattern != null) {
            int[] table = new int[Character.MAX_VALUE + 1];
            int i;
            for (int c = 0; c < Character.MAX_VALUE + 1; c++) {
                table[c] = -1;
            }
            for (i = 0; i < pattern.length(); i++) {
                table[pattern.charAt(i)] = i;
            }
            return table;
        } else {
            throw new IllegalArgumentException("Pattern cant be null");
        }
    }
    /**
     * Hash function used for Rabin-Karp. The formula for hashing a string is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i), where c is the integer
     * value of the current character, and i is the index of the character
     *
     * For example: Hashing "bunn" as a substring of "bunny" with base 433 hash
     * = b * 433 ^ 3 + u * 433 ^ 2 + n * 433 ^ 1 + n * 433 ^ 0 = 98 * 433 ^ 3 +
     * 117 * 433 ^ 2 + 110 * 433 ^ 1 + 110 * 433 ^ 0 = 7977892179
     *
     * @throws IllegalArgumentException if current is null
     * @throws IllegalArgumentException if length is negative or greater
     *     than the length of current
     * @param current substring you are generating hash function for
     * @param length the length of the string you want to generate the hash for,
     * starting from index 0. For example, if length is 4 but current's length
     * is 6, then you include indices 0-3 in your hash (and pretend the actual
     * length is 4)
     * @return hash of the substring
     */
    @Override
    public int generateHash(CharSequence current, int length) {
        // fix length shit
        if (current != null && length >= 0 && current.length() >= length) {
            int hash = 0;
            for (int i = 0; i < length; i++) {
                hash += current.charAt(i) * pow(BASE, length - 1 - i);
            }
            return hash;
        } else {
            if (current == null) {
                throw new IllegalArgumentException("Current can't be null");
            } else if (length < 0) {
                throw new IllegalArgumentException("length cant be negative");
            } else {
                throw new IllegalArgumentException("Length can't"
                        + "be greater than current's length");
            }
        }
    }
    /**
     * Updates a hash in constant time to avoid constantly recalculating
     * entire hash. To update the hash:
     *
     *  remove the oldChar times BASE raised to the length - 1, multiply by
     *  BASE, and add the newChar.
     *
     * For example: Shifting from "bunn" to "unny" in "bunny" with base 433
     * hash("unny") = (hash("bunn") - b * 433 ^ 3) * 433 + y * 433 ^ 0 =
     * (7977892179 - 98 * 433 ^ 3) * 433 + 121 * 433 ^ 0 = 9519051770
     *
     * @throws IllegalArgumentException if length is negative
     * @param oldHash hash generated by generateHash
     * @param length length of pattern/substring of text
     * @param oldChar character we want to remove from hashed substring
     * @param newChar character we want to add to hashed substring
     * @return updated hash of this substring
     */
    @Override
    public int updateHash(int oldHash, int length, char oldChar, char newChar) {
        if (length >= 0) {
            return (oldHash - (oldChar * pow(BASE, length - 1))) * BASE
                    + newChar;
        } else {
            throw new IllegalArgumentException("Length can't be negative");
        }
    }

    /**
     * power helper method for hash functions
     * @param base the base number
     * @param power the power number
     * @return base to the nth power
     */
    private static int pow(int base, int power) {
        if (power == 0) {
            return 1;
        }
        return base * pow(base, --power);
    }
    /**
     * Runs Rabin-Karp algorithm. Generate initial hash, and compare it with
     * hash from substring of text same length as pattern. If the two
     * hashes match compare their individual characters, else update hash
     * and continue.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern a string you're searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    @Override
    public List<Integer> rabinKarp(CharSequence pattern, CharSequence text) {
        if (pattern != null && pattern.length() != 0 && text != null) {
            if (text.length() == 0 || pattern.length() == 0
                    || pattern.length() > text.length()) {
                return new ArrayList<Integer>();
            }
            List<Integer> rabinKarp = new ArrayList<Integer>();
            int haystack = generateHash(text, pattern.length());
            int needle = generateHash(pattern, pattern.length());
            for (int i = 0; (i + pattern.length()) <= text.length(); i++) {
                if (needle == haystack) {
                    int j = 0;
                    boolean matching = true;
                    while (j < pattern.length() && matching) {
                        if (text.charAt(i + j) == pattern.charAt(j)) {
                            matching = true;
                            j++;
                        } else {
                            matching = false;
                        }
                    }
                    if (matching) {
                        rabinKarp.add(i);
                    }
                }
                if ((i + pattern.length() + 1) <= text.length()) {
                    haystack = updateHash(haystack, pattern.length(),
                            text.charAt(i), text.charAt(i + pattern.length()));
                }
            }
            return rabinKarp;
        } else {
            if (pattern == null) {
                throw new IllegalArgumentException("Pattern can't be null");
            } else if (pattern.length() == 0) {
                throw new IllegalArgumentException("Pattern length cant be 0");
            } else {
                throw new IllegalArgumentException("Text can't be null");
            }
        }
    }


}
