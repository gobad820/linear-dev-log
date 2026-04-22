package com.ssafy.model.service;

public class TempServiceImpl implements TempService {

	@Override
	public double toFahrenheit(double celsius) {
		// TODO Auto-generated method stub
		return Math.round(((celsius * 9.0 / 5.0) + 32) * 100.0) / 100.0;
	}

	@Override
	public double toCelsius(double fahrenheit) {
		// TODO Auto-generated method stub
		return Math.round(((fahrenheit - 32) * 5 / 9) * 100.0) / 100.0;
	}

	@Override
	public double toKelvin(double celsius) {
		// TODO Auto-generated method stub
		return Math.round((celsius + 273.15) * 100.0) / 100.0;
	}

}
