package com.vinted.assessment;

import com.vinted.assessment.model.PackageSize;
import com.vinted.assessment.model.Provider;
import com.vinted.assessment.model.ShipmentData;
import com.vinted.assessment.model.ShipmentOutput;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Factory {
	public static ShipmentData createShipmentData(String input) {
		ShipmentData result = new ShipmentData();
		result.input = s;
		String[] fields = s.split(" ");
		
		if (fields.length == 3) {
			try {
				LocalDate ld = LocalDate.parse(fields[0]);
				PackageSize packageSize = PackageSize.valueOf(fields[1]);
				Provider provider = Provider.valueOf(fields[2]);
				
				if (Tariffs.getTariff(provider,
					packageSize) != null) {
					result.date = ld;
					result.size = packageSize;
					result.provider = provider;
					result.input = null;
				}
			} catch (IllegalArgumentException | DateTimeParseException e) {}
		}
		return result;
	}
	
	public static String toString(ShipmentData shipmentData) {
		return String.format("%s %s %s",
			getDate().toString(),
			getSize().toString(),
			getProvider().toString());
	}
	
	public static ShipmentOutput createShipmentOutput(ShipmentData shipmentData, double discount) {
		ShipmentOutput result = new ShipmentOutput();
		result.shipmentData = shipmentData;
		
		if (shipmentData.getInput() == null) {
			result.discount = discount;
			result.shipmentCost = Tariffs.getTariff(shipmentData.getProvider(), shipmentData.getSize()) - discount;
		}
		
		return result;
	}
	
	public static String toString(ShipmentOutput shipmentOutput) {
		if (shipmentData.getInput() != null) {
			return String.format("%s Ignored", shipmentData.getInput());
		}
		return String.format(Locale.ROOT, "%s %.2f %s",
			shipmentData.toString(),
			shipmentCost,
			discount == 0 ? "-" : String.format(Locale.ROOT, "%.2f", discount));
	}
}
