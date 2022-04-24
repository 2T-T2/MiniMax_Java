package t_panda.minimax;

public interface MiniMaxFuncs<StateType> {
    public StateType[] getNextState(StateType now, int depth);
    public int calcScore(StateType state);
}
