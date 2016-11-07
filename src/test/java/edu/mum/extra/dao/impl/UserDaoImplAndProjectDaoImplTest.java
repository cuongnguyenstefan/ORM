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
import edu.mum.extra.dao.UserDao;
import edu.mum.extra.entity.Project;
import edu.mum.extra.entity.Resource;
import edu.mum.extra.entity.Role;
import edu.mum.extra.entity.Service;
import edu.mum.extra.entity.Status;
import edu.mum.extra.entity.Task;
import edu.mum.extra.entity.User;

/** 
 * Since running each test will make it drop and create and import database all over again, I put
 * all of them in one file here
 * 
 * @author Cuong Nguyen
 *
 */
public class UserDaoImplAndProjectDaoImplTest {
	
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
	public void testAllFunction() {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			UserDao userDao = new UserDaoImpl(em);
			ProjectDao projectDao = new ProjectDaoImpl(em);
			
			// test add user
			User user = new User();
			user.setName("John Smith");
			HashSet<Role> roles = new HashSet<Role>();
			roles.add(Role.ADMINISTRATOR);
			user.setRoles(roles);
			boolean addUser = userDao.addUser(user);
			assertEquals(true, addUser);
			
			// test get user
			User user2 = userDao.getUser(1);
			assertEquals(user.getName(), user2.getName());
			
			// add project
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
			projectDao.createProject(user, pj);
			
			//get new created project
			Project project = projectDao.getProject(2);
			assertEquals("Fairfield", project.getLocation());
			
			//add service
			Service service = new Service();
			service.setOfferService("USer 1 on Task 3");
			service.setOfferTime(new Date());
			userDao.addService(1, 3, service);
			
			System.out.println("Find Project on user 1000");
			List<Project> findProjectsAndTasks = userDao.findProjectsAndTasks(1000);
			printListProject(findProjectsAndTasks);
			
			System.out.println("Projects are on pending");
			List<Project> findProjectPending = projectDao.findByStatus(Status.PENDING);
			printListProject(findProjectPending);
			
			System.out.println("Project has resource 'my resource'");
			List<Project> findByResource = projectDao.findByResource("my resource");
			printListProject(findByResource);
			
			System.out.println("Project by keyword");
			List<Project> findByKeyword = projectDao.findByKeyword("%is%");
			printListProject(findByKeyword);
			
			System.out.println("Project by location");
			List<Project> findByLocation = projectDao.findByLocation("Fairfield");
			printListProject(findByLocation);
			
			tx.commit();
		} catch (Throwable th) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
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
