package com.tamil.billing;

import com.tamil.billing.controller.BillController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BillingModuleApplicationTests {
	@Autowired
	private BillController controller;


	@DisplayName("Context  : Spring Application Context Loaded")
	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller);

	}
}
