package com.vinted.assessment.model;

import java.time.LocalDate;

public record ShipmentData (
	LocalDate date,
	PackageSize size,
	Provider provider){}
