import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.*;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

public class exercise2astar
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
        int f;
        int x;
        int y;
        // int g_temp;
        int g;
        //考虑放父亲的节点？
        public Point(int f,int x,int y,int g){
            this.f=f;
            this.x=x;
            this.y=y;
            //    g_temp=this.g_temp;
            this.g=g;
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

        Comparator<Point> pointcmp;
        pointcmp= new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.f-o2.f;///重载优先级使其变为小根堆
            }
        };

        Queue<Point> queue = new PriorityQueue<Point>(pointcmp);
        int prex=1;
        int prey=1;
        int num=in.nextInt();
        int point[][]=new int[num][2];
        for(int i=0;i<num;i++){
            point[i][0]=in.nextInt()+1;
            point[i][1]=in.nextInt()+1;//因为围了一圈的0，所以需要所有坐标加一
        }
        int sum=0;
        for(int i=0;i<num;i++){
            int targetx=point[i][0];
            int targety=point[i][1];
            //   out.println("prex "+prex+"prey "+prey+"targetx "+targetx+"targety "+targety);
            int result=dis(queue,prex,prey,targetx,targety,All,n,m);
            //   out.println(result);
            if(!isfind){
                sum=-1;
                break;
            }
            prex=targetx;
            prey=targety;
            // out.println("prex "+prex+"prey "+prey+"result "+result);//+"targetx "+targetx+"targety "+targety);
            sum+=result;
            //     out.println(diss);
            isfind=false;
            queue.clear();
        }
        out.println(sum);
        out.close();
    }
    static boolean isfind=false;
    public static int dis(Queue<Point> queue,int prex,int prey,int targetx,int targety,int All[][],int n,int m){
        boolean close[][]=new boolean[n+2][m+2];
        queue.add(new Point(0,prex,prey,0));
        int result=0;
        while(!queue.isEmpty()){
            Point temp=queue.poll();
            int Tempx=temp.x;
            int Tempy=temp.y;
            close[Tempx][Tempy]=true;
            if(Tempx==targetx&&(Tempy+1)==targety&&(All[Tempx][Tempy+1]==1)){//到达终点
                int hv = Math.abs(Tempx - targetx) + Math.abs(Tempy - targety);
                int gtemp = temp.g + 1;
                int ftemp = gtemp + hv;
                result=gtemp;
                // out.println("here");
                isfind=true;
                break;//找到了最终的值
            }
            else if((All[Tempx][Tempy+1]==1)&&!close[Tempx][Tempy+1]){//不是不可到达点且不是close里的点
                //   out.println("po");
                int hv = Math.abs(Tempx - targetx) + Math.abs(Tempy - targety);
                int gtemp = temp.g + 1;
                int ftemp = gtemp + hv;
                boolean isinopen=false;
                for (Point it : queue) {
                    if((it.x==Tempx)&&(it.y==(Tempy+1))){//说明在open里
                        isinopen=true;
                        if(it.g>gtemp){
                            it.g=gtemp;
                            it.f=ftemp;
                        }
                        break;//找到了就不用继续遍历了
                    }
                }
                if(!isinopen){////既不是open，也不是close
                    queue.add(new Point(ftemp,Tempx,Tempy+1,gtemp));
                }
            }
            if(Tempx==targetx&&(Tempy-1)==targety&&(All[Tempx][Tempy-1]==1)){
                int hv = Math.abs(Tempx - targetx) + Math.abs(Tempy - targety);
                int gtemp = temp.g + 1;
                int ftemp = gtemp + hv;
                result=gtemp;
                // out.println("here");
                isfind=true;
                break;//找到了最终的值
            }
            else if((All[Tempx][Tempy-1]==1)&&!close[Tempx][Tempy-1]){//不是不可到达点且不是close里的点
                //   out.println("po");
                int hv = Math.abs(Tempx - targetx) + Math.abs(Tempy - targety);
                int gtemp = temp.g + 1;
                int ftemp = gtemp + hv;
                boolean isinopen=false;
                for (Point it : queue) {
                    if((it.x==Tempx)&&(it.y==(Tempy-1))){//说明在open里
                        isinopen=true;
                        if(it.g>gtemp){
                            it.g=gtemp;
                            it.f=ftemp;
                        }
                        break;//找到了就不用继续遍历了
                    }
                }
                if(!isinopen){////既不是open，也不是close
                    queue.add(new Point(ftemp,Tempx,Tempy-1,gtemp));
                }
            }
            if((Tempx+1)==targetx&&(Tempy)==targety&&(All[Tempx+1][Tempy]==1)){
                int hv = Math.abs(Tempx - targetx) + Math.abs(Tempy - targety);
                int gtemp = temp.g + 1;
                int ftemp = gtemp + hv;
                result=gtemp;
                // out.println("here");
                isfind=true;
                break;//找到了最终的值
            }
            else if((All[Tempx+1][Tempy]==1)&&!close[Tempx+1][Tempy]){//不是不可到达点且不是close里的点
                //   out.println("po");
                int hv = Math.abs(Tempx - targetx) + Math.abs(Tempy - targety);
                int gtemp = temp.g + 1;
                int ftemp = gtemp + hv;
                boolean isinopen=false;
                for (Point it : queue) {
                    if((it.x==Tempx+1)&&(it.y==(Tempy))){//说明在open里
                        isinopen=true;
                        if(it.g>gtemp){
                            it.g=gtemp;
                            it.f=ftemp;
                        }
                        break;//找到了就不用继续遍历了
                    }
                }
                if(!isinopen){////既不是open，也不是close
                    queue.add(new Point(ftemp,Tempx+1,Tempy,gtemp));
                }
            }
            if((Tempx-1)==targetx&&(Tempy)==targety&&(All[Tempx-1][Tempy]==1)){
                int hv = Math.abs(Tempx - targetx) + Math.abs(Tempy - targety);
                int gtemp = temp.g + 1;
                int ftemp = gtemp + hv;
                result=gtemp;
                // out.println("here");
                isfind=true;
                break;//找到了最终的值
            }
            else if((All[Tempx-1][Tempy]==1)&&!close[Tempx-1][Tempy]){//不是不可到达点且不是close里的点
                //   out.println("po");
                int hv = Math.abs(Tempx - targetx) + Math.abs(Tempy - targety);
                int gtemp = temp.g + 1;
                int ftemp = gtemp + hv;
                boolean isinopen=false;
                for (Point it : queue) {
                    if((it.x==Tempx-1)&&(it.y==(Tempy))){//说明在open里
                        isinopen=true;
                        if(it.g>gtemp){
                            it.g=gtemp;
                            it.f=ftemp;
                        }
                        break;//找到了就不用继续遍历了
                    }
                }
                if(!isinopen){////既不是open，也不是close
                    queue.add(new Point(ftemp,Tempx-1,Tempy,gtemp));
                }
            }
        }
        return result;
    }

}
