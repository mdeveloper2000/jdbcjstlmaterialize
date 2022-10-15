package mysoftwaresdatabasejstl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mysoftwaresdatabasejstl.pojos.Software;

public class SoftwareDAO {

    private Connection conn;
    
    public SoftwareDAO() {
        this.conn = ConnectionFactory.getConnection();
    }
    
    public int save(Software software) {
        int resultado = 0;
        PreparedStatement ps = null;
        try {
            String SQL = "INSERT INTO softwares (nome, versao, data_lancamento) " +
                "VALUES(?, ?, ?)";
            ps = this.conn.prepareStatement(SQL);
            ps.setString(1, software.getNome());
            ps.setString(2, software.getVersao());
            ps.setDate(3, new java.sql.Date(software.getData_lancamento().getTime()));
            resultado = ps.executeUpdate();            
        }
        catch(SQLException e) {
            e.printStackTrace();            
        }
        return resultado;
    }
    
    public int edit(Software software) {
        int resultado = 0;
        PreparedStatement ps = null;
        try {
            String SQL = "UPDATE softwares SET nome = ?, versao = ?, " +
                "data_lancamento = ? WHERE id = ?";
            ps = this.conn.prepareStatement(SQL);
            ps.setString(1, software.getNome());
            ps.setString(2, software.getVersao());
            ps.setDate(3, new java.sql.Date(software.getData_lancamento().getTime()));
            ps.setInt(4, software.getId());
            resultado = ps.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();            
        }
        return resultado;
    }
    
    public List list() {
        List<Software> softwares = new ArrayList<>();        
        try {
            String SQL = "SELECT * FROM softwares";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()) {
                Software software = new Software();
                software.setId(rs.getInt("id"));
                software.setNome(rs.getString("nome"));
                software.setVersao(rs.getString("versao"));
                software.setData_lancamento(rs.getDate("data_lancamento"));
                softwares.add(software);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return softwares;
    }
    
    public Software read(int id) {
        PreparedStatement ps = null;
        try {
            String SQL = "SELECT * FROM softwares WHERE id = ?";
            ps = this.conn.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Software software = new Software();
                software.setId(rs.getInt("id"));
                software.setNome(rs.getString("nome"));
                software.setVersao(rs.getString("versao"));
                software.setData_lancamento(rs.getDate("data_lancamento"));
                return software;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;        
    }
    
    public void delete(int id) {        
        PreparedStatement ps = null;
        try {
            String SQL = "DELETE FROM softwares WHERE id = ?";
            ps = this.conn.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.executeUpdate();            
        }
        catch(SQLException e) {
            e.printStackTrace();
        }        
    }
    
}