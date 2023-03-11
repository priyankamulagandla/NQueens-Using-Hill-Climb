import java.util.*;

public class Knapsack implements HC {

  public final Parameters parameters;

  public Knapsack(Parameters hillClimbParams) {
    this.parameters = hillClimbParams;
  }

  @Override
  public int value(ArrayList<Integer> state) {
    int totalValue = 0;
    int totalWeight = 0;
    for (int i = 0; i < state.size(); i++) {
      if (state.get(i) > 0) {
        Item knapsackItem = parameters.getInputKnapsack().getItems().get(i);
        totalValue += knapsackItem.getValue();
        totalWeight += knapsackItem.getWeight();
      }
    }
    return Math.max(totalWeight - parameters.getInputKnapsack().getM(), 0) + Math.max(parameters.getInputKnapsack().getT() - totalValue, 0);
  }

  @Override
  public ArrayList<ArrayList<Integer>> next(ArrayList<Integer> state) {
    ArrayList<ArrayList<Integer>> neighbouringStates = new ArrayList<>();
    for (int i = 0; i < state.size(); i++) {
      ArrayList<Integer> newState = (ArrayList<Integer>)state.clone();
      if (state.get(i) == 0) newState.set(i, 1);
      else newState.set(i, 0);
      neighbouringStates.add(newState);
    }

    Set<Integer> selectedIndices = new HashSet<>();
    Set<Integer> unSelectedIndices = new HashSet<>();
    for (int i = 0; i < state.size(); i++) {
      if (state.get(i) == 1) selectedIndices.add(i);
      else unSelectedIndices.add(i);
    }

    for (int unselectedIndex : unSelectedIndices) {
      for (int selectedIndex : selectedIndices) {
        ArrayList<Integer> newState = (ArrayList<Integer>)state.clone();
        newState.set(selectedIndex, 0);
        newState.set(unselectedIndex, 1);
        neighbouringStates.add(newState);
      }
    }
    return neighbouringStates;
  }

  @Override
  public ArrayList<Integer> restart() {
    ArrayList<Integer> newState = new ArrayList<>();
    for (int i = 0; i < parameters.getInputKnapsack().getItems().size(); i++) {
      boolean b = new Random().nextBoolean();
      if (!b) newState.add(0);
      else newState.add(1);
    }
    return newState;
  }

  @Override
  public String print(ArrayList<Integer> state) {
    String result = "[";
    for (int i =0; i < state.size() ;i++) {
      if (state.get(i) > 0)
        result += parameters.getInputKnapsack().getItems().get(i).getName() + " ";
    }
    result += "] = " + value(state);
    return result;
  }

  public int totalWeight(ArrayList<Integer> state){
    int totalWeight =0;
    for (int i = 0; i < state.size(); i++) {
      if (state.get(i) > 0)
        totalWeight += parameters.getInputKnapsack().getItems().get(i).getWeight();
    }
    return totalWeight;
  }

  public int totalValue(ArrayList<Integer> state){
    int totalValue =0;
    for (int i = 0; i < state.size(); i++) {
      if (state.get(i) > 0)
        totalValue += parameters.getInputKnapsack().getItems().get(i).getValue();
    }
    return totalValue;
  }

  @Override
  public boolean compare(ArrayList<Integer> state1, ArrayList<Integer> state2) {

    if(value(state2) !=value(state1)){
      return value(state2)>value(state1);
    }
    if(totalValue(state1) != totalValue(state2)){
      return totalValue(state2)>totalValue(state1);
    }
    return totalWeight(state2)<totalWeight(state1);

  }
}
