//import org.libindic.common.LanguageDetect;
package contalletres;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by sujith on 27/5/14.
 */
public class Syllabifier {

    /**
     * This function syllabifies English text
     *
     * @param text English text
     * @return syllabified English text
     */
    private String syllabifyEnglish(String text) {

        text = " " + text + " ";

        final List<String> VOWEL_LIST = Arrays.asList("a", "e", "i", "o", "u", "y");
        final List<String> VOWEL_PAIRS = Arrays.asList("ai", "au", "aw", "ee", "ea",
                "oa", "oi", "ou", "oo", "ow", "oy", "uu");
        final List<String> CONSONANT_PAIRS = Arrays.asList("b", "c", "d", "f", "g",
                "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v",
                "w", "x", "z");
        final List<String> CONSONANT_BLENDS = Arrays.asList("bl", "br", "ch", "chr",
                "cl", "cr", "dr", "fl", "fr", "gl", "gr", "kn", "pl", "pr",
                "sc", "sh", "sk", "sl", "sm", "sn", "sp", "spr", "squ", "st",
                "str", "sw", "th", "tr", "thr", "nt", "wh");

        for (int i = 0; i < 2; i++) {
            text = (Pattern.compile("([0-9])([0-9])", Pattern.CASE_INSENSITIVE)
                    .matcher(text)).replaceAll("$1#$2");
        }

        text = (Pattern.compile("i([aeiuy])([bcdfghjklmnpqrstvwxz])",
                Pattern.CASE_INSENSITIVE).matcher(text)).replaceAll("i+$1+$2");

        text = (Pattern.compile("the([aeiouy])([bcdfghjklmnpqrstvwxz])",
                Pattern.CASE_INSENSITIVE).matcher(text))
                .replaceAll("the+$1+$2");

        text = (Pattern.compile("([bcdfghjklmnpqrstvwxz])$1([^ ])",
                Pattern.CASE_INSENSITIVE).matcher(text)).replaceAll("$1-$1$2");
        System.out.println(text);

        int position = 0;
        while (position < text.length() - 1) {
            if (VOWEL_LIST.contains("" + text.charAt(position))
                    && VOWEL_LIST.contains("" + text.charAt(position + 1))) {
                if (!(VOWEL_PAIRS.contains(text.substring(position,
                        position + 2)))) {
                    if (!(Arrays.asList("tion", "dual", "nion", "quir", "tiou")
                            .contains(text
                                    .substring(position - 1, position + 3)))) {
                        text = text.substring(0, position + 1) + "_"
                                + text.substring(position + 1);
                    }
                }
            }
            ++position;
        }

        int start = 0;
        int end = 0;

        while (start < text.length() - 1) {
            if (VOWEL_LIST.contains("" + text.charAt(start))
                    && CONSONANT_PAIRS.contains("" + text.charAt(start + 1))) {
                end = start + 1;
                while (end <= text.length() - 1
                        && CONSONANT_PAIRS.contains("" + text.charAt(end))) {
                    end = end + 1;
                }

                if (end <= text.length() - 1
                        && (CONSONANT_PAIRS.contains(text.substring(start + 1,
                                end)) || CONSONANT_BLENDS.contains(text
                                .substring(start + 1, end)))
                        && VOWEL_LIST.contains("" + text.charAt(end))
                        && !(text.substring(end, end + 2).equals("e "))) {
                    text = text.substring(0, start + 1) + "/"
                            + text.substring(start + 1);
                }
            }
            start = start + 1;
        }

        start = 0;
        end = 0;
//test1
        while (start < text.length() - 1) {
            if (VOWEL_LIST.contains("" + text.charAt(start))
                    && CONSONANT_PAIRS.contains("" + text.charAt(start + 1))) {
                end = start + 2;
                while (end <= text.length() - 1
                        && CONSONANT_PAIRS.contains("" + text.charAt(end))) {
                    end = end + 1;
                }

                if (end <= text.length() - 1 && end > start + 2
                        && VOWEL_LIST.contains("" + text.charAt(end))) {
                    if (!(CONSONANT_BLENDS.contains(text.substring(start + 1,
                            end)))) {
                        text = text.substring(0, start + 2) + "-"
                                + text.substring(start + 2);
                    }
                }
            }
            start = start + 1;
        }
        return text.trim();
    }

    public List<String> getSyllables(String text) {
        List<String> syllables;
        String str = syllabifyEnglish(text);
        syllables = new ArrayList<String>();
        for (char ch : str.toCharArray()) {
            syllables.add("" + ch);
        }
        return syllables;
    }
}
