import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.*;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

public class exercise3
{
    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

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

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }

    }
    static class Point
    {
       int start;
       int end;
        //考虑放父亲的节点？
        public Point(int start,int end){
           this.start=start;
           this.end=end;
        }
    }
    public static void main(String[] args)
    {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int casenumber=in.nextInt();
        for(int i=0;i<casenumber;i++){
            int number=in.nextInt();
            Comparator<Point> pointcmp;
            pointcmp= new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.end-o2.end;///重载优先级使其变为小根堆
                }
            };
            Queue<Point> queue = new PriorityQueue<Point>(pointcmp);
            int count=1;
            for(int j=0;j<number;j++){
                int s=in.nextInt();
                int e=in.nextInt();
                queue.add(new Point(s,e));
            }
            int preend=0;
            if(!queue.isEmpty()){
               Point t= queue.poll();
                preend=t.end;
            }
          //  out.println(preend);
            while(!queue.isEmpty()){
                Point temp=queue.poll();
         //       out.println("start"+temp.start);
                if(temp.start>preend){
            //        out.println(preend);
                    preend=temp.end;
                    count++;
                }
            //    queue.poll();
            }
            out.println(count);
        }
        out.close();
    }
}
