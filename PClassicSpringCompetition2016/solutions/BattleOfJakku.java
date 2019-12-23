import java.util.*;
import java.io.*;
class BattleOfJakku {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new FileReader("BattleOfJakkuIN.txt"));
    while (sc.hasNext()) {
      String num = sc.nextLine();
      String[] num1 = sc.nextLine().split(" ");
      String[] num2 = sc.nextLine().split(" ");
      String op = sc.nextLine();
      String ans = jakku(Integer.parseInt(num), num1, num2, op.charAt(0));
      System.out.println(ans);
    }
  }
  
  public static String jakku(int N, String[] num1, String[] num2, char op) {
      int[] c = new int[N];
      for (int i = 0; i < N; i++) {
        if (op == '&') {
          c[i] = Integer.parseInt(num1[i]) & Integer.parseInt(num2[i]);
        }
        if (op == '|') {
          c[i] = Integer.parseInt(num1[i]) | Integer.parseInt(num2[i]);
        }
        if (op == '^') {
          c[i] = Integer.parseInt(num1[i]) ^ Integer.parseInt(num2[i]);
        }
      }
      String ans = "";
      for (int i = 0; i < N; i++)
        ans += c[i] + " ";
      return ans.substring(0, ans.length() - 1);
  }
  
}
