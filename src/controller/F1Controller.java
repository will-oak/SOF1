package controller;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class F1Controller extends Thread {
	Semaphore semaforoPista, semaforoEscuderia;
	Carro carro;
	private int tamanhoPista = 4310;
	private int distanciaPercorrida = 0;
	private int qtdVoltas = 3;
	private static int terminou = 0;
	List<Carro> gridDeLargada;

	public F1Controller(Semaphore semaforoPista, Semaphore semaforoEscuderia, Carro carro, List<Carro> gridDeLargada) {
		this.semaforoPista = semaforoPista;
		this.semaforoEscuderia = semaforoEscuderia;
		this.carro = carro;
		this.gridDeLargada = gridDeLargada;
	}

	@Override
	public void run() {
		try {
			semaforoEscuderia.acquire();
			try {
				semaforoPista.acquire();
				correrNaPista();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoPista.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforoEscuderia.release();
		}

		if (terminou >= 14) {
			Collections.sort(gridDeLargada);
			
			
			gridDeLargada.forEach(carro -> {
				System.out.println("Carro (" + carro.idCarro + "), escuderia (" + carro.idEscuderia + "), tempo: " + carro.voltaMaisRapida + "ms.");
			});
		}

	}

	public void correrNaPista() {
		System.out.println("Carro (" + carro.idCarro + "), da escuderia (" + carro.idEscuderia + ") saiu para correr");

		for (int volta = 0; volta < qtdVoltas; volta++) {
			long tempoVoltaInicio = System.currentTimeMillis();
			distanciaPercorrida = 0;

			do {
				distanciaPercorrida += (int) ((Math.random() * 200) + 1);
				
				try {
					sleep((int) ((Math.random() * 50) + 10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			} while (distanciaPercorrida < tamanhoPista);

			long tempoVoltaFim = System.currentTimeMillis();
			carro.voltas[volta] = (tempoVoltaFim - tempoVoltaInicio);

			System.out.println("Carro (" + carro.idCarro + "), da escuderia (" + carro.idEscuderia + ") deu volta "
					+ (volta + 1) + " em " + carro.voltas[volta] + "ms.");
		}

		carro.getVoltaMaisRapida();
		gridDeLargada.add(carro);
		terminou++;
	}

}
