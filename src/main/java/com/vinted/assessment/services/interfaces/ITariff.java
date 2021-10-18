package com.vinted.assessment.services.interfaces;

import java.util.List;

public interface ITariff {
	Double getTariff(String provider, String packageSize);
	List<String> getProviders();
}
