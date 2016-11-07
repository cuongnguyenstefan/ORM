package edu.mum.extra.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer projectId;
	
	private String desciption;
	
	private String location;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@OneToMany(mappedBy = "project", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Task> tasks = new ArrayList<Task>();
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy = "project", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Picture> pictures = new ArrayList<Picture>();
	
	@OneToMany(mappedBy = "project", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>();
	
	@OneToMany(mappedBy = "project", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Resource> resources = new ArrayList<Resource>();
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Task> getTasks() {
		return Collections.unmodifiableList(tasks);
	}
	
	public void addTask(Task t) {
		tasks.add(t);
		t.setProject(this);
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Picture> getPictures() {
		return Collections.unmodifiableList(pictures);
	}
	
	public void addPicture(Picture p) {
		pictures.add(p);
		p.setProject(this);
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public List<Beneficiary> getBeneficiaries() {
		return Collections.unmodifiableList(beneficiaries);
	}
	
	public void addBeneficiary(Beneficiary b) {
		beneficiaries.add(b);
		b.setProject(this);
	}

	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}

	public List<Resource> getResources() {
		return Collections.unmodifiableList(resources);
	}
	
	public void addResource(Resource r) {
		resources.add(r);
		r.setProject(this);
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

}
