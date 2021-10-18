package com.vinted.assessment.rules;

import com.vinted.assessment.Factory;
import com.vinted.assessment.model.ShipmentData;
import com.vinted.assessment.services.interfaces.ITariff;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class RuleForSmallPackage implements Function<ShipmentData, Double> {
	private static ITariff tariff = Factory.getTariff();
	private static final String sizeS = "S";
	
	@Override
	public Double apply(ShipmentData shipmentData) {
		if (sizeS.equals(shipmentData.size())) {
			Double currentCost = tariff.getTariff(shipmentData.provider(), sizeS);
			Optional<Double> minCost = tariff.getProviders().stream().map(provider -> tariff.getTariff(provider, sizeS)).filter(Objects::nonNull).min(Double::compare);

			if (minCost.isPresent() && minCost.get() < currentCost) {
				return currentCost - minCost.get();
			}
		}
		return 0.0;
	}
}
