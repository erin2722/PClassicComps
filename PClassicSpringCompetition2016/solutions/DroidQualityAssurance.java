import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class DroidQualityAssurance {

	public static boolean hasShortCircuit(ArrayList<LinkedList<Integer>> pins){
		Color [] status = new Color[pins.size()];
		
		for(int i = 0; i < status.length; i++){
			status[i] = Color.WHITE;
		}
		
		for(int i = 0; i < status.length; i++){
			if (status[i] == Color.WHITE && detectCycle(i, -1, pins, status)){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean detectCycle(int i, int parent, ArrayList<LinkedList<Integer>> nodes, Color[] status){
		if (status[i] == Color.GRAY){
			return true;
		}
		
		status[i] = Color.GRAY;

		for(int neighbor : nodes.get(i)){
			if (neighbor != parent && detectCycle(neighbor, i, nodes, status)){
				return true;
			}
		}
		
		status[i] = Color.BLACK;
		return false;
		
	}
	
	public enum Color {
		WHITE, GRAY, BLACK
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader b = new BufferedReader(new FileReader("DroidQualityAssuranceIN.txt"));
		
		while(b.ready()){	
			ArrayList<LinkedList<Integer>> pins = new ArrayList<LinkedList<Integer>>();

			int numPins = Integer.parseInt(b.readLine());
			
			for(int i = 0; i < numPins; i++){
				LinkedList<Integer> connections = new LinkedList<Integer>();
				String[] connectionsInfo = b.readLine().split(" ");
				
				for(int j = 1; j < connectionsInfo.length; j++){
					connections.add(Integer.parseInt(connectionsInfo[j]));
				}
				
				pins.add(i, connections);	
			}
			
			b.readLine();
			b.readLine();
			b.readLine();
			
			System.out.println(hasShortCircuit(pins));
		}
		
		b.close();
	}
}
