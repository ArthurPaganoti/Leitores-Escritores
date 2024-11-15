public class MonitoresLeitoresEscritores {
    public static void main(String[] args) {
        BibliotecaComMonitores biblioteca = new BibliotecaComMonitores();

        Thread leitor1 = new Thread(() -> {
            try {
                biblioteca.iniciarLeitura("Leitor 1");
                Thread.sleep(500);
                biblioteca.finalizarLeitura("Leitor 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread leitor2 = new Thread(() -> {
            try {
                biblioteca.iniciarLeitura("Leitor 2");
                Thread.sleep(500);
                biblioteca.finalizarLeitura("Leitor 2");
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
        leitor2.start();
        escritor.start();
    }
}