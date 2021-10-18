package com.vinted.assessment.rules;

import com.vinted.assessment.model.ShipmentData;
import com.vinted.assessment.model.ShipmentOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

class RulesEngineTest {
	private static Path getPath(String fileName) {
		return Paths.get("src","test","resources", fileName);
	}
	
	private static void test(String inputFileName, String outputFileName)  throws IOException {
		List<String> inputList = Files.readAllLines(getPath(inputFileName));
		RulesEngine rulesEngine = new RulesEngine();
		List<String> outputList = inputList.stream().map(ShipmentData::of).map(rulesEngine::calculateDiscount).map(ShipmentOutput::toString).collect(Collectors.toList());
		List<String> expectedOutputList = Files.readAllLines(getPath(outputFileName));
		Assertions.assertLinesMatch(expectedOutputList, outputList);
	}

	@Test
	void controlAllTest() throws IOException {
		test("input.txt", "output.txt");
	}
	
	@Test
	void invalidInputTest() throws IOException {
		test("invalidInput.txt", "invalidOutput.txt");
	}
	
	@Test
	void smallPackageDiscountTest() throws IOException {
		test("smallPackageDiscountInput.txt", "smallPackageDiscountOutput.txt");
	}
	
	@Test
	void largePackageDiscountTest() throws IOException {
		test("largePackageDiscountInput.txt", "largePackageDiscountOutput.txt");
	}
	
	@Test
	void resetDiscountTest() throws IOException {
		test("resetDiscountInput.txt", "resetDiscountOutput.txt");
	}
}