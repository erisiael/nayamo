package vo;

public class Ajaxuser {

	private String name;
	private int age;
	private String addr;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "Ajaxuser [name=" + name + ", age=" + age + ", addr=" + addr + "]";
	}
}
