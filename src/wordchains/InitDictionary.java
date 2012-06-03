package wordchains;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class InitDictionary {

    public List<String> readDictionaryAndInitData(int worSize) {
        List<String> wordList = new ArrayList<String>();
        File file = new File("OWL2.txt");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            String word;
            while ((text = reader.readLine()) != null) {
                word = text.split(" ")[0];
                if (worSize == word.length()) {
                    wordList.add(word);
                } else if (word.length() > worSize) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wordList;
    }


    public String findChains(String start, String end) {
        List<String> candidateList = readDictionaryAndInitData(start.length());
        if (!candidateList.contains(start) || !candidateList.contains(end)) {
            System.out.println("Not in dictionary.");
            return null;
        }
        List<String> result = new ArrayList<String>();
        result.add(start);
        for (int i = 0; i < start.length(); i++) {
            String pattern = buildPattern(start, end, i);
            for (String w : candidateList) {
                if (w.matches(pattern)) {
                    start = w;
                    result.add(w);
                    break;
                }
            }
        }
        result.add(end);
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        return null;
    }

    private String buildPattern(String start, String end, int pos) {
        char[] startCharArray = start.toCharArray();
        char[] endCharArray = end.toCharArray();
        startCharArray[pos] = endCharArray[pos];
        StringBuilder patternBuilder = new StringBuilder();
        for (char c : startCharArray) {
            patternBuilder.append(c);
        }
        return patternBuilder.toString();
    }
}
