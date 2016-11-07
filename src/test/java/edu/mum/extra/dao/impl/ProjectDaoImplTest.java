package edu.mum.extra.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.mum.extra.dao.ProjectDao;
import edu.mum.extra.entity.Project;
import edu.mum.extra.entity.Resource;
import edu.mum.extra.entity.Role;
import edu.mum.extra.entity.Status;
import edu.mum.extra.entity.Task;
import edu.mum.extra.entity.User;

public class ProjectDaoImplTest {

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
	public void testCreateProject() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			ProjectDao projectDao = new ProjectDaoImpl(em);
			Project pj = new Project();
			pj.setDesciption("This is my project");
			pj.setEndDate(new Date());
			pj.setStartDate(new Date());
			pj.setLocation("Fairfield");
			pj.setStatus(Status.PENDING);
			Task t1 = new Task();
			t1.setDescription("Task 1");
			t1.setStartDate(new Date());
			t1.setEndDate(new Date());
			pj.addTask(t1);
			Resource r = new Resource();
			r.setResource("my resource");
			pj.addResource(r);
			User user = new User();
			user.setName("John Smith");
			HashSet<Role> roles = new HashSet<Role>();
			roles.add(Role.ADMINISTRATOR);
			user.setRoles(roles);
			projectDao.createProject(user, pj);
			tx.commit();
		} catch (Throwable th) {
			if ((tx != null) && (tx.isActive()))
				tx.rollback();
		}
	}

	@Test
	public void testGetProject() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			ProjectDao projectDao = new ProjectDaoImpl(em);
			Project project = projectDao.getProject(2000);
			assertEquals("Fairfield", project.getLocation());
			tx.commit();
		} catch (Throwable th) {
			if ((tx != null) && (tx.isActive()))
				tx.rollback();
		}
	}
	
	@Test
	public void testFindByStatus() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			ProjectDao projectDao = new ProjectDaoImpl(em);
			System.out.println("Projects are on pending");
			List<Project> findProjectPending = projectDao.findByStatus(Status.PENDING);
			printListProject(findProjectPending);
			tx.commit();
		} catch (Throwable th) {
			if ((tx != null) && (tx.isActive()))
				tx.rollback();
		}
	}
	
	@Test
	public void testFindByResource() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			ProjectDao projectDao = new ProjectDaoImpl(em);

			System.out.println("Project has resource 'my resource'");
			List<Project> findByResource = projectDao.findByResource("my resource");
			printListProject(findByResource);
			tx.commit();
		} catch (Throwable th) {
			if ((tx != null) && (tx.isActive()))
				tx.rollback();
		}
	}
	
	@Test
	public void testFindByKeyword() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			ProjectDao projectDao = new ProjectDaoImpl(em);
			System.out.println("Project by keyword");
			List<Project> findByKeyword = projectDao.findByKeyword("%is%");
			printListProject(findByKeyword);
			tx.commit();
		} catch (Throwable th) {
			if ((tx != null) && (tx.isActive()))
				tx.rollback();
		}
	}
	
	@Test
	public void testFindByLocation() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			ProjectDao projectDao = new ProjectDaoImpl(em);
			System.out.println("Project by location");
			List<Project> findByLocation = projectDao.findByLocation("Fairfield");
			printListProject(findByLocation);
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
