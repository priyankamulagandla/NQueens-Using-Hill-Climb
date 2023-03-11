import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.cli.CommandLine;

public class HillClimb {


  public static void main(String[] args) throws Exception {

    HillClimbRunner hillClimbRunner = new HillClimbRunner();
    CommandParser parser = new CommandParser();
    CommandLine cmd = parser.parser(args);
    String knapsackFileName = cmd.getOptionValue("knapsack");
    boolean verbose = cmd.hasOption("verbose");
    int sideways = getOption(cmd, "sideways");
    int restarts = getOption(cmd, "restarts");
    int queens = getOption(cmd, "queens");

    Parameters hillClimbParams;
    HC hillClimb;
    ArrayList<Integer> initialState ;

    if (cmd.hasOption("knapsack")) {
      BufferedReader br = new BufferedReader(new FileReader(knapsackFileName));
      InputKnapsack knapsackInput = new Gson().fromJson(br, InputKnapsack.class);
      hillClimbParams = new Parameters(knapsackInput, restarts, sideways, verbose);
      hillClimb = new Knapsack(hillClimbParams);
      initialState = new ArrayList<>();
      List<String> start = knapsackInput.getStart();
      List<Item> items = knapsackInput.getItems();
      Set<String> startSet = new HashSet<>(start);
      for (Item item : items) {
        if (startSet.contains(item.getName())) {
          initialState.add(1);
        } else {
          initialState.add(0);
        }
      }
    } else if (cmd.hasOption("queens")) {
      hillClimbParams = new Parameters(queens, restarts, sideways, verbose);
      hillClimb = new NQueens(hillClimbParams);
      initialState = (ArrayList<Integer>) IntStream.range(0, queens).boxed().collect(Collectors.toList());
    }
    else {
      throw new Exception("Please pass arguments related to knapsack or N queens");
    }
    hillClimbRunner.hillClimb(initialState, hillClimbParams, hillClimb);
  }
  public static int getOption(CommandLine cmd, String option) throws Exception {
    String parsedOptionValue = (String)cmd.getParsedOptionValue(option);
    return cmd.hasOption(option) ? Integer.parseInt(parsedOptionValue) : 0;
  }
}
