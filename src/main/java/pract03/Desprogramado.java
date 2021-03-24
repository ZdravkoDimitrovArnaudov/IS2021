package pract03;

import java.util.Date;

public class Desprogramado extends AlarmasState {

	@Override
	public void alarmaOn(String id, Alarmas context) {
		this.exitAction(context);
		context.setState(estadoProgramado);
		// Acciones asociadas a la transicion
		context.activaAlarma(context.buscaAlarmaByID(id));
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);
	}

	@Override
	public void borraAlarma(String id, Alarmas context) {
		this.exitAction(context); 
		context.setState(estadoDesprogramado);
		// Acciones asociadas a la transicion
		context.eliminaAlarma(context.buscaAlarmaByID(id));
		estadoDesprogramado.entryAction(context);
		estadoDesprogramado.doAction(context);
	}

	@Override
	public void nuevaAlarma(String id, Date hora, Alarmas context) {
		this.exitAction(context);
		context.setState(estadoProgramado);
		// Acciones asociadas a la transicion
		context.anhadeAlarma(new Alarma(id, hora));
		estadoProgramado.entryAction(context);
		estadoProgramado.doAction(context);			
	}

	@Override
	public void apagar(Alarmas context) {}

	@Override
	public void alarmaOff(String id, Alarmas context) {}

	@Override
	public void entryAction(Alarmas context) {}

	@Override
	public void doAction(Alarmas context) {}

	@Override
	public void exitAction(Alarmas context) {}
}
