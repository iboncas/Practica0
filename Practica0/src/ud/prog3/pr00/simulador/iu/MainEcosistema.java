package ud.prog3.pr00.simulador.iu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ud.prog3.pr00.simulador.*;

public class MainEcosistema extends JFrame{
	
	private JPanel pCentral;
	private Hilo hilo;
	
	public MainEcosistema() {
		this.setSize(800, 600);
		this.setLocation(225, 50);
		this.setTitle("Ecosistema");
		
		this.pCentral = new JPanel();
		pCentral.setLayout(null);
		this.add(pCentral, BorderLayout.CENTER);
		JPanel pBotonera = new JPanel();
		JToggleButton bMover = new JToggleButton("Mover");
		JToggleButton bCrear = new JToggleButton("Crear");
		bCrear.setSelected(true);
		JComboBox<String> cbElementos = new JComboBox<String>(new String[] {"Abejas", "Flores", "Agua"});
		JButton bVida = new JButton("Vida");
		pBotonera.add(bMover);
		pBotonera.add(bCrear);
		pBotonera.add(cbElementos);
		pBotonera.add(bVida);
		this.add(pBotonera, BorderLayout.SOUTH);
		
		bMover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(bMover.isSelected()) {
					bMover.setSelected(true);
					bCrear.setSelected(false);
				}
			}
		});
		bCrear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(bCrear.isSelected()) {
					bCrear.setSelected(true);
					bMover.setSelected(false);
				}
				
			}
		});
		
		bVida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(bVida.getText().equals("Vida")) {
					bVida.setText("Parar");
					MainEcosistema.this.hilo = new Hilo();
					hilo.start();
				}else if(bVida.getText().equals("Parar")) {
					bVida.setText("Vida");
					if(hilo != null) {
						hilo.kill();
					}
				}
			}
		});
		
		pCentral.addMouseListener(new MouseAdapter() {
			Point coordPulsa = null;
			int i = 1,j = 1, k = 1;
			@Override
			public void mousePressed(MouseEvent e) {
				coordPulsa = e.getPoint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Point coordSuelta = e.getPoint();
				if(bCrear.isSelected()) {
					ElementoEcosistema ee = null;
					if(!e.getPoint().equals(coordPulsa)) {
						if(cbElementos.getSelectedItem().equals("Agua")) {
							ee = new Agua("Estanque " + i, (int)coordPulsa.getX(), (int)coordPulsa.getY(), (int)Math.abs(coordSuelta.getX() - coordPulsa.getX()), (int)Math.abs(coordSuelta.getY() - coordPulsa.getY()));
							i++;
						}else if(cbElementos.getSelectedItem().equals("Abejas")) {
							ee = new ColoniaAbejas("Abejas " + j, (int)coordPulsa.getX(), (int)coordPulsa.getY(), (int)Math.abs(coordSuelta.getX() - coordPulsa.getX()), (int)Math.abs(coordSuelta.getY() - coordPulsa.getY()));;
							j++;
						}else if(cbElementos.getSelectedItem().equals("Flores")) {
							ee = new PlantacionFlores("Plantación " + k, (int)coordPulsa.getX(), (int)coordPulsa.getY(), (int)Math.abs(coordSuelta.getX() - coordPulsa.getX()), (int)Math.abs(coordSuelta.getY() - coordPulsa.getY()));
							k++;
						}
						Ecosistema.getMundo().getElementos().add(ee);
						JPanel pAñadido = ee.getPanel();
						pCentral.add(pAñadido);
						pCentral.validate();
					}
				}else if(bMover.isSelected()) {
					for(ElementoEcosistema el: Ecosistema.getMundo().getElementos()) {
						if(el.getPanel().getBounds().contains(coordPulsa)) {
							el.getPanel().setLocation(coordSuelta);
							MainEcosistema.this.pCentral.add(el.getPanel());
							break;
						}
					}
				}
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	

	private class Hilo extends Thread{
		boolean activo = true;
		
		public Hilo() {}
		public void run() {
			while(activo) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(ElementoEcosistema el : Ecosistema.getMundo().getElementos()) {
					if(el instanceof Evolucionable) {
						Evolucionable ev = (Evolucionable)el;
						ev.evoluciona(1);
					}
				}
				pCentral.validate();
			}
		}
		public void kill() {
			activo = false;
		}
	}
	
	public static void main(String[] args) {
		MainEcosistema v = new MainEcosistema();
		v.setVisible(true);
	}
	
	
}
