package vn.myclass.core.persistence.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
/*
	bảng result chứa các kết quả của user
	id
	listencore: điểm nghe
	readingscore: điểm đọc
	createddate: ngày tạo
	examinationid: khóa ngoại tham chiếu đến bảng examination(bài kiểm tra)
	userid: khóa ngoại tham chiếu đến bảng user 
*/
@Entity
@Table(name = "result")
public class ResultEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resultId;

	@Column(name = "listenscore")
	private Integer listenScore;

	@Column(name = "readingscore")
	private Integer readingScore;

	@Column(name = "createddate")
	private Timestamp createdDate;

	@ManyToOne
	@JoinColumn(name = "examinationid")
	private ExaminationEntity examination; // khóa ngoại tham chiếu đến ExaminationEntity

	@ManyToOne
	@JoinColumn(name = "userid")
	private UserEntity user; // khóa ngoại tham chiếu đến UserEntity

	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public Integer getListenScore() {
		return listenScore;
	}

	public void setListenScore(Integer listenScore) {
		this.listenScore = listenScore;
	}

	public Integer getReadingScore() {
		return readingScore;
	}

	public void setReadingScore(Integer readingScore) {
		this.readingScore = readingScore;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public ExaminationEntity getExamination() {
		return examination;
	}

	public void setExamination(ExaminationEntity examination) {
		this.examination = examination;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
