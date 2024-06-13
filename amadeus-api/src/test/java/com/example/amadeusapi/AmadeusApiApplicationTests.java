package com.example.amadeusapi;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.referenceData.Locations;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AmadeusApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Value("${amadeus.apiKey}")
	private String apiKey;

	@Value("${amadeus.apiSecret}")
	private String apiSecret;

	@Test
	void apiConnectionTest() {
		System.out.println(apiKey);
		System.out.println(apiSecret);

		Amadeus amadeus = Amadeus
				.builder(apiKey, apiSecret)
				.build();

        Location[] locations = null;
        try {
            locations = amadeus.referenceData.locations.get(Params
                    .with("keyword", "LON")
                    .and("subType", Locations.ANY));
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }

        int locationNum = locations.length;
		System.out.println(locationNum);
        for (Location location : locations) System.out.println(location.toString());
	}
}
