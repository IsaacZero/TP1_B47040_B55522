public class FilterConfiguration {
    private double spamProbability;
    private double spamThreshold;
    private int trainerSetSize;
    public FilterConfiguration(int trainerSetSize){
        this.spamProbability = 0.3;
        this.spamThreshold = 0.9;
        this.trainerSetSize = trainerSetSize;
    }

    public double getSpamProbability() {
        return spamProbability;
    }

    public void setSpamProbability(double spamProbability) {
        this.spamProbability = spamProbability;
    }

    public double getSpamThreshold() {
        return spamThreshold;
    }

    public void setSpamThreshold(double spamThreshold) {
        this.spamThreshold = spamThreshold;
    }

    public int getTrainerSetSize() {
        return trainerSetSize;
    }

    public void setTrainerSetSize(int trainerSetSize) {
        this.trainerSetSize = trainerSetSize;
    }
}
