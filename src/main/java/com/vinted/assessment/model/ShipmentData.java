package com.vinted.assessment.model;

import com.vinted.assessment.Tariffs;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ShipmentData {
	private LocalDate date;
	private PackageSize size;
	private Provider provider;
	private String input;
	
	public static ShipmentData of(String s) {
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
	
	@Override
	public String toString() {
		return String.format("%s %s %s",
			getDate().toString(),
			getSize().toString(),
			getProvider().toString());
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public PackageSize getSize() {
		return size;
	}
	
	public Provider getProvider() {
		return provider;
	}
	
	public String getInput() {
		return input;
	}
}
