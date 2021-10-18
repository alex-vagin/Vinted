package com.vinted.assessment.model;

import com.vinted.assessment.Tariffs;

import java.util.Locale;

public record ShipmentOutput (
 	ShipmentData shipmentData,
	double shipmentCost,
	double discount){}
