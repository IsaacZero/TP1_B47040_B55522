import com.google.api.services.gmail.Gmail;
import java.util.List;

public class MailRetriever {
    private List<Email> messageReservoir;

    public MailRetriever() {
    }
    public MailRetriever(List<Email> emailList){
        messageReservoir = emailList;
    }

   /* public List<Email> listMessageWithLabels(Gmail gmail,String userId, List<String> stringList){

    }*/

    public List<Email> getMessageReservoir() {
        return messageReservoir;
    }

    public void setMessageReservoir(List<Email> messageReservoir) {

        this.messageReservoir = messageReservoir;
    }
}
