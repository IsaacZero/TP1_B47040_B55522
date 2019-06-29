import java.util.Map;

public class SpamFilter {
    private Trainer trainer;
    private Map<String,Term> blackList;
    private FileManager fileManager;
    private FilterConfiguration filterConfiguration;
}
