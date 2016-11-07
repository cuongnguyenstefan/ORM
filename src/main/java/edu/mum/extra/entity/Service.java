package edu.mum.extra.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Service {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer serviceId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	private String offerService;
	
	@Temporal(TemporalType.DATE)
	private Date offerTime;
	
	@ManyToOne
	@JoinColumn(name = "taskId")
	private Task task;

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOfferService() {
		return offerService;
	}

	public void setOfferService(String offerService) {
		this.offerService = offerService;
	}

	public Date getOfferTime() {
		return offerTime;
	}

	public void setOfferTime(Date offerTime) {
		this.offerTime = offerTime;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}
