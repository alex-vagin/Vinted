package com.vinted.assessment;

import com.vinted.assessment.model.ShipmentData;
import com.vinted.assessment.rules.RulesEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class VintedApplication {
	public static void main(String[] args) {
		try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
			RulesEngine rulesEngine = new RulesEngine();
			stream.map(ShipmentData::of).map(rulesEngine::calculateDiscount).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
