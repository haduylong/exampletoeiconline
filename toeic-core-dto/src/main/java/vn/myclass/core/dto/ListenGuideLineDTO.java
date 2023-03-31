package vn.myclass.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import vn.myclass.core.persistence.entity.CommentEntity;

public class ListenGuideLineDTO implements Serializable{
	private Integer listenGuideLineId;
	
	private String title;
	
	private String image;

	private String content;

	private Timestamp createdDate;

	private Timestamp modifiedDate;

	private List<CommentEntity> commentList;

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
