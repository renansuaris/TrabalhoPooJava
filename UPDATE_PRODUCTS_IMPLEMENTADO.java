//Nova feature no código, função de atualizar produtos está disponível. Assim ficou a classe de operações e main respectivamente:
//As outras classes não sofreram alterações!

public class Operacoes {
	
	// Atributos
	private List<Produto> produtos;
    private List<Produto> carrinho;
    private Scanner scanner; 
    
    // Método Construtor
    public Operacoes() {
        this.produtos = new ArrayList<>();
        this.carrinho = new ArrayList<>();
        this.scanner = new Scanner(System.in); //inicializar o Scanner
    }
    
    // Método para retornar a lista de produtos
    public List<Produto> getProdutos(){
        return this.produtos;
    }
    
    //método para retornar o carrinho do usuário
    public List<Produto> getCarrinho(){
        return this.carrinho;
    }
    
    // Método para cadastrar produtos no Sistema
    // Possui tratamento de exceção caso o input
    // nao seja como o esperado ou caso o produto
    // já esteja cadastrado no sistema (PJCException)
    public void cadastrarProdutos() {
    	
        try {
        	
        	System.out.print("Digite o nome do produto: ");
        	String nome = scanner.nextLine();
        	System.out.print("\nDigite o preço do produto: ");
        	double preco = Double.parseDouble(scanner.nextLine());
        	System.out.println("\nEscolha a categoria do produto: ");
        	System.out.println("【1】Alimentos 【2】Eletrônicos");
        	System.out.println("【3】Móveis    【4】Eletrodoméstico");
        	System.out.println("【5】Produto de Limpeza");
		
        	int categoria = Integer.parseInt(scanner.nextLine());
        	int id = this.idGenerator();
        	
		    	switch(categoria){
		        	case 1: //alimentos
		        		addProducts(new Alimentos(id, nome, preco, ProductsCategory.ALIMENTOS));
		                 break;
		        	case 2: //eletrônicos
		        		addProducts(new Eletronico(id, nome, preco, ProductsCategory.ELETRONICOS));
		                 break;
		        	case 3: //móveis
		        		addProducts(new Moveis(id, nome, preco, ProductsCategory.MOVEIS));
		                 break;
		        	case 4: //Eletrodoméstico
		        		addProducts(new Eletrodomestico(id, nome, preco, ProductsCategory.ELETRODOMESTICO));
		                 break;
		        	case 5: //Produtos de limpeza
		        		addProducts(new Limpeza(id, nome, preco, ProductsCategory.LIMPEZA));
		                 break;
		        	default:
		        		System.out.println(Color.RED_BOLD_BRIGHT + "Categoria inválida" + Color.RESET);
		                 break;
		        	}
		    	
        	} catch (NumberFormatException e) {
        			 System.out.println(Color.RED_BOLD_BRIGHT + "Erro. Digite um número válido." + Color.RESET);
        		} catch (PJCException e) {
        			System.out.println(Color.RED_BOLD_BRIGHT + e.getMessage() + Color.RESET);
        		}
    }
    
    // Logica desse metodo, tendo em vista que o ID deve ser único, ele irá gerar um id normal 
	// porem se esse id ja existir ele irá incrementar mais um até chegar em um ID exclusivo. 
	// Sem isso, pode ocorrer uma duplicação de ID, podendo acontecer erros futuros em outros métodos.
    public int idGenerator () {
    	
    	int id = getProdutos().size() + 1;
    	
    	for (Produto produto : produtos) {
    		if(id == produto.getId()) {
    			id += 1;
    		}
    	}
    	return id;
    }
     
    // Método addProducts que adiciona o produto
    // na array de produtos. Esse método específico
    // é chamado dentro de cadastrarProdutos(),
    public void addProducts(Produto produto) {
    	
        if (!exists(produto.getNome())) {
            produtos.add(produto);
            System.out.println(Color.GREEN_BOLD_BRIGHT+ produto.getNome() + " cadastrado com sucesso!" + Color.RESET);
        } 
        else {
        	throw new PJCException(Color.RED_BOLD_BRIGHT + "Erro. O produto já está cadastrado no sistema." + Color.RESET);
        }	// Eu não posso cadastrar o mesmo produto duas vezes, caso ocorra essa tentativa, meu programa
    }		// vai informar uma mensagem de erro e abortar o cadastramento
    
    
    public void addToCart(Produto produto){
        System.out.println(Color.GREEN_BOLD_BRIGHT + produto.getNome() + " adicionado ao carrinho com sucesso!" + Color.RESET);
        carrinho.add(produto);
    }
    
    // Método para remover produtos cadastrados no Sistema
    public void removeProducts() {
    	
    	try {    
    
    		if( getTamProdutos() ){
    			
    			this.listProducts();
    			System.out.println("Digite o ID do produto que deseja remover: ");
    			int id = Integer.parseInt(scanner.nextLine());
    			Produto produto = find(id); 
    			
    			if(produto != null){		
    				produtos.remove(produto);
    				System.out.println(Color.GREEN_BOLD_BRIGHT + produto.getNome() + " removido com sucesso!" + Color.RESET);
    			}
            } 
    		
    		else {
            	System.out.println(Color.RED_BOLD_BRIGHT + "\nNão existem produtos cadastrados" + Color.RESET);
            }
    		
    	} catch (NumberFormatException e) {
    				System.out.println(Color.RED_BOLD_BRIGHT + "Erro! Digite um número válido." + Color.RESET); 
    		} catch (PNException e) {
    				System.out.println(Color.RED_BOLD_BRIGHT + e.getMessage() + Color.RESET);
    		}
    	
    }
    
    public boolean getTamProdutos() {
        if(produtos.size() > 0){
            return true;
        }
        else 
        	return false;
    }
    
    public boolean getTamCarrinho() {
    	if(carrinho.size() > 0) {
    		return true;
    	}
    	else
    		return false;
    }
    
    public void listProducts() {
    	
        if(getTamProdutos()) {
            System.out.println("Produtos disponíveis: \n");
            
            for(int i=0; i<produtos.size(); i++) {
                System.out.println("Produto #" + (i+1) + "\n" + produtos.get(i));
            }
            
        } else System.out.println(Color.RED_BOLD_BRIGHT + "\nNão existem produtos cadastrados" + Color.RESET);
    }
    
    public void buyProducts() {
    	
    try {	
    	
        if(getTamProdutos()){ 
        	
        	listProducts(); 
		    System.out.println("Digite o id do produto que deseja comprar:");
		    int id = Integer.parseInt(scanner.nextLine()); 
		    Produto produto = find(id); 
		    
		    if(produto != null){
		    	
                addToCart(produto); 
                System.out.println(Color.YELLOW_BOLD_BRIGHT + "\nDeseja adicionar outro produto ao carrinho?");
    		    System.out.print("Digite 1 para sim e 0 para não: ");
    		    int option = Integer.parseInt(scanner.nextLine());
    		    
    		    if (option == 1) {
    		    	this.buyProducts();
    		    }
    		    else { 
    		    	  return; 
    		    	}
		    	}
        	}
        
        	else {
        		System.out.println(Color.RED_BOLD_BRIGHT + "\nNão existem produtos disponíveis para compra :(" + Color.RESET);
        	}
        
    	} catch (NumberFormatException e) {
    		System.out.println(Color.RED_BOLD_BRIGHT + "Erro. Digite um número válido." + Color.RESET);
    	} catch (PNException e) {
    		System.out.println(Color.RED_BOLD_BRIGHT + e.getMessage() + Color.RESET);
    	}
	}
    
    // Método para mostrar o carrinho
    // Me mostra os produtos no carrinho (se houver)
    // + o valor total e o que eu quero fazer em seguida, 
    // Adicionar mais, remover produtos do carrinho, etc.
    public void showCart() {
    	
    try {    
    	
        if(getTamCarrinho()) {
        	
            double valor_total = 0.00;
            System.out.println("----------------------------");
            System.out.println("Produtos no seu carrinho:");
            for(Produto produto : carrinho){
                System.out.println(produto);
                valor_total += produto.getPreco();
            }
            System.out.println("----------------------------");
            
            System.out.println("Valor total: " + valor_total + "R$");
            System.out.println("\nDigite o que deseja fazer: ");
            System.out.println("【1】Adicionar mais produtos 【2】Remover produtos do carrinho");
            System.out.println("【3】Finalizar Compra        【4】Voltar à tela de menu inicial");
            int opcao = Integer.parseInt(scanner.nextLine());
            
            switch (opcao) {
            	case 1:
            		this.buyProducts();
            		break;
            	case 2: 
            		this.removeFromCart();
            		break;
            	case 3:
            		finalizarCompra(valor_total);
            		break;
            	case 4:
            		this.showMenu();
            	default:
            		System.out.println(Color.RED_BOLD_BRIGHT + "Opção inválida." + Color.RED_BOLD_BRIGHT);
            		break;
            	}
        	}
        	else {
        			System.out.println(Color.RED_BOLD_BRIGHT + "\nNão existem produtos no seu carrinho!" + Color.RESET);
        			System.out.println(Color.RED_BOLD_BRIGHT + "Você deve ir as compras primeiramente." + Color.RESET);
            	}
        
        } catch (NumberFormatException e) { System.out.println("\nOcorreu um Erro! Digite um número válido."); }
    }
    
    public void finalizarCompra(double valor_total) {
    	System.out.println(Color.GREEN_BOLD_BRIGHT + "Sua compra no valor de " + valor_total + "R$ foi aprovada com sucesso." + Color.RESET);
    	System.out.println(Color.GREEN_BOLD_BRIGHT + "Quixadá Street Market Agradece a sua compra :)" + Color.RESET);
    	carrinho.clear();
    }
    
    // Função para remover produtos do carrinho
    public void removeFromCart() {
    	
        try {
        	
            for (Produto produto : carrinho) {
                System.out.println(produto);
            }
            System.out.println("Digite o ID do produto que deseja remover: ");
            int option = Integer.parseInt(scanner.nextLine());
            Produto produto = findNoCarrinho(option);
            if (produto != null) {
                carrinho.remove(produto);  
                System.out.println(Color.GREEN_BOLD_BRIGHT + produto.getNome() + " removido do carrinho com sucesso!" + Color.RESET);
            }
            
        } catch (NumberFormatException e) {
            System.out.println(Color.RED_BOLD_BRIGHT + "Erro! Digite um número válido." + Color.RESET);
        } catch (PNException e) {
        	System.out.println(Color.RED_BOLD_BRIGHT + e.getMessage() + Color.RESET);
        }
        return;
    }
    
    public void updateProducts() {
    	try {
    		
    		if(this.getTamProdutos()) {
    			
    			System.out.println("Bem vindo a função de Atualizar Produtos");
    			this.listProducts();
    			System.out.println("Digite o ID do produto que deseja atualizar");
    			int id = Integer.parseInt(scanner.nextLine());
    			Produto produto = this.find(id);
    			
    			if(produto != null) {
    				
    				System.out.println("Produto escolhido: " + produto);
    				System.out.print("Digite o novo nome do produto: ");
    				String nome = scanner.nextLine();
    				produto.setNome(nome);
    				System.out.println(Color.GREEN_BOLD_BRIGHT + "Alteração realizada com sucesso!" + Color.RESET);
    				System.out.println(Color.GREEN_BOLD_BRIGHT + "O novo nome do seu produto é: " + produto.getNome() + " :)" + Color.RESET);
    			}
    			
    		}
    		
    	} catch(NumberFormatException e) {
    		System.out.println(Color.RED_BOLD_BRIGHT + "Erro! Digite um número válido." + Color.RESET);
    	} catch(PNException e) {
    		System.out.println(Color.RED_BOLD_BRIGHT + e.getMessage() + Color.RESET);
    	}
    }

    //método para verificar se um produto existe na lista de produtos pelo nome
    public boolean exists(String nome) {
    	
        for (Produto p : produtos) {
            if (p.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }
    
    // Método para encontrar um produto no carrinho pelo id
    // segue a mesma lógica do find 
    public Produto findNoCarrinho(int id) {
    	
    	for(Produto p : carrinho) {
    		if(p.getId() == id) {
    			return p;
    		}
    	}
    	throw new PNException(Color.RED_BOLD_BRIGHT + "Produto não encontrado." + Color.RESET);
    }

    //método para encontrar um produto na lista de produtos pelo id
    public Produto find(int id){
    	
    	for(Produto p : produtos){
    		if(p.getId() == id){
    			return p;
            	}
            }
    	throw new PNException(Color.RED_BOLD_BRIGHT + "Produto não encontrado." + Color.RESET);
    }

   // Método para exibir o menu de opções para o usuário
   // interface simples, porém fácil de se visualizar
   // e com cores implementadas no mesmo.
    
    public void showMenu() {
        System.out.println(Color.YELLOW_BOLD_BRIGHT); 
        System.out.println("╔══════════════════════════════════════╗ "); 
        System.out.println("║  BEM VINDO AO QUIXADÁ STREET MARKET  ║ ");
        System.out.println("╚══════════════════════════════════════╝ ");
        System.out.println();
        System.out.println("【1】Cadastrar Produtos 【2】Remover Produtos");
        System.out.println("【3】Listar Produtos    【4】Atualizar Produtos");
        System.out.println("【5】Comprar Produtos   【6】Ver carrinho");
        System.out.println("         【7】Sair da Aplicação\n");
        System.out.print("Digite a opção desejada: ");
        
    }
    
    /*
     * Função pra sair da aplicação exibindo
     * uma mensagem de agradecimento com delay
     * onde aprendi:
     * https://www.geeksforgeeks.org/thread-sleep-method-in-java-with-examples/
     */
    public void outOfApplication() {
        System.out.println(Color.GREEN_BOLD_BRIGHT + "Quixadá Street Market Agradece a sua presença!" + Color.RESET);

        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Color.RED_BOLD_BRIGHT + "Saindo da aplicação..." + Color.RESET);
    }
}

// CLASSE MAIN NOVA

import java.util.Scanner;

import enums.Color;

public class Main {
	public static void main(String[] args) {
		
		Operacoes operacoes = new Operacoes();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		
		while(running) {
		    operacoes.showMenu(); //exibir o menu de opções
		    int option = 0;
		    try {    
		        option = Integer.parseInt(scanner.nextLine()); //ler a opção do terminal
		        switch(option){
		        case 1: //cadastrar produtos
		            operacoes.cadastrarProdutos();
		            break;
		        case 2: //remover produtos
		            operacoes.removeProducts();
		            break;
		        case 3: //listar produtos
		            operacoes.listProducts(); 
		            break;
		        case 4: //atualizar produtos
		        	operacoes.updateProducts();
		        	break;
		        case 5: //comprar produtos
		            operacoes.buyProducts();
		            break;
		        case 6: //ver carrinho de compras
		            operacoes.showCart();
		            break;
		        case 7: //sair da aplicação
		        	operacoes.outOfApplication();
		            running = false;
		            break;
		        default:
		            System.out.println(Color.RED_BOLD_BRIGHT + "Opção inválida. Digite um número de 1 a 6." + Color.RESET);
		            break;
		        }
	        } catch(NumberFormatException e){ 
	            System.out.println(Color.RED_BOLD_BRIGHT + "Ocorreu um erro! Digite um número válido" + Color.RESET); 
	        } 
		}
		
		scanner.close();
	}
}
