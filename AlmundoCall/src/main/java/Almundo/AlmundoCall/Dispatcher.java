package Almundo.AlmundoCall;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase Dispatcher de llamadas, que trabaja por concurrencia
 * 
 * @author Daniel Esneider Leon Avendaño
 *
 */
public class Dispatcher implements Runnable {

	static int MAX_CONCURRENT_QUANTITY_OF_CALLS = 10;
	private static final Map<String, Employee> operators;
	static {
		operators = new ConcurrentHashMap<String, Employee>();
		operators.put("Juan", new Employee("Juan", TypeofEmployee.OPERADOR, State.FREE));
		operators.put("Emilio", new Employee("Emilio", TypeofEmployee.OPERADOR, State.FREE));
		operators.put("David", new Employee("David", TypeofEmployee.OPERADOR, State.FREE));
		operators.put("Carolina", new Employee("Carolina", TypeofEmployee.OPERADOR, State.FREE));
		operators.put("Mary", new Employee("Mary", TypeofEmployee.OPERADOR, State.FREE));
		operators.put("Victoria", new Employee("Victoria", TypeofEmployee.OPERADOR, State.FREE));
		operators.put("Joel", new Employee("Joel", TypeofEmployee.OPERADOR, State.FREE));
		operators.put("Mariana", new Employee("Mariana", TypeofEmployee.SUPERVISOR, State.FREE));
		operators.put("Jacobo", new Employee("Jacobo", TypeofEmployee.SUPERVISOR, State.FREE));
		operators.put("Martin", new Employee("Martin", TypeofEmployee.DIRECTOR, State.FREE));
	}

	private Map<String, Call> calls;
	private Call calltoBeQueued;
	private int currentEmployeesBusy;

	/**
	 * Constructor
	 * 
	 * @param callToBeAttended
	 *            Llamada a ser Atendida
	 */
	public Dispatcher(Call calltoBeQueued) {
		calls = new HashMap<String, Call>();
		this.setCalltoBeQueued(calltoBeQueued);
		System.out.println("Constructor");

	}

	public synchronized void dispatchCall(Call call) {
		System.out.println("***Numero Actual de Llamadas:" + calls.size());
		System.out.println("***Vacio:" + calls.isEmpty());
		Employee emp = null;
		imprimirEmpleadosLibres();

		emp = findEmployeAvailable();
		while (emp == null) {
			// Si llega hasta este punto es porque no encontro un empleado
			// que
			// pudiera atender la llamada en cuyo caso es necesario esperar
			// a que se
			// desocupe un empleado
			try {
				System.out.println(
						"No hay empleados disponibles para atender su llamada por favor mantengase en la linea");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		notify();

		while (!calls.isEmpty()) {

			answer(emp, call);

			
		}
		System.out.println("Despachando llamada");
	}

	/**
	 * Metodo de la interfaz Runnable que permite la ejecucion por hilos
	 */
	public void run() {
		System.out.println("Entrando metodo run");
		dispatchCall(calltoBeQueued);

	}

	/**
	 * Busca un empleado libre para atender la llamada segun el tipo de empleado
	 * 
	 * @param type
	 *            Tipo de Empleado
	 * @return Objeto empleado libre, null de lo contrario
	 */
	private synchronized Employee findbyType(TypeofEmployee type) {
		Employee employeeFree = null;

		for (String key : operators.keySet()) {

			Employee employee = operators.get(key);

			if (employee.getType().equals(type)) {
				if (employee.getState().equals(State.FREE)) {
					System.out.println("Encontró");
					employee.setState(State.OCCUPIED);
					currentEmployeesBusy++;
					return employee;

				}
			}

		}

		return employeeFree;

	}

	/**
	 * Busca el primer empleado libre para atender la llamada buscando primero
	 * por operador, luego por supervisor y finalmente por director
	 * 
	 * @return Objeto empleado libre, null de lo contrario
	 */
	private synchronized Employee findEmployeAvailable() {
		Employee empleadoOperador = findbyType(TypeofEmployee.OPERADOR);

		if (empleadoOperador != null) {
			return empleadoOperador;
		}
		Employee empleadoSupervisor = findbyType(TypeofEmployee.SUPERVISOR);
		if (empleadoSupervisor != null) {
			return empleadoSupervisor;
		}
		Employee empleadoDirector = findbyType(TypeofEmployee.DIRECTOR);
		if (empleadoDirector != null) {
			return empleadoDirector;
		}

		return null;

	}

	/**
	 * Permite contestar una llamada segun un empleado y llamada asignados
	 * 
	 * @param emp
	 *            Empleado que responde
	 * @param currentCall
	 *            Llamada por ser contestada
	 */
	public synchronized void answer(Employee emp, Call currentCall) {
		while(currentCall.isTaken()){
			System.out.println("Call Taken " + currentCall.isTaken());
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		if (!currentCall.isTaken()) {
			
			currentCall.setTaken(true);
			System.out.println("Habla " + emp.getName() + " en que le puedo colaborar " + currentCall.getName());

			// El tiempo de llamada es entre 5 y 10 segundos
			long randomNumberofMilliSeconds = ThreadLocalRandom.current().nextLong(5000, 10000);

			try {

				Thread.sleep(randomNumberofMilliSeconds);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			System.out.println("Tiempo de Llamada en milisegundos>" + randomNumberofMilliSeconds);
			System.out.println("Llamada Finalizada");

			System.out.println("Removiendo " + currentCall.getName());
			calls.remove(currentCall.getName());
			System.out.println("Tamaño despues de remocion" + calls.size());
			currentEmployeesBusy--;
			Employee empToRelease = this.operators.get(emp.getName());
			empToRelease.setState(State.FREE);

			imprimirEmpleadosLibres();
			operators.replace(empToRelease.getName(), empToRelease);
			notify();
			imprimirLlamadas();

		}
	

	}

	public Map<String, Call> getCalls() {
		return calls;
	}

	public void setCalls(Map<String, Call> calls) {
		this.calls = calls;
	}

	public Call getCalltoBeQueued() {
		return calltoBeQueued;
	}

	public void setCalltoBeQueued(Call calltoBeQueued) {
		System.out.println("Encolando llamada");
		this.calltoBeQueued = calltoBeQueued;
		this.calls.put(calltoBeQueued.getName(), calltoBeQueued);
	}

	private void imprimirEmpleadosLibres() {
		System.out.println("******Empleados******");
		for (Employee e : operators.values()) {
			System.out.println("NombreEmpleado" + e.getName() + "Estatus" + e.getState());
		}

	}

	private void imprimirLlamadas() {
		System.out.println("******Llamadas******");
		for (Call c : calls.values()) {
			System.out.println("NombreLlamada" + c.getName());
		}
	}
}
