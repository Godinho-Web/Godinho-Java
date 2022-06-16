package org.libertas.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private Connection  conexao;
	public Conexao() {
		try {
			// Criando conexao com o banco de dados
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			String url = "jdbc:mariadb://localhost/bdjogos";
			conexao = DriverManager.getConnection(url, "root", "masterkey");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConexao() {
		return conexao;
	}
}
