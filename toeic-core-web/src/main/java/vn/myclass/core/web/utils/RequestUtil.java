package vn.myclass.core.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.mysql.cj.util.StringUtils;

import vn.myclass.core.web.command.AbstractCommand;

public class RequestUtil {
	public static void initSearchBean(HttpServletRequest req, AbstractCommand bean) {
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
}
