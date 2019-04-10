import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

public class exercise4
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
        String all=in.next();
//        String zero="0";
//        while(all.length()%4!=0){//说明不是4的倍数
//            all=zero+all;
//        }
        int beixuan1=0;
        int beixuan2=0;
      //  out.println(all.length());
        char All[]=all.toCharArray();
     //   out.println(All[all.length()-1]);
        int start=0;
        for(int j=0;j<all.length();j++){
            if(All[start]=='0'){
                start++;
            }
            else
                break;
          //  out.print(All[j]);
        }

        int count=0;
        for(int i=start;i<all.length();i++){
      //      out.print(All[i]);
            if(All[i]=='1'){
                count++;
            }
        }
     //    out.println();
        int the1number=count;
        int the0number=all.length()-start-count;
    //    out.println("1: "+the1number+"\n0: "+the0number);
        if(the0number>=the1number){
     //       out.println(the1number);
            beixuan1=the1number;
        }
        else {
            int caozuo=the0number;
            int point=all.length()-1;
           while(All[point]=='0'){
               point--;
               caozuo--;
           }
    //       out.println(2+caozuo);
           beixuan1=2+caozuo;
        }

        int poi=all.length()-1;
        for(int j=all.length()-1;j>=0;j--){
            if(All[poi]=='1'){
               poi--;
            }
        }
        int start1=0;
        for(int j=0;j<=poi;j++){
            if(All[start1]=='0'){
                start1++;
            }
            else
                break;
            //  out.print(All[j]);
        }

        int count1=0;
      //  out.println("poi"+poi);
        for(int i=start1;i<=poi;i++){
      //      out.print(All[i]);
            if(All[i]=='1'){
                count1++;
            }
        }
       // out.println();
        int the1number1=count1;
        int the0number1=poi+1-start1-count1;
   //     out.println("1: "+the1number1+"\n0: "+the0number1);
        if(the0number1>=the1number1){
           // out.println(the1number1);
            beixuan2=the1number1+2;
        }
        else {
            int caozuo1=the0number1;
            int point1=all.length()-1;
            while(All[point1]=='0'){
                point1--;
                caozuo1--;
            }
        //    out.println(caozuo1+"---");
       //     out.println(2+caozuo1);
            beixuan2=2+caozuo1+2;
        }

        if(beixuan1>beixuan2){
            out.println(beixuan2);
        }
        else out.println(beixuan1);



        out.close();
    }
}
