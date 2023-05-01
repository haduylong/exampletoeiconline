package vn.myclass.core.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.mysql.cj.util.StringUtils;

import vn.myclass.core.web.command.AbstractCommand;
// set các thuộc tính cho Abscommand giúp hỗ trợ phân trang
public class RequestUtil {
	public static void initSearchBean(HttpServletRequest req, AbstractCommand bean) {// phân trang cho table
		if(bean!=null) {
	        String sortExpression = req.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));
	        String sortDirection = req.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));
	        String pageStr = req.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));

			
			Integer page = 1;
			if(org.apache.commons.lang.StringUtils.isNotBlank(pageStr)) {
				try {
					page = Integer.valueOf(pageStr);
				} catch (Exception e) {
					// ignore
				}
				
			}
			bean.setPage(page);
			bean.setSortExpression(sortExpression);
			bean.setSortDirection(sortDirection);
			bean.setFirstItem((bean.getPage()-1)*bean.getMaxPageItems());
		}
	}
	
	public static void initSearchBeanManual(HttpServletRequest req, AbstractCommand command) {// phân trang cho danh sách ...
		if(command != null) {
			Integer page = 1;
			if(command.getPage() != 0) {
				page = command.getPage();
			}
			command.setPage(page);
			command.setFirstItem((command.getPage()-1)*command.getMaxPageItems());;
		}
	}
	
}
