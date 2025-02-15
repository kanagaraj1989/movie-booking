package com.movie.booking_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true")
class BookingServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
