public class Email {


    private String body;
    private String subject;
    private String to;
    private String from;
    public Email(){
        body = "";
        subject = "";
        to  = "";
        from  = "";
    }
    public Email(String body, String subject, String to, String from){
        this.body = body;
        this.subject = subject;
        this.to = to;
        this.from = from;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
