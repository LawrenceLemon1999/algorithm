import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Orienting_Astar {

    private int Row;
    private int Col;
    private boolean[][] map;         // r = 1; w = 0;
    private boolean[][] isVisit;
    private short[][] aim;
    public int result = 0;
    private Node destNode;

    public static void main(String[] args) {
        Orienting_Astar orienteering = new Orienting_Astar();
    }

    public Orienting_Astar() {
        parse();

        for (int i = 1; i < aim.length; i++) {
            isVisit = new boolean[Row + 2][Col + 2];
            destNode = new Node(aim[i][0], aim[i][1]);
//      System.out.println("Aim: (" + aim[i][0] + ", " + aim[i][1] + ")");
            int res = bfs(aim[i - 1][0], aim[i - 1][1]);
            if (res == -1) {
                System.out.println("-1");
                return;
            } else {
//        System.out.println("res = " + res);
                result += res;
            }
        }
        System.out.println(result);
    }

    private int bfs(int iniX, int iniY) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.priority - o2.priority;
            }
        });
        Node start = new Node(iniX, iniY);
        start.setMove(0);
        queue.offer(start);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.equals(destNode)) {
                return cur.move;
            }
            isVisit[cur.x][cur.y] = true;
            int nextMove = cur.move + 1;
            for (Node neighbor : getNeighbors(cur)) {
                Node node = null;
                for (Node n: queue) {
                    if (n.x == neighbor.x && n.y == neighbor.y) {
                        node = n;
                        break;
                    }
                }
                if (node == null) {
                    neighbor.setMove(nextMove);
                    neighbor.setPriority();
                    queue.offer(neighbor);
                } else {
                    neighbor.setMove(nextMove);
                    neighbor.setPriority();
                    if (neighbor.priority < node.priority) {
                        queue.remove(node);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return -1;
    }

    private Iterable<Node> getNeighbors(Node node) {
        Stack<Node> stack = new Stack<>();
        short x = node.x;
        short y = node.y;
        if (map[x - 1][y] && !isVisit[x - 1][y]) {
            stack.push(new Node(x - 1, y));
        }
        if (map[x + 1][y] && !isVisit[x + 1][y]) {
            stack.push(new Node(x + 1, y));
        }
        if (map[x][y - 1] && !isVisit[x][y - 1]) {
            stack.push(new Node(x, y - 1));
        }
        if (map[x][y + 1] && !isVisit[x][y + 1]) {
            stack.push(new Node(x, y + 1));
        }
        return stack;
    }

    private class Node {

        short x;
        short y;
        short move;
        short priority;

        public Node(int x, int y) {
            this.x = (short) (x);
            this.y = (short) (y);
            this.move = 0;
        }

        public void setMove(int move) {
            this.move = (short) (move);
        }

        public boolean equals(Node that) {
            return this.x == that.x && this.y == that.y;
        }

        public void setPriority() {
            this.priority = (short) (this.move + Math.abs(this.x - destNode.x) + Math
                    .abs(this.y - destNode.y));
        }
    }

    private void parse() {
        try {
//      InputReader inputReader = new InputReader(System.in);
            InputReader inputReader = new InputReader(new FileInputStream("input.txt"));
            Row = inputReader.nextInt();
            Col = inputReader.nextInt();

            map = new boolean[Row + 2][Col + 2];

            for (int i = 1; i <= Row; i++) {
                char[] chars = inputReader.next().toCharArray();
                for (int j = 0; j < Col; j++) {
                    map[i][j + 1] = (chars[j] == 'r');
                }
            }

            aim = new short[inputReader.nextInt() + 1][2];
            aim[0][0] = 0 + 1;
            aim[0][1] = 0 + 1; // initial position
            for (int i = 1, length = aim.length; i < length; i++) {
                aim[i][0] = (short) (inputReader.nextInt() + 1);
                aim[i][1] = (short) (inputReader.nextInt() + 1);
            }
            inputReader.close();
        } catch (Exception e) {
            System.err.println("Error when paring");
        }
    }

    static class InputReader {

        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }

        public void close() {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("close err");
            }
            tokenizer = null;
        }
    }

}