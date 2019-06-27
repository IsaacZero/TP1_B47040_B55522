public class Term {
    private String word;
    private int frequencySpam;
    private int frequencyNormal;
    private double probSpam;
    private double probNormal;

    public Term() {
    }

    public Term(String word, int frequencyNormal, int frequencySpam, double probSpam, double probNormal){
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

    public int getFrequencySpam() {
        return frequencySpam;
    }

    public void setFrequencySpam(int frequencySpam) {
        this.frequencySpam = frequencySpam;
    }

    public int getFrequencyNormal() {
        return frequencyNormal;
    }

    public void setFrequencyNormal(int frequencyNormal) { this.frequencyNormal = frequencyNormal; }

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
