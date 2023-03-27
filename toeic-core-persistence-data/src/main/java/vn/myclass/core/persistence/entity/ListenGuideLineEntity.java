package vn.myclass.core.persistence.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "listenguideline")
public class ListenGuideLineEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer listenGuideLineId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "createddate")
	private Timestamp createdDate;
	
	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@OneToMany(mappedBy = "listenGuideLineEntity", fetch = FetchType.LAZY)
	private List<CommentEntity> commentList;
	
	public ListenGuideLineEntity() {}

	public Integer getListenGuideLineId() {
		return listenGuideLineId;
	}

	public void setListenGuideLineId(Integer listenGuideLineId) {
		this.listenGuideLineId = listenGuideLineId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public List<CommentEntity> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentEntity> commentList) {
		this.commentList = commentList;
	}
	
	
}
