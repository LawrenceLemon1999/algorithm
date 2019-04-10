import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.*;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

public class lab1
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
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
       int num=in.nextInt();//SA、ST人数
       String[]ST=new String[num+1];
       HashMap<String,Integer> SAprenm = new HashMap<>();
       HashMap<String,Integer> STprenm = new HashMap<>();
       int [][]reverselist=new int[num+1][num+1];
       for(int i=1;i<=num;i++){
           String temp=in.next();
           SAprenm.put(temp,i);
    }
        for(int i=1;i<=num;i++){
            String temp=in.next();
            ST[i]=temp;
            STprenm.put(temp,i);
        }
        int [][]saprelist=new int[num+1][num+1];
        int [][]stprelist=new int[num+1][num+1];
        for(int i=1;i<=num;i++){
            for(int j=1;j<=num;j++){
                String te=in.next();//把学生名字录下来，用STprenm来找到这个名字是第几个学生
                saprelist[i][j]= STprenm.get(te);//录入是第几个学生
            }
        }
        for(int i=1;i<=num;i++){
            for(int j=1;j<=num;j++){
                String te=in.next();//把SA名字记录下来，用SAprenm来找这是第几位SA
                stprelist[i][j]=SAprenm.get(te);//录入这个SA
                reverselist[SAprenm.get(te)][i]=j;
            }
        }
        int saengage[]=new int[num+1];
        boolean ststatus[]=new boolean[num+1];//用来存放st是否已经有sa在教了（如果没有就可以直接engage了
        int []stengage=new int[num+1];//用来存放现在的sa名字（如果有的话
        int []saprepoint=new int[num+1];//代表现在的sa追求到了第几个st
        for(int i=1;i<=num;i++){//先全部初始化为1
            saprepoint[i]=1;
        }
        //在开始循环前，所有sa都是无st状态，所以都先加入stack里面
        Stack stack = new Stack();
        for(int i=num;i>=1;i--){
            stack.push(i);
        }
        boolean isok=false;
        int number=0;
       while((!isok)) {
            if (!stack.empty()) {
                int s = (Integer) stack.pop();//表示这是第几个sa
                int st = saprelist[s][saprepoint[s]];//根据自己的指针往后找自己喜欢的st
                if (!ststatus[st]) {//如果st没被追求则二者在一起了
                    saengage[s] = st;
                    stengage[st] = s;
                    ststatus[st] = true;
                    number++;
                }
                else {
                    if (reverselist[s][st] < reverselist[stengage[st]][st]) {
                        int qianren = stengage[st];
                        saengage[qianren] = 0;
                        stengage[st] = s;
                        saengage[s] = st;
                        stack.push(qianren);
                        saprepoint[qianren]++;
                    }
                    else {
                        saprepoint[s]++;
                        stack.push(s);
                    }
                }
                if (number == num)
                    isok = true;
            }
        }
        for(int i=1;i<=num;i++){
            out.print(ST[saengage[i]]+" ");
        }
        out.close();
    }
}
