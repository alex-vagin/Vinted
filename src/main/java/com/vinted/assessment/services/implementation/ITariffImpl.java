package com.vinted.assessment.services.implementation;

import com.vinted.assessment.services.interfaces.ITariff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ITariffImpl implements ITariff {
	private static final ITariffImpl instance = new ITariffImpl();
	private Map<String, Map<String, Double>> tariffs;

	private ITariffImpl(){
		tariffs = new HashMap<>();
		Map<String, Double> tariffsLP = new HashMap<>();
		tariffsLP.put("S", 1.5);
		tariffsLP.put("M", 4.9);
		tariffsLP.put("L", 6.9);
		tariffs.put("LP", tariffsLP);
		
		Map<String, Double> tariffsMR = new HashMap<>();
		tariffsMR.put("S", 2.0);
		tariffsMR.put("M", 3.0);
		tariffsMR.put("L", 4.0);
		tariffs.put("MR", tariffsMR);
	}
	
	public static ITariffImpl getInstance(){
		return instance;
	}
	
	@Override
	public Double getTariff(String provider, String packageSize) {
		return null;
	}
	
	@Override
	public List<String> getProviders() {
		return null;
	}
}
