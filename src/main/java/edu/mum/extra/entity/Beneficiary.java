package edu.mum.extra.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Beneficiary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer beneficiaryId;
	
	@ManyToOne
	@JoinColumn(name = "projectId")
	private Project project;
	
	private String beneficiary;

	public Integer getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Integer beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

}
