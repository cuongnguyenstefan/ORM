package edu.mum.extra.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.mum.extra.dao.UserDao;
import edu.mum.extra.entity.Project;
import edu.mum.extra.entity.Role;
import edu.mum.extra.entity.Service;
import edu.mum.extra.entity.Task;
import edu.mum.extra.entity.User;

public class UserDaoImpl implements UserDao {

	private EntityManager em;

	public UserDaoImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public User getUser(int userId) {
		return em.find(User.class, userId);
	}

	@Override
	public boolean addUser(User user) {
		try {
			em.persist(user);
		} catch (EntityExistsException e) {
			System.out.println("User exists " + user.getName());
			return false;
		}
		return true;
	}

	@Override
	public boolean addService(int userId, int taskId, Service service) {
		User user = em.find(User.class, userId);
		if (user != null) {
			Task task = em.find(Task.class, taskId);
			if (task != null) {
				service.setUser(user);
				service.setTask(task);
				service.setOfferTime(new Date());
				user.getServices().add(service);
				em.merge(user);
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findProjectsAndTasks(int userId) {
		User user = em.find(User.class, userId);
		if (user != null && user.getRoles().contains(Role.VOLUNTEER)) {
			Query query = em.createQuery(
					"SELECT DISTINCT p "
					+ "FROM Project p "
					+ "JOIN p.tasks t "
					+ "JOIN t.volunteerServices v "
					+ "JOIN v.user u "
					+ "WHERE u.userId = :userId "
					+ "ORDER BY t.startDate DESC");
			query.setParameter("userId", userId);
			List<Project> resultList = query.getResultList();
			return resultList;
		}
		return null;
	}

}
