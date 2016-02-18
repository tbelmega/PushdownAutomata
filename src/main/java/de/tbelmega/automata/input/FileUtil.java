package de.tbelmega.automata.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileUtil {

    public static final String UTF_8 = StandardCharsets.UTF_8.toString();
    private static final String END_OF_FILE = "\\A";

    /**
     * Returns the content of a text file as String.
     */
    public static String readFile(File f) {
        try {
            return readFile(new FileInputStream(f));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the content of a text file as String.
     *
     * @param s path to an existing text file
     */
    public static String readFile(String s) {
        return readFile(new File(s));
    }

    /**
     * Returns the content of a text file as String.
     */
    public static String readFile(InputStream i) throws IOException {
        Scanner scan = new Scanner(i, UTF_8);
        scan.useDelimiter(END_OF_FILE);
        String content = scan.next();
        scan.close();
        return content;
    }
}
