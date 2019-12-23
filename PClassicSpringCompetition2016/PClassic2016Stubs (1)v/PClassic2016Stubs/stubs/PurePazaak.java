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

  //Fill out the body of this method
  public static int score(int[] cards) {
    return 0;
  }


  
}
