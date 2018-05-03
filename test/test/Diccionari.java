/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import contalletres.FilterStrings;
import contalletres.GameType;
import contalletres.Languages;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jesus
 */
public class Diccionari {

    private static final String CONSONANT = "[a-z&&[^aeiou]]";
    private static final String VOCAL = "[aeiou]";
    private static final String ACCENT = "[\\xe9\\xed\\xf3\\xfa\\xfc\\xe0\\xe8\\xf2]";

    public static void main(String[] args) {
        //juntaObres();
        //creaDic();
        separaMonoSil();
        //llevarBasura();
        //reescriuDic();
        //separa200();
        //unificar();
    }

    private static void juntaObres() {
        Scanner s;
        PrintWriter pw;
        String line;
        String myDirName = "valencia/llibres";
        File dir = new File(myDirName);
        File[] directoryListing = dir.listFiles();
        try {
            pw = new PrintWriter(new File(myDirName + "/fontDic.txt"));
            for (File child : directoryListing) {
                System.out.println(child.getName());
                s = new Scanner(child);
                while (s.hasNext()) {
                    line = s.nextLine();
                    System.out.println(line);
                    pw.println(line);
                }
                s.close();
            }
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Diccionari.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void creaDic() {
        ArrayList<String> palabras = new ArrayList<>();
        Scanner s;
        PrintWriter pw;
        String text = "", line;
        String myDirName = "valencia/llibres/";
        try {
            s = new Scanner(new File(myDirName + "fontDic.txt"));
            pw = new PrintWriter(new File(myDirName + "dicVal.txt"));
            while (s.hasNext()) {
                line = s.nextLine();
                System.out.println("LINE " + line);
                if (text.endsWith("-")) {
                    if ((line.substring(0).length() > 2 && line.substring(0, 3).equals("la ")) //text = ...oferir-   line = li... --> ...oferir-la...
                            || (line.substring(0).length() > 2 && line.substring(0, 3).equals("li "))
                            || (line.substring(0).length() > 2 && line.substring(0, 3).equals("lo "))
                            || (line.substring(0).length() > 2 && line.substring(0, 3).equals("me "))
                            || (line.substring(0).length() > 2 && line.substring(0, 3).equals("ne "))
                            || (line.substring(0).length() > 2 && line.substring(0, 3).equals("te "))
                            || (line.substring(0).length() > 2 && line.substring(0, 3).equals("se "))
                            || (line.substring(0).length() > 2 && line.substring(0, 3).equals("us "))
                            || (line.substring(0).length() > 2 && line.substring(0, 3).equals("hi "))
                            || (line.substring(0).length() > 2 && line.substring(0, 3).equals("ho "))
                            || (line.substring(0).length() > 3 && line.substring(0, 4).equals("les "))
                            || (line.substring(0).length() > 3 && line.substring(0, 4).equals("los "))
                            || (line.substring(0).length() > 3 && line.substring(0, 4).equals("nos "))
                            || (line.substring(0).length() > 3 && line.substring(0, 4).equals("vos "))
                            || (line.substring(0).length() > 4 && line.substring(0, 5).equals("li'l "))
                            || (line.substring(0).length() > 4 && line.substring(0, 5).equals("me'l "))
                            || (line.substring(0).length() > 4 && line.substring(0, 5).equals("se'l "))
                            || (line.substring(0).length() > 4 && line.substring(0, 5).equals("l'hi "))
                            || (line.substring(0).length() > 4 && line.substring(0, 5).equals("m'hi "))
                            || (line.substring(0).length() > 4 && line.substring(0, 5).equals("t'hi "))
                            || (line.substring(0).length() > 4 && line.substring(0, 5).equals("s'ho "))
                            || (line.substring(0).length() > 5 && line.substring(0, 6).equals("li'ls "))) {
                        text += line;
                    } else {                                //text = ...ordi-   line = nador...  --> ...ordinador...
                        text = text.substring(0, text.length() - 1) + line;
                    }
                } else {                                    //text = ...per a   line = menjar demà....  --> ...per a menjar demà...
                    text += " " + line;
                }
            }
            s.close();

            text = text.toLowerCase();
            FilterStrings fs;
            for (String word : text.split("\\s")) {
                fs = new FilterStrings(GameType.WORDS, word, Languages.VALENCIAN);
                word = fs.filterLanguage();
                if (!palabras.contains(word)) {
                    System.out.println("NEW WORD " + word);
                    palabras.add(word);
                }
            }
            for (String st : palabras) {
                pw.println(st);
            }
            pw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        }
    }

    private static void separaMonoSil() {
        Scanner s;
        PrintWriter pw;
        String word;
        try {
            s = new Scanner(new File("valencia/dicVal.txt"));
            pw = new PrintWriter(new File("valencia/monoSilVal.txt"));
            Pattern p = Pattern.compile("a*b");
            Matcher m = p.matcher("aaaaab");
            boolean b = m.matches();
            while (s.hasNext()) {
                word = s.nextLine();
                if (word.length() < 4) {
                    System.out.println(word);
                }
                if (word.length() < 3) {
                    System.out.println("\tWORD 3 --> " + word);
                    pw.println(word);
                } else if (word.length() < 4) {
                    System.out.println("\tWORD 4 --> " + word);
                    if (word.startsWith(word)) {
                        System.out.println("\t\tCONSONANT");
                        if (!hiat(word.substring(1))) {
                            System.out.println("\t\tHIAT");
                        }
                    }
                }
            }
            s.close();
            pw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        }
    }

    private static boolean hiat(String subWord) {
        if (subWord.endsWith(CONSONANT)) {
            return false;
        }
        //vocal + ^i/u
        if (subWord.startsWith(VOCAL) && (subWord.endsWith("i") || subWord.endsWith("u"))) {
            return false;
        }
        //En les combinacions:    consonant+i+a/e/o     di-es, Ma-ri-a, re-li-gi-ó
        //                        consonant+u+a/e/o    cu-a, du-es
        if ((subWord.startsWith("i") || subWord.startsWith("u"))
                && (subWord.endsWith("a") || subWord.endsWith("e") || subWord.endsWith("o"))) {
            return true;
        }
        //Un accent o una dièresi marquen la no formació del diftong:    veí, països
        if (subWord.contains(ACCENT)) {
            return true;
        }
        return false;
    }

    private static void reescriuDic() {
        Scanner sDic, s;
        PrintWriter pw;
        String word;
        ArrayList<String> silVal = new ArrayList<>();
        try {
            s = new Scanner(new File("dicVal.txt"));
            sDic = new Scanner(new File("silabesVal.txt"));
            pw = new PrintWriter(new File("dicVal_v1.txt"));
            while (sDic.hasNext()) {
                word = sDic.nextLine();
                if (!silVal.contains((word))) {
                    silVal.add(word);
                    //pw.println(word);
                }
            }
            sDic.close();
            while (s.hasNext()) {
                word = s.nextLine();
                if (!silVal.contains(word)) {
                    pw.println(word);
                }
            }
            s.close();
            pw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        }
    }

    private static void separa200() {
        Scanner s;
        PrintWriter pw;
        Integer nom = 0;
        int i = 0;
        String word;
        try {
            s = new Scanner(new File("dicVal_v1.txt"));
            pw = new PrintWriter(new File("silabificar/" + Integer.toString(nom)));
            while (s.hasNext()) {
                word = s.nextLine();
                if (i == 200) {
                    pw.close();
                    pw = new PrintWriter(new File("silabificar/" + Integer.toString(++nom)));
                    i = 0;
                }
                pw.println(word);
                ++i;
            }
            s.close();
            pw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        }
    }

    private static void unificar() {
        Scanner s;
        PrintWriter pw;
        Integer nom = 0;
        int silCount, wordCount, fileCount;
        String word, syllable, entry;
        List<String> fileToUnify = new ArrayList<>();
        try {
            pw = new PrintWriter(new File("silabificar/dicValSilabes.txt"));
            for (fileCount = 0; fileCount < 31; fileCount++) {
                s = new Scanner(new File("silabificar/" + Integer.toString(nom++)));
                while (s.hasNext()) {
                    word = s.nextLine();
                    System.out.println("ARRAYLIST " + word);
                    fileToUnify.add(word);
                }
                wordCount = 0;
                silCount = fileToUnify.size() / 2;
                for (int i = 0; i < fileToUnify.size() / 2; i++) {
                    word = fileToUnify.get(wordCount++);
                    syllable = fileToUnify.get(silCount++);
                    entry = checkCorrectness(word, syllable);
                    pw.println(entry);
                }
                s.close();
                fileToUnify.clear();
            }
            pw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        }
    }

    private static String checkCorrectness(String word, String syllable) {
        int apPos;
        if (word.startsWith("'")) {
            word = word.substring(1, word.length());
        }
        if (word.endsWith("'")) {
            word = word.substring(0, word.length() - 1);
        }
        if (word.contains("'") && !syllable.contains("'")) {    //excluim si està ben apostrofat
            apPos = word.indexOf("'");
            if (apPos == 1) {                  //només pot estar al ppi o al final (posició 1 o n-1)
                syllable = syllable.substring(0, 1) + "'" + syllable.substring(1);
            } else if (apPos == word.length() - 2) {
                syllable = syllable.substring(0, syllable.length() - 1) + "'" + syllable.substring(syllable.length() - 1);
            }
        }
        return word + "=" + syllable;
    }

    private static void llevarBasura() {
        Scanner s, s1;
        PrintWriter pw;
        List<String> dicValNoGarbage = new ArrayList<>();
        String word;
        try {
            s = new Scanner(new File("valencia/basura"));
            s1 = new Scanner(new File("valencia/dicVal.txt"));
            pw = new PrintWriter(new File("valencia/dicValSenseBasura"));
            while (s1.hasNext()) {
                dicValNoGarbage.add(s1.nextLine());
            }
            s1.close();
            while (s.hasNext()) {
                word = s.nextLine();
                if (dicValNoGarbage.contains(word)) {
                    dicValNoGarbage.remove(word);
                }
            }
            s.close();
            for (String st : dicValNoGarbage) {
                pw.println(st);
            }
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Diccionari.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
