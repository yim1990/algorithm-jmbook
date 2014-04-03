import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


public class Main {
	private static Map<Integer, ArrayList<Integer>> adj=new HashMap<Integer, ArrayList<Integer>>();
	private static Set<Integer> visited;
	private static int denyCount=0;
	private static int recursionCount=0;

	public static boolean isCycle(Integer here) {
		recursionCount++;
		ArrayList<Integer> pAdj=adj.get(here);
		visited.add(here);
		int len=pAdj.size();
		for(int i=0; i<len; i++) {
			Integer there=pAdj.get(i);
			if(!visited.contains(there)) {
				pAdj.remove(there);
				adj.get(there).remove(here);
				boolean ret=isCycle(there);
				adj.get(there).add(here);
				pAdj.add(i, there);

				if(ret) {
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		String line;
		while(!(line=br.readLine()).equals("-1")) {
			visited=new HashSet<Integer>();
			recursionCount=0;

			StringTokenizer st=new StringTokenizer(line);
			Integer a=Integer.parseInt(st.nextToken());
			Integer b=Integer.parseInt(st.nextToken());

			ArrayList<Integer> aAdj=adj.get(a);
			ArrayList<Integer> bAdj=adj.get(b);
			if(aAdj==null) {
				aAdj=new ArrayList<Integer>();
				aAdj.add(b);
				adj.put(a, aAdj);
			} else {
				aAdj.add(b);
			}

			if(bAdj==null) {
				bAdj=new ArrayList<Integer>();
				bAdj.add(a);
				adj.put(b, bAdj);
			} else {
				bAdj.add(a);
			}

			if(isCycle(a)) {//아무데서나 시작해도 똑같다
				denyCount++;
				adj.get(a).remove(b);
				adj.get(b).remove(a);
			}
			if(recursionCount>100) {
				System.out.println(recursionCount);
			}
		}

		System.out.println(denyCount);
	}
}
