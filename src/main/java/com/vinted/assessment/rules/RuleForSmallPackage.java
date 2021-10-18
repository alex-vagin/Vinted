package com.vinted.assessment.rules;

import com.vinted.assessment.Tariffs;
import com.vinted.assessment.model.PackageSize;
import com.vinted.assessment.model.ShipmentData;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class RuleForSmallPackage implements Function<ShipmentData, Double> {
	@Override
	public Double apply(ShipmentData shipmentData) {
		if (PackageSize.S.equals(shipmentData.getSize())) {
			Double currentCost = Tariffs.getTariff(shipmentData.getProvider(), PackageSize.S);
			Optional<Double> minCost = Tariffs.getTariffs().values().stream().map(p -> p.get(PackageSize.S)).filter(Objects::nonNull).min(Double::compare);

			if (minCost.isPresent() && minCost.get() < currentCost) {
				return currentCost - minCost.get();
			}
		}
		return 0.0;
	}
}
