package vn.myclass.command;

import vn.myclass.core.dto.ListenGuideLineDTO;
import vn.myclass.core.web.command.AbstractCommand;

public class ListenGuidelineCommand extends AbstractCommand<ListenGuideLineDTO>{
	public ListenGuidelineCommand() {
		this.pojo = new ListenGuideLineDTO();
	}
}
