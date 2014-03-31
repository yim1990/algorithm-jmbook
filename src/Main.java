import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;


public class Main {
	//도시수는 2~1000개, 길의 수는 1~50000개라.. 최대 100만 개*4 byte=4백만 byte=4 MB
	static TreeSet<Integer> cities=null;
	static Map<Integer, ArrayList<Integer>> edges=null;

	public static void shortestPath(int v, int[][] parent) {
		int len=parent.length;
		for(int i=1; i<=len; i++) {
			if(parent[v][i] == 1) {
				cities.add(v);
				break;
			} else if(parent[v][i] != 0) {
				cities.add(v);
				shortestPath(v, parent);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		int testcase=Integer.parseInt(br.readLine());

		while(testcase-- > 0) {
			cities=new TreeSet<Integer>();
			edges=new HashMap<Integer, ArrayList<Integer>>();

			String[] numCityAndRoads=br.readLine().split(" ");
			int numCity=Integer.parseInt(numCityAndRoads[0]);
			int numRoad=Integer.parseInt(numCityAndRoads[1]);

			for(int i=0; i<numRoad; i++) {
				String[] edgeOne=br.readLine().split(" ");
				int start=Integer.parseInt(edgeOne[0]);
				int end=Integer.parseInt(edgeOne[1]);

				if(edges.containsKey(start)) {
					edges.get(start).add(end);
				} else {
					ArrayList<Integer> v=new ArrayList<Integer>();
					v.add(end);
					edges.put(start, v);
				}
			}

			//TODO : Map<Integer, Integer>, Map<Integer, ArrayList<Integer>> 로 변경 50000개를 다 뒤질 수 없다
			int[] distance=new int[edges.size()+1];
			int[][] parent=new int[edges.size()+1][edges.size()+1];

			for(int i=1; i<edges.size()+1; i++) {
				distance[i]=-1;
			}

			LinkedList<Integer> queue=new LinkedList<Integer>();

			distance[1]=0;
			parent[1][0]=1;

			queue.push(1);

			while(!queue.isEmpty()) {
				int here=queue.poll();
				ArrayList<Integer> edge=edges.get(here);
				for(Integer there : edge) {
					if(distance[there]==-1) { //미방문 노드
						queue.push(there);
						distance[there]=distance[here]+1;
						parent[there][here]=here;
					} else if(distance[there] == distance[here]+1) { //방문했으나 겹치는 노드
						parent[there][here]=here;
					}
				}
			}

			shortestPath(numCity, parent);
			StringBuffer sb=new StringBuffer();
			for(Integer city : cities) {
				sb.append(city);
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length()-1);

			System.out.println(sb);
		}
	}
}