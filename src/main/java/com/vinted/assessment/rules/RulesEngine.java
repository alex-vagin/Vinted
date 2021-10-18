package com.vinted.assessment.rules;

import com.vinted.assessment.model.ShipmentData;
import com.vinted.assessment.model.ShipmentOutput;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class RulesEngine  {
	private final List<Function<ShipmentData, Double>> rules = Arrays.asList(new RuleForSmallPackage(), new RuleForLP());
	private final RuleDiscountCorrection discountCorrection = new RuleDiscountCorrection();
	
	public ShipmentOutput calculateDiscount(ShipmentData shipmentData) {
		if (shipmentData.getInput() != null) {
			return ShipmentOutput.of(shipmentData, Double.NaN);
		}
		
		Double discount = rules.stream().map(f -> f.apply(shipmentData)).reduce(0.0, Double::sum);
		
		if (discount > 0 ) {
			discount = discountCorrection.apply(discount, shipmentData);
		}
		
		return ShipmentOutput.of(shipmentData, discount);
	}
}
