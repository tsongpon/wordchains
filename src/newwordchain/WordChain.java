package newwordchain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class WordChain {

    private String startWord;
    private String endWord;
    private TreeNode root;
    private TreeNode targetNode;
    private List<String> wordList;
    private HashMap<String, String> usedWord = new HashMap<String, String>();

    public String findWordChain(String start, String end) {
        startWord = start;
        endWord = end;
        wordList = initData(startWord.length());
        System.out.println("List size before : " + wordList.size());
        initTree(startWord);

        completeTree(root, false);
        //printTree(root);
        retrieveTargetNode(root, endWord);
        System.out.println("****************CHAIN**************");
        printChain(targetNode);
        return "";
    }

    private void printChain(TreeNode tree) {
        if (tree.getParentNode() == null) {
            System.out.println(tree.getData());
        } else {
            System.out.println(tree.getData());
            printChain(tree.getParentNode());
        }
    }

    private void retrieveTargetNode(TreeNode root, String targetWord) {
        if (root != null) {
            if (root.getData().equals(targetWord)) {
                System.out.println("===== FOUND =====");
                targetNode = root;
            }
            List<TreeNode> childNodes = root.getChilds();
            if (childNodes != null) {
                for (TreeNode node : childNodes) {
                    retrieveTargetNode(node, targetWord);
                }
            }
        }
    }

    private void initTree(String startWord) {
        TreeNode rootNode = new TreeNode();
        root = rootNode;
        rootNode.setData(startWord);
        List<String> chainList = listAllChain(startWord);
        List<TreeNode> childNodes = new ArrayList<TreeNode>();
        for (String w : chainList) {
            TreeNode node = new TreeNode();
            node.setData(w);
            node.setParentNode(rootNode);
            childNodes.add(node);
        }
        rootNode.setChilds(childNodes);

    }

    private void completeTree(TreeNode root, boolean isFinish) {
        if (root.getData().equals(endWord)) {
            // STOP
        } else {
            List<TreeNode> childNode = root.getChilds();
            if (childNode == null || childNode.size() == 0) {
                //System.out.println("I am leaf : " + root.getData());
                if (!isFinish) {
                    List<String> chainList = listAllChain(root.getData());
                    List<TreeNode> childNodes = new ArrayList<TreeNode>();
                    int processCount = 0;
                    for (String w : chainList) {
                        if (usedWord.get(w) == null) {
                            TreeNode node = new TreeNode();
                            node.setData(w);
                            node.setParentNode(root);
                            childNodes.add(node);
                            usedWord.put(w, w);
                            processCount++;
                        }
                    }
                    root.setChilds(childNodes);
                    if (processCount > 0) {
                        completeTree(root, false);
                    } else {
                        completeTree(root, true);
                    }
                }
            } else {
                for (TreeNode node : root.getChilds()) {
                    completeTree(node, false);
                }
            }
        }
    }


    private void printTree(TreeNode root) {
        usedWord = new HashMap<String, String>();
        if (root != null) {
            System.out.println(root.getData());
            List<TreeNode> childNodes = root.getChilds();
            if (childNodes != null) {
                for (TreeNode node : childNodes) {
                    if (usedWord.get(node.getData()) == null) {
                        printTree(node);
                        usedWord.put(node.getData(), node.getData());
                    }
                }
            }
        }
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
