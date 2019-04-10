import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

public class exercise2
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

    public static void main(String[] args)
    {
        InputReader in= new InputReader(System.in);
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
        }
        for(int i=1;i<n+1;i++) {
            String s = in.next();
            for(int j=0;j<m;j++){
                all[i][j+1]=s.charAt(j);
            }
        }

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(all[i][j]=='r')
                    All[i][j]=1;
                else if(all[i][j]=='w')
                    All[i][j]=2;
            }
        }
//        for(int i=0;i<n+2;i++){
//            for(int j=0;j<m+2;j++){
//                out.print(All[i][j]+" ");
//            }
//            out.println();
//        }//已经把数据放进去了.
        int num=in.nextInt();
        int point[][]=new int[num][2];
        int sum=0;
        int prex=1;
        int prey=1;
        for(int i=0;i<num;i++){
            point[i][0]=in.nextInt()+1;
            point[i][1]=in.nextInt()+1;//因为围了一圈的0，所以需要所有坐标加一
        }
        for(int i=0;i<num;i++){
            int targetx=point[i][0];
            int targety=point[i][1];
            int diss=dis(All,targetx,targety,n,m,prex,prey);//调用方法查找两点间最短距离
            if(diss==-1){
                sum=-1;
                break;
            }

            //if(di)
            prex=targetx;
            prey=targety;
            sum+=diss;
       //     out.println(diss);
        }
        out.println(sum);
        out.close();
    }
    public static int dis (int [][]All,int targetx,int targety,int n,int m,int prex,int prey){
        Queue<Integer> queue= new LinkedList<Integer>();
        int distance[][]=new int[n+2][m+2];
        boolean hascome[][]=new boolean[n+2][m+2];//对于每一个点来说去查看他点上下左右，
        // 如果已经入队、为0、旁边是w，那么跳过这个选择，如果为r，那么入队且把这个点标记为hascome=true
        boolean isok=false;
        queue.add(prex);
        queue.add(prey);
        hascome[prex][prey]=true;
        while(!isok){
            if(!queue.isEmpty()) {
                int x = queue.poll();
                int y = queue.poll();
                if(targetx==x&&targety==y){//找到就break
                    break;
                }

                if(All[x][y - 1] ==1&&!hascome[x][y-1]){
                    queue.add(x);
                    queue.add(y-1);
                    distance[x][y-1]=distance[x][y]+1;//如果入队，那么这个点距离初始位置距离为上一个点+1
                    hascome[x][y-1] = true;
                }
                if(All[x][y + 1] ==1&&!hascome[x][y+1]){
                     queue.add(x);
                    queue.add(y+1);
                    distance[x][y+1]=distance[x][y]+1;
                    hascome[x][y+1] = true;
                }
                if(All[x - 1][y] ==1&&!hascome[x-1][y]){
                    queue.add(x-1);
                    queue.add(y);
                    distance[x-1][y]=distance[x][y]+1;
                    hascome[x-1][y] = true;
                }
                if(All[x + 1][y] ==1&&!hascome[x+1][y]){
                    queue.add(x+1);
                    queue.add(y);
                    distance[x+1][y]=distance[x][y]+1;
                    hascome[x+1][y] = true;
                }

            }
            else{

                return -1;//如果没有找到这个点，队列会空掉，那么就返回-1，表示没有办法到达。
            }
          //  con++;
        }
        return distance[targetx][targety];
    }
}
