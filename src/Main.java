import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class Main {
	//도시수는 2~1000개, 길의 수는 1~50000개라.. 최대 100만 개*4 byte=4백만 byte=4 MB
	static TreeSet<Integer> cities=null;
	static int[][] edges=null;
	static int[] edgesLen=null;
	static int[][] parent=null;
	static int[] parentLen=null;

	public static void shortestPath(int v) {
		cities.add(v);
		int[] p=parent[v];
		int sizeOfP=p.length;
		for(int i=0; i<sizeOfP; i++) {
			if(p[i]==1) {
				return;
			} else if(p[i]!=0) {
				shortestPath(p[i]);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testcase=Integer.parseInt(br.readLine());

		while(testcase-- > 0) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int numCity=Integer.parseInt(st.nextToken());
			int numRoad=Integer.parseInt(st.nextToken());

			cities=new TreeSet<Integer>();
			edges=new int[numCity+1][numCity+1];
			edgesLen=new int[numCity+1];
			parent=new int[numCity+1][numCity+1];
			parentLen=new int[numCity+1];

			for(int i=0; i<numRoad; i++) {
				st=new StringTokenizer(br.readLine());
				int start=Integer.parseInt(st.nextToken());
				int end=Integer.parseInt(st.nextToken());

				edges[start][edgesLen[start]++]=end;
			}
			//long current=System.currentTimeMillis();

			int[] distance=new int[numCity+1];

			LinkedList<Integer> queue=new LinkedList<Integer>();

			distance[1]=0;
			parent[1][parentLen[1]++]=1;

			queue.push(1);

			while(!queue.isEmpty()) {
				int here=queue.poll();

				int[] edge=edges[here];
				int edgeLen=edgesLen[here];

				for(int j=0; j<edgeLen; j++) {
					int there=edge[j];
					if(distance[there]==0 && there!=1) { //미방문 노드
						queue.add(there);
						distance[there]=distance[here]+1;
						parent[there][parentLen[there]++]=here;
					} else if(distance[there] == distance[here]+1) { //방문했으나 겹치는 노드
						parent[there][parentLen[there]++]=here;
					}
				}
			}

			shortestPath(numCity);
			cities.add(1);
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