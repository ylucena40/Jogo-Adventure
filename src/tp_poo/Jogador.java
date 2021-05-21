/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Yan
 */
public class Jogador extends Itens {

    private int numSala;
    private int sala1;
    private int sala2;
    private int qtdItens;
    private ArrayList<String> chaves = new ArrayList<String>();
    private static Jogador usuario;

    private Jogador() {
        numSala = 1;
        sala1 = 3;
        sala2 = 0;
        qtdItens = 0;
    }

    public static synchronized Jogador getInstace() {
        if (usuario == null) {
            usuario = new Jogador();
        }
        return usuario;
    }

    public void mostrarOuroDiamante() {
        System.out.println("Diamante: " + this.usuario.getDiamante());
        System.out.println("Ouro: " + this.usuario.getOuro());
    }

    public boolean verificaPocao() {
        if (this.usuario.getPocao() > 0) {
            this.usuario.setPocao(this.usuario.getPocao() - 1);
            return true;
        }
        return false;
    }

    public boolean verificaMachado() {
        if (this.usuario.getMachado() > 0) {
            this.usuario.setMachado(this.usuario.getMachado() - 1);
            return true;
        }
        return false;
    }

    public void removerItem(Mapa a) {
        System.out.println("Esolha um item para poder remover da mochila.");
        Scanner scanner = new Scanner(System.in);
        int s = -1;
        while (true) {
            imprimeItensMochila();
            System.out.printf("Digite o numero do item: ");
            s = -2;
            while (s == -2) {
                try {
                    s = scanner.nextInt();
                } catch (Exception ErroInt) {
                    System.out.println("Digite novamente");
                }
            }
            if (s == 0) {
                break;
            } else if (s == 3) {
                System.out.println("Chaves:");
                for (int i = 0; i < usuario.getChaves().size(); i++) {
                    System.out.println((i + 1) + ":  " + usuario.getChaves().get(i));
                }
                System.out.println("Escolha uma chave acima para remover digitando o numero antes do ':' .\nOu digite 0 para sair.");
                Scanner sc = new Scanner(System.in);
                int r = 0;
                s = -2;
                while (s == -2) {
                    try {
                        s = sc.nextInt();
                    } catch (Exception ErroInt) {
                        System.out.println("Digite novamente");
                    }
                }
                if (s == 0) {
                    return;
                }
                if (s - 1 < usuario.getChaves().size()) {
                    ArrayList<String> ar = new ArrayList<String>();
                    ar.add(usuario.getChaves().get(s - 1));
                    if (a.getSala().get(sala1).get(sala2).getChave() == null) {
                        a.getSala().get(sala1).get(sala2).setChave(ar);
                    } else {
                        a.getSala().get(sala1).get(sala2).getChave().add(usuario.getChaves().get(s - 1));
                    }
                    usuario.getChaves().remove(s - 1);
                }
            } else {
                int qtd;
                while (true) {
                    System.out.printf("Digite a quantidade: ");
                    qtd = -2;
                    while (qtd == -2) {
                        try {
                            qtd = scanner.nextInt();
                        } catch (Exception ErroInt) {
                            System.out.println("Digite novamente");
                        }
                    }
                    if (qtd <= this.usuario.getPocao() && qtd >= 0) {
                        break;
                    } else if (qtd <= this.usuario.getMachado() && qtd >= 0) {
                        break;
                    } else {
                        System.out.println("Quantidade indisponivel, digite uma quantidade valida");
                    }
                }
                while (true) {
                    if (qtdItens - qtd >= 0) {
                        if (s == 1) {
                            usuario.setPocao(usuario.getPocao() - qtd);
                            qtdItens -= qtd;
                            a.getSala().get(sala1).get(sala2).setPocao(a.getSala().get(sala1).get(sala2).getPocao() + qtd);
                            break;
                        } else if (s == 2) {
                            usuario.setMachado(usuario.getMachado() - qtd);
                            qtdItens -= qtd;
                            a.getSala().get(sala1).get(sala2).setMachado(a.getSala().get(sala1).get(sala2).getMachado() + qtd);
                            break;
                        } else {
                            System.out.println("Esse valor não entra nos padroes da mochila.");
                            System.out.printf("Digite '-1' para sair, ou digite novamente a quantidade: ");
                            qtd = -2;
                            while (qtd == -2) {
                                try {
                                    qtd = scanner.nextInt();
                                } catch (Exception ErroInt) {
                                    System.out.println("Digite novamente");
                                }
                            }
                            if (qtd == -1) {
                                break;
                            }
                        }

                    } else {
                        System.out.println("Esse valor não entra nos padroes da mochila.");
                        System.out.printf("Digite '-1' para sair, ou digite novamente a quantidade: ");
                        qtd = -2;
                        while (qtd == -2) {
                            try {
                                qtd = scanner.nextInt();
                            } catch (Exception ErroInt) {
                                System.out.println("Digite novamente");
                            }
                        }
                        if (qtd == -1) {
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

    public void imprimeItensMochila() {
        if (this.usuario.getChaves() == null) {
            System.out.println(
                    "0.Sair\n1.Pocao: " + this.usuario.getPocao()
                    + "\n2.Machado: " + this.usuario.getMachado()
                    + "\n3.Chave: " + "nao ha chaves na mochila"
            );
        } else {
            System.out.println(
                    "0.Sair\n1.Pocao: " + this.usuario.getPocao()
                    + "\n2.Machado: " + this.usuario.getMachado()
                    + "\n3.Chave: "
            );
            for (int i = 0; i < usuario.getChaves().size(); i++) {
                System.out.printf("%s,  ", usuario.getChaves().get(i));
            }
        }
        System.out.println();
    }

    public void adicionaItem(Mapa a) {
        if (qtdItens >= 6) {
            System.out.println("Mochila cheia");
            System.out.println("Pegue apenas ouro e diamante.\n1.Ouro." + usuario.getOuro() + "\n2.Diamante" + usuario.getDiamante());
            Scanner scanner = new Scanner(System.in);
            int s = -1;
            s = -2;
            while (s == -2) {
                try {
                    s = scanner.nextInt();
                } catch (Exception ErroInt) {
                    System.out.println("Digite novamente");
                }
            }
            if (s == 1) {
                usuario.setOuro(usuario.getOuro() + a.getSala().get(sala1).get(sala2).getOuro());
                a.getSala().get(sala1).get(sala2).setOuro(0);
            } else if (s == 2) {
                usuario.setDiamante(usuario.getDiamante() + a.getSala().get(sala1).get(sala2).getDiamante());
                a.getSala().get(sala1).get(sala2).setDiamante(0);
            }
            return;
        }
        System.out.println("Esolha um item para poder adicionar a mochila.");
        Scanner scanner = new Scanner(System.in);
        int s = -1;
        while (true) {
            imprimeItensSala(a);
            System.out.printf("Digite o numero do item: ");
            s = -2;
            while (s == -2) {
                try {
                    s = scanner.nextInt();
                } catch (Exception ErroInt) {
                    System.out.println("Digite novamente");
                }
            }
            if (s == 0) {
                break;
            }
            if (s == 1) {
                usuario.setOuro(usuario.getOuro() + a.getSala().get(sala1).get(sala2).getOuro());
                a.getSala().get(sala1).get(sala2).setOuro(0);
            } else if (s == 2) {
                usuario.setDiamante(usuario.getDiamante() + a.getSala().get(sala1).get(sala2).getDiamante());
                a.getSala().get(sala1).get(sala2).setDiamante(0);
            } else if (s == 3) {

                System.out.println("Digite o numero da chave que você quer adicionar:");
                for (int i = 0; i < a.getSala().get(sala1).get(sala2).getChave().size(); i++) {
                    System.out.println(i + 1 + ":  " + a.getSala().get(sala1).get(sala2).getChave().get(i));
                }
                Scanner s1 = new Scanner(System.in);
                int ar;
                ar = -2;
                while (ar == -2) {
                    try {
                        ar = s1.nextInt();
                    } catch (Exception ErroInt) {
                        System.out.println("Digite novamente");
                    }
                }

                if (ar - 1 < a.getSala().get(sala1).get(sala2).getChave().size()) {
                    //ArrayList<String> ar = new ArrayList<String>();
                    //ar.add(a.getSala().get(sala1).get(sala2).getChave().get(s - 1));
                    this.chaves.add(a.getSala().get(sala1).get(sala2).getChave().get(ar - 1));
                    a.getSala().get(sala1).get(sala2).getChave().remove(ar - 1);
                    qtdItens += 1;
                }
            } else {
                int qtd;
                while (true) {
                    System.out.printf("Digite a quantidade: ");
                    qtd = -2;
                    while (qtd == -2) {
                        try {
                            qtd = scanner.nextInt();
                        } catch (Exception ErroInt) {
                            System.out.println("Digite novamente");
                        }
                    }
                    if (qtd <= a.getSala().get(sala1).get(sala2).getPocao() && qtd >= 0) {
                        break;
                    } else if (qtd <= a.getSala().get(sala1).get(sala2).getMachado() && qtd >= 0) {
                        break;
                    } else {
                        System.out.println("Quantidade indisponivel, digite uma quantidade valida");
                    }
                }
                while (true) {
                    if (qtd + qtdItens <= 6) {
                        if (s == 4) {
                            usuario.setPocao(usuario.getPocao() + qtd);
                            qtdItens += qtd;
                            a.getSala().get(sala1).get(sala2).setPocao(a.getSala().get(sala1).get(sala2).getPocao() - qtd);
                            break;
                        } else if (s == 5) {
                            usuario.setMachado(usuario.getMachado() + qtd);
                            qtdItens += qtd;
                            a.getSala().get(sala1).get(sala2).setMachado(a.getSala().get(sala1).get(sala2).getMachado() - qtd);
                            break;
                        } else {
                            System.out.println("Esse valor não entra nos padroes da mochila.");
                            System.out.printf("Digite '-1' para sair, ou digite novamente a quantidade: ");
                            qtd = -2;
                            while (qtd == -2) {
                                try {
                                    qtd = scanner.nextInt();
                                } catch (Exception ErroInt) {
                                    System.out.println("Digite novamente");
                                }
                            }
                            if (qtd == -1) {
                                break;
                            }
                        }

                    } else {
                        System.out.println("Essa quantidade utrapassa o limite da mochila, digite uma quantidade menor ou nao adicione o item.");
                        System.out.printf("Digite '-1' para sair, ou digite novamente a quantidade: ");
                        qtd = -2;
                        while (qtd == -2) {
                            try {
                                qtd = scanner.nextInt();
                            } catch (Exception ErroInt) {
                                System.out.println("Digite novamente");
                            }
                        }
                        if (qtd == -1) {
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

    public void imprimeItensSala(Mapa a) {
        if (a.getSala().get(sala1).get(sala2).getChave() == null) {
            System.out.println("0.Sair: \n1.Ouro: " + a.getSala().get(sala1).get(sala2).getOuro()
                    + "\n2.Diamante: " + a.getSala().get(sala1).get(sala2).getDiamante()
                    + "\n3.Chave: " + "nao ha chaves na sala"
                    + "\n4.Pocao: " + a.getSala().get(sala1).get(sala2).getPocao()
                    + "\n5.Machado: " + a.getSala().get(sala1).get(sala2).getMachado()
            );
        } else {
            System.out.println("0.Sair: \n1.Ouro: " + a.getSala().get(sala1).get(sala2).getOuro()
                    + "\n2.Diamante: " + a.getSala().get(sala1).get(sala2).getDiamante()
                    + "\n3.Chaves: " + a.getSala().get(sala1).get(sala2).getChave()
                    + "\n4.Pocao: " + a.getSala().get(sala1).get(sala2).getPocao()
                    + "\n5.Machado: " + a.getSala().get(sala1).get(sala2).getMachado()
            );
        }
    }

    public void imprimeMochila() {
        if (usuario.getChaves() == null) {
            System.out.println("Ouro: " + usuario.getOuro()
                    + "\nDiamante: " + usuario.getDiamante()
                    + "\nPocao: " + usuario.getPocao()
                    + "\nMachado: " + usuario.getMachado()
                    + "\nChave: " + "nao ha chaves na sala"
            );
        } else {
            System.out.printf("Ouro: %d" + usuario.getOuro()
                    + "\nDiamante: %d" + usuario.getDiamante()
                    + "\nPocao: %d" + usuario.getPocao()
                    + "\nMachado:%d " + usuario.getMachado()
                    + "\nChave: "
            );
            for (int i = 0; i < usuario.getChaves().size(); i++) {
                System.out.printf("%s,  ", usuario.getChaves().get(i));
            }

        }
    }

    public int mudarDeSala(Mapa a) {
        System.out.printf("Portas abertas para as salas: ");
        for (int i = 0; i < a.getPortas().get(numSala - 1).size(); i++) {
            if (a.getPortas().get(numSala - 1).get(i) == 1) {
                System.out.printf("%d  ", i + 1);
            }
        }
        System.out.printf("\nPortas fechadas para as salas: ");
        for (int i = 0; i < a.getPortas().get(numSala - 1).size(); i++) {
            if (a.getPortas().get(numSala - 1).get(i) == 2) {
                System.out.printf("%d  ", i + 1);
            }
        }
        if (numSala == 4) {
            System.out.println("Saida");
            System.out.println("Para selecionar a saida, digite numero 100 na selecao da porta");
        }
        System.out.println("\nEscolha o numero da sala que voce deseja ir:");
        Scanner scanner = new Scanner(System.in);
        int s = -2;
        while (s == -2) {
            try {
                s = scanner.nextInt();
            } catch (Exception ErroInt) {
                System.out.println("Digite novamente");
            }
        }
        int aux1 = 0;
        if (s == 100) {
            if (usuario.getChaves().contains("Saida")) {
                System.out.println("Parabéns, você concluiu o jogo");
                return 100;
            } else {
                System.out.println("Voce nao contém a chave da saida.");
                mudarDeSala(a);
            }

        }
        while (true) {
            for (int i = 0; i < a.getPortas().get(numSala - 1).size(); i++) {
                if (a.getPortas().get(numSala - 1).get(i) > 0) {
                    if (s == i + 1) {
                        aux1++;
                    }
                }
            }
            if (aux1 > 0) {
                break;
            } else {
                System.out.println("Numero de sala indisponível, digite novamente.");
                s = -2;
                while (s == -2) {
                    try {
                        s = scanner.nextInt();
                    } catch (Exception ErroInt) {
                        System.out.println("Digite novamente");
                    }
                }
            }
        }
        int aux = 0;
        if (a.getPortas().get(numSala - 1).get(s - 1) == 2) {
            aux++;
        }

        if (aux == 0) {
            String st = new String();
            st = a.verificaNumSala(s);
            String si = new String();
            String sj = new String();
            int aux2 = 0;
            for (int l = 0; l < st.length(); l++) {
                if (st.charAt(l) == '-') {
                    aux2++;
                } else if (aux2 == 0) {
                    si += st.charAt(l);
                } else {
                    sj += st.charAt(l);
                }
            }

            usuario.setSala1(Integer.parseInt(si));
            usuario.setSala2(Integer.parseInt(sj));
            usuario.setNumSala(s);

        } else {
            String st = Integer.toString(s);
            int aux2 = 0;
            for (int i = 0; i < usuario.getChaves().size(); i++) {
                if (usuario.getChaves().get(i).contains(st)) {
                    aux2++;
                    break;
                }
            }
            if (aux2 == 0) {
                System.out.println("Porta trancada e voce nao tem a chave.\nEscolha novamente.");
                mudarDeSala(a);
            } else {
                String st1 = new String();
                st = a.verificaNumSala(s);
                String si = new String();
                String sj = new String();
                int aux3 = 0;
                for (int l = 0; l < st1.length(); l++) {
                    if (st1.charAt(l) == '-') {
                        aux3++;
                    } else if (aux3 == 0) {
                        si += st1.charAt(l);
                    } else {
                        sj += st1.charAt(l);
                    }
                }

                usuario.setSala1(Integer.parseInt(si));
                usuario.setSala2(Integer.parseInt(sj));
                usuario.setNumSala(s);
                a.getPortas().get(numSala - 1).set(s - 1, 1);
            }
        }
        return 1;

    }

    public int getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(int qtdItens) {
        this.qtdItens = qtdItens;
    }

    public ArrayList<String> getChaves() {
        return chaves;
    }

    public void setChaves(ArrayList<String> chaves) {
        this.chaves = chaves;
    }

    public int getNumSala() {
        return numSala;
    }

    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    public int getSala1() {
        return sala1;
    }

    public void setSala1(int sala1) {
        this.sala1 = sala1;
    }

    public int getSala2() {
        return sala2;
    }

    public void setSala2(int sala2) {
        this.sala2 = sala2;
    }

}
