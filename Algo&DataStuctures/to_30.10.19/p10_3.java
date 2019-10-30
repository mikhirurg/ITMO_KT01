import java.util.*;

public class p10_3 {
    private static ArrayList<Integer>[] graph;
    static int[] p;
    static boolean[] used;
    static boolean hasCycle;
    static int numAge;

    static void dfs(int v1, int w) {
        numAge++;
        used[v1] = true;
        for (int v : graph[v1]) {
            if (!used[v]) {
                dfs(v, v1);
            } else if (w!=v) hasCycle = true;
        }
    }

    static void addAge(int v1, int v2) {
        int x1 = p[v1];
        int y1 = p[v2];
        graph[v1].add(v2);
        graph[v2].add(v1);
        for (int i = 1; i < p.length; i++) {
            if (p[i] == x1) {
                p[i] = y1;
            }
        }
    }

    static int hasCycle(int comp){
        int n = 0;
        for (int i = 0; i < p.length; i++) {
            if (p[i] == comp) {
                n = i;
            }
        }
        hasCycle = false;
        dfs(n, -1);
        if (hasCycle){
            return 1;
        } return -1;
    }

    static int getNumCycles() {
        int count = 0;
        Set<Integer> comp = new HashSet();
        for (int i : p) {
            comp.add(i);
        }
        for (int i : comp) {
            if (hasCycle(i) == 1) {
                count++;
            }
        }
        return count;
    }

    static int getNumAges(int comp) {
        numAge = 0;
        int n = -1;
        for (int i = 0; i < p.length; i++) {
            if (p[i] == comp) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return n;
        } else {
            return numAge - 1;
        }
    }

    static int isTree(int comp) {
        numAge = 0;
        int n = -1;
        int sum = 0;
        int suma = 0;
        for (int i = 0; i < p.length; i++) {
            if (p[i] == comp) {
                n = i;
                sum += graph[i].size();
                suma++;
            }
        }
        sum /= 2;
        if (n == -1) {
            return n;
        } else {
            Arrays.fill(used, false);
            dfs(n,n);
            if (sum + 1 == suma || numAge == 1) {
                return 1;
            } else return -1;
        }

    }

    static int numTrees() {
        int count = 0;
        Set<Integer> comp = new HashSet();
        for (int i : p) {
            comp.add(i);
        }
        for (int i : comp) {
            if (isTree(i) == 1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        graph = new ArrayList[n];
        p = new int[n];
        used = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            p[i] = i;
        }
        addAge(1, 2);
        addAge(2, 3);
        addAge(3, 1);

        //System.out.println(getNumAges(3));
        System.out.println(getNumCycles());
    }
}
