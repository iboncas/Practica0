package ud.prog3.pr00.simulador;

import java.awt.*;

import javax.swing.*;

public class Agua extends ElementoEcosistema{
	
	protected long cantidad;
	private Point punto;
	private Dimension tamaño;
	private JPanel panel = null;
	
	public Agua(String titulo, int x, int y, int ancho, int alto) {
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
	public String toString() {
		return "Agua: " + titulo + " - " + cantidad + " metros cúbicos - Coord(" + punto.getX() + "," + punto.getY() + ") - Tamaño (" + tamaño.getWidth() + "," + tamaño.getHeight() + ")";
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
			this.panel.setSize(tamaño);
			this.panel.setBackground(Color.CYAN);
		}
		return panel;
	}
}
