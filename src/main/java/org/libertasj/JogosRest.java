package org.libertasj;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libertas.dao.JogoDao;
import org.libertas.pojo.Jogo;

import com.google.gson.Gson;

/**
 * Servlet implementation class JogosRest
 */
@WebServlet("/JogosRest/*")
public class JogosRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private void enviaResposta(HttpServletResponse response, String json, int codigo) throws IOException {
		response.addHeader("Content-Type", "application/json; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
		
		response.setStatus(codigo);
		
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(json.getBytes("UTF-8"));
		out.close();
		
	}
    public JogosRest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Jogo> lista = new LinkedList<>();
		
		String json;
		int id = 0;
			if (request.getPathInfo()!=null) {
				String info = request.getPathInfo().replace("/", "");
				id = Integer.parseInt(info);
		}
		
		Gson gson = new Gson();
		JogoDao jdao = new JogoDao();
		
		if (id == 0) {
			lista = jdao.listar();
			// converte o objeto JAVA em JSON
			 json = gson.toJson(lista);
		} else {
			Jogo j = jdao.consultar(id);
			json = gson.toJson(j);
		}
		
		
		// envia resposta
		enviaResposta(response, json, 200);
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String json = request.getReader().lines().collect(Collectors.joining());
			Gson gson = new Gson();
			Jogo j = (Jogo) gson.fromJson(json, Jogo.class);
			//inserir novo jogo
			JogoDao jdao = new JogoDao();
			jdao.inserir(j);
			// enviar resposta sucesso
			enviaResposta(response, "Inserido com sucesso", 200);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			enviaResposta(response, e.getMessage(), 500);
		}
		// pega os parametros enviados no BODY da requisição
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String json = request.getReader().lines().collect(Collectors.joining());
			Gson gson = new Gson();
			Jogo j = (Jogo) gson.fromJson(json, Jogo.class);
			//alterando novo Jogo
			JogoDao jdao = new JogoDao();
			jdao.alterar(j);
			// enviar resposta sucesso
			enviaResposta(response, "Alterado com sucesso", 200);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			enviaResposta(response, e.getMessage(), 500);
		}
		// pega os parametros enviados no BODY da requisição
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//pega o ID a ser excluido, passado na URL
			int id = 0;
			if (request.getPathInfo()!=null) {
				String info = request.getPathInfo().replace("/", "");
				id = Integer.parseInt(info);
			}
			
			Jogo j = new Jogo();
			j.setIdjogo(id);
			//deleta pessoa
			JogoDao jdao = new JogoDao();
			jdao.excluir(j);
			// enviar resposta sucesso
			enviaResposta(response, "Excluído com sucesso", 200);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			enviaResposta(response, e.getMessage(), 500);
		}
		// pega os parametros enviados no BODY da requisição
		
	}

}
