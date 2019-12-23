import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ForceFluctuations {
  // Before submitting, make sure the main method hasn't been changed!
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("ForceFluctuationsIN.txt"));

    while (br.ready()) {
			int N = Integer.parseInt(br.readLine());
      String[] data = br.readLine().split(" ");
			long[] days = new long[data.length];
			for (int i = 0; i < data.length; i++) {
				days[i] = Long.parseLong(data[i]);
			}
      System.out.println(countTriples(days));
    }
    br.close();
  }

	static class Day {
		public long value;
		public int day;
		public Day(long value, int day) {
			this.value = value;
			this.day = day;
		}
	}
	public static Day[] array;
	public static Day[] tempArray;
	public static long[] frontInversions; // if you are at the front
	public static long[] backInversions; // if you are at the back

	public static void recurse(int l, int r) { // [l, m] [m+1, r]
		if (l >= r) {
			return;
		}
		int m = (l+r)/2;
		recurse(l, m);
		recurse(m+1, r);

		int lp = l;
		int rp = m+1;
		int rightCounter = 0;
		int leftCounter = m - l + 1;
		int ep = l;
		while (lp <= m || rp <= r) {
			if (rp == r+1 || (lp <= m && rp <= r && array[lp].value < array[rp].value)) {
				tempArray[ep++] = array[lp++];
				leftCounter--;
				frontInversions[tempArray[ep-1].day] += rightCounter;
			} else if (lp == m+1 || (lp <= m && rp <= r && array[lp].value > array[rp].value)) {
				tempArray[ep++] = array[rp++];
				rightCounter++;
				backInversions[tempArray[ep-1].day] += leftCounter;
			}
		}
		for (int i = l; i <= r; i++) {
			array[i] = tempArray[i];
		}
		return;
	}

  public static long countTriples(long[] days) {
		int n = days.length;
		array = new Day[n];
		tempArray = new Day[n];
		frontInversions = new long[n];
		backInversions = new long[n];
		for (int i = 0; i < n; i++) {
			array[i] = new Day(days[i], i);
		}
		recurse(0, n-1);
		long ret = 0;
		for (int i = 0; i < days.length; i++) {
			ret += frontInversions[i] * backInversions[i];
		}
		return ret;
  }
}
