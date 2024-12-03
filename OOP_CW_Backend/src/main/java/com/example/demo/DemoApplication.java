package com.example.demo;

import com.example.demo.models.TicketPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		TicketPool ticketPool = new TicketPool(30);
		ObjectRegistry.setTicketPool(ticketPool);

		SpringApplication.run(DemoApplication.class, args);

	}

	public static class ObjectRegistry {
		private static TicketPool ticketPool;

		public static void setTicketPool(TicketPool pool) {
			ticketPool = pool;
		}

		public static TicketPool getTicketPool() {
			return ticketPool;
		}
	}
}
