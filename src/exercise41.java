import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

public class exercise41
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
        String oo="00";
        String now=all+oo;
        char All[]=now.toCharArray();
     //   out.println(All.length);
        int count=0;
       // ArrayList<Character>s=new ArrayList<>();
        int i=0;
        int count1=0;
        int count0=0;
        int sum=0;
    //    out.println(Arrays.toString(All));
   //     out.println(All.length);
       while(i<All.length){
            char temp=All[i];
            if(temp=='1'){
                //s.add(temp);
                count1++;
                i++;
            }
           else if(temp=='0'){
                if(i+1<All.length&&All[i+1]=='0'){
              while(i+1<All.length&&All[i+1]=='0'){
                  i++;
              }
              i++;
         //     out.println(count1+" "+count0+" "+i);
              sum=sum+Math.min(count1,count0+2);
              count0=0;
              count1=0;
            }
//            else if(i==All.length-1){
//                    count0
//                }
            else {
                    count0++;
                    i++;
                }
    }
    }
    out.println(sum);
        out.close();

    }
}
