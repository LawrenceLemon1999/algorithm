import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class lab3 {
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
//        out.println("isem"+queue.isEmpty());
//        out.println("right"+right);
        int mid=0;
        int result=0;
        Queue<Point>s=queue;

        out.println("pandua"+isok(s,4,maxlength));
        out.println("pandua"+isok(queue,4,maxlength));
        while(right>left){
            mid=(left+right)/2;
       //     Queue<Point>newqueue=queue;
            boolean iscando=isok(queue,mid,maxlength);
            if(iscando)
                result=mid;
       //     out.println(iscando+" "+result);
            if(iscando)
                left=mid+1;
            else
                right=mid-1;
       //     out.println("bianjie "+left+" "+right);
        }
      //  out.println("mid"+mid);//输出答案
        out.println(result);
        out.close();
    }
    public static boolean isok(Queue<Point> queue,int mid,int maxlength){
         //boolean iscando=false;
        System.out.println("mid"+mid);
         boolean isoccupy[]=new boolean[maxlength+1];
        System.out.println("que"+queue.isEmpty());
        while(!queue.isEmpty()){
            Point temp=queue.poll();
            int count=0;
            for(int i=temp.start;i<=temp.end;i++){
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
       //     System.out.println("count"+count);
        }
       // System.out.println(Arrays.toString(isoccupy));
      //  System.out.println(mid);

        return true;
    }

}

