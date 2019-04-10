
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class lab41{
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {
        public static class bian{
            boolean haveselect;
            int node1;
            int node2;
            int weight;

            public bian(boolean haveselect,int node1,int node2,int weight) {
                this.haveselect=haveselect;
                this.node1=node1;
                this.node2=node2;
                this.weight=weight;
            }
        }
        public void solve(Reader in, PrintWriter out) throws IOException {
            int a = in.nextInt();
            for (int i = 0; i < a; i++) {
                int n = in.nextInt();
                int m1 = in.nextInt();
                int m2=in.nextInt();
                int m=m1+m2;
                bian ed[]=new bian[m+1];
                int count2=0;
                boolean havecome[]=new boolean[n+1];
                //  boolean node[][]=new boolean[n+1][n+1];
                ArrayList<ArrayList>s=new ArrayList<>();
                for(int j=0;j<n+1;j++){
                    s.add(new ArrayList());
                }
                for(int j=0;j<m+1;j++){
                    ed[j]=new bian(false,0,0,0);
                }
                for(int j=1;j<m+1;j++){
                    int n1=in.nextInt();
                    int n2=in.nextInt();
                    int weight=in.nextInt();
                    ed[j].node1=n1;
                    ed[j].node2=n2;
                    if(!havecome[n1]){
                        count2++;
                        havecome[n1]=true;
                    }
                    if(!havecome[n2]){
                        count2++;
                        havecome[n2]=true;
                    }
                    ed[j].weight=weight;
                }
//                for(int j=1;j<m+1;j++){
//                    out.println(ed[j].node1+"-"+ed[j].node2+"-"+ed[j].weight);
//                }
//                out.println("---");
                bian temp=null;
                if(count2==n){
                    for(int j=1;j<m+1;j++){
                        for(int k=j+1;k<m+1;k++){
                            if(ed[j].weight>ed[k].weight){
                                temp=ed[k];
                                ed[k]=ed[j];
                                ed[j]=temp;
                            }
                        }
                    }
                    //   boolean havecomedenode[]=new boolean[n+1];
                    //int count=0;
                    int parent[]=new int[n+1];
                    for(int j=1;j<m+1;j++){
                        if(parent[ed[j].node1]==0&&parent[ed[j].node2]==0){
                            parent[ed[j].node2]=ed[j].node1;
                            parent[ed[j].node1]=ed[j].node1;
                            ed[j].haveselect=true;
                        }
                        else if(parent[ed[j].node1]==0&&parent[ed[j].node2]!=0){
                            parent[ed[j].node1]=parent[ed[j].node2];
                            ed[j].haveselect=true;
                        }
                        else  if(parent[ed[j].node1]!=0&&parent[ed[j].node2]==0){
                            parent[ed[j].node2]=parent[ed[j].node1];
                            ed[j].haveselect=true;
                        }
                        else if(parent[ed[j].node1]!=0&&parent[ed[j].node2]!=0){
                            if(parent[ed[j].node1]==parent[ed[j].node2]){
                                continue;
                            }
                            else {
                                ed[j].haveselect=true;
                                int tem=parent[ed[j].node2];
                                for(int b=0;b<parent.length;b++){
                                    if(parent[b]==tem){//把这边的parent全都改掉
                                        parent[b]=parent[ed[j].node1];
                                    }
                                }
                                parent[ed[j].node2]=parent[ed[j].node1];
                            }
                        }
                    }
//                for(int f=1;f<n+1;f++){
//                    out.println(parent[f]);
//                }
//                for(int j=1;j<m+1;j++){
//                    out.println(ed[j].node1+"-"+ed[j].node2+"-"+ed[j].weight);
//                }
                    long sum=0;
                    for(int j=1;j<m+1;j++){
                        if(ed[j].haveselect){
                            //             out.println(j+"--"+ed[j].weight);
                            sum+=ed[j].weight;
                        }
                    }
                    out.println(sum);}
                else {
                    out.println("-1");
                }
            }
        }
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

}