package ud.prog3.pr00.simulador;

import java.awt.*;

import javax.swing.JPanel;

public abstract class ElementoEcosistema {
	
	protected String titulo;
	private Point posicion;
	private Dimension dimension;
	public abstract JPanel getPanel();

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Point getPosicion() {
		return posicion;
	}
	public Dimension getDimension() {
		return dimension;
	}
	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
}
