import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
public class lab4jc {
    private static int[] parent;  // parent[i] = parent of i
    private static int count;     // number of components
    public static void main(String[] args) {
        PriorityQueue<Edge> pq = parse();
        solve(pq);
    }
    private static void solve(PriorityQueue<Edge> pq) {
        long result = 0;
        while (!pq.isEmpty() && count != 1) {
            Edge edge = pq.poll();
            int i = edge.i;
            int j = edge.j;
            if (!connected(i, j)) {
                union(i, j);
                result += edge.weight;
            }
        }
        System.out.println(result);
    }
    private static PriorityQueue<Edge> parse() {
        try {
            InputReader inputReader = new InputReader(System.in);
            //  InputReader inputReader = new InputReader(new FileInputStream("1122.txt"));
            int N = inputReader.nextInt();
            /* ***Initialize the unionFind*** */
            parent = new int[N + 1];
            count = N + 1;
            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }
            /* ****************************** */
            PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return o1.weight - o2.weight;
                }
            });
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j <= N; j++) {
                    pq.offer(new Edge(i, j, inputReader.nextInt()));
                }
            }
            return pq;
        } catch (IOException e) {
                System.err.println("IOException");
            }
            return null;
        }
        /* *** Helper Functions For UnionFound *** */
        private static int count() {
            return count;
        }
        private static int find (int p) {
            while (p != parent[p]) {
                p = parent[p];
            }
            return p;
        }
        private static void validate(int p) {
            int n = parent.length;
            if (p < 0 || p >= n) {
                throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
            }
        }
        private static boolean connected(int p, int q) {
            return find(p) == find(q);
        }
        private static void union(int p, int q) {
            int rootP= find(p);
            int rootQ= find(q);
            if (rootP== rootQ)
                return;
            parent[rootP] = rootQ;
            count--;
        }




//
//        System.out.println("Union " + p + " and " + q);
//        parent[rootP] = rootQ;
//        count--; }
    /* ***************************************** */
    private static class Edge {
        short i;
        short j;
        int weight;
        public Edge(int i, int j, int weight) {
            this.i = (short) i;
            this.j = (short) j;
            this.weight = weight;
        } }
    static class InputReader {
        private StreamTokenizer tokenizer;
        public InputReader(InputStream stream) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = new StreamTokenizer(reader);
        }
        private double next() throws IOException {
            if (tokenizer != null) {
                tokenizer.nextToken();
            }
            return tokenizer.nval;
        }
        private int nextInt() throws IOException {
            return (int) next();
        }
        private double nextDouble() throws IOException {
            return next();
        }
    } }