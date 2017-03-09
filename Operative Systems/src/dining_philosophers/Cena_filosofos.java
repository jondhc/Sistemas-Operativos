package dining_philosophers;

import java.util.Random;

class tenedor {
	private static int contador = 0;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		return "Tenedor " + (contador+1);
	}
}

class filosofo extends Thread {
	private static Random rnd = new Random();
	private static int contador = 0;
	private tenedor Tizquierdo;
	private tenedor Tderecho;
	static int espera = 0;
	public filosofo(tenedor izquierdo, tenedor derecho){
		Tizquierdo = izquierdo;
		Tderecho = derecho;
		start();
	}
	public void pensar(){
		System.out.println(this + " está pensando");
		if (espera>0){
			try{
				sleep(rnd.nextInt(espera));
			}
			catch (InterruptedException e){
				throw new RuntimeException(e);
			}
		}
	}
	public void comer(){
		synchronized (Tizquierdo){
			System.out.println(this + " tiene " + this.Tizquierdo + " esperando el " + this.Tderecho);
			synchronized (Tderecho){
				System.out.println(this + " tiene ambos tenedores");
				System.out.println(this + " comiendo");
			}
		}
	}
	@Override
	public String toString(){
		return "Filósofo "+ (contador+1);
	}
	@Override
	public void run(){
		while (true){
			pensar();
			comer();
		}
	}
}


public class Cena_filosofos {
	public static void main(String[] args) {
		filosofo[] Uno = new filosofo[10];
		filosofo[] Dos = new filosofo [10];
		filosofo[] Tres = new filosofo [10];
		filosofo[] Cuatro = new filosofo [10];
		filosofo[] Cinco = new filosofo [10];
		filosofo.espera=8;
		tenedor izquierdo = new tenedor(), derecho = new tenedor(), primero=izquierdo;
		int i = 0;
		while (i<Uno.length-1){
			Uno[i++] = new filosofo(izquierdo, derecho);
			izquierdo=derecho;
			derecho = new tenedor();
		}
	}
}

	