package t_panda.minimax;

public class MiniMax<State> {
    private MiniMaxFuncs<State> funcs = null;

    public MiniMax(MiniMaxFuncs<State> funcs) {
        this.funcs = funcs;
    }

    public State start(int depth, State nowState) {
        GameTree<State> gt = new GameTree<>(nowState, funcs.getNextState(nowState, 1));
        if( depth == 1 ) { // 深さ1の探索終了
            var nodes = gt.getChildren();
            int[] scores = new int[nodes.size()];
            for (int i = 0; i < scores.length; i++) { scores[i] = funcs.calcScore(nodes.get(i).getMyState()); }
            int idx = ArrayUtil.findIndex(scores, ArrayUtil.min(scores));
            return nodes.get(idx).getMyState();
        }

        int restDepth = depth-1;
        var parents = gt.getChildren();
        var nParents = parents.size()-1;
        while( true ) {
            for(; nParents >= 0; nParents--) {
                GameNode<State> parent = parents.get(nParents);
                for (State next: funcs.getNextState(parent.getMyState(), depth-restDepth+1)) {
                    parent.addChildren(new GameNode<State>(parent, next, depth-restDepth+1));
                }
            }
            restDepth--;
            if( restDepth == 0 ) { break; }
            var newParents = new java.util.ArrayList<GameNode<State>>();
            for (var parent : parents) { newParents.addAll( parent.getChildren() ); }
            parents = newParents;
            nParents = parents.size()-1;
        }

        var lastChildren = new java.util.ArrayList<GameNode<State>>();
        for (var parent : parents) { lastChildren.addAll(parent.getChildren()); }

        for (GameNode<State> child : lastChildren) {
            child.setScore(funcs.calcScore(child.getMyState()));
        }

        var tmpParent = new java.util.ArrayList<GameNode<State>>();
        var tmpChildren = lastChildren;
        for (GameNode<State> child : tmpChildren) { if( !tmpParent.contains( child.getParent() ) ) { tmpParent.add(child.getParent()); }  }

        for (int i = depth; i > 0; i--) {
            for (var child : tmpChildren) {
                if( !tmpParent.contains( child.getParent() ) ) { tmpParent.add(child.getParent()); }
                if( i%2 == 0 ) {
                    if( child.getParent().getScore() == GameNode.initScore || child.getParent().getScore() < child.getScore() ) {
                        child.getParent().setScore(child.getScore());
                        // System.out.println("depth" + i);
                        // System.out.println("child parent score: " + child.getParent().getScore() );
                        // System.out.println("child score: " + child.getScore() );
                    }

                }else {
                    if( child.getParent().getScore() == GameNode.initScore || child.getParent().getScore() > child.getScore() ) {
                        child.getParent().setScore(child.getScore());
                        // System.out.println("depth" + i);
                        // System.out.println("child parent score: " + child.getParent().getScore() );
                        // System.out.println("child score: " + child.getScore() );
                    }
                }
            }
            tmpChildren = new java.util.ArrayList<GameNode<State>>( tmpParent );
            for (int j = tmpParent.size()-1; j >= 0; j--) { tmpParent.remove(j); }
        }

        for (GameNode<State> child : gt.getChildren()) {
            if ( gt.getRoot().getScore() == child.getScore() ) {
                return child.getMyState();
            }
        }
        return null;
    }
}