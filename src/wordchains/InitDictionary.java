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
        HashMap<String, String> dict = new HashMap<String, String>();
        BinarySearchTree t = new BinarySearchTree( );
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
                t.insert(text);
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

        System.out.println("***"+t.find("UNPROBLEMATIC"));
    }
}
