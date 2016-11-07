package edu.mum.extra.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Picture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pictureId;
	
	@Lob
	private Byte[] pic;
	
	@ManyToOne
	@JoinColumn(name = "projectId")
	private Project project;

	public Integer getPictureId() {
		return pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public Byte[] getPic() {
		return pic;
	}

	public void setPic(Byte[] pic) {
		this.pic = pic;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
