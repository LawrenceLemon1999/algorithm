import java.io.*;
import java.util.*;


public class exerciese3gai {
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
    public static void main(String[] args) throws IOException {
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

