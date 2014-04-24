import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
	public static Map<Integer, List<Integer>> adj;
	public static int numLight;
	public static int visitCount;
	public static boolean[] visited;

	public static void visit(int j) {
		if(!visited[j]) {
			visited[j]=true;
			visitCount++;
		}

		List<Integer> list=adj.get(j);

		if(list==null) {
			return;
		}

		for(Integer k : list) {
			if(!visited[k]) {
				visited[k]=true;
				if(j!=k) {
					visit(k);
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		Integer testcases=Integer.parseInt(br.readLine());
		for(int i=1; i<=testcases; i++) {
			StringTokenizer t=new StringTokenizer(br.readLine());
			numLight=Integer.parseInt(t.nextToken());
			int inputNum=Integer.parseInt(t.nextToken());

			adj=new HashMap<Integer, List<Integer>>();
			visited=new boolean[numLight+1];
			visitCount=0;

			while(inputNum-- > 0) {
				t=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(t.nextToken());
				int b=Integer.parseInt(t.nextToken());

				List<Integer> list=adj.get(a);
				if(list==null) {
					list=new ArrayList<Integer>();
					list.add(b);
					adj.put(a, list);
				} else {
					list.add(b);
				}
			}

			for(Integer integer :adj.keySet()) {
				if(!visited[integer]) {
					visit(integer);
				}
			}

			System.out.println("Case "+i+": "+visitCount);
			br.readLine();
		}
	}
}
