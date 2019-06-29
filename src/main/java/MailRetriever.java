import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import org.jsoup.Jsoup;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MailRetriever {

    public MailRetriever() {
    }

    public ArrayList<Email> listMessageWithLabels(Gmail service, String userId, List<String> labelsList, int emailAmount)
            throws IOException, MessagingException {
        String to;
        String from;
        String subject;
        String body;
        int emailCount = 0;
        ArrayList<Email> emailList = new ArrayList<Email>();

        ListMessagesResponse response = service.users().messages().list(userId)
                .setIncludeSpamTrash(true).setLabelIds(labelsList).execute();

        List<Message> messages = new ArrayList<Message>();
        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = service.users().messages().list(userId).setLabelIds(labelsList)
                        .setPageToken(pageToken).execute();
            } else {
                break;
            }
        }

        for (Message message : messages) {
            if(emailCount < emailAmount) {
                message = service.users().messages().get(userId, message.getId()).setFormat("raw").execute();

                byte[] emailBytes = message.decodeRaw();//base64Url.decodeBase64(message.getRaw());// message.getSnippet()
                Properties props = new Properties();
                Session session = Session.getDefaultInstance(props, null);

                MimeMessage email = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
                if (email.isMimeType("multipart/*")) {
                    Multipart mp = (Multipart) email.getContent();
                    int numParts = mp.getCount();
                    StringBuilder bodyBuilder = new StringBuilder(1024);
                    for (int count = 0; count < numParts; count++) {
                        MimeBodyPart part = (MimeBodyPart) mp.getBodyPart(count);
                        String content = part.getContent().toString();
                        if (part.getContentType().contains("text/html")) {
                            bodyBuilder.append(Jsoup.parseBodyFragment(content).text());
                        }
                    }
                    body = bodyBuilder.toString();
                    subject = message.getSnippet();
                    to = "";
                    from = "";
                    Email fullEmail = new Email(body, subject, to, from);
                    emailList.add(fullEmail);
                    emailCount++;
                }
            }
        }
        return emailList;
    }
}
