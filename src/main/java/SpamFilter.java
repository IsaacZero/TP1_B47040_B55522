import com.google.api.client.util.ArrayMap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class SpamFilter {
    public Trainer trainer;
    private ArrayMap<String,Term> blackList;
    private FileManager fileManager;
    private FilterConfiguration filterConfiguration;

    public SpamFilter(){
        filterConfiguration = new FilterConfiguration();
        filterConfiguration.readConfig();
        fileManager = new FileManager();
        trainer = new Trainer();
        blackList = new ArrayMap<>();
    }

    public void isSpam(ArrayList<Email> newEmails){
        double productProbSpam = 1;
        double productProbNotSpam = 1;
        double probabilityNotSpam = (1- filterConfiguration.getSpamProbability());
        double probEmailSpam;
        for (Email email: newEmails){
            String[] words = email.getBody().split(" ");
            for (String word: words){
                if(blackList.containsKey(word) && blackList.get(word).getFrequencyNormal() > 0 &&
                        blackList.get(word).getFrequencySpam() > 0){
                    productProbSpam *= blackList.get(word).getProbSpam() * filterConfiguration.getSpamProbability();
                    productProbNotSpam *= blackList.get(word).getProbNormal() * probabilityNotSpam;
                }
            }
            probEmailSpam = productProbSpam / (productProbSpam + productProbNotSpam);
            if(probEmailSpam >= filterConfiguration.getSpamThreshold())
                System.out.printf("- %s\n", "Snippet: " + email.getSubject() + "\n" + "This Email Is SPAM.");
            else
                System.out.printf("- %s\n", "Snippet: " + email.getSubject() + "\n" + "This Email Is NOT SPAM :)");
        }
    }

    public ArrayMap<String, Term> getBlackList() {
        return blackList;
    }

    public void setBlackList(ArrayMap<String, Term> blackList) {
        this.blackList = blackList;
    }

    public FilterConfiguration getFilterConfiguration() {
        return filterConfiguration;
    }

    public void setFilterConfiguration(FilterConfiguration filterConfiguration) {
        this.filterConfiguration.setSpamThreshold(filterConfiguration.getSpamThreshold());
        this.filterConfiguration.setSpamProbability(filterConfiguration.getSpamProbability());
        this.filterConfiguration.setTrainerSetSize(filterConfiguration.getTrainerSetSize());
    }

    public ArrayMap<String, Term> train(ArrayList<Email> spamEmailList, ArrayList<Email> normalEmailList) throws IOException {
        blackList = fileManager.readBlackList();
        if(blackList.size() == 0){
            blackList = trainer.train(spamEmailList, normalEmailList);
        }
        return blackList;
    }

    public void saveBlackList(ArrayMap<String, Term> blackList) throws FileNotFoundException, UnsupportedEncodingException {
        fileManager.saveBlackList(blackList);
    }

    public void cleanBlacklist() throws IOException {
        fileManager.cleanBlacklist();
    }

    public void cleanConfig() throws IOException {
        filterConfiguration.cleanConfiguration();
    }
}
