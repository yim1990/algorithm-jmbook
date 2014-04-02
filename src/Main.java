import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
	private static Map<Integer, Map<Integer, Integer>> adj=new HashMap<Integer, Map<Integer, Integer>>();
	public static void checkEuler(int here) {
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		int denyCount=0;
		while(!(line=br.readLine()).equals("-1")) {
			StringTokenizer st=new StringTokenizer(line);
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			Map<Integer, Integer> aAdj=adj.get(a);
			Map<Integer, Integer> bAdj=adj.get(b);
			if(aAdj==null) {
				aAdj=new HashMap<Integer, Integer>();
				aAdj.put(b, 1);
				adj.put(a, aAdj);
			} else {
				aAdj.put(b, (aAdj.get(b)==null) ? 1 : aAdj.get(b)+1);
			}
			
			if(bAdj==null) {
				bAdj=new HashMap<Integer, Integer>();
				bAdj.put(a, 1);
				adj.put(b, bAdj);
			} else {
				bAdj.put(a, (bAdj.get(a)==null) ? 1 : bAdj.get(a)+1);
			}
		}
		System.out.println(denyCount);
	}
}
