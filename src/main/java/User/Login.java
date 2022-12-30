package User;

public class Login {
	private int id;
	private String kadi;
	private String sifre;
	
	private static Login login = null;
	
	private Login() {
		
	}
	
	public static Login getInstance() {
		if(login == null) {
			login = new Login();
		}
		
		return login;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKadi() {
		return kadi;
	}
	public void setKadi(String kadi) {
		this.kadi = kadi;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
}
