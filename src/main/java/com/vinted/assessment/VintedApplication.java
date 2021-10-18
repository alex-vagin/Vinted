package com.vinted.assessment;

import com.vinted.assessment.model.ShipmentData;
import com.vinted.assessment.rules.RulesEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.Stream;

public class VintedApplication {
	public static void main(String[] args) {
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
		EndSubscriber subscriber = new EndSubscriber();
		publisher.subscribe(subscriber);
		Files.lines(Paths.get("D:\\MyStaff\\MyPrj\\Vinted\\src\\main\\resources\\input.txt")).forEach(p -> publisher.submit(p));
		
		
		
		try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
			RulesEngine rulesEngine = new RulesEngine();
			stream.map(ShipmentData::of).map(rulesEngine::calculateDiscount).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
