package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Carro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarroDAO implements IDAO {
    private Connection con;
    private Carro carro;

    public CarroDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Object obj) {
        carro = (Carro) obj;

        String sql = "insert into ddd_carro(placa,cor,descricao) values(?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, carro.getPlaca());
            ps.setString(2, carro.getCor());
            ps.setString(3, carro.getDescricao());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(Object obj) {
        carro = (Carro) obj;
        String sql = "update ddd_carro set cor = ?,descricao = ? where placa = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, carro.getCor());
            ps.setString(2, carro.getDescricao());
            ps.setString(3, carro.getPlaca());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso.";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String excluir(Object obj) {
        carro = (Carro) obj;
        String sql = "delete from ddd_carro where placa = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, carro.getPlaca());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso.";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String listarUm(Object obj) {
        carro = (Carro) obj;
        String sql = "select * from ddd_carro where placa = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setString(1, carro.getPlaca());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "Placa: " + carro.getPlaca() +
                        "\nCor: " + rs.getString("cor") +
                        "\nDescrição: " + rs.getString("descricao");
            } else {
                return "Registro não encontrado!";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public ArrayList<Carro> listarTodos() {
        String sql = "select * from ddd_carro order by placa";
        ArrayList<Carro> listaCarro = new ArrayList<Carro>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            if (rs != null) {
                while (rs.next()) {
                    Carro carro = new Carro();
                    carro.setPlaca(rs.getString(1));
                    carro.setCor(rs.getString(2));
                    carro.setDescricao(rs.getString(3));
                    listaCarro.add(carro);
                }
                return listaCarro;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

}
