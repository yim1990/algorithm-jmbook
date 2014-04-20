
public class Main {
	public static int cache[][]=new int[30][30]; 
	public static int bino(int n, int r) {
		if(r==0 || n==r) {
			return 1;
		}
		if(cache[n][r] != 0) {
			return cache[n][r];
		}
		cache[n][r]=bino(n-1, r-1) + bino(n-1, r);
		return cache[n][r];
	}

	public static void main(String[] args) {
		System.out.println(bino(25,12));

	}
}
