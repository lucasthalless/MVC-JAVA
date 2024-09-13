package br.com.fiap.controller;

import br.com.fiap.model.dao.CarroDAO;
import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dto.Carro;

import java.sql.Connection;
import java.sql.SQLException;

public class CarroController {
    public String inserirCarro(String placa, String cor, String descricao) throws ClassNotFoundException, SQLException {
        String res;
        Connection con = ConnectionFactory.abrirConexao();
        Carro carro = new Carro();

        carro.setPlaca(placa);
        carro.setCor(cor);
        carro.setDescricao(descricao);

        CarroDAO carroDAO = new CarroDAO(con);
        res = carroDAO.inserir(carro);

        ConnectionFactory.fecharConexao(con);
        return res;
    }

    public String alterarCarro(String placa, String cor, String descricao) throws ClassNotFoundException, SQLException {
        String res;
        Connection con = ConnectionFactory.abrirConexao();
        Carro carro = new Carro();

        carro.setPlaca(placa);
        carro.setCor(cor);
        carro.setDescricao(descricao);

        CarroDAO carroDAO = new CarroDAO(con);
        res = carroDAO.alterar(carro);

        ConnectionFactory.fecharConexao(con);
        return res;
    }

    public String excluirCarro(String placa) throws ClassNotFoundException, SQLException {
        String res;
        Connection con = ConnectionFactory.abrirConexao();
        Carro carro = new Carro();

        carro.setPlaca(placa);

        CarroDAO carroDAO = new CarroDAO(con);
        res = carroDAO.excluir(carro);

        ConnectionFactory.fecharConexao(con);
        return res;
    }

    public String listarCarro(String placa) throws ClassNotFoundException, SQLException {
        String res;
        Connection con = ConnectionFactory.abrirConexao();
        Carro carro = new Carro();

        carro.setPlaca(placa);

        CarroDAO carroDAO = new CarroDAO(con);
        res = carroDAO.listarUm(carro);

        ConnectionFactory.fecharConexao(con);
        return res;
    }
}
