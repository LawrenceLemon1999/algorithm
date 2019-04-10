import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class lab4{
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

    }
    public static int find (int pre) {
        while (pre != parent[pre]) {
            pre = parent[pre];
        }
        return pre;
    }
    public static void union(int p, int q) {
        int rootP= find(p);
        int rootQ= find(q);
        if (rootP== rootQ)
            return;
        parent[rootP] = rootQ;
    }
    static class bian
    {
        int n1;
        int n2;
        int weight;
        public bian(int n1,int n2,int weight){
            this.n1=n1;
            this.n2=n2;
            this.weight=weight;
        }
    }
       static int []parent;
    public static void main(String[] args) throws IOException {
        InputReader in= new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n=in.nextInt();
        parent = new int[n + 1];//因为多搞了一个0，所以大一点
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        Comparator<bian> biancmp;
        biancmp= new Comparator<bian>() {
            @Override
            public int compare(bian o1, bian o2) {
                return o1.weight-o2.weight;
            }
        };
        Queue<bian> queue = new PriorityQueue<bian>(biancmp);
        for (int i = 0; i < n; i++) {//直接在输入的时候就把诸如（0，1】边安排好
            for (int j = i + 1; j <= n; j++) {
                queue.offer(new bian(i, j, in.nextInt()));
            }
        }
        long cost = 0;
        int count =0;//其实这里写n就行
        while (!queue.isEmpty()) {
            if(count==n)
                break;
            bian temp = queue.poll();
            int x = temp.n1;
            int y = temp.n2;
            if (find(x)!=find(y)) {
                union(x, y);
                cost+=temp.weight;
                count++;
            }
        }
        out.println(cost);
        out.close();
    }

}

