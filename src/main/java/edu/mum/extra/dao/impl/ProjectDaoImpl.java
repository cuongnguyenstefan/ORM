package edu.mum.extra.dao.impl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.mum.extra.dao.ProjectDao;
import edu.mum.extra.entity.Project;
import edu.mum.extra.entity.Role;
import edu.mum.extra.entity.Status;
import edu.mum.extra.entity.User;

@SuppressWarnings("unchecked")
public class ProjectDaoImpl implements ProjectDao {
	
	private EntityManager em;
	
	public ProjectDaoImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public boolean createProject(User creator, Project project) {
		if (creator.getRoles().contains(Role.ADMINISTRATOR)) {
			try {
			em.persist(project);
			return true;
			} catch (EntityExistsException e) {
				System.out.println("Project exists : " + project.getDesciption());
				return false;
			}
		}
		return false;
	}

	@Override
	public Project getProject(int projectId) {
		return em.find(Project.class, projectId);
	}

	@Override
	public List<Project> findByStatus(Status status) {
		Query query = em.createQuery("SELECT p FROM Project p WHERE p.status = :status");
		query.setParameter("status", status);
		List<Project> listProject = query.getResultList();
		return listProject;
	}

	@Override
	public List<Project> findByResource(String resource) {
		Query query = em.createQuery("SELECT DISTINCT p FROM Project p "
				+ "Join p.resources r "
				+ "WHERE r.resource = :resource");
		query.setParameter("resource", resource);
		List<Project> listProject = query.getResultList();
		return listProject;
	}

	
	@Override
	public List<Project> findByKeyword(String keyword) {
		Query query = em.createQuery("SELECT p FROM Project p WHERE p.desciption like :keyword");
		query.setParameter("keyword", keyword);
		List<Project> listProject = query.getResultList();
		return listProject;
	}

	@Override
	public List<Project> findByLocation(String location) {
		Query query = em.createQuery("SELECT p FROM Project p WHERE p.location like :location");
		query.setParameter("location", location);
		List<Project> listProject = query.getResultList();
		return listProject;
	}

}
