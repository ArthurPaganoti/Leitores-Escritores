class BibliotecaComMonitores {
    private int leitores = 0;
    private boolean escritorAtivo = false;

    public synchronized void iniciarLeitura(String leitor) throws InterruptedException {
        while (escritorAtivo) {
            wait();
        }
        leitores++;
        System.out.println(leitor + " está lendo...");
    }

    public synchronized void finalizarLeitura(String leitor) {
        leitores--;
        if (leitores == 0) {
            notifyAll(); // Avisa os escritores que podem prosseguir
        }
        System.out.println(leitor + " terminou de ler.");
    }

    public synchronized void escrever(String escritor) throws InterruptedException {
        while (escritorAtivo || leitores > 0) {
            wait();
        }
        escritorAtivo = true;
        System.out.println(escritor + " está escrevendo...");
        Thread.sleep(1000); // Simula o tempo de escrita
        System.out.println(escritor + " terminou de escrever.");
        escritorAtivo = false;
        notifyAll(); // Avisa leitores e outros escritores que podem prosseguir
    }
}