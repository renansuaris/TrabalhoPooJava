public abstract class Produto {
	
	// Atributos
    private Integer id;
    private String nome;
    private Double preco;
    private ProductsCategory categoria;
    
    public Produto() {
        
    }
    
    public Produto(Integer id, String nome, Double preco, ProductsCategory categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Double getPreco() {
        return this.preco;
    }
    
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    public ProductsCategory getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(ProductsCategory categoria) {
        this.categoria = categoria;
    }
    
    public String toString() {
    	return "ID: " + getId() + "\n" +
                "Nome: " + getNome() + "\n" + 
                "Preço: " + getPreco() + "\n" +
                "Categoria: " + getCategoria() + "\n"; 
    }
	
}
