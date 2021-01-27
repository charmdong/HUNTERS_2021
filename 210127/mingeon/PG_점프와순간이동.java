package Hunters_210120_27;

public class PG_점프와순간이동 {

	public static void main(String[] args) {
		int n = 5000;
		점프와순간이동 s = new 점프와순간이동();
		System.out.println(s.solution(n));
	}

}

class 점프와순간이동 {
	public int solution(int n) {
		int ans = 0;

		while (n != 0) {
			if (n % 2 == 0) { // 짝수
				n /= 2;
			} else { // 홀수
				ans += 1;
				n--;
			}
		}

		return ans;
	}
}