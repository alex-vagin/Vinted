package com.vinted.assessment.model;

import com.vinted.assessment.Tariffs;

import java.util.Locale;

public class ShipmentOutput {
 	private ShipmentData shipmentData;
	private double shipmentCost;
	private double discount;
	
	public static ShipmentOutput of(ShipmentData shipmentData, double discount) {
		ShipmentOutput result = new ShipmentOutput();
		result.shipmentData = shipmentData;

		if (shipmentData.getInput() == null) {
			result.discount = discount;
			result.shipmentCost = Tariffs.getTariff(shipmentData.getProvider(), shipmentData.getSize()) - discount;
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		if (shipmentData.getInput() != null) {
			return String.format("%s Ignored", shipmentData.getInput());
		}
		return String.format(Locale.ROOT, "%s %.2f %s",
			shipmentData.toString(),
			shipmentCost,
			discount == 0 ? "-" : String.format(Locale.ROOT, "%.2f", discount));
	}
	
	public ShipmentData getShipmentData() {
		return shipmentData;
	}
	
	public double getShipmentCost() {
		return shipmentCost;
	}
	
	public double getDiscount() {
		return discount;
	}
}
