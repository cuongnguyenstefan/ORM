package edu.mum.extra.dao.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.mum.extra.dao.UserDao;
import edu.mum.extra.entity.Project;
import edu.mum.extra.entity.Role;
import edu.mum.extra.entity.Service;
import edu.mum.extra.entity.User;

public class UserDaoImplTest {

	private static EntityManager em;

	@Before
	public void setUp() throws Exception {
		try {
			em = Persistence.createEntityManagerFactory("extra").createEntityManager();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void testGetUser() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			UserDao userDao = new UserDaoImpl(em);
			User user2 = userDao.getUser(1002);
			assertEquals("Mary Jane Holland", user2.getName());
			tx.commit();
		} catch (Throwable th) {
			if ((tx != null) && (tx.isActive()))
				tx.rollback();
		}
	}

	@Test
	public void testAddUser() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			UserDao userDao = new UserDaoImpl(em);

			User user = new User();
			user.setName("John Smith");
			HashSet<Role> roles = new HashSet<Role>();
			roles.add(Role.ADMINISTRATOR);
			user.setRoles(roles);
			boolean addUser = userDao.addUser(user);
			assertEquals(true, addUser);
			tx.commit();
		} catch (Throwable th) {
			if ((tx != null) && (tx.isActive()))
				tx.rollback();
		}
	}

	@Test
	public void testAddService() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			UserDao userDao = new UserDaoImpl(em);

			// add service
			Service service = new Service();
			service.setOfferService("USer 1 on Task 3");
			service.setOfferTime(new Date());
			boolean addService = userDao.addService(1, 3, service);
			assertEquals(true, addService);
			tx.commit();
		} catch (Throwable th) {
			if ((tx != null) && (tx.isActive()))
				tx.rollback();
		}
	}

	@Test
	public void testFindProjectsAndTasks() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			UserDao userDao = new UserDaoImpl(em);

			System.out.println("Find Project on user 1000");
			List<Project> findProjectsAndTasks = userDao.findProjectsAndTasks(1000);
			printListProject(findProjectsAndTasks);
			tx.commit();
		} catch (Throwable th) {
			if ((tx != null) && (tx.isActive()))
				tx.rollback();
		}
	}

	public void printListProject(List<Project> projects) {
		System.out.println(String.format("%-7s  %-30s %-30s %8s", "Id:", "Description:", "Start:", "Location"));
		for (Project p : projects) {
			System.out.println(String.format("%-7s  %-30s %-30s %8s", p.getProjectId(), p.getDesciption(),
					p.getStartDate(), p.getLocation()));
		}
	}
}
