package ud.prog3.pr00.simulador;

import java.awt.*;
import javax.swing.*;

public class PlantacionFlores extends ElementoEcosistema implements Evolucionable{
	
	protected long cantidad;
	private Point punto;
	private Dimension tamaño;
	private JPanel panel = null;
	private JLabel labCant = new JLabel("", SwingConstants.CENTER);
	
	public PlantacionFlores(String titulo, int x, int y, int ancho, int alto) {
		this.titulo = titulo;
		this.punto = new Point(x, y);
		this.tamaño = new Dimension(ancho, alto);
		this.cantidad = (long)Math.sqrt((double)ancho * alto);
	}
	
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public void evoluciona(int dias) {
		// TODO Auto-generated method stub
		double factorCrecimiento = 0.75;
		for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
			int dist = Ecosistema.distancia( this, ee );
			if (ee instanceof ColoniaAbejas) { // La cercanía de abejas beneficia
				if (dist < 500) factorCrecimiento = factorCrecimiento / dist * 500;
			} else if (ee instanceof Agua) { // La cercanía de agua beneficia
				if (dist < 500) factorCrecimiento = factorCrecimiento / dist * 500;
			}
			}
			cantidad = (long) (cantidad * factorCrecimiento * dias);
			if (cantidad > 5000) cantidad = 5000; 
			labCant.setText(""+cantidad);
		}
	@Override
	public String toString() {
		return "Flores: " + titulo + " - " + cantidad + " uds. - Coord(" + punto.getX() + "," + punto.getY() + ") - Tamaño (" + tamaño.getWidth() + "," + tamaño.getHeight() + ")";
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		if(this.panel == null) {
			this.panel = new JPanel();
			this.panel.setLayout(new BorderLayout());
			this.panel.add(new JLabel(titulo + "\n", SwingConstants.CENTER), BorderLayout.NORTH);
			this.panel.add(labCant, BorderLayout.CENTER);
			labCant.setText(cantidad+"");
			this.panel.add(new JLabel(""), BorderLayout.SOUTH);
			this.panel.setLocation(punto);
			this.panel.setSize(tamaño);
			this.panel.setBackground(Color.GREEN);
		}
		return panel;
	}
}
