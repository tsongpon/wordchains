package wordchains;

import wordchains.data.BinarySearchTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 */
public class InitDictionary {

    public void readDictionaryFile() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        //BinarySearchTree t = new BinarySearchTree( );
        File file = new File("OWL2.txt");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            String word;
            while ((text = reader.readLine()) != null) {
                contents.append(text)
                        .append(System.getProperty(
                                "line.separator"));
                word = text.split(" ")[0];
                //t.insert(word);
                map.put(word,word.length());
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

/*        System.out.println("***"+t.find("AA"));
        System.out.println("***"+t.find("UNSPECTACULAR"));
        System.out.println("***"+t.find("SAGGERED"));
        System.out.println("***"+t.find("SABAYONS"));
        System.out.println("***"+t.find("POTENTIATE"));
        System.out.println("***"+t.find("CLATTER"));
        System.out.println("***"+t.find("CLEARLY"));*/

        System.out.println("***"+map.get("AA"));
        System.out.println("***"+map.get("UNSPECTACULAR"));
        System.out.println("***"+map.get("SAGGERED"));
        System.out.println("***"+map.get("SABAYONS"));
        System.out.println("***"+map.get("POTENTIATE"));
        System.out.println("***"+map.get("CLATTER"));
        System.out.println("***"+map.get("CLEARLY"));
    }
}
