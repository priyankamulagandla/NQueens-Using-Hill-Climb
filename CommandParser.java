import org.apache.commons.cli.*;

public class CommandParser {
    public CommandLine parser(String[] args) {
        Options options = new Options();
        Option knapsackFilePathOption = new Option("knapsack", "knapsack-file", true,
                "input file path");
        Option verboseOption = new Option("v", "verbose", false, "print verbose output");
        Option sidewaysOption = new Option("sideways", true, "No.of sideways motions allowed");
        Option restartsOption = new Option("restarts", true, "No. of restarts allowed");
        Option queensOption = new Option("queens", true, "No. of queens on board");

        options.addOption(knapsackFilePathOption);
        options.addOption(verboseOption);
        options.addOption(sidewaysOption);
        options.addOption(restartsOption);
        options.addOption(queensOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
        return cmd;
    }
}
