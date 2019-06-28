public class Controller {
    private SpamFilter filter;
    private MenuHandler menuHandler;
    private FilterConfiguration configurator;
    private Authenticator log;
    private MailRetriever retriever;

    public Controller(){
        menuHandler = new MenuHandler();
    }

    public void start(){

    }
}
