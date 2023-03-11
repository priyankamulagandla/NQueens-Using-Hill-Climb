import java.util.ArrayList;

public interface HC {

  int value(ArrayList<Integer> state);

  ArrayList<ArrayList<Integer>> next(ArrayList<Integer> state);

  ArrayList<Integer> restart();

  String print(ArrayList<Integer> state);

  boolean compare(ArrayList<Integer> state1, ArrayList<Integer> state2);

}
