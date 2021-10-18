The application implemented as a pipe:
1. Read input lines, convert them in DTO - Java class ShipmentData
2. Calculate discounts with functions on two levels in class RuleEngine
3. Create output DTO - ShipmentOutput and output it to OutpuStream

The core functionality is concentrated in RuleEngine class. Each ShipmentData DTO is handleing on two levels:
1. Functions that calculate discounts only for specific Provider and/or Package Size. RuleForLP and RuleForSmallPackage are implemented.
2. Functions which are responsible for current record in general. RuleDiscountCorrection is implemented. This function corrects calculated discounts in dependence of allowed discount in a month.

Each function class has own state which allows to calculate discounts not only in dependce of ShipmentData DTO but also with other information - record number, number of discounts in a month and so on.
This approach allows isolate rule state and functionality in own class instances and give as flixibility to change and add toher rules without influence on other rules
Each function is added to fields of RulesEngine class. In Spring or Java EE environment it could be done automatically when each function class marked with some special annotation @BusinessRule, for example. In current implementation nevertheless it also possible automatically link function to RuleEngine via reflections for example, but it's excessive.

* Build tool - maven
* Used Java version - 1.8
* build command: **mvn clean package**
* run commands (from root of maven project): 
**mvn exec:java -mvn exec:java -Dexec.mainClass="com.vinted.assessment.VintedApplication" -Dexec.args="src/test/resources/input.txt"**

Or change directory to /target and run: **java -jar vinted-1.0-SNAPSHOT.jar ../src/test/resources/input.txt**

This variant is commited in main branch and implements the task as simple as possible.

This kind of task often happens in production.
Further possible improvements:
1. Create services for:
    1. Input loading to load from other formats (JSON, XML) and other transport (JMS, HTTP)
    2. The same for output - services for different transports with pluggable converters to other formats
    3. Retrieving and caching tariffs from the persistence layer
2. To implement the handling process in a reactive manner.


