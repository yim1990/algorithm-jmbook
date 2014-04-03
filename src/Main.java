import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
	private static Map<Integer, Integer> counts=new HashMap<Integer, Integer>(); // Node 번호 : Count
	private static Map<Integer, ArrayList<Integer>> countAndNodes=new HashMap<Integer, ArrayList<Integer>>(); // Count : [Node 번호들]
	private static int denyCount=0;
	private static int counter=0;

	public static boolean isCycle(Integer here1, Integer here2) {
		Integer here1Count=counts.get(here1);
		Integer here2Count=counts.get(here2);

		if(here1Count==null && here2Count==null) {
			counter++;
			counts.put(here1, counter);
			counts.put(here2, counter);

			//caching
			ArrayList<Integer> cn=new ArrayList<Integer>();
			cn.add(here1);
			cn.add(here2);
			countAndNodes.put(counter, cn);
		} else if(here1Count==null && here2Count!=null) {
			counts.put(here1, counter);
			countAndNodes.get(counter).add(here1);
		} else if(here1Count!=null && here2Count==null) {
			counts.put(here2, counter);
			countAndNodes.get(counter).add(here2);
		} else if(here1Count > here2Count) {
			ArrayList<Integer> nodes=countAndNodes.remove(here2Count);
			for(Integer node : nodes) {
				counts.put(node, here1Count);
			}

			countAndNodes.get(here1Count).addAll(nodes);
		} else if(here1Count < here2Count) {
			ArrayList<Integer> nodes=countAndNodes.remove(here1Count);
			for(Integer node : nodes) {
				counts.put(node, here2Count);
			}

			countAndNodes.get(here2Count).addAll(nodes);
		} else {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		long start=System.currentTimeMillis();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		String line;
		while(!(line=br.readLine()).equals("-1")) {
			StringTokenizer st=new StringTokenizer(line);
			Integer a=Integer.parseInt(st.nextToken());
			Integer b=Integer.parseInt(st.nextToken());

			if(isCycle(a, b)) {//아무데서나 시작해도 똑같다
				denyCount++;
			}
		}

		System.out.println(denyCount);

		System.out.println("Time : "+(System.currentTimeMillis()-start));
	}
}