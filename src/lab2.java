import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.*;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

public class lab2
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
        int d;
        int x;
        int y;

        //考虑放父亲的节点？
        public Point(int d,int x,int y){
            this.d=d;
            this.x=x;
            this.y=y;

        }
    }
    public static void main(String[] args)
    {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n=in.nextInt();//行
        int m=in.nextInt();//列
        char all[][]=new char[n+2][m+2];
        int All[][]=new int[n+2][m+2];
        for(int i=0;i<=n+1;i++){
            All[i][0]=0;
            All[i][m+1]=0;
        }
        for(int i=0;i<=m+1;i++){
            All[0][i]=0;
            All[n+1][i]=0;
        }//在边框塞0
        for(int i=1;i<n+1;i++) {
            String s = in.next();
            for(int j=0;j<m;j++){
                all[i][j+1]=s.charAt(j);
            }
        }
        int HX=-1;
        int HY=-1;
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(all[i][j]=='S')
                    All[i][j]=1;
                else if(all[i][j]=='H'){
                    All[i][j]=2;
                    HX=i;
                    HY=j;
                }
                else if(all[i][j]=='R')
                    All[i][j]=3;
                else if(all[i][j]=='B')
                    All[i][j]=4;
                else if(all[i][j]=='W')
                    All[i][j]=5;//其实可以不写这一步，因为W是墙，和0相比没有本质区别。

            }
        }
//        for(int i=0;i<n+2;i++){
//            for(int j=0;j<m+2;j++){
//                out.print(All[i][j]+" ");
//            }
//            out.println();
//        }//已经把数据放进去了.
       // out.println(HX+" "+HY);

        Comparator<Point> pointcmp;
        pointcmp= new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.d-o2.d;///重载优先级使其变为小根堆
            }
        };

        Queue<Point> queue = new PriorityQueue<Point>(pointcmp);
        queue.add(new Point(0,HX,HY));
        boolean [][]hascome=new boolean[n+2][m+2];
        while(!queue.isEmpty()){
            Point temp=queue.poll();
            int tx=temp.x;
            int ty=temp.y;//变化成四种，都带入fin里面？？？
            int td=temp.d;
           //写一个方法，对于输入的x，y值执行相同的操作，返回queue
            int ty_1=ty-1;
            int tyjia1=ty+1;
            int tx_1=tx-1;
            int txjia1=tx+1;
            fin(queue,tx,ty_1,td,All);
            fin(queue,tx,tyjia1,td,All);
            fin(queue,tx_1,ty,td,All);
            fin(queue,txjia1,ty,td,All);
            if(isfind){
                out.println(temp.d+1);
                break;
            }

        }

//        for(int i=0;i<n+2;i++){
//            for(int j=0;j<m+2;j++){
//                out.print(All[i][j]+" ");
//            }
//            out.println();
//        }//已经把数据放进去了.

        out.close();
    }
    static boolean isfind=false;
    public static Queue<Point>fin(Queue<Point>queue,int tx,int ty,int td,int All[][]){
        if(All[tx][ty]==3){//若碰到R（3），则需要其f+1。B（4）需要+2，S（1），直接可以结束查找。遇到自己H（2）以及W（5）、0时都是不管。
             queue.add(new Point(td+1,tx,ty));
             All[tx][ty]=0;//因为不需要回来了，就直接变成0
        }
        else if(All[tx][ty]==4){
            queue.add(new Point(td+2,tx,ty));
            All[tx][ty]=0;//因为不需要回来了，就直接变成0
        }
        else if(All[tx][ty]==1){
            isfind=true;//别忘了每次循环要改回false
           // queue.peek().d++;
        }

        return queue;


    }
}
