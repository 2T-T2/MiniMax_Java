package t_panda.minimax;

class ArrayUtil {
    public static int max(int[] a) {
        int m = a[0];
        for (int i = 1; i < a.length; i++) {
            if( m < a[i] ) {
                m = a[i];
            }
        }
        return m;
    }
    public static int min(int[] a) {
        int m = a[0];
        for (int i = 1; i < a.length; i++) {
            if( m > a[i] ) {
                m = a[i];
            }
        }
        return m;
    }
    public static int findIndex(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if ( a[i] == target) { return i; }
        }
        return -1;
    }
}