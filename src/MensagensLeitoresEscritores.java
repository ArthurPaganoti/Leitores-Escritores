public class MensagensLeitoresEscritores {
    public static void main(String[] args) {
        BibliotecaComMensagens biblioteca = new BibliotecaComMensagens();

        Thread escritor = new Thread(() -> {
            try {
                biblioteca.escrever("Escritor");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread leitor = new Thread(() -> {
            try {
                biblioteca.ler("Leitor 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        escritor.start();
        leitor.start();
    }
}