package Almundo.AlmundoCall;
/**
 * 
 * @author dleon	Daniel Leon
 *
 */
public enum State {
	
	OCCUPIED("OCCUPIED"),
	FREE("FREE");
	
	private final String stateName;

	State(String stateName) {
        this.stateName = stateName;
    }
    
    public String getStateName() {
        return this.stateName;
    }

}
