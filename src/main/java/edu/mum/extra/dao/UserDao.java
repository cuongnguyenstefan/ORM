package edu.mum.extra.dao;

import java.util.List;

import edu.mum.extra.entity.Project;
import edu.mum.extra.entity.Service;
import edu.mum.extra.entity.User;

public interface UserDao {
	
	public User getUser(int userId);
	
	public boolean addUser(User user);
	
	public boolean addService(int userId, int taskId, Service service);
	
	public List<Project> findProjectsAndTasks(int userId);
	
}
