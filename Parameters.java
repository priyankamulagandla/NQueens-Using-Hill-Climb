public class Parameters {

  Integer NQueens;
  InputKnapsack inputKnapsack;
  int restarts;
  int sideways;
  boolean verbose;

  public Parameters(InputKnapsack inputKnapsack, int restarts, int sideways, boolean verbose) {
    this.inputKnapsack = inputKnapsack;
    this.restarts = restarts;
    this.sideways = sideways;
    this.verbose = verbose;
  }

  public Parameters(int NQueens, int restarts, int sideways, boolean verbose) {
    this.NQueens = NQueens;
    this.restarts = restarts;
    this.sideways = sideways;
    this.verbose = verbose;
  }

  public int getNQueens() {
    return NQueens;
  }

  public InputKnapsack getInputKnapsack() {
    return inputKnapsack;
  }

  public int getSideways() {
    return sideways;
  }

  public boolean isVerbose() {
    return verbose;
  }
}
