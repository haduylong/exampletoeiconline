package vn.myclass.core.dto;

import java.io.Serializable;

public class CheckLogin implements Serializable{
	private boolean isUserExist;
	private String roleName;
	
	public boolean isUserExist() {
		return isUserExist;
	}
	public void setUserExist(boolean isUserExist) {
		this.isUserExist = isUserExist;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
