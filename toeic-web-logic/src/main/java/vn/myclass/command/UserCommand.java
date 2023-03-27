package vn.myclass.command;

import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.web.command.AbstractCommand;

public class UserCommand extends AbstractCommand<UserDTO>{
	private String confirmPassWord;
	
	public UserCommand() {
		this.pojo = new UserDTO();
	}

	public String getConfirmPassWord() {
		return confirmPassWord;
	}

	public void setConfirmPassWord(String confirmPassWord) {
		this.confirmPassWord = confirmPassWord;
	}
}
