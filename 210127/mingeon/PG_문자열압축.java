package Hunters_210120_27;

public class PG_문자열압축 {

	public static void main(String[] args) {
		String s = "abcabcabcabcdededededede";
		문자열압축 sol = new 문자열압축();
		System.out.println(sol.solution(s));
	}

}

class 문자열압축 {
	public int solution(String s) {
		int len = s.length(); // 문자열이 길이
		int answer = len;
		String res = "";

		// unit개 단위
		for (int unit = 1; unit <= len / 2; unit++) {
			StringBuffer tempSB = new StringBuffer();

			for (int start = 0; start <= len; start += unit) {
				int end = start + unit;

				String word = "";

				// unit크기만큼의 다음 문자열 검사
				if (start + unit >= len) {
					word = s.substring(start);
				} else {
					word = s.substring(start, start + unit);
				}

				int cnt = 1;
				StringBuilder sb = new StringBuilder();

				for (int next = end; next < len; next += unit) {
					String word2 = "";

					if (next + unit >= len) {
						word2 = s.substring(next);
					} else {
						word2 = s.substring(next, next + unit);
					}

					if (word.equals(word2)) {
						cnt++;
						start = next;
					} else {
						break;
					}
				}

				if (cnt == 1) {
					sb.append(word);
				} else {
					sb.append(cnt).append(word);
				}

				tempSB.append(sb.toString());

			} // End of start

			answer = Math.min(answer, tempSB.toString().length());

		} // End of Unit

		return answer;
	}
}