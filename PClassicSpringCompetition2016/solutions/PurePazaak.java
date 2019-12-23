import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;


public class PurePazaak {
  // Before submitting, make sure the main method hasn't been changed!
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("PurePazaakIN.txt"));
    while (br.ready()) {
      String[] data = br.readLine().split(" ");
      int n = Integer.parseInt(data[0]);
      int[] cards = new int[n];
      for (int i = 0; i < n; i++) {
        cards[i] = Integer.parseInt(data[i+1]);
      }
      System.out.println(score(cards));
    }
    br.close();
  }

  public static int score(int[] cards) {
    int N = cards.length;
    int[][] T = new int[N][N];
    for (int i = 0; i < N-1; i++) {
      T[i][i+1] = Math.max(cards[i], cards[i+1]);
    }
    for (int k = 3; k <= N-1; k += 2) {
      for (int i = 0; i + k < N; i++) {
        T[i][i+k] = Math.max(cards[i] + Math.min(T[i+2][i+k], T[i+1][i+k-1]),
                             cards[i+k] + Math.min(T[i][i+k-2], T[i+1][i+k-1]));
      }
    }
    return T[0][N-1];
  }


  
}
