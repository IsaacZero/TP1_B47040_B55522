import java.io.*;

public class FilterConfiguration {
    private double spamProbability;
    private double spamThreshold;
    private int trainerSetSize;

    public FilterConfiguration(){
        spamProbability = 0.3;
        spamThreshold = 0.9;
        trainerSetSize = 50;
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


    //Saves the spamProbability, spamThreshold and trainerSetSize in that order.
    public void saveConfig() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("config.txt", "UTF-8");
        writer.println(spamProbability + " " + spamThreshold + " " + trainerSetSize);
    }

    public boolean readConfig(){
        boolean fileFound = true;
        try(BufferedReader reader = new BufferedReader(new FileReader("config.txt"))){
            while(reader.ready()) {
                String[] words = reader.readLine().split(" ");
                spamProbability = Double.parseDouble(words[0]);
                spamThreshold = Double.parseDouble(words[1]);
                trainerSetSize = Integer.parseInt(words[2]);
            }
        }catch(Exception e){
            fileFound = false;
        }
        return fileFound;
    }

    public void cleanConfiguration() throws IOException{
        File file = new File("config.txt");
        file.delete();
    }
}
