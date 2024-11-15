import java.util.concurrent.Semaphore;

class Biblioteca {
    private int leitores = 0;
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore recurso = new Semaphore(1);

    public void iniciarLeitura(String leitor) throws InterruptedException {
        mutex.acquire();
        leitores++;
        if (leitores == 1) recurso.acquire(); // Primeiro leitor bloqueia escritores
        mutex.release();
        System.out.println(leitor + " está lendo...");
    }

    public void finalizarLeitura(String leitor) throws InterruptedException {
        mutex.acquire();
        leitores--;
        if (leitores == 0) recurso.release(); // Último leitor libera escritores
        mutex.release();
        System.out.println(leitor + " terminou de ler.");
    }

    public void escrever(String escritor) throws InterruptedException {
        recurso.acquire();
        System.out.println(escritor + " está escrevendo...");
        Thread.sleep(1000); // Simula a escrita
        System.out.println(escritor + " terminou de escrever.");
        recurso.release();
    }
}