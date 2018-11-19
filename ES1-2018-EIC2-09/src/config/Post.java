package config;

public class Post {
	
	private String data;
	private String canal;
	private String origem;
	private String assunto;

	public Post(String data, String canal, String origem, String assunto) {
		this.data = data;
		this.canal = canal;
		this.origem = origem;
		this.assunto = assunto;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getCanal() {
		return canal;
	}
	
	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	public String getOrigem() {
		return origem;
	}
	
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public String getAssunto() {
		return assunto;
	}
	
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
}
