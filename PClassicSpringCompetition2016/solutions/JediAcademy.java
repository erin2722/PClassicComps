import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class JediAcademy {
	
	public static int scoreSummary(int[] scores, int g){
		Arrays.sort(scores);
		
		double[][] withness = new double[scores.length + 1][g + 1];
		
		for(int i = 1; i  <= scores.length; i++){
			withness[i][0] = Double.MAX_VALUE;
		}
		
		for(int m = 1; m <= g; m++){
			for(int i = 1; i <= scores.length; i++){
				double temp = Double.MAX_VALUE;
				
				double sum = 0.0;
				double mean = 0.0 ;
				double sumSq = 0.0;
				for(int j = m; j <= i; j++){
					
					/*
					double prevMean = mean;
					
					mean = (scores[j - 1] + (j - m) * prevMean) / (j - m + 1);
					sumSq += (scores[j - 1] - prevMean) * (scores[j - 1] - mean);
					System.out.println("SumSq: " + mean);					
					if(sumSq + withness[j-1][m-1] <= 0.0) {
						System.out.print("HERE: " + (j-1) + " " + (m-1) + " ");
						System.out.println(withness[j-1][m-1]);
					}
					*/
					
					sum = 0.0;
					sumSq = 0.0;
					
					//May or may not add the linear speedup
					for(int k = j; k <= i; k++){
						sum += scores[k - 1];
					}
					
					mean = sum / (i - j + 1);
					
					for(int k = j; k <= i; k++){
						sumSq += Math.pow(scores[k - 1] - mean, 2);
					}
					temp = Math.min(temp, sumSq + withness[j - 1][m - 1]);
				}
				withness[i][m] = temp;
			}
		}
		
		return (int) withness[scores.length][g];
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader b = new BufferedReader(new FileReader("JediAcademyIN.txt"));

		while(b.ready()){			
			String in = b.readLine();
			String[] inBreakLast = in.split("--");
			
			int last = Integer.parseInt(inBreakLast[1]);
			
			String[] inArr = inBreakLast[0].split(",");
			int[] scores = new int[inArr.length];
			
			for (int i = 0; i < inArr.length; i++){
				scores[i] = Integer.parseInt(inArr[i]);
			}
			
			System.out.println(scoreSummary(scores, last));
		}
		
		b.close();
				
	}

}
