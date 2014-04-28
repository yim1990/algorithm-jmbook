import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		/*
		 * 절차
		 * 1. input을 정렬한다.
		 * 2. 가장 앞의 2개를 리스트에서 빼고, 합을 원 목록에 정렬된 순서로 삽입한다.
		 * 3. 리스트의 length가 1개만 남을 때 까지 2를 반복한다.
		 * 4. 끝!
		 */

		Scanner sc=new Scanner(System.in);
		int i, sum=0, cost=0;
		int int_num=0;
		PriorityQueue<Integer> queue=new PriorityQueue<Integer>();

		do {
			sum=0;
			cost=0;
			queue.clear();
			int_num=sc.nextInt();
			if(int_num==0) {
				break;
			}

			for(i=0; i<int_num; i++) {
				queue.add(sc.nextInt());
			}

			while(queue.size()>1) {
				sum=queue.poll()+queue.poll();
				cost+=sum;
				if(queue.size() == 0) {
					break;
				}

				queue.add(sum);
			}

			System.out.println(cost);
		} while(int_num!=0);
	}
}