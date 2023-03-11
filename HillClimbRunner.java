import java.util.ArrayList;

public class HillClimbRunner {
    public static void hillClimb(ArrayList<Integer> init, Parameters hillClimbParams, HC hillClimb) throws Exception {
        System.out.println(hillClimb.print(init));
        int chosenValue = hillClimb.value(init);
        ArrayList<Integer> chosenState = init;
        int sideways = hillClimbParams.getSideways();
        int temp = sideways;
        int restart = hillClimbParams.restarts;
        while (chosenValue != 0) {
            ArrayList<ArrayList<Integer>> states = hillClimb.next(chosenState);
            int best = hillClimb.value(chosenState);
            ArrayList<Integer> state = chosenState;
            for (ArrayList<Integer> neighbourState : states) {
                if (hillClimbParams.isVerbose()) {
                    System.out.println(hillClimb.print(neighbourState));
                }
                int nextValue = hillClimb.value(neighbourState);
                if (best > nextValue) {
                    best = nextValue;
                    state = neighbourState;
                    temp = sideways;
                }
                if (best == nextValue && temp > 0) {
                    if (hillClimbParams.NQueens != null || (hillClimb.compare(state, neighbourState))) {
                        best = nextValue;
                        state = neighbourState;
                    }
                }
            }

            if (best < chosenValue) {
                chosenState = state;
                chosenValue = best;
                System.out.println("choose: " + hillClimb.print(chosenState));
            } else if (best == 0) {
                System.out.println("Goal: " + hillClimb.print(state));
                return;
            } else if (state.equals(chosenState)) {
                if (temp > 0) {
                    temp--;
                    chosenState = state;
                    chosenValue = hillClimb.value(chosenState);
                    System.out.println("choose(sideways): " + hillClimb.print(chosenState));
                } else
                    throw new Exception("Optimum state not achieved");
            } else {
                if (temp > 0) {
                    if (restart > 0) {
                        restart--;
                        chosenState = hillClimb.restart();
                        chosenValue = hillClimb.value(chosenState);
                        temp = sideways;
                        System.out.println("Using" + hillClimb.print(chosenState) + " for restart");
                    } else
                        throw new Exception("Optimum state not achieved");
                }
            }
        }
    }
}
