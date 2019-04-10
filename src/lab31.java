import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class lab31{
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
        public Point(int start,int end){
            this.start=start;
            this.end=end;
        }
    }
    public static void main(String[] args) throws IOException {
        InputReader in= new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int number=in.nextInt();
        Comparator<Point> pointcmp;
        pointcmp= new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.end==o2.end)
                    return o1.start-o2.start;//这样重写可以保证start小的在前面
                else
                    return o1.end-o2.end;///重载优先级使其变为小根堆
            }
        };
        Queue<Point> queue = new PriorityQueue<Point>(pointcmp);
        int left=0;
        int right=10000;
        int maxlength=0;
        for(int j=0;j<number;j++){
            int s=in.nextInt();
            int e=in.nextInt();
            if(e>maxlength)
                maxlength=e;
            if(e-s<right){
                right=e-s+1;//找到最小的间隔//之前应该是这里出错了= =
            }
            queue.add(new Point(s,e));
        }
       // out.println(right);
        int all[][]=new int[number][2];
        int point=0;
        while(!queue.isEmpty()){
            Point temp=queue.poll();
            all[point][0]=temp.start;
            all[point][1]=temp.end;
            point++;
        }
//        for(int i=0;i<number;i++) {
//            out.println(Arrays.toString(all[i]));
//        }
        int mid=0;
        int result=0;
        while(right>=left){
            mid=(left+right)/2;
            boolean iscando=isok(all,mid,maxlength);
            if(iscando)
                result=mid;
         //   out.println(iscando+" "+result+" "+mid);
            if(iscando)
                left=mid+1;
            else
                right=mid-1;
        }
        out.println(result);
        out.close();
    }
    public static boolean isok(int[][]all,int mid,int maxlength){
        boolean isoccupy[]=new boolean[maxlength+1];
        for(int j=0;j<all.length;j++){
            int count=0;
            for(int i=all[j][0];i<=all[j][1];i++){
                if(!isoccupy[i]){
                    count++;
                    isoccupy[i]=true;
                }
                if(count==mid){
                    break;
                }
            }
            if(count!=mid)
                return false;
        }
       // System.out.println(Arrays.toString(isoccupy));
        return true;
        // System.out.println(Arrays.toString(isoccupy));
    }

}

