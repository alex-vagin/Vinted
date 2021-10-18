package com.vinted.assessment;

import com.vinted.assessment.model.ShipmentData;
import com.vinted.assessment.model.ShipmentOutput;
import com.vinted.assessment.services.implementation.ITariffImpl;
import com.vinted.assessment.services.interfaces.ITariff;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Factory {
	private static ITariff tariff = getTariff();

	public static ShipmentData createShipmentData(String input) {
		String[] fields = input.split(" ");
		
		if (fields.length == 3) {
			try {
				return new ShipmentData(LocalDate.parse(fields[0]), fields[1], fields[2]);
			} catch (IllegalArgumentException | DateTimeParseException e) {}
		}
		throw new IllegalArgumentException(input);
	}
	
	public static String toString(ShipmentData shipmentData) {
		return String.format("%s %s %s",
			shipmentData.date().toString(),
			shipmentData.size(),
			shipmentData.provider());
	}
	
	public static ShipmentOutput createShipmentOutput(ShipmentData shipmentData, double discount) {
		double shipmentCost = tariff.getTariff(shipmentData.provider(), shipmentData.size()) - discount;
		return new ShipmentOutput(shipmentData, shipmentCost, discount);
	}
	
	public static String toString(ShipmentOutput shipmentOutput) {
		return String.format(Locale.ROOT, "%s %.2f %s",
			toString(shipmentOutput.shipmentData()),
			shipmentOutput.shipmentCost(),
			shipmentOutput.discount() == 0 ? "-" : String.format(Locale.ROOT, "%.2f", shipmentOutput.discount()));
	}
	
	public static ITariff getTariff() {
		return ITariffImpl.getInstance();
	}
}
