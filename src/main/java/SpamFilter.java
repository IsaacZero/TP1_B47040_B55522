import com.google.api.client.util.ArrayMap;

public class SpamFilter {
    Trainer trainer;
    Configuration configuration;
    ArrayMap<String, Term> blackList;
    ArrayMap<String, Term> normalList;
    FileManager fileManager;
}
