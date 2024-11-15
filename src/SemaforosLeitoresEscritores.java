public class SemaforosLeitoresEscritores {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Thread leitor1 = new Thread(() -> {
            try {
                biblioteca.iniciarLeitura("Leitor 1");
                Thread.sleep(500);
                biblioteca.finalizarLeitura("Leitor 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread escritor = new Thread(() -> {
            try {
                biblioteca.escrever("Escritor");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        leitor1.start();
        escritor.start();
    }
}