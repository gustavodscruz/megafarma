package br.com.fiap.dao;

import br.com.fiap.to.RemedioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RemedioDAO extends Repository{
    public ArrayList<RemedioTO> findAll(){
        ArrayList<RemedioTO> remedios = new ArrayList<RemedioTO>();
        String sql = "select * from ddd_remedios order by codigo";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                while (rs.next()){
                    RemedioTO remedio = new RemedioTO();
                    remedio.setCodigo(rs.getLong("codigo"));
                    remedio.setNome(rs.getString("nome"));
                    remedio.setPreco(rs.getDouble("preco"));
                    remedio.setDataDeFabricacao(rs.getDate("data_de_fabricacao").toLocalDate());
                    remedio.setDataDeValidade(rs.getDate("data_de_validade").toLocalDate());
                    remedios.add(remedio);
                }
            }
        }catch (SQLException e){
            System.out.println("Erro de sql: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return remedios;


    }
}
