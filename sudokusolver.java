import java.io.*;
import java.util.*;

public class sudokusolver {

    static PrintWriter pw = new PrintWriter(System.out);
    static int n;
    static int[][] arr;


    static boolean isSafe(int row, int col, int num) {

        for (int d = 0; d < n; d++) {
            if (arr[row][d] == num)
                return false;
        }

        for (int r = 0; r < n; r++) {
            if (arr[r][col] == num)
                return false;
        }
        int sqrt = (int) Math.sqrt(n);
        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;

        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart; d < boxColStart + sqrt; d++) {
                if (arr[r][d] == num)
                    return false;
            }
        }
        return true;
    }


    static boolean solve() {

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (arr[i][j] == 0) {

                    for (int k = 1; k <= 9; k++) {

                        if (isSafe(i, j, k)) {
                            arr[i][j] = k;

                            if (solve())
                                return true;

                            arr[i][j] = 0;
                        }

                    }
                    return false;
                }

            }

        }
        return true;
    }

    static void print2dgrid(int arr[][]){
        for (int i = 0; i < n; i++) {

            if (i % 3 == 0 && i != 0) {
                pw.println("---------------------");
            }

            for (int j = 0; j < n; j++) {
                if (j % 3 == 0 && j != 0)
                    pw.print("| ");

                if (j == 8)
                    pw.println(arr[i][j]);
                else
                    pw.print(arr[i][j] + " ");

            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scan.nextInt();
            }
        }

        solve();
        print2dgrid(arr);

        pw.close();
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(String file) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(file));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }
}
/*
test case 1
9
5 3 0 0 7 0 0 0 0
6 0 0 1 9 5 0 0 0
0 9 8 0 0 0 0 6 0
8 0 0 0 6 0 0 0 3
4 0 0 8 0 3 0 0 1
7 0 0 0 2 0 0 0 6
0 6 0 0 0 0 2 8 0
0 0 0 4 1 9 0 0 5
0 0 0 0 8 0 0 7 9
 */