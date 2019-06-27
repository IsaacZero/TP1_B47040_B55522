import java.util.ArrayList;
import java.util.Map;

public class SpamFilter {
    private Trainer trainer;
    private Map<String,Term> blackList;
    private FileManager fileManager;
    private FilterConfiguration filterConfiguration;

    public void isSpam(ArrayList<Email> newEmails, double probabilitySpam, double spamThreshold){
        double productProbSpam = 1;
        double productProbNotSpam = 1;
        double probabilityNotSpam = (double)(1-probabilitySpam);
        double probEmailSpam;
        for (Email email: newEmails){
            String[] words = email.getBody().split(" ");
            for (String word: words){
                if(blackList.containsKey(word) && blackList.get(word).getFrequencyNormal() > 0 &&
                        blackList.get(word).getFrequencySpam() > 0){
                    productProbSpam *= blackList.get(word).getProbSpam() * probabilitySpam;
                    productProbNotSpam *= blackList.get(word).getProbNormal() * probabilityNotSpam;
                }
            }
            probEmailSpam = productProbSpam / (productProbSpam + productProbNotSpam);
            if(probEmailSpam >= spamThreshold)
                System.out.printf("- %s\n", "Snippet: " + email.getSubject() + "\n" + "This Email Is SPAM.");
            else
                System.out.printf("- %s\n", "Snippet: " + email.getSubject() + "\n" + "This Email Is NOT SPAM :)");
        }
    }
}
