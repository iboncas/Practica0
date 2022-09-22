package ud.prog3.pr00.simulador;

import java.util.ArrayList;

public class Ecosistema {
	
	private ArrayList<ElementoEcosistema> listaElementos = new ArrayList<ElementoEcosistema>();
	private static Ecosistema ecosistema = new Ecosistema();

	public ArrayList<ElementoEcosistema> getElementos() {
		return listaElementos;
	}
	public void setElementos(ArrayList<ElementoEcosistema> listaElementos) {
		this.listaElementos = listaElementos;
	}
	public static Ecosistema getMundo() {
		return ecosistema;
	}
	
	public static int distancia(ElementoEcosistema e1, ElementoEcosistema e2) {
		return (int)Math.sqrt(Math.pow(e2.getPanel().getX() - e1.getPanel().getX(), 2) + Math.pow(e2.getPanel().getY() - e1.getPanel().getY(), 2));
	}
}
