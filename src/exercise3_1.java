import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.*;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

public class exercise3_1
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
        int casenumber=in.nextInt();
        for(int i=0;i<casenumber;i++) {
            int number = in.nextInt();
            TreeMap<Integer, Integer> s = new TreeMap<>();
            for (int j = 0; j < number; j++) {
                int start = in.nextInt();
                int end = in.nextInt();
                if (s.containsKey(end)) {
                    if (s.get(end) < start) {//end为key，找start晚的
                        s.put(end, start);
                    }
                } else {
                    s.put(end, start);
                }
            }
            int count=1;
            Iterator it = s.keySet().iterator();
            int preend=(Integer)it.next();
            while (it.hasNext()) {
                int t=(Integer)it.next();
                if(s.get(t)>preend){
                    preend=t;
                    count++;
                }
                //System.out.println(it.next());  //it.next()得到的是key，tm.get(key)得到obj
            }
            out.println(count);
        }







        out.close();
    }
}
