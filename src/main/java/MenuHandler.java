import com.google.api.client.util.ArrayMap;

public class MenuHandler {

    public MenuHandler(){

    }

    public int firstMenuText(){
        int option = 0;
        System.out.printf("- %s\n", "Welcome to this Bayesian Spam Filter. Please Select an option" +
                "\n" + "1) Log In" + "\n" + "2) Exit");
        option = Integer.parseInt(System.console().readLine());
        while (option == 0 || option > 2) {
            System.out.printf("- %s\n", "Please enter a valid option" +
                    "\n" + "1) Log In" + "\n" + "2) Exit");
            option = Integer.parseInt(System.console().readLine());
        }
        return option;
    }

    public int secondMenuText(){
        int option = 0;
        System.out.printf("- %s\n", "Please Select an option" +
                "\n" + "1) Configuration" + "\n" + "2) Train" + "\n" + "3) View Data" + "\n" + "4) Get Newest Emails"
                + "\n" + "5) Log Out" + "\n" + "6) Exit");
        option = Integer.parseInt(System.console().readLine());
        while (option == 0 || option > 6) {
            System.out.printf("- %s\n", "Please enter a valid option" +
                    "\n" + "1) Configuration" + "\n" + "2) Train" + "\n" + "3) View Data" + "\n" + "4) Get Newest Emails"
                    + "\n" + "5) Log Out" + "\n" + "6) Exit");
            option = Integer.parseInt(System.console().readLine());
        }
        return option;
    }

    public int configurationMenuText(double spamProb, double spamThreshold, int trainerSetSize){
        int option = 0;
        System.out.printf("- %s\n", "This is your configuration: " + "\n" + "Spam Probability: " + spamProb
                + "\n" + "Spam Threshold: " + spamThreshold + "\n" + "Trainer Set Size: " + trainerSetSize
                + "\n" + "Please Select an option" + "\n" + "1) Set Spam Probability"
                + "\n" + "2) Set Spam Threshold" + "\n" + "3) Set Trainer Set Size" + "\n" + "4) Back to Menu");
        while (option == 0 || option > 4) {
            System.out.printf("- %s\n", "Please enter a valid option" +
                    "\n" + "1) Configuration" + "\n" + "2) Train" + "\n" + "3) View Data");
            option = Integer.parseInt(System.console().readLine());
        }
        return option;
    }

    public double setSpamProbMenuText(){
        double spamProb = 0.0;
        System.out.printf("- %s\n", "Please enter a Probability between 0 and 1");
        spamProb = Double.parseDouble(System.console().readLine());
        while(spamProb < 0.0 || spamProb > 1.0){
            System.out.printf("- %s\n", "Please enter a Probability between 0 and 1");
            spamProb = Double.parseDouble(System.console().readLine());
        }
        return spamProb;
    }

    public double setSpamThresholdMenuText(){
        double spamThreshold = 0.0;
        System.out.printf("- %s\n", "Please enter a Threshold between 0 and 1");
        spamThreshold = Double.parseDouble(System.console().readLine());
        while(spamThreshold < 0.0 || spamThreshold > 1.0){
            System.out.printf("- %s\n", "Please enter a Threshold between 0 and 1");
            spamThreshold = Double.parseDouble(System.console().readLine());
        }
        return spamThreshold;
    }

    public int setTrainerSizeMenuText(){
        int size = 0;
        System.out.printf("- %s\n", "Please enter a Size of 50 or more");
        size = Integer.parseInt(System.console().readLine());
        while(size < 50){
            System.out.printf("- %s\n", "Please enter a Size of 50 or more");
            size = Integer.parseInt(System.console().readLine());
        }
        return size;
    }

    public void viewBlackList(ArrayMap<String, Term> blackList){
        blackList.forEach((word, term) -> {
            System.out.printf("- %s\n", "Word: " + word + ": " + "Frequency Spam: " + term.getFrequencySpam() + ", "
                    + "Probability Spam: " + term.getProbSpam() + "\n" + "Frequency Not Spam: " + term.getFrequencyNormal() + ", "
                    + "Probability Not Spam: " + term.getProbNormal());
        });
    }

    public void spamFilterMessage(boolean isSpam){

    }
}
