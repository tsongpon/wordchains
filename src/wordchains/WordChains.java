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

    private List<String> initData(int worSize) {
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
        List<String> candidateList = initData(start.length());
        if (!candidateList.contains(start) || !candidateList.contains(end)) {
            System.out.println("Not in dictionary.");
            return null;
        }
        boolean reverse = start.compareTo(end) > 0;
        if(reverse) {
            String temp;
            temp = end;
            end = start;
            start = temp;
        }
        LinkedHashSet<String> result = new LinkedHashSet<String>();
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

        if(isChainValid(start, end)) {
            result.add(end);
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

    private String produceOutput(Set resultSet, boolean reverse) {
        StringBuilder outputBuilder = new StringBuilder();
        Iterator iterator = resultSet.iterator();
        while (iterator.hasNext()) {
            if(outputBuilder.length() != 0) {
                if(reverse) {
                    outputBuilder.insert(0, "-");
                }  else {
                    outputBuilder.append("-");
                }
            }
            if(reverse) {
                outputBuilder.insert(0, iterator.next());
            } else {
                outputBuilder.append(iterator.next());
            }
        }
        return outputBuilder.toString();
    }

    private boolean isChainValid(String lastWord, String end) {
        if(lastWord.equals(end)) {
            return true;
        }
        int changeCount = 0;
        char [] lastWordCharArray = lastWord.toCharArray();
        char [] endWordCharArray = end.toCharArray();
        for(int i=0;i<lastWordCharArray.length;i++) {
            if(lastWordCharArray[i] != endWordCharArray[i]) {
                changeCount ++;
            }
        }
        if(changeCount > 1) {
            return false;
        }
        return true;
    }
}
