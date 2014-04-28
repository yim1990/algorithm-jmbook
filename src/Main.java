import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);

		long num=sc.nextLong();
		long i,result;

		for(i=0; i<num; i++) {
			long target=sc.nextLong();
			target=(target<0) ? -target : target;

			long targetOdd=target%2;

			if(target==0 || target==2) {
				result=3;
			} else {
				for(result=1; result<target; result++) { //TODO : Binary Search로 변경가능
					long resultSum=(result*(result+1))/2;
					if(resultSum%2!=targetOdd) { //Input이 홀이면, Sum도 홀. Input이 짝이면, Sum도 짝.
						continue;
					}

					if(resultSum >= target) {
						break;
					}
				}
			}
			System.out.println(result);
			if(i!=num-1) {
				System.out.println();
			}
		}
	}
}
