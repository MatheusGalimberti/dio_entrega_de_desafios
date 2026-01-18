package IPhone7;

import IPhone7.Interfaces.AparelhoTelefonico;
import IPhone7.Interfaces.NavegadorInternet;
import IPhone7.Interfaces.ReprodutorMusical;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class iPhone implements ReprodutorMusical, AparelhoTelefonico, NavegadorInternet {

    private boolean emLigacao = false;
    private boolean ocupado = false;

    private int quantidadeDeRecados = 0;
    private int paginasWeb = 0;

    private boolean musicaSelecionada = false;

    // Scheduler para chamadas automáticas
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private volatile boolean chamadasAtivas = false;

    // ========= GETTERS / SETTERS =========

    public boolean isEmLigacao() {
        return emLigacao;
    }

    public void setEmLigacao(boolean emLigacao) {
        this.emLigacao = emLigacao;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public int getQuantidadeDeRecados() {
        return quantidadeDeRecados;
    }

    public int getPaginasWeb() {
        return paginasWeb;
    }

    public boolean isMusicaSelecionada() {
        return musicaSelecionada;
    }

    public void setMusicaSelecionada(boolean musicaSelecionada) {
        this.musicaSelecionada = musicaSelecionada;
    }

    // ========= CONTROLE DAS CHAMADAS AUTOMÁTICAS =========

    public void iniciarChamadasAutomaticas() {
        if (chamadasAtivas) return;
        chamadasAtivas = true;

        // chama imediatamente e depois a cada 20 segundos
        scheduler.scheduleAtFixedRate(this::chamada, 0, 20, TimeUnit.SECONDS);
        System.out.println("✅ Chamadas automáticas iniciadas (a cada 20s).");
    }

    public void pararChamadasAutomaticas() {
        chamadasAtivas = false;
        scheduler.shutdownNow();
        System.out.println("🛑 Chamadas automáticas paradas.");
    }

    // ========= APARELHO TELEFONICO =========

    @Override
    public void ligar(int numero) {
        System.out.println("Ligando para o número: " + numero);
        setEmLigacao(true);
    }

    @Override
    public void atender() {
        System.out.println("Ligação atendida ✅");
        setEmLigacao(true);
    }

    @Override
    public void recusarLigacao() {
        System.out.println("Ligação recusada ❌. Enviando para o correio de voz...");
        quantidadeDeRecados++;
        setEmLigacao(false);
    }

    @Override
    public void desligandoLigacao() {
        System.out.println("Desligando a ligação 📵");
        setEmLigacao(false);
    }

    @Override
    public void iniciarCorreioDeVoz() {
        if (quantidadeDeRecados > 0) {
            System.out.println("📨 Começando a ouvir os recados...");
            System.out.println("✅ Todos os recados ouvidos.");
            quantidadeDeRecados = 0;
        } else {
            System.out.println("📭 Não tem recados no Correio de Voz.");
        }
    }

    /**
     * Chamada() que ocorre automaticamente a cada 20s.
     * Regras:
     * - Se NÃO estiver em ligação e NÃO estiver ocupado: oferece 1 atender / 2 recusar
     * - Se estiver ocupado OU em ligação: incrementa recado no correio de voz
     */
    @Override
    public void chamada() {
        System.out.println("\n📞 Chamada recebida...");

        if (!isEmLigacao() && !isOcupado()) {
            System.out.println("1 - Atender");
            System.out.println("2 - Recusar");

            try {
                Scanner sc = new Scanner(System.in);
                int opcao = sc.nextInt();

                if (opcao == 1) {
                    atender();
                } else if (opcao == 2) {
                    recusarLigacao();
                } else {
                    System.out.println("Opção inválida. Ligação recusada.");
                    recusarLigacao();
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Ligação recusada.");
                recusarLigacao();
            }

        } else {
            System.out.println("iPhone ocupado/em ligação. Salvando recado no correio de voz...");
            quantidadeDeRecados++;
        }
    }

    // ========= NAVEGADOR INTERNET =========

    @Override
    public void exibirPagina() {
        System.out.println("🌐 Abrindo página na Web");
        if (getPaginasWeb() == 0) {
            System.out.println("Não há nenhuma página aberta. Abrindo uma aba...");
            adicionarNovaAba();
        }
        setOcupado(true);
    }

    @Override
    public void adicionarNovaAba() {
        System.out.println("➕ Abrindo nova aba/página");
        paginasWeb += 1;
        setOcupado(true);
    }

    @Override
    public void atualizarPagina() {
        if (getPaginasWeb() >= 1) {
            System.out.println("🔄 Atualizando a página atual");
        } else {
            System.out.println("Não há página para atualizar.");
        }
    }

    @Override
    public void excluirPagina() {
        if (paginasWeb >= 1) {
            System.out.println("❎ Fechando página");
            paginasWeb -= 1;
        } else {
            System.out.println("Não há páginas abertas para fechar.");
        }

        if (paginasWeb == 0) setOcupado(false);
    }

    // ========= REPRODUTOR MUSICAL =========

    @Override
    public void tocar() {
        if (!isMusicaSelecionada()) {
            System.out.println("Nenhuma música selecionada. Selecione uma música primeiro.");
            return;
        }
        System.out.println("🎵 Tocando música");
        setOcupado(true);
    }

    @Override
    public void pausar() {
        System.out.println("⏸️ Pausando música");
        // opcional: não necessariamente "desocupa" o aparelho, mas vou deixar como false
        setOcupado(false);
    }

    @Override
    public void selecionarMusica() {
        System.out.println("🎶 Selecionando música");
        setMusicaSelecionada(true);
    }
}
