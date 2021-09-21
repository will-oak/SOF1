package controller;

import java.util.Arrays;

public class Carro implements Comparable<Carro> {
	public long[] voltas = new long[3];
	public long voltaMaisRapida;
	public int idEscuderia;
	public int idCarro;
	
	

	public Carro(int idEscuderia, int idCarro) {
		this.idEscuderia = idEscuderia;
		this.idCarro = idCarro;
	}
	
	public long[] getVoltas() {
		return voltas;
	}
	
	public long[] getOrderedVoltas() {
		long[] orderedVoltas = voltas;
		
		Arrays.sort(orderedVoltas);
		
		return orderedVoltas;
	}
	
	public void getVoltaMaisRapida() {
		long voltas[] = getOrderedVoltas();
		
		voltaMaisRapida = voltas[0];
	}
	
	@Override
	public int compareTo(Carro outroCarro) {
		if (this.voltaMaisRapida < outroCarro.voltaMaisRapida) {
			return -1;
		} 

		if (this.voltaMaisRapida > outroCarro.voltaMaisRapida) {
			return 1;
		}
		
		return 0;
	}

}
