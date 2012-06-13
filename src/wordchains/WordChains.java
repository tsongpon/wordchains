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

    private List<String> candidateList = null;
    private List<String> chainListFromStart = null;
    private List<String> chainListFromEnd = null;

    public String findChains(final String start, final String end) {
        candidateList = initData(start.length());
        if (!candidateList.contains(start) || !candidateList.contains(end)) {
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

        boolean found = false;
        for (int i = 0; i < start.length(); i++) {
            String pattern = buildPattern(startChain, endChain, i);
            for (String w : candidateList) {
                if (w.matches(pattern)) {
                    //System.out.println("Found word : " + w);
                    found = true;
                    startChain = w;
                    result.add(w);
                    break;
                }
            }
        }

        if (!found) {

            findWordStart(start);
            findWordEnd(end);
            System.out.println("Found :: " + found);
            System.out.println("Chain List :: " + chainListFromStart.size());
            for(String chain : chainListFromStart) {
                System.out.println("Chain : "+chain);
                //findChains(chain, end);
            }

            System.out.println("Found END :: " + found);
            System.out.println("Chain List END :: " + chainListFromEnd.size());
            for(String chain : chainListFromEnd) {
                System.out.println("Chain END : "+chain);
                //findChains(chain, end);
            }
        }

        if (isChainValid(startChain, endChain)) {
            result.add(endChain);
            return produceOutput(result, reverse);
        }

        return "No chains";
    }


    private void findWordStart(String start) {
        chainListFromStart = new ArrayList<String>();
        for (String w : candidateList) {
            if (isChainValid(start, w)) {
                chainListFromStart.add(w);
            }
        }
    }

    private void findWordEnd(String end) {
        chainListFromEnd = new ArrayList<String>();
        for (String w : candidateList) {
            if (isChainValid(end, w)) {
                chainListFromEnd.add(w);
            }
        }
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

    private List<String> initData(int worSize) {
        List<String> wordList = new ArrayList<String>();
        File file = new File("OWL2.txt");
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
}
