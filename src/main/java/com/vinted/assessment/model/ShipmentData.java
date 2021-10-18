package com.vinted.assessment.model;

import java.time.LocalDate;

public record ShipmentData (
	LocalDate date,
	String size,
	String provider){}
