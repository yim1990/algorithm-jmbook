import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
	public static Map<Integer, List<Integer>> adj;
	public static int numLight;

	public static Map<Integer, Integer> sccId;
	public static Map<Integer, Integer> discovered;
	public static Map<Integer, Integer> finished;
	public static LinkedList<Integer> st;
	public static int sccCounter;
	public static int vertexCounter;

	public static int scc(int here) {
		int ret=vertexCounter++;
		discovered.put(here, ret);

		st.push(here);

		List<Integer> list=adj.get(here);
		if(list!=null) {
			for(Integer there : list) {
				if(!discovered.containsKey(there)) {
					ret=Math.min(ret,  scc(there));
				} else if(discovered.containsKey(there) && discovered.get(there) < discovered.get(here) && !sccId.containsKey(there)) {
					ret=Math.min(ret,  discovered.get(there));
				}
			}
		}

		if(ret == discovered.get(here)) {
			while(true) {
				int t=st.pop();
				sccId.put(t, sccCounter);
				if(t==here) {
					break;
				}
			}
			++sccCounter;
		}

		finished.put(here, 1);

		return ret;
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		Integer testcases=Integer.parseInt(br.readLine());
		for(int i=1; i<=testcases; i++) {
			StringTokenizer t=new StringTokenizer(br.readLine());
			numLight=Integer.parseInt(t.nextToken());
			int inputNum=Integer.parseInt(t.nextToken());

			adj=new HashMap<Integer, List<Integer>>();

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

			sccId=new HashMap<Integer, Integer>(adj.size());
			discovered=new HashMap<Integer, Integer>(adj.size());
			finished=new HashMap<Integer, Integer>(adj.size());
			st=new LinkedList<Integer>();
			sccCounter=0;
			vertexCounter=0;

			for(Integer j : adj.keySet()) {
				if(!discovered.containsKey(j)) {
					scc(j);
				}
			}

			System.out.println("Case "+i+": "+sccCounter);
			br.readLine();
		}
	}
}
