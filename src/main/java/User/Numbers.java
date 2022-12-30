package User;

public class Numbers {
	private int numberId;
	private int userId;
	private String number;
	
	private static Numbers numbera;
	
	public Numbers(int id,int userId,String number){
		this.number = number;
		this.numberId = id;
		this.userId = userId;
	}
	
	public Numbers() {}
	
	public static Numbers getInstance() {
		if(numbera == null) {
			numbera = new Numbers();
		} 
		
		return numbera;
	}
	
	public int getNumberId() {
		return numberId;
	}
	public void setNumberId(int numberId) {
		this.numberId = numberId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
