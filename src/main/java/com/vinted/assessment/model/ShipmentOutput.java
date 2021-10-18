package com.vinted.assessment.model;

public record ShipmentOutput (
 	ShipmentData shipmentData,
	double shipmentCost,
	double discount){}
