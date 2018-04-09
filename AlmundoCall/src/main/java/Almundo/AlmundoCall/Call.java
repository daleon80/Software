package Almundo.AlmundoCall;

public class Call {
	
	private boolean isTaken;
	
	private String name;

	public Call(String name){
		this.isTaken=false;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTaken() {
		return isTaken;
	}

	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}

	
	
	
	
	
	

}
