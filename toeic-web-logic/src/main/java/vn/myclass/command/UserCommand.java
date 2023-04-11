package vn.myclass.command;

import java.util.ArrayList;
import java.util.List;

import vn.myclass.core.dto.RoleDTO;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.web.command.AbstractCommand;

public class UserCommand extends AbstractCommand<UserDTO>{
	private String confirmPassWord;
	private List<RoleDTO> roles;
	private Integer roleId;
	
	public UserCommand() {
		this.pojo = new UserDTO();
		this.roles = new ArrayList<>();
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public String getConfirmPassWord() {
		return confirmPassWord;
	}

	public void setConfirmPassWord(String confirmPassWord) {
		this.confirmPassWord = confirmPassWord;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
