import com.google.api.client.util.ArrayMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Trainer {
    public  Trainer(){

    }

    public ArrayMap<String,Term> train(ArrayList<Email> spamEmailList, ArrayList<Email> normalEmailList)
            throws IOException {
        Map<String, Boolean> analyzed = new ArrayMap<String, Boolean>();
        ArrayMap<String,Term> blackList = new ArrayMap<String, Term>();
        ArrayList<String> stopWords = new ArrayList<>();
        //Fill list of stopwords.
        BufferedReader reader = new BufferedReader(new FileReader("stopwords.txt"));
        while(reader.ready()) {
            stopWords.add(reader.readLine());
        }
        reader.close();

        //Fill map with spam words.
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
                    if (!stopWords.contains(word) && word.length() < 50 && !word.contains("https")) {
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
        //Fill map with normal words.
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
                    if (!stopWords.contains(word) && word.length() < 50 && !word.contains("https")) {
                        Term term = new Term(word, 1, 0, 0.0, 0.0);
                        blackList.put(word, term);
                        analyzed.put(word, true);
                    }
                }
            }
            analyzed.forEach((word, state) -> {
                analyzed.replace(word, state, false);
            });
        }
        //Calculate probability for spam and normal words.
        blackList.forEach((word, term) -> {
            term.setProbSpam((double)term.getFrequencySpam()/spamEmailList.size());
            term.setProbNormal((double)term.getFrequencyNormal()/normalEmailList.size());
            blackList.replace(word, term);
        });
        return blackList;
    }
}
