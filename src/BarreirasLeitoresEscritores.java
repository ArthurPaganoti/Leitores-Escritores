public class BarreirasLeitoresEscritores {
    public static void main(String[] args) {
        BibliotecaComBarreiras biblioteca = new BibliotecaComBarreiras(3);

        for (int i = 1; i <= 3; i++) {
            final int leitorId = i;
            new Thread(() -> biblioteca.iniciarLeitura("Leitor " + leitorId)).start();
        }

        new Thread(() -> biblioteca.escrever("Escritor")).start();
    }
}