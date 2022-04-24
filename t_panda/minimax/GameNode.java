package t_panda.minimax;

class GameNode<T> {
    public static int initScore = Integer.MIN_VALUE;
    private final GameNode<T> parent;
    private final T myState;
    private int score = initScore;
    // private int depth;
    private java.util.ArrayList<GameNode<T>> children = new java.util.ArrayList<GameNode<T>>();

    GameNode(GameNode<T> parent, T myState, int depth) {
        this.parent = parent;
        this.myState = myState;
        // this.depth = depth;
    }


    public void addChildren(GameNode<T> child) { children.add( child ); }

    public void setScore(int score) { this.score = score; }

    public java.util.ArrayList<GameNode<T>> getChildren() { return children; }
    public T getMyState() { return myState; }
    public GameNode<T> getParent() { return parent; }
    public int getScore() { return score; }
}
