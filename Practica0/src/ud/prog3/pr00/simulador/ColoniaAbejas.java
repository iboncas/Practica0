package ud.prog3.pr00.simulador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ColoniaAbejas extends ElementoEcosistema implements Evolucionable{
	
	protected long poblacion;
	private Point punto;
	private Dimension tamaño;
	private JPanel panel = null;
	
	public ColoniaAbejas(String titulo, int x, int y, int ancho, int alto) {
		this.titulo = titulo;
		this.punto = new Point(x, y);
		this.tamaño = new Dimension(ancho, alto);
		this.poblacion = (long)Math.sqrt((double)ancho * alto);
	}
	
	public long getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(long poblacion) {
		this.poblacion = poblacion;
	}

	@Override
	public void evoluciona(int dias) {
		// TODO Auto-generated method stub
		double factorCrecimiento = 1.0;
		int numFlores = 0;
		for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
			int dist = Ecosistema.distancia( this, ee );
			if (ee instanceof ColoniaAbejas && ee!=this) { // Otra colonia de abejas perjudica
				if (dist < 500) factorCrecimiento = factorCrecimiento * dist / 500;
			} else if (ee instanceof PlantacionFlores) { // La cercanía de flores beneficia
				if (dist < 500) factorCrecimiento = factorCrecimiento / dist * 500;
				numFlores += ((PlantacionFlores)ee).getCantidad();
			}
		}
		 if (numFlores < 50) factorCrecimiento *= 0.1; // Insuficientes flores mata
		 	poblacion = (long) (poblacion * factorCrecimiento * dias);
		 if (poblacion > 5000) poblacion = 5000; // Límite de población
		}	
	@Override
	public String toString() {
		return "Abejas: " + titulo + " - " + poblacion + " individs. - Coord(" + punto.getX() + "," + punto.getY() + ") - Tamaño (" + tamaño.getWidth() + "," + tamaño.getHeight() + ")";
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		if(this.panel == null) {
			this.panel = new JPanel();
			this.panel.setLayout(new BorderLayout());
			this.panel.add(new JLabel(titulo + "\n", SwingConstants.CENTER), BorderLayout.NORTH);
			this.panel.add(new JLabel(poblacion + "", SwingConstants.CENTER), BorderLayout.CENTER);
			this.panel.add(new JLabel(""), BorderLayout.SOUTH);
			this.panel.setLocation(punto);
			this.panel.setSize(tamaño);
			this.panel.setBackground(Color.YELLOW);
		}
		return panel;
	}
}
