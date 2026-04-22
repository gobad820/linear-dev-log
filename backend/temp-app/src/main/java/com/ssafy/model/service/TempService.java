package com.ssafy.model.service;

public interface TempService {
	double toFahrenheit(double celsius);

	double toCelsius(double fahrenheit);

	double toKelvin(double celsius);
}
