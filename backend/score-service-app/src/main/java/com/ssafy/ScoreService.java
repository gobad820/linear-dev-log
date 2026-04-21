package com.ssafy;

public interface ScoreService {

	double average(int kor, int eng, int math);

	String grade(int kor, int eng, int math);

	String pass(int kor, int eng, int math);
}
