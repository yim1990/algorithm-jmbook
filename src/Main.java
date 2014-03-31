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

	public static void shortestPath(int v, Map<Integer, ArrayList<Integer>> parent) {
		cities.add(v);
		ArrayList<Integer> parentEdges=parent.get(v);
		if(parentEdges!=null) {
			for(Integer edge : parentEdges) {
				if(edge == 1) {
					return;
				} else {
					shortestPath(edge, parent);
				}
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
			long current=System.currentTimeMillis();

			Map<Integer, Integer> distance=new HashMap<Integer,Integer>();
			Map<Integer, ArrayList<Integer>> parent=new HashMap<Integer, ArrayList<Integer>>();

			LinkedList<Integer> queue=new LinkedList<Integer>();

			distance.put(1, 0);
			ArrayList<Integer> startParent=new ArrayList<Integer>();
			startParent.add(1);
			parent.put(1, startParent);

			queue.push(1);

			while(!queue.isEmpty()) {
				int here=queue.poll();
				ArrayList<Integer> edge=edges.get(here);
				if(edge!=null) {
					for(Integer there : edge) {
						if(!distance.containsKey(there)) { //미방문 노드
							queue.add(there);
							distance.put(there, distance.get(here)+1);
							if(parent.containsKey(there)) {
								parent.get(there).add(here);
							} else {
								ArrayList<Integer> p=new ArrayList<Integer>();
								p.add(here);
								parent.put(there, p);
							}
						} else if(distance.get(there) == distance.get(here)+1) { //방문했으나 겹치는 노드
							parent.get(there).add(here);
						}
					}
				}
			}

			shortestPath(numCity, parent);
			cities.add(1);
			StringBuffer sb=new StringBuffer();
			for(Integer city : cities) {
				sb.append(city);
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length()-1);

			System.out.println(sb);
		
			System.out.println((System.currentTimeMillis()-current));
		}
	}
	
	public static void printTest() {
		for(int i=1; i<1000; i++) {
			System.out.println(i+" "+(i+1));
		}
	}
}