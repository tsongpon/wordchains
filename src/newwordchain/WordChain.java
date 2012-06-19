package newwordchain;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class WordChain {

    private String endWord;
    private int level;
    private TreeNode root;
    private TreeNode targetNode;
    private LinkedList<String> wordList;
    private LinkedList<String> filteredWordList;
    private LinkedList<TreeNode> nodeList;

    public String findWordChain(String start, String end) {
        endWord = end;
        wordList = initData(start.length());
        level = 1;
        initTree(start);

        boolean foundEndWord = false;
        while (!foundEndWord) {
            nodeList = new LinkedList<TreeNode>();
            retrieveNodesWithLevel(root);
            foundEndWord = growTree(end);
            level++;
        }

        System.out.println("****************CHAIN**************");
        printChain(targetNode);
        return "";
    }

    private void retrieveNodesWithLevel(TreeNode root) {
        if (root.getLevel() == level) {
            nodeList.add(root);
        } else {
            if (root.getChilds() != null) {
                for (TreeNode node : root.getChilds()) {
                    retrieveNodesWithLevel(node);
                }
            }
        }
    }

    private boolean growTree(String endWord) {
        //System.out.println("nodeList : "+nodeList.size());
        boolean found = false;
        for (TreeNode node : nodeList) {
            //System.out.println("Data : "+node.getData());
                LinkedList<String> chainList = listAllChain(node.getData());
                List<TreeNode> childNodes = new LinkedList<TreeNode>();
                for (String w : chainList) {
                        wordList.remove(w);
                        TreeNode newNode = new TreeNode();
                        newNode.setData(w);
                        newNode.setParentNode(node);
                        newNode.setLevel(node.getLevel() + 1);
                        childNodes.add(newNode);
                        if (endWord.equals(w)) {
                            targetNode = newNode;
                            return true;
                        }
                }
                node.setChilds(childNodes);
                if (found) {
                    return true;
                }
        }

        return found;
    }

    private void printChain(TreeNode tree) {
        if (tree.getParentNode() == null) {
            System.out.println(tree.getData());
        } else {
            System.out.println(tree.getData());
            printChain(tree.getParentNode());
        }
    }

    private void initTree(String startWord) {
        nodeList = new LinkedList<TreeNode>();
        root = new TreeNode();
        root.setData(startWord);
        root.setLevel(0);
        LinkedList<String> chainList = listAllChain(startWord);
        List<TreeNode> childNodes = new LinkedList<TreeNode>();
        for (String w : chainList) {
                wordList.remove(w);
                TreeNode node = new TreeNode();
                node.setData(w);
                node.setParentNode(root);
                node.setLevel(root.getLevel() + 1);
                childNodes.add(node);
                nodeList.add(node);
        }
        root.setChilds(childNodes);

    }

    private LinkedList<String> listAllChain(String parentWord) {
        LinkedList<String> chainList = new LinkedList<String>();
        for (String word : wordList) {
            if (isChainValid(parentWord, word)) {
                chainList.add(word);
            }
        }
        return chainList;
    }

    private boolean isChainValid(String lastWord, String end) {
        if (lastWord.equals(end)) {
            return false;
        }
        int changeCount = 0;
        char[] lastWordCharArray = lastWord.toCharArray();
        char[] endWordCharArray = end.toCharArray();
        for (int i = 0; i < lastWordCharArray.length; i++) {
            if (lastWordCharArray[i] != endWordCharArray[i]) {
                changeCount++;
            }
        }
        if (changeCount == 1) {
            return true;
        }
        return false;
    }

    private LinkedList<String> initData(int worSize) {
        LinkedList<String> wordList = new LinkedList<String>();
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

//    private boolean isCandidate(String word) {
//        String filterStr = startWord + endWord;
//        filterStr = filterStr.replaceAll("A", "");
//        filterStr = filterStr.replaceAll("E", "");
//        filterStr = filterStr.replaceAll("I", "");
//        filterStr = filterStr.replaceAll("O", "");
//        filterStr = filterStr.replaceAll("U", "");
//        char[] filterChars = filterStr.toCharArray();
//        for (char c : filterChars) {
//            if (word.contains(Character.toString(c))) {
//                return true;
//            }
//        }
//        return false;
//    }

}
