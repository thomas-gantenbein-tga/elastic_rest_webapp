package com.example;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Timer {
	private static Map<String, Long> timeList = new LinkedHashMap<>();
	private static long previousTimeTaken = 0;
	private static Logger log = Logger.getLogger("elasticLogger");

	static void takeTime(String message) {
		if (previousTimeTaken == 0) {
			timeList.put(message, 0l);
			previousTimeTaken = System.currentTimeMillis();
		} else {
			timeList.put(message, System.currentTimeMillis() - previousTimeTaken);
			previousTimeTaken = System.currentTimeMillis();
		}
	}

	static void printTimes() {
		Long gesamtZeitInMillis = 0l;
		for(Map.Entry<String, Long> entry : timeList.entrySet()) {
			log.info(entry.getKey() + ": " + entry.getValue());
			gesamtZeitInMillis += entry.getValue();
		}
		log.info("Gesamtzeit: " + gesamtZeitInMillis);
		previousTimeTaken = 0;
	}

}
