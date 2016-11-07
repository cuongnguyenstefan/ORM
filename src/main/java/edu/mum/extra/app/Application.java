package edu.mum.extra.app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Application {

	private static EntityManagerFactory emf;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("extra");
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void main(String[] args) {
		emf.close();
	}

}
