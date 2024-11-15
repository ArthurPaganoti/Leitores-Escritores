import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class BibliotecaComMutex {
    private int leitores = 0;
    private boolean escritorAtivo = false;
    private final Lock lock = new ReentrantLock();
    private final Condition podeLerOuEscrever = lock.newCondition();

    public void iniciarLeitura(String leitor) throws InterruptedException {
        lock.lock();
        try {
            while (escritorAtivo) {
                podeLerOuEscrever.await();
            }
            leitores++;
            System.out.println(leitor + " está lendo...");
        } finally {
            lock.unlock();
        }
    }

    public void finalizarLeitura(String leitor) {
        lock.lock();
        try {
            leitores--;
            if (leitores == 0) {
                podeLerOuEscrever.signalAll();
            }
            System.out.println(leitor + " terminou de ler.");
        } finally {
            lock.unlock();
        }
    }

    public void escrever(String escritor) throws InterruptedException {
        lock.lock();
        try {
            while (escritorAtivo || leitores > 0) {
                podeLerOuEscrever.await();
            }
            escritorAtivo = true;
            System.out.println(escritor + " está escrevendo...");
            Thread.sleep(1000); // Simula o tempo de escrita
            System.out.println(escritor + " terminou de escrever.");
            escritorAtivo = false;
            podeLerOuEscrever.signalAll();
        } finally {
            lock.unlock();
        }
    }
}