import java.util.concurrent.CyclicBarrier;

class BibliotecaComBarreiras {
    private final CyclicBarrier barreira;
    private final StringBuilder conteudo = new StringBuilder("Conteúdo inicial.");

    public BibliotecaComBarreiras(int numeroDeLeitores) {
        barreira = new CyclicBarrier(numeroDeLeitores, () -> {
            // Ação a ser tomada após todos os leitores terminarem
            System.out.println("Todos os leitores terminaram. Escritor pode atualizar o conteúdo.");
        });
    }

    public void iniciarLeitura(String leitor) {
        System.out.println(leitor + " está lendo...");
        try {
            System.out.println(leitor + " leu: " + conteudo.toString());
            Thread.sleep(500);
            barreira.await(); // Aguarda todos os leitores
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escrever(String escritor) {
        System.out.println(escritor + " está escrevendo...");
        try {
            Thread.sleep(1000);
            conteudo.append("\nAtualizado por ").append(escritor);
            System.out.println(escritor + " terminou de escrever.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}