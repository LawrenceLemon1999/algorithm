import java.io.*;
import java.util.*;


public class exercise3_2 {

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
    static class Point {
        int start;
        int end;
        public Point(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    static class NameComparator implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            return o1.end - o2.end;
        }
    }

    public static void main(String[] args) throws IOException {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int casenumber=in.nextInt();
        for(int i=0;i<casenumber;i++) {
            List<Point> list=new ArrayList<Point>();
            int number=in.nextInt();
            for(int j=0;j<number;j++) {
                int start = in.nextInt();
                int end = in.nextInt();
                list.add(new Point(start, end));
            }
            sortPerson(list);
            // int preend=(Integer)it.next();
            //  Iterator<list>=
            //   Iterator<List> iterator = list.iterator();
            Iterator it = list.iterator();
            Point preend=(Point)it.next();
            int Preend=preend.end;
            //  out.println("pre"+Preend);
            int count=1;
            while(it.hasNext()){
                Point t=(Point)it.next();
                if(t.start>Preend){
                    //     out.println("t"+t.start);
                    Preend=t.end;
                    count++;
                }
            }
            out.println(count);
            //  out.println("------");
        }
        out.close();
    }
    public  static void sortPerson(List<Point> list){
        Collections.sort(list,new NameComparator());
    }

}

