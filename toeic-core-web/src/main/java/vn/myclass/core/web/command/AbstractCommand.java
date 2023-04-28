package vn.myclass.core.web.command;

import java.util.List;

// có thuộc tính pojo là 1 DTO
public class AbstractCommand<T> {
	protected T pojo; // T là generic của 1 dto bất kì
	private String crudaction;// thao tác crud của người dùng
    private List<T> listResult;// danh sách các item
    private String tableId = "tableList";// lấy id bất kì của trang
    private int maxPageItems = 5;// số lượng max item trên một bảng trang
    private int totalItems = 0;// tổng số item
    private int firstItem = 0;// item đầu tiên của bảng trang
    private String sortExpression;// tên thuộc tính muốn chọn để sort
    private String sortDirection;// sort giảm dần hoặc tăng dần
    private String[] checkList;// check list: khi tích chọn sẽ lưu id của item vào (map với name='checkList')
    private String messageResponse;// message respense
    private int page = 1;// bảng trang đầu tiên là 1
    private String urlType;// tên url type truyền lên từ jsp
    private int totalPages;// số bảng trang
	
	
	public T getPojo() {
		return pojo;
	}
	public void setPojo(T pojo) {
		this.pojo = pojo;
	}
	public String getCrudaction() {
		return crudaction;
	}
	public void setCrudaction(String crudaction) {
		this.crudaction = crudaction;
	}
	public List<T> getListResult() {
		return listResult;
	}
	public void setListResult(List<T> listResult) {
		this.listResult = listResult;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public int getMaxPageItems() {
		return maxPageItems;
	}
	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public int getFirstItem() {
		return firstItem;
	}
	public void setFirstItem(int firstItem) {
		this.firstItem = firstItem;
	}
	public String getSortExpression() {
		return sortExpression;
	}
	public void setSortExpression(String sortExpression) {
		this.sortExpression = sortExpression;
	}
	public String getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	public String[] getCheckList() {
		return checkList;
	}
	public void setCheckList(String[] checkList) {
		this.checkList = checkList;
	}
	public String getMessageResponse() {
		return messageResponse;
	}
	public void setMessageResponse(String messageResponse) {
		this.messageResponse = messageResponse;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getUrlType() {
		return urlType;
	}
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
}
