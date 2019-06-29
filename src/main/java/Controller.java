import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private SpamFilter filter;
    private MenuHandler menuHandler;
    private Authenticator log;
    private MailRetriever retriever;

    public Controller(){
        menuHandler = new MenuHandler();
    }

    public void start() throws IOException, GeneralSecurityException, MessagingException {
        int option = menuHandler.firstMenuText();
        if(option == 1){
            filter = new SpamFilter();
            retriever = new MailRetriever();
            log = new Authenticator();
            boolean inMenu = true;
            while(inMenu){
                //1) Configuration" + "\n" + "2) Train" + "\n" + "3) View Data" + "\n" + "4) Get Newest Emails"
                //                    + "\n" + "5) Log Out" + "\n" + "6) Exit"
                option = menuHandler.secondMenuText();
                if(option == 1){
                    boolean inConfigMenu = true;
                    while(inConfigMenu) {
                        option = menuHandler.configurationMenuText(filter.getFilterConfiguration().getSpamProbability(), filter.getFilterConfiguration().getSpamThreshold(),
                                filter.getFilterConfiguration().getTrainerSetSize());
                        //"1) Set Spam Probability" + "\n" + "2) Set Spam Threshold" + "\n"
                        // + "3) Set Trainer Set Size" + "\n" + "4) Back to Menu");
                        if(option == 1){
                            FilterConfiguration newConfiguration = new FilterConfiguration();
                            newConfiguration.setSpamProbability(menuHandler.setSpamProbMenuText());
                            filter.setFilterConfiguration(newConfiguration);
                        }else if(option == 2){
                            FilterConfiguration newConfiguration = new FilterConfiguration();
                            newConfiguration.setSpamThreshold(menuHandler.setSpamThresholdMenuText());
                            filter.setFilterConfiguration(newConfiguration);
                        }else if(option == 3){
                            FilterConfiguration newConfiguration = new FilterConfiguration();
                            newConfiguration.setTrainerSetSize(menuHandler.setTrainerSizeMenuText());
                            filter.setFilterConfiguration(newConfiguration);
                        }else {
                            inConfigMenu = false;
                        }
                    }
                }else if(option == 2){
                    List<String> spam = new ArrayList<>(1);
                    spam.add("SPAM");
                    List<String> normal = new ArrayList<>(1);
                    normal.add("INBOX");
                    filter.setBlackList(filter.train(retriever.listMessageWithLabels(log.service, log.user, spam, filter.getFilterConfiguration().getTrainerSetSize()), retriever.listMessageWithLabels(log.service, log.user, normal, filter.getFilterConfiguration().getTrainerSetSize())));
                    System.out.printf("- %s\n", "Train successfully ended");
                }else if(option == 3){
                    menuHandler.viewBlackList(filter.getBlackList());
                }else if(option == 4){
                    List<String> newest = new ArrayList<>(1);
                    newest.add("UNREAD");
                    filter.isSpam(retriever.listMessageWithLabels(log.service, log.user, newest, filter.getFilterConfiguration().getTrainerSetSize()));
                }else if(option == 5){
                    filter.cleanBlacklist();
                    filter.cleanConfig();
                    log.cleanCredential();
                    inMenu = false;
                }else{
                    filter.saveBlackList(filter.getBlackList());
                    filter.getFilterConfiguration().saveConfig();
                    inMenu = false;
                }
            }
        }
    }
}
