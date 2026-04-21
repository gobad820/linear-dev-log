package com.ssafy;

public class ScoreServiceImpl implements ScoreService {

	@Override
	public double average(int kor, int eng, int math) {
		return (kor + eng + math) / 3.0;
	}

	@Override
	public String grade(int kor, int eng, int math) {
		double average = average(kor, eng, math);

		if      (average >= 90) return "A";
		else if (average >= 80) return "B";
		else if (average >= 70) return "C";
		else if (average >= 60) return "D";
		else                    return "F";
	}

	@Override
	public String pass(int kor, int eng, int math) {
		double average = average(kor, eng, math);
		return average >= 60 ? "합격" : "불합격";
	}
}
