import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BlackMarket {

	public static boolean freeMoney(double[][] prices){
		double[][] logPrices = new double[prices.length][prices.length];
		
		for(int i = 0; i < prices.length; i++){
			for(int j = 0; j < prices[i].length; j++){
				logPrices[i][j] = Math.log(prices[i][j])/Math.log(2);
			}
		}
		
		double[] dist = new double[prices.length];
		
		for(int i = 1; i < dist.length; i ++){
			dist[i] = Double.MAX_VALUE;
		}
		
		for(int k = 0; k < dist.length - 1; k++){
			for(int i = 0; i < prices.length; i++){
				for(int j = 0; j < prices[i].length; j++){
					if(dist[i] + logPrices[i][j] < dist[j]){
						dist[j] = dist[i] + logPrices[i][j];
					}
				}
			}
		}
		
		for(int i = 0; i < prices.length; i++){
			for(int j = 0; j < prices[i].length; j++){
				if(dist[i] + logPrices[i][j] < dist[j]){
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader b = new BufferedReader(new FileReader("BlackMarketIN.txt"));

		while(b.ready()){
			String in = b.readLine();
			String[] inArr = in.split(",");
			double[][] prices = new double[inArr.length][inArr.length];
			
			for (int i = 0; i < inArr.length; i++){
				String[] row = inArr[i].split(" ");
				for(int j = 0; j < inArr.length; j++){
					prices[i][j] = Double.parseDouble(row[j].trim());
				}
			}
			System.out.println(freeMoney(prices));

		}
		
	}

}
