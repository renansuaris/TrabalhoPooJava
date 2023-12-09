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
		        case 4: //comprar produtos
		            operacoes.buyProducts();
		            break;
		        case 5: //ver carrinho de compras
		            operacoes.showCart();
		            break;
		        case 6: //sair da aplicação
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
