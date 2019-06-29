import com.google.api.client.util.ArrayMap;

import java.util.Scanner;

public class MenuHandler {

    Scanner input;

    public MenuHandler(){
        input = new Scanner(System.in);
    }

    public int firstMenuText(){
        int option = 0;
        //Scanner input = new Scanner(System.in);
        String optionEntered = "";
        System.out.printf("- %s\n", "Welcome to this Bayesian Spam Filter. Please Select an option" +
                "\n" + "1) Log In" + "\n" + "2) Exit");
        optionEntered = input.nextLine();
        option = Integer.parseInt(optionEntered);
        while (option == 0 || option > 2) {
            System.out.printf("- %s\n", "Please enter a valid option" +
                    "\n" + "1) Log In" + "\n" + "2) Exit");
            input = new Scanner(System.in);
            optionEntered = input.nextLine();
            option = Integer.parseInt(optionEntered);
        }
        return option;
    }

    public int secondMenuText(){
        int option = 0;
        System.out.printf("- %s\n", "Please Select an option" +
                "\n" + "1) Configuration" + "\n" + "2) Train" + "\n" + "3) View Data" + "\n" + "4) Get Newest Emails"
                + "\n" + "5) Log Out" + "\n" + "6) Exit");
        Scanner input = new Scanner(System.in);
        String optionEntered = input.nextLine();
        option = Integer.parseInt(optionEntered);
        while (option == 0 || option > 6) {
            System.out.printf("- %s\n", "Please enter a valid option" +
                    "\n" + "1) Configuration" + "\n" + "2) Train" + "\n" + "3) View Data" + "\n" + "4) Get Newest Emails"
                    + "\n" + "5) Log Out" + "\n" + "6) Exit");
            input = new Scanner(System.in);
            optionEntered = input.nextLine();
            option = Integer.parseInt(optionEntered);
        }
        return option;
    }

    public int configurationMenuText(double spamProb, double spamThreshold, int trainerSetSize){
        int option = 0;
        System.out.printf("- %s\n", "This is your configuration: " + "\n" + "Spam Probability: " + spamProb
                + "\n" + "Spam Threshold: " + spamThreshold + "\n" + "Trainer Set Size: " + trainerSetSize
                + "\n" + "Please Select an option" + "\n" + "1) Set Spam Probability"
                + "\n" + "2) Set Spam Threshold" + "\n" + "3) Set Trainer Set Size" + "\n" + "4) Back to Menu");
        Scanner input = new Scanner(System.in);
        String optionEntered = input.nextLine();
        option = Integer.parseInt(optionEntered);
        while (option == 0 || option > 4) {
            System.out.printf("- %s\n", "Please enter a valid option" +
                    "\n" + "1) Configuration" + "\n" + "2) Train" + "\n" + "3) View Data");
            input = new Scanner(System.in);
            optionEntered = input.nextLine();
            option = Integer.parseInt(optionEntered);
        }
        return option;
    }

    public double setSpamProbMenuText(){
        double spamProb = 0.0;
        System.out.printf("- %s\n", "Please enter a Probability between 0 and 1");
        Scanner input = new Scanner(System.in);
        String optionEntered = input.nextLine();
        spamProb = Double.parseDouble(optionEntered);
        while(spamProb < 0.0 || spamProb > 1.0){
            System.out.printf("- %s\n", "Please enter a Probability between 0 and 1");
            input = new Scanner(System.in);
            optionEntered = input.nextLine();
            spamProb = Double.parseDouble(optionEntered);
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
        Scanner input = new Scanner(System.in);
        String optionEntered = input.nextLine();
        size = Integer.parseInt(optionEntered);
        while(size < 50){
            System.out.printf("- %s\n", "Please enter a Size of 50 or more");
            input = new Scanner(System.in);
            optionEntered = input.nextLine();
            size = Integer.parseInt(optionEntered);
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
