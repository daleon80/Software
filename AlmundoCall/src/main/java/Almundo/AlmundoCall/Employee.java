package Almundo.AlmundoCall;
/**
 * 
 * @author dleon	Daniel Leon
 *
 */
public class Employee {
	private TypeofEmployee type;

	private String name;

	private State state;

	public Employee(String name) {
		this.name = name;
	}
	public Employee(String name,TypeofEmployee type,State state) {
		this.state=state;
		this.type=type;
		this.name = name;
	}


	public TypeofEmployee getType() {
		return type;
	}

	public void setType(TypeofEmployee type) {
		this.type = type;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
