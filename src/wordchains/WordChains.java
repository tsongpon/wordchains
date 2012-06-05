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
public class WordChains {

    public String findChains(final String start, final String end) {
        HashMap<String, String> candidateMap = initData(start.length());
        if (candidateMap.get(start) == null || candidateMap.get(end) == null) {
            throw new WordChainsException("Word not found in dictionary.");
        }
        if (start.length() != end.length()) {
            throw new WordChainsException("Start and End word have to save length.");
        }
        boolean reverse = start.compareTo(end) > 0;
        String startChain = start;
        String endChain = end;
        if (reverse) {
            String temp;
            temp = endChain;
            endChain = startChain;
            startChain = temp;
        }
        LinkedHashSet<String> result = new LinkedHashSet<String>();
        result.add(startChain);

        for (int i = 0; i < start.length(); i++) {
            String pattern = buildPattern(startChain, endChain, i);
            String word = candidateMap.get(pattern);
            if (word != null) {
                startChain = word;
                result.add(word);
            }
        }

        if (isChainValid(startChain, endChain)) {
            result.add(endChain);
            return produceOutput(result, reverse);
        }

        return "No chains";
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

    private boolean isChainValid(String lastWord, String end) {
        if (lastWord.equals(end)) {
            return true;
        }
        int changeCount = 0;
        char[] lastWordCharArray = lastWord.toCharArray();
        char[] endWordCharArray = end.toCharArray();
        for (int i = 0; i < lastWordCharArray.length; i++) {
            if (lastWordCharArray[i] != endWordCharArray[i]) {
                changeCount++;
            }
        }
        if (changeCount > 1) {
            return false;
        }
        return true;
    }

    private String produceOutput(Set resultSet, boolean reverse) {
        StringBuilder outputBuilder = new StringBuilder();
        Iterator iterator = resultSet.iterator();
        while (iterator.hasNext()) {
            if (outputBuilder.length() != 0) {
                if (reverse) {
                    outputBuilder.insert(0, "-");
                } else {
                    outputBuilder.append("-");
                }
            }
            if (reverse) {
                outputBuilder.insert(0, iterator.next());
            } else {
                outputBuilder.append(iterator.next());
            }
        }
        return outputBuilder.toString();
    }

    private HashMap<String, String> initData(int worSize) {
        HashMap<String, String> wordList = new HashMap<String, String>();
        File file = new File("OWL2.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            String word;
            while ((text = reader.readLine()) != null) {
                word = text.split(" ")[0];
                if (worSize == word.length()) {
                    wordList.put(word, word);
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
}
