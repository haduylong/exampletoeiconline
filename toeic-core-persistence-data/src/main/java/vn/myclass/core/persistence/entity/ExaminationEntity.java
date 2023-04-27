package vn.myclass.core.persistence.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/*
 * bảng bài kiểm tra 
 * examinationid
 * name
 * createddate
 * modifieddate
 * examinationquestions : một bài kiểm tra có thể chứa nghiều câu hỏi kiểm tra
 * result: một bài kiểm tra có thể có nhiều kết quả (vd: thi nhiều lần)
 */
@Entity
@Table(name = "examination")
public class ExaminationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer examinationId;

	@Column(name = "name")
	private String name;

	@Column(name = "createddate")
	private Timestamp createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@OneToMany(mappedBy = "examination", fetch = FetchType.LAZY)
	private List<ExaminationQuestionEntity> examinationQuestions;

	@OneToMany(mappedBy = "examination", fetch = FetchType.LAZY)
	private List<ResultEntity> results;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<ExaminationQuestionEntity> getExaminationQuestions() {
		return examinationQuestions;
	}

	public void setExaminationQuestions(List<ExaminationQuestionEntity> examinationQuestions) {
		this.examinationQuestions = examinationQuestions;
	}

	public List<ResultEntity> getResults() {
		return results;
	}

	public void setResults(List<ResultEntity> results) {
		this.results = results;
	}

	public Integer getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(Integer examinationId) {
		this.examinationId = examinationId;
	}
}
