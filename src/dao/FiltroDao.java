/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.metodos;
import java.awt.RenderingHints.Key;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Filtro;

/**
 *
 * @author Victor.Morales
 */
public class FiltroDao implements metodos<Filtro>{

    private Object con;

    @Override
    public boolean create(Filtro g) {
        //servira para preparar la consulta de insert
        PreparedStatement ps;
        try{
            ps = con.getCnx.prepareStatement(SQL_INSERT);
            ps.setString(1,g.getCodigo());
            ps.setString(2,g.getMarca());
            ps.setInt(3,g.getStock());
            ps.setBoolean(4, true);
            if (ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
         PreparedStatement ps;
         try{
             ps = con.getCnx().preparedStatment(SQL_DELETE);
             ps.setString(1, Key.toString());
             
             if (ps.executeUpdate () > 0){
                 return true;
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             con.cerrarConexion();
         }
         return false;
    }

    @Override
    public boolean update(Filtro c) {
        PreparedStatement ps;
         try{
             System.out.println(c.getCodigo());
             ps = con.getCnx().preparedStatment(SQL_UPDATE);
             ps.setString(1, c.getMarca());
             ps.setInt(2, c.getStock());
             ps.setBoolean(3, c.getExistencia());
             ps.setString(4, c.getCodigo());
             
             if (ps.executeUpdate () > 0){
                 return true;
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             con.cerrarConexion();
         }
         return false;    
    }

    @Override
    public Filtro read(Object key) {
        Filtro f = null;
        PreparedStatement ps;
        ResultSet rs;
     try{
             ps = con.getCnx().preparedStatment(SQL_READ);
             ps.setString(1, key.toString());
             
             rs = ps.executeQuery();
             
             while (rs.next()){
                 f = new Filtro(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(4), rs.getBoolean(5));
             }rs.close();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             con.cerrarConexion();
         }
         return f; 
    }

    @Override
    public ArrayList<Filtro> readAll() {
       ArrayList<Filtro> all = new ArrayList();
       Statement s;
       ResultSet rs;
       try{
           s = con.getCnx().prepareStatement(SQL_READALL);
           while (rs.next()){
               all.add(new Filtro (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5)));
           }rs.close();
       }catch (SQLException ex){
           Logger.getLogger(FiltroDao.class.getName()).log(level.SEVERE, null, ex);
       }return all;
    }
    
}
