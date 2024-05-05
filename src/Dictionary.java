import java.util.*;
import java.io.*;

public class Dictionary {
    // Atribute
    private Set<String> kamus;

    // Methods
    /**
     * Make a new dictionary from the txt file
     */
    public Dictionary() {
        kamus = new HashSet<String>();
        try {
            BufferedReader f = new BufferedReader(new FileReader("bin/dictionary.txt"));
            String line;
            while ((line = f.readLine()) != null) {
                line = line.trim().toUpperCase();
                kamus.add(line);
            }
            f.close();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan. Mohon jalankan ulang");
        }
    }

    /**
     * Searching a word in the dictionary
     * @param s the word to be looked for
     * @return true if s exists in the dictionary, false otherwise
     */
    public boolean isWordValid(String s) {
        return kamus.contains(s);
    }
}
