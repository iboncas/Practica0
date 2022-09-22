package ud.prog3.pr00.simulador;

public class PruebaEcosistema {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Agua agua = new Agua("Estanque", 100, 100, 50, 50);
		ColoniaAbejas colonia = new ColoniaAbejas("Colmena", 300, 300, 50, 50);
		PlantacionFlores plantacion = new PlantacionFlores("Flor", 500, 500, 50, 50);
		for(int i=0; i<10;i++) {
			System.out.println("Dia " + i);
			System.out.println(agua.toString());
			System.out.println(colonia.toString());
			System.out.println(plantacion.toString());
			colonia.evoluciona(1);
			plantacion.evoluciona(1);
			System.out.println("\n");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
