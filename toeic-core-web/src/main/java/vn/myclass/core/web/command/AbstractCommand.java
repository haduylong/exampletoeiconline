package vn.myclass.core.web.command;
// có thuộc tính pojo là 1 DTO
public class AbstractCommand<T> {
	protected T pojo;
	private String crudaction;
	
	
	
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
		
}
