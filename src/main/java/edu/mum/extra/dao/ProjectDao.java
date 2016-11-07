package edu.mum.extra.dao;

import java.util.List;

import edu.mum.extra.entity.Project;
import edu.mum.extra.entity.Status;
import edu.mum.extra.entity.User;

public interface ProjectDao {
	
	public boolean createProject(User creator, Project project);
	
	public Project getProject(int projectId);
	
	public List<Project> findByStatus(Status status);
	
	public List<Project> findByResource(String resource);
	
	public List<Project> findByKeyword(String keyword);
	
	public List<Project> findByLocation(String location);
}
