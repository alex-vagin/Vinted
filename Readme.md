The application implemented as a pipe:
1. Read input lines, convert them in DTO - Java class ShipmentData
2. Calculate discounts with functions on two levels in class RuleEngine
3. Create output DTO - ShipmentOutput and output it to OutpuStream

The core functionality is concentrated in RuleEngine class. Each ShipmentData DTO is handleing on two levels:
1. Functions that calculate discounts only for specific Provider and/or Package Size. RuleForLP and RuleForSmallPackage are implemented.
2. Functions which are responsible for current record in general. RuleDiscountCorrection is implemented. This function corrects calculated discounts in dependence of allowed discount in a month.

Each function class has own state which allows to calculate discounts not only in dependce of ShipmentData DTO but also with other information - record number, number of discounts in a month and so on.
This approach allows isolate rule state and functionality in own class instances and give as flixibility to change and add toher rules without influence on other rules

This variant is commited in main branch and implements the task as simple as possible.

* Build tool - maven
* Used Java version - 1.8
* build command: **mvn clean package**
* run commands (from root of maven project): 
**mvn exec:java -mvn exec:java -Dexec.mainClass="com.vinted.assessment.VintedApplication" -Dexec.args="src/test/resources/input.txt"**

Or change directory to /target and run: **java -jar vinted-1.0-SNAPSHOT.jar ../src/test/resources/input.txt**

