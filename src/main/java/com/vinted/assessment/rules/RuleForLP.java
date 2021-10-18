package com.vinted.assessment.rules;

import com.vinted.assessment.Factory;
import com.vinted.assessment.model.ShipmentData;
import com.vinted.assessment.services.interfaces.ITariff;

import java.util.function.Function;

public class RuleForLP  implements Function<ShipmentData, Double> {
	private static ITariff tariff = Factory.getTariff();
	
	private static final String providerLP = "LP";
	private static final String sizeL = "L";
	
	private int lastMonthOfDiscount;
	private int pass = 1;
	
	@Override
	public Double apply(ShipmentData shipmentData) {
		if (providerLP.equals(shipmentData.provider()) &&	sizeL.equals(shipmentData.size()) &&
			(pass++ % 3) == 0 &&
			lastMonthOfDiscount != shipmentData.date().getMonthValue()) {
			lastMonthOfDiscount = shipmentData.date().getMonthValue();
			return tariff.getTariff(providerLP, sizeL);
		}
		return 0.0;
	}
}
