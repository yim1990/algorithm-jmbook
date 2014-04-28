import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int MAX_DIGITS = 11;
	/**
	 * cache long[자리수][digit]
	 * 0부터 (10^자리수*digit-1)까지의 합.
	 * 계산편의를 위해 각 자리수의 digit 0은 비운다.
	 */
	public static long[][] cache = new long[MAX_DIGITS][11];

	/**
	 * 10의 멱승을 계산한다
	 * {0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000...}
	 */
	public static long[] powers = new long[MAX_DIGITS];

	public static void preCalc() {
		cache[1][1] = 1;
		for (int i = 1; i < MAX_DIGITS; i++) { // 1의 자리부터 MAX 자리까지
			// 10^i의 초기화
			if (i == 1) {
				powers[i] = 1;
			} else {
				powers[i] = powers[i - 1] * 10;
			}

			for (int j = 1; j < 11; j++) {
				if (i > 1 && j == 1) { // 2의 자리 이상, digit 1일 때
					if (i == 2) {
						cache[i][j] = cache[1][9];
					} else {
						cache[i][j] = cache[i - 1][10];
					}
				} else { // 모든 자리에서 digit 2 이상일 때
					cache[i][j] = ((j - 1) * powers[i] + cache[i][1])
							+ cache[i][j - 1];
				}
			}
		}
	}

	// 0부터 num까지의 digit 합을 반환한다.
	public static long calcSum(String num) {
		long ret = 0;
		int len = num.length();

		int prevDigit = 0;
		long remainNum = Long.parseLong(num);
		for (int i = 0; i < len; i++) { // i는 자리수, 첫째 자리부터 계산한다
			char digitChar = num.charAt(i);
			int digit = digitChar - 48; // 아스키코드 처리

			/**
			 * 예를들어 num=513인 경우 아래와 같이 나눌 수 있다.
			 * ret+=0..499의 캐시된 부분합
			 * ret+=0..9의 캐시된 부분합 + 5*(13+1)
			 * ret+=0..3의 캐시된 부분합 + 1*(3+1)
			 */
			ret += cache[len - i][digit] + prevDigit * (remainNum + 1);

			prevDigit = digit;
			remainNum -= digit * powers[len - i];
		}

		return ret;
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		preCalc();

		boolean isStarted = false;

		try {
			while (true) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();

				long longA = Long.parseLong(a);
				long longB = Long.parseLong(b);
				long longOpenA = longA - 1;

				if (a.equals("0") && b.equals("0")) {
					break;
				} else if(longA > longB) {
					continue;
				} else if (isStarted) {
					System.out.println();
				}


				long sumBA = calcSum(b) - calcSum(String.valueOf(longOpenA)); // 0 ~ B의 합에서 0~A-1의 합을 뺀다.

				System.out.print(sumBA);
				isStarted = true;
			}
		} catch (IOException e) {
		}
	}
}
