import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NQueens implements HC {

  public final Parameters hillClimbParams;

  public NQueens(Parameters hillClimbParams) {
    this.hillClimbParams = hillClimbParams;
  }

  public boolean isClash(int q1x, int q1y, int q2x, int q2y) {
    return (q1x + q1y == q2x + q2y) || (q1x - q1y == q2x - q2y);
  }

  @Override
  public int value(ArrayList<Integer> state) {
    int count = 0;
    for (int i = 0; i < state.size() - 1; i++) {
      for (int j = i + 1; j < state.size(); j++) {
        if (isClash(i, state.get(i), j, state.get(j)))
          count++;
      }
    }
    return count;
  }

  @Override
  public ArrayList<ArrayList<Integer>> next(ArrayList<Integer> state) {
    ArrayList<ArrayList<Integer>> states= new ArrayList<>();
    for (int i = 0; i < state.size() - 1; i++) {
      for (int j = i + 1; j < state.size(); j++) {
        ArrayList<Integer> tempState = (ArrayList<Integer>)state.clone();
        tempState.set(j, state.get(i));
        tempState.set(i, state.get(j));
        states.add(tempState);
      }
    }
    return states;
  }

  @Override
  public ArrayList<Integer> restart() {
    List<Integer> list = Arrays.stream(IntStream.range(0, hillClimbParams.getNQueens()).toArray()).boxed().collect(Collectors.toList());
    Collections.shuffle(list);
    return (ArrayList<Integer>) list;
  }

  @Override
  public String print(ArrayList<Integer> state) {
    String result = "[";
    for (int i : state) {
      result = result +(i);
      result = result +(" ");
    }
    result = result +("] = ");
    result = result +(value(state));
    return result;
  }

  @Override
  public boolean compare(ArrayList<Integer> state1, ArrayList<Integer> state2) {
    return value(state2)> value(state1);
  }
}
