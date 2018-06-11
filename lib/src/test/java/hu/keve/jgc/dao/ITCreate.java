package hu.keve.jgc.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ITCreate {
	@Test
	void create() {
		System.out.println(System.getenv());
		assertNotNull(System.getenv("it-pgsql.port"));
	}
}
