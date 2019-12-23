import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class InterstellarTransportation {
	static int INFINITY = 10000000;
	
	public static int shortestRoute(int stops, int[][] routes, int start, int end){
		int[] dist = new int[stops + routes.length];
		boolean[] visited = new boolean[stops + routes.length];
		ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < stops + routes.length; i++) {
			edges.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < routes.length; i++) {
			for (int j = 0; j < routes[i].length; j++) {
				int t = routes[i][j];
				edges.get(t).add(stops + i);
				edges.get(stops + i).add(t);
			}
		}
		for (int i = 0; i < dist.length; i++) {
			dist[i] = INFINITY;
			visited[i] = false;
		}
		dist[start] = 0;
		visited[start] = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		while (q.peek() != null) {
			int x = q.poll();
			for (int i = 0; i < edges.get(x).size(); i++) {
				int t = edges.get(x).get(i);
				if (!visited[t]) {
					q.add(t);
					dist[t] = dist[x] + 1;
					visited[t] = true;
				}
			}
		}
		if (start == end) return 1;
		if (dist[end] == INFINITY) return 0;
		return dist[end] / 2;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader b = new BufferedReader(new FileReader("InterstellarTransportationIN.txt"));

		while(b.ready()){
			String[] data = b.readLine().split(" ");
			int N = Integer.parseInt(data[0]);
			int M = Integer.parseInt(data[1]);
			int start = Integer.parseInt(data[2]);
			int end = Integer.parseInt(data[3]);
			int[][] routes = new int[N][];
			for (int i = 0; i < N; i++) {
				data = b.readLine().split(" ");
				routes[i] = new int[data.length];
				for (int j = 0; j < data.length; j++) {
					routes[i][j] = Integer.parseInt(data[j]);
				}
			}
			
			System.out.println(shortestRoute(M, routes, start, end) - 1);
		}
				
	}

}
