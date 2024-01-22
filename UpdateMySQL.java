// Importação de todos os itens da biblioteca SQL
import java.sql.*;
// Importação de todos os itens da biblioteca UTIL
import java.util.*;
// Declaração da Classe/Objeto UpdateMySQL
public class UpdateMySQL {

    /* 
     * Declação do metodo Executor main.
     * Public : Pode ser Invocado por outras Classes/Objetos.
     * Static : Não pode ser alterado ou sobrescritos.
     * Void : Um metodo sem retorno.
     * Strings[] : Pode ser invocados métodos do tipo String e Matrizes[].
     * args : porque podera ser invocado o objeto args da biblioteca java.lang
    */ 
    public static void main(String[] args) { // inicia o metodo executor
        // variavel scnLogin recebendo o scanner onde pode receber inputs do usuario.
        Scanner scnLogin = new Scanner(System.in);
        // variavel scnSenha recebendo o scanner onde pode receber inputs do usuario.
        Scanner scnSenha = new Scanner(System.in);
        // variavel scnResp recebendo o scanner onde pode receber inputs do usuario.
        Scanner scnResp = new Scanner(System.in);
        // variavel scnCadastroUpdate recebendo o scanner onde pode receber inputs do usuario.
        Scanner scnCadastroUpdate = new Scanner(System.in);
        // String [strLogin, strSenha e status] sendo criado.
        String strLogin, strSenha, status = "Nada aconteceu ainda...";
        // variavel inteira [resp] sendo criado.
        int resp;
        // variaveis booleana sendo criado.
        boolean update = false, validLogin = false;
        // variavel String [novoNome] e [NovaSenha] sendo criado e atribuido a elas valor vazio.
        String novoNome = "", novaSenha = "";
        // inicio o bloco que tenta pegar uma exceção.
        try { // inicia o [try]
            // laço de repetição sendo iniciado. Enquanto validLogin for falso o laço continuara sem parar.
            while (validLogin == false) { // inicia [while]
                // variavel [conn] recebendo o Objeto [App] e executando o metodo [conectar()]. Conexão com o bando de dados.
                Connection conn = App.conectar();
                // Imprimindo para o usuario para digitar o seu login.
                System.out.println("\nDigite seu login: ");
                // variavel String [strLogin] recebendo o Input do usuario.
                strLogin = scnLogin.nextLine();
                // Imprimindo ao usuario para digitar a sua senha.
                System.out.println("Digite sua senha: ");
                // variavel String [strSenha] recebendo Input do usuario.
                strSenha = scnSenha.nextLine();
                // variavel String [strSqlSelect] recebendo o comando para procurar o login e a senha no banco de dados MySQL
                String strSqlSelect = "select * from `mysql_connector`.`tbl_login` where `login` = '" + strLogin + "' and `senha` = '" + strSenha + "';";
                // variavel [stmSql] recebendo a conexão com o banco de dados da variavel [conn] e criando uma declaração
                Statement stmSql = conn.createStatement();
                // variavel [rsSql] recebendo todo o conteudo da variavel [stmSql] + [conn] e executando esse conteudo junto com o valor da variavel [strSqlSelect]
                ResultSet rsSql = stmSql.executeQuery(strSqlSelect);
                // variavel String [login] criado e sendo atribuido a ela o valor vazio.
                String login = "";
                // variavel String [senha] criado e sendo atribuido a ela o valor vazio.
                String senha = "";
                    // laço de repetição criado com a intenção de procurar informações de login e senha que o usuario informou.
                    while (rsSql.next()) { // inicia [while]
                        // enquanto o login que o usuario digitou exista, a varivel [login] ira receber a informação no banco de dados do campo login.
                        login = "[" + rsSql.getString("login") + "] ";
                        // enquanto a senha que o usuario digitou exista, a varivel [senha] ira receber a informação no banco de dados do campo senha.
                        senha = "[" + rsSql.getString("senha") + "] ";
                    } // encerra [while]
                    // condição criada com o teste logico sendo verdadeiro se o login OU a senha tendo valor vazio.
                    if (login == "" || senha == "") { // inicia [if]
                        // comando para esperar 2 segundos antes de executar a proxima linha de codigo.
                        Thread.sleep(2000);
                        // variavel [status] recebendo um valor de Login Invalido.
                        status = "\nLogin Invalido! Tente Novamente.";
                        // Imprimindo o valor da variavel [status] na tela para o usuario.
                        System.out.println(status);
                    } // encerra [if]
                      // condição que resultado deu falso. Se a condição colocada acima for falsa significa que achamos os dados do usuario no banco de dados.
                      else { // inicia [else]
                        // variavel [status] sendo atualizada informando o Login e a Senha que o usuario usou.
                        status = "Login usado: " + login + "\nSenha usada: " + senha;
                        // comanda para esperar 2 segundos para executar a proxima linha de codigo.
                        Thread.sleep(2000);
                        // Imprimindo ao usuario que o login foi realizado com sucesso.
                        System.out.println("\nBem vindo " + login);
                        // variavel [validLogin] sendo atribuido verdadeira para sair do primeiro laço de repetição ja que o login foi realizado com sucesso.
                        validLogin = true;
                        // variavel [update] recebendo falso para entrar no proximo laço de repetição. Esse laço sendo o Menu.
                        update = false;
                        // laço de repetição que ira continuar enquanto a variavel [update] for falsa. Esse laço de repetição é o Menu.
                        while (update == false) { // inicia [while]
                            // Imprimindo ao usuario que esse Menu é o Update de Cadastro.
                            System.out.println("\n--- Update de cadastro ---");
                            // Imprimindo ao usuario as opções do Menu de update de cadastro.
                            System.out.println("Digite [1] para alterar o nome.\nDigite [2] para alterar a senha.\nDigite [3] para sair.");
                            // variavel [resp] recebendo qual Input o usuario escolheu.
                            resp = scnResp.nextInt();
                            // condição criado que ira executar caso o usuario escolheu a primeira opção
                            if (resp == 1) { // inicia [if]
                                // Imprimindo ao usuario para digitar o novo nome que ele deseja a sua conta.
                                System.out.println("Digite o novo nome: ");
                                // variavel [novoNome] recebendo a resposta do usuario.
                                novoNome = scnCadastroUpdate.nextLine();
                                // variavel String [stmSqlUpdate] criado e recebendo o comando do MySQL que ira dar update no novo nome que o usuario desejou.
                                String stmSqlUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `nome` = '" + novoNome + "' WHERE (`login` = '" + strLogin + "')";
                                // variavel [prepareStm] criado e recebendo a variavel [conn] que tem a conexão com o banco de dados e preparando uma declaração com o valor da variavel [stmSqlUpdate] que tem o comando do MySQL para dar update na conta do usuario.
                                PreparedStatement preparedStm = conn.prepareStatement(stmSqlUpdate);
                                // executando o update com todo o conteudo que a varivel [prepareStm] recebeu.
                                preparedStm.executeUpdate();
                                // Informando ao usuario que o nome foi alterado e mostrando a ele para qual o novo nome foi criado.
                                System.out.println("\nNome alterado com sucesso para " + "[" + novoNome + "]");
                            } // encerra o [if]
                              // condição que ira ser executada caso o usuario escolha a segunda opção.
                              else if (resp == 2) { // inicia [else if]
                                // Perguntando ao usuario para digitar a sua senha atual.
                                System.out.println("Digite a senha atual:");
                                // variavel String [senhaAtual] criado e recebendo dados do usuario à pergunta acima.
                                String senhaAtual = scnCadastroUpdate.nextLine();
                                    // condição criada que ira ser executada caso a senha atual que o usuario digitou é a mesma que ele usou para logar. A senha que ele usou para logar ja passou pela validação do bando de dados.
                                    if (strSenha.equals(senhaAtual) || novaSenha.equals(senhaAtual)) { // inicia [if] dentro do [else if] acima
                                        // Imprimindo ao usuario para ele digitar a nova senha que ele deseja.
                                        System.out.println("Digite a nova senha: ");
                                        // variavel String [novaSenha] recebendo a nova senha que o usuario deseja.
                                        novaSenha = scnCadastroUpdate.nextLine();
                                        // Pedindo para o usuario confirmar a nova senha que ele deseja.
                                        System.out.println("Confirme a nova senha:");
                                        // variavel String [novaSenhaConf] sendo criado e recebendo o Input do usuario.
                                        String novaSenhaConf = scnCadastroUpdate.nextLine();
                                            
                                            if (novaSenha.equals(novaSenhaConf)) { // inicia [if] dentro do [if] acima que esta dentro do [else if]
                                                // variavel String [stmSqlUpdate] criada e recebendo o comando do MySQL para dar update na senha do usuario.
                                                String stmSqlUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `senha` = '" + novaSenha + "' WHERE (`login` = '" + strLogin + "')";
                                                // variavel [prepareStm] criada e recebendo a variavel [conn] que tem a conexão com o banco de dados e preparando uma declaração com a variavel [stmSqlUpdate] que tem os comando do MySQL para dar update na senha do usuario.
                                                PreparedStatement preparedStm = conn.prepareStatement(stmSqlUpdate);
                                                // variavel [prepareStm] executando todos os comandos e valores nela.
                                                preparedStm.executeUpdate();
                                                // Informando ao usuario que a sua senha foi alterada com sucesso e mostrando a nova senha escolhida.
                                                System.out.println("\nSenha alterada com sucesso para " + "[" + novaSenha + "]"); 
                                                // Peguntando ao usuario se ele deseja voltar a pagina inicial de login ou continuar no menu de update de cadastro.
                                                System.out.println("Deseja voltar à pagina inicial ou continuar? ['c' para continuar ou 's' para ir à pagina inicial]");
                                                // variavel String [resp2] criada e recebendo a resposta do usuario se ele deseja continuar ou voltar a pagina inicial.
                                                String resp2 = scnCadastroUpdate.nextLine();
                                                // condição criada que ira ser executada caso a resposta do usuario seja "s" OU "S".
                                                if (resp2.equals("s") || resp2.equals("S")) { // inicia [if]
                                                    // variavel [validLogin] recebendo falso para entrar no primeiro laço que repetiçao que é a tela inicial de login
                                                    validLogin = false;
                                                    // variavel [update] recebendo verdadeiro para sair do laço de repetição do Menu de Update de Cadastro e entra no laço de repetição do menu inicial de Login
                                                    update = true;
                                                } // encerra [if]
                                            } // encerra o segundo [if]
                                              // condição que ira ser executada caso a condição de confirmação de senha seja falsa.
                                              else { // inicia [else]
                                                // Informando ao usuario que a confirmação de senha é errada
                                                System.out.println("Confirmação de senha errado. Tente novamente.");
                                        } // encerra o [else]
                                    } // encerra o if da senha atual sendo verdadeira
                                     
                                      else { // inicia o [else] informando a que a senha atual é errada.
                                        // Informando ao usuario que a senha atual digirada é errada.
                                        System.out.println("Senha atual errada. Tente novamente.");
                                    } // encerra o [else]
                            } // encerra o [else if] que dar update na senha do usuario.
                              // condição que ira ser executada caso o usuario escolha a terceira opção
                              else if (resp == 3) { // inicia o [else if]
                                // variavel [status] recebendo novo valor
                                status = "\nVolte Novamente! " + login;
                                // variavel [update] recebendo o valor verdadeiro para sair do laço de repetição do menu de Update de Cadastro.
                                update = true;
                            } // encerra o [else if] 
                              // condição que ira ser executada caso o usario nao escolha nenhuma opção acima
                              else if (resp != 1 || resp != 2 || resp != 3) { // inicia [else if]
                                // Informe ao usuario que ocorreu um erro e para tentar novamente.
                                System.out.println("Ocorreu um erro! Tente Novamente.");
                            } // encerra [else if]
                        } // encerra o [while] do menu de Update de Cadastro.
                    } // encerra o primeiro [else]
                    stmSql.close();
                    rsSql.close();
                } // encerra o primeiro laço de repetição. laço da paginal inicial de Login
                // fechando o Scanner
                scnLogin.close();
                // fechando o Scanner
                scnSenha.close();
                // fechando o Scanner
                scnResp.close();
                // fechando o Scanner
                scnCadastroUpdate.close();    
        } // encerra o [try] 
          // inicia a execução da exceção
          catch (Exception e) { // inicia [catch]
            // Informa ao usuario que ocorreu um erro e mostra qual erro foi esse
            System.out.println("Ops! Ocorreu o erro " + e);
        } // encerra o [catch]
        // Imprimi o status com o seu valor atual.
        System.out.println(status);
    } // encerra o metodo executor.
} // encerra a Classe UpdateMySQL