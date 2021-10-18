package com.vinted.assessment;

import com.vinted.assessment.model.PackageSize;
import com.vinted.assessment.model.Provider;

import java.util.EnumMap;
import java.util.Map;

public class Tariffs {
	private static Map<Provider, Map<PackageSize, Double>> tariffs = new EnumMap<>(Provider.class);
	
	static {
		Map<PackageSize, Double> tariffsLP = new EnumMap<>(PackageSize.class);
		tariffsLP.put(PackageSize.S, 1.5);
		tariffsLP.put(PackageSize.M, 4.9);
		tariffsLP.put(PackageSize.L, 6.9);
		tariffs.put(Provider.LP, tariffsLP);
		
		Map<PackageSize, Double> tariffsMR = new EnumMap<>(PackageSize.class);
		tariffsMR.put(PackageSize.S, 2.0);
		tariffsMR.put(PackageSize.M, 3.0);
		tariffsMR.put(PackageSize.L, 4.0);
		tariffs.put(Provider.MR, tariffsMR);
	}
	
	public static Double getTariff(Provider provider, PackageSize packageSize) {
		try {
			return tariffs.get(provider).get(packageSize);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	public static Map<Provider, Map<PackageSize, Double>> getTariffs() {
		return tariffs;
	}
}
