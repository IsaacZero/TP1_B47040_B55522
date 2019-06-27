import com.google.api.client.util.ArrayMap;
import java.util.*;

public class Trainer {
    public  Trainer(){

    }

    public Map<String,Term> train(ArrayList<Email> spamEmailList, ArrayList<Email> normalEmailList) {
        Map<String, Boolean> analyzed = new ArrayMap<String, Boolean>();
        Map<String,Term> blackList = new ArrayMap<String, Term>();
        int frequencySpam;
        int frequencyNormal;
        double probabilitySpam;
        double probabilityNormal;
        //Fill map with spam words
        for (Email body: spamEmailList) {
            String[] words;
            words = body.getBody().split(" ");
            for (String word : words) {
                if (blackList.containsKey(word) && !analyzed.get(word)) {
                     Term value = blackList.get(word);
                    value.setFrequencySpam(value.getFrequencySpam() + 1);
                    blackList.replace(word, value);
                    analyzed.replace(word, false, true);
                } else {
                    if (word.length() < 50 && !word.contains("https")) {
                        Term term = new Term(word, 0, 1, 0.0, 0.0);
                        blackList.put(word, term);
                        analyzed.put(word, true);
                    }
                }
            }
            analyzed.forEach((word, state) -> {
                analyzed.replace(word, state, false);
            });
            //5 letras = https.
            // más de 50 caracteres.
        }
        //Fill map with normal words
        for (Email body: normalEmailList) {
            String[] words;
            words = body.getBody().split(" ");
            for (String word : words) {
                if (blackList.containsKey(word) && !analyzed.get(word)) {
                    Term value = blackList.get(word);
                    value.setFrequencyNormal(value.getFrequencyNormal() + 1);
                    blackList.replace(word, value);
                    analyzed.replace(word, false, true);
                } else {
                    if (word.length() < 50 && !word.contains("https")) {
                        Term term = new Term(word, 1, 0, 0.0, 0.0);
                        blackList.put(word, term);
                        analyzed.put(word, true);
                    }
                }
            }
            analyzed.forEach((word, state) -> {
                analyzed.replace(word, state, false);
            });
            //5 letras = https.
            // más de 50 caracteres.
        }
        //Calculate probability for spam and normal words.
        blackList.forEach((word, term) -> {
            term.setProbSpam((double)(term.getFrequencySpam()/spamEmailList.size()));
            term.setProbNormal((double)(term.getFrequencyNormal()/normalEmailList.size()));
            blackList.replace(word, term);
        });
        return blackList;
    }
}
