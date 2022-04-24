package t_panda.minimax;

class GameTree<T> {
    private final GameNode<T> root;
    private java.util.ArrayList<GameNode<T>> children = new java.util.ArrayList<GameNode<T>>();

    GameTree(T root, T[] children) {
        this.root = new GameNode<T>(null, root, 0);
        for (T child : children) { this.children.add( new GameNode<T>(this.root, child, 1) ); }
    }

    public T evel() {
        return root.getMyState();
    }

    public GameNode<T> getRoot() { return root; }
    public java.util.ArrayList<GameNode<T>> getChildren() { return children; }
}
