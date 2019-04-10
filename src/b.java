import java.io.*;


public class b{
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

    public static void main(String[] args) throws IOException {
        InputReader in= new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);


        out.close();
    }

}

