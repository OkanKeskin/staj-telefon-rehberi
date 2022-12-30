package User;

public class User {
	private int id;
	private String name;
	private String surname;
	private String tckn;
	
	public User() {
		
	}
	
	public User(int id,String name,String surname,String tckn) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.tckn = tckn;
	}
	
	private static User user = null;
	
	private synchronized static void createInstance() {
		if(user == null) {
			user = new User();
		}
	}
	
	public static User getInstance() {
		if(user == null) {
			createInstance();
		}
		return user;
	}

	public int getId() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTckn() {
		return tckn;
	}

	public void setTckn(String tckn) {
		this.tckn = tckn;
	}
}
