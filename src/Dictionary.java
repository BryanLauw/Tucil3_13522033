import java.util.*;
import java.io.*;

public class Dictionary {
    private Set<String> kamus;

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

    public boolean isWordValid(String s) {
        return kamus.contains(s);
    }
}
