package newwordchain;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class WordChain {

    private String endWord;
    private int level;
    private TreeNode root;
    private TreeNode targetNode;
    private List<String> wordList;
    private List<TreeNode> nodeList;
    private HashMap<String, String> usedWord = new HashMap<String, String>();

    public String findWordChain(String start, String end) {
        endWord = end;
        wordList = initData(start.length());
        level = 1;
        initTree(start);

        boolean foundEndWord = false;
        while (!foundEndWord) {
            nodeList = new ArrayList<TreeNode>();
            retrieveNodesWithLevel(root);
            foundEndWord = growTree(end);
            level++;
        }

        retrieveTargetNode(root);

        System.out.println("****************CHAIN**************");
        printChain(targetNode);
        return "";
    }

    private void retrieveNodesWithLevel(TreeNode root) {
        if (root.getLevel() == level) {
            nodeList.add(root);
        } else {
            for (TreeNode node : root.getChilds()) {
                retrieveNodesWithLevel(node);
            }
        }
    }

    private boolean growTree(String endWord) {
        boolean found = false;
        for (TreeNode node : nodeList) {
            List<String> chainList = listAllChain(node.getData());
            List<TreeNode> childNodes = new ArrayList<TreeNode>();
            for (String w : chainList) {
                if (usedWord.get(w) == null) {
                    usedWord.put(w, w);
                    TreeNode newNode = new TreeNode();
                    newNode.setData(w);
                    newNode.setParentNode(node);
                    newNode.setLevel(node.getLevel() + 1);
                    childNodes.add(newNode);
                    if (endWord.equals(w)) {
                        found = true;
                        break;
                    }
                }
            }
            node.setChilds(childNodes);
            if(found) {
                break;
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

    private void retrieveTargetNode(TreeNode root) {
        if (root != null) {
            if (root.getData().equals(endWord)) {
                targetNode = root;
            }
            List<TreeNode> childNodes = root.getChilds();
            if (childNodes != null) {
                for (TreeNode node : childNodes) {
                    retrieveTargetNode(node);
                }
            }
        }
    }

    private void initTree(String startWord) {
        root = new TreeNode();
        root.setData(startWord);
        root.setLevel(0);
        List<String> chainList = listAllChain(startWord);
        List<TreeNode> childNodes = new ArrayList<TreeNode>();
        for (String w : chainList) {
            TreeNode node = new TreeNode();
            node.setData(w);
            node.setParentNode(root);
            node.setLevel(root.getLevel() + 1);
            childNodes.add(node);
        }
        root.setChilds(childNodes);

    }

    private List<String> listAllChain(String parentWord) {
        List<String> chainList = new ArrayList<String>();
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
