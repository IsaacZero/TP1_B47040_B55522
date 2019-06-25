public class Term {
    private String word;
    private double frequencySpam;
    private double frequencyNormal;
    private double probSpam;
    private double probNormal;

    public Term() {
    }

    public Term(String word, double frequencyNormal, double frequencySpam, double probSpam, double probNormal){
        this.word = word;
        this.frequencySpam = frequencySpam;
        this.frequencyNormal = frequencyNormal;
        this.probSpam = probSpam;
        this.probNormal = probNormal;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public double getFrequenceSpam() {
        return frequencySpam;
    }

    public void setFrequenceSpam(double frequenceSpam) {
        this.frequencySpam = frequenceSpam;
    }

    public double getFrequenceNormal() {
        return frequencyNormal;
    }

    public void setFrequenceNormal(double frequenceNormal) {
        this.frequencyNormal = frequenceNormal;
    }

    public double getProbSpam() {
        return probSpam;
    }

    public void setProbSpam(double probSpam) {
        this.probSpam = probSpam;
    }

    public double getProbNormal() {
        return probNormal;
    }

    public void setProbNormal(double probNormal) {
        this.probNormal = probNormal;
    }

}
