import java.util.Map;

public class SpamFilter {
    private Trainer trainer;
    private Map<String,Term> blackList;
    private Map<String,Term> normalList;
    private FileManager fileManager;
    private FilterConfiguration filterConfiguration;

}
