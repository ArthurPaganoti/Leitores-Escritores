import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class BibliotecaComMensagens {
    private final BlockingQueue<String> fila = new LinkedBlockingQueue<>();

    public void escrever(String escritor) throws InterruptedException {
        System.out.println(escritor + " está escrevendo...");
        fila.put("Novo conteúdo escrito por " + escritor);
        System.out.println(escritor + " terminou de escrever.");
    }

    public void ler(String leitor) throws InterruptedException {
        System.out.println(leitor + " está lendo...");
        String conteudo = fila.take(); // Lê o conteúdo mais recente
        System.out.println(leitor + " leu: " + conteudo);
    }
}