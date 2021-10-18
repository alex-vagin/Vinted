package com.vinted.assessment.rules;

import com.vinted.assessment.model.ShipmentData;

import java.util.function.BiFunction;

public class RuleDiscountCorrection implements BiFunction<Double, ShipmentData, Double> {
	private static final double MAX_DISCOUNT = 10.0;
	private int monthOfDiscount;
	private double discountGiven;
	
	@Override
	public Double apply(Double discount, ShipmentData shipmentData) {
		if (discount == 0) {
			return discount;
		}
		
		if (shipmentData.date().getMonthValue() != monthOfDiscount) {
			monthOfDiscount = shipmentData.date().getMonthValue();
			discountGiven = 0;
		}
		
		double discountGivenTemp = discountGiven;
		discountGiven = Math.min(discountGiven + discount, MAX_DISCOUNT);
		
		return discountGiven - discountGivenTemp;
	}
}
