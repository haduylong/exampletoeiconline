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

@Entity
@Table(name = "comment")
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	
	@Column(name = "content")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private UserEntity userEntity;	// khóa ngoại tham chiếu đến user bằng userid
	
	@ManyToOne
	@JoinColumn(name = "listenguidelineid")
	private ListenGuideLineEntity listenGuideLineEntity; // khóa ngoại tham chiếu đến listenguideline bằng listenguidelineid
	
	@Column(name = "createddate")
	private Timestamp createdDate;

	public CommentEntity() {}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity user) {
		this.userEntity = user;
	}

	public ListenGuideLineEntity getListenGuideLineEntity() {
		return listenGuideLineEntity;
	}

	public void setListenGuideLineEntity(ListenGuideLineEntity listenGuideLineEntity) {
		this.listenGuideLineEntity = listenGuideLineEntity;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
