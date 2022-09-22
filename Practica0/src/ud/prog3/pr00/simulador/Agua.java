package ud.prog3.pr00.simulador;

import java.awt.*;

import javax.swing.*;

public class Agua extends ElementoEcosistema{
	
	protected long cantidad;
	private Point punto;
	private Dimension tama�o;
	private JPanel panel = null;
	
	public Agua(String titulo, int x, int y, int ancho, int alto) {
		this.titulo = titulo;
		this.punto = new Point(x, y);
		this.tama�o = new Dimension(ancho, alto);
		this.cantidad = (long)Math.sqrt((double)ancho * alto);
	}
	
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Agua: " + titulo + " - " + cantidad + " metros c�bicos - Coord(" + punto.getX() + "," + punto.getY() + ") - Tama�o (" + tama�o.getWidth() + "," + tama�o.getHeight() + ")";
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		if(this.panel == null) {
			this.panel = new JPanel();
			this.panel.setLayout(new BorderLayout());
			this.panel.add(new JLabel(titulo + "\n", SwingConstants.CENTER), BorderLayout.NORTH);
			this.panel.add(new JLabel(cantidad + "", SwingConstants.CENTER), BorderLayout.CENTER);
			this.panel.add(new JLabel(""), BorderLayout.SOUTH);
			this.panel.setLocation(punto);
			this.panel.setSize(tama�o);
			this.panel.setBackground(Color.CYAN);
		}
		return panel;
	}
}
