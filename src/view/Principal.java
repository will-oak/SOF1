package view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


import controller.Carro;
import controller.F1Controller;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforoPista = new Semaphore(5);
		
		List<Carro> gridDeLargada = new ArrayList<Carro>();
		
		for (int idEscuderia = 1; idEscuderia <= 7; idEscuderia ++) {
			Semaphore semaforoEscuderia = new Semaphore(1);
			
			for (int idCarro = 1; idCarro <= 2; idCarro++) {
				Carro carro = new Carro(idEscuderia, idCarro);
				F1Controller f1 = new F1Controller(semaforoPista, semaforoEscuderia, carro, gridDeLargada);
				f1.start();
			}
		}

	}

}

 