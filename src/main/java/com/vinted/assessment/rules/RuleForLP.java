package com.vinted.assessment.rules;

import com.vinted.assessment.Tariffs;
import com.vinted.assessment.model.PackageSize;
import com.vinted.assessment.model.Provider;
import com.vinted.assessment.model.ShipmentData;

import java.util.function.Function;

public class RuleForLP  implements Function<ShipmentData, Double> {
	private int lastMonthOfDiscount;
	private int pass = 1;
	
	@Override
	public Double apply(ShipmentData shipmentData) {
		if (Provider.LP.equals(shipmentData.getProvider()) &&	PackageSize.L.equals(shipmentData.getSize()) &&
			(pass++ % 3) == 0 &&
			lastMonthOfDiscount != shipmentData.getDate().getMonthValue()) {
			lastMonthOfDiscount = shipmentData.getDate().getMonthValue();
			return Tariffs.getTariff(Provider.LP, PackageSize.L);
		}
		return 0.0;
	}
}
