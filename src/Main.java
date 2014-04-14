import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	public static int n;
	public static int adj[][];
	public static int bus[];
	public static int time;
	public static int bicycleNum;

	public static int search(int path[], int left, int right) {
		if(left==right) {
			for(int i=0; i<path.length; i++) {
				System.out.print(path[i]+ " ");
			}
			System.out.println(" ");
			return 0;
		} else {
			int ret=987654321;
			for(int i=left; i<=right; i++) {
				int temp=path[left];
				path[left]=path[i];
				path[i]=temp;
				ret=Math.min(ret, search(path, left+1, right));
				temp=path[left];
				path[left]=path[i];
				path[i]=temp;
			}
			return ret;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		int testcase=Integer.parseInt(br.readLine());

		while(testcase-- > 0) {
			n=Integer.parseInt(br.readLine());
			adj=new int[n][n];
			bus=new int[n];

			StringTokenizer st;

			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					adj[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				bus[i]=Integer.parseInt(st.nextToken())-1;
			}

			st=new StringTokenizer(br.readLine());
			time=Integer.parseInt(st.nextToken());
			bicycleNum=Integer.parseInt(st.nextToken());

			int[] path=new int[n];
			for(int i=0; i<n; i++) {
				path[i]=i;
			}

			search(path, 1, n-1);

		}
	}
}
