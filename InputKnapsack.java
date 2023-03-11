import java.util.List;

public class InputKnapsack {

  public int T;
  public int M;
  public List<String> Start;
  public List<Item> Items;

  public InputKnapsack(int t, int m, List<String> start, List<Item> items) {
    T = t;
    M = m;
    this.Start = start;
    this.Items = items;
  }

  public int getT() {
    return T;
  }

  public int getM() {
    return M;
  }

  public List<String> getStart() {
    return Start;
  }

  public List<Item> getItems() {
    return Items;
  }
}
