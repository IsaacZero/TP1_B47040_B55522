import com.google.api.client.util.ArrayMap;

import java.io.*;

public class FileManager {

    public ArrayMap<String, Term> readBlackList(){
        boolean fileFound;
        ArrayMap<String, Term> blackList = new ArrayMap<String, Term>();
        try(BufferedReader reader = new BufferedReader(new FileReader("blacklist.txt"))){
            while(reader.ready()) {
                String[] words = reader.readLine().split(" ");
                Term term = new Term(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]),
                        Double.parseDouble(words[3]), Double.parseDouble(words[4]));
            }
        }catch(Exception e){
            fileFound = false;
        }
        return blackList;
    }

    //Saves the word, frequencyNormal, frequencySpam, probSpam and probNormal in that order
    public void saveBlackList(ArrayMap<String, Term> blackList)
            throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("blacklist.txt", "UTF-8");
        blackList.forEach((word, term) -> {
            writer.println(word + " " + term.getFrequencyNormal() + " " + term.getFrequencySpam()
                    + " " + term.getProbSpam() + " " + term.getProbNormal());
        });
    }

    public void cleanBlacklist() throws IOException{
        File file = new File("blacklist.txt");
        file.delete();
    }
}
