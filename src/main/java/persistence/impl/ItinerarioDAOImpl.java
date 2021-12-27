package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.PromocionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;

public class ItinerarioDAOImpl implements ItinerarioDAO {

    public int actualizarItinerario(Usuario usuario) {
        int rows = 0;
        for (Producto producto : usuario.getItinerario()) {
            String sql = "INSERT INTO itinerario (usuario_id, atraccion_id, promocion_id) VALUES (?, ?, ?)";
            Connection conn;
            try {
                conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, usuario.getId());
                if (producto.esPromocion()) {
                    statement.setString(2, null);
                    statement.setInt(3, producto.getId());
                } else if (!producto.esPromocion()) {
                    statement.setInt(2, producto.getId());
                    statement.setString(3, null);
                }
                rows = statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return rows;
    }

    @Override
    public List<Atraccion> obtenerAtraccionesCompradas(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM itinerario WHERE usuario_id = ?";
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement statement2 = conn.prepareStatement(sql);
        statement2.setInt(1, idUsuario);
        ResultSet resultados = statement2.executeQuery();

        List<Atraccion> atraccionesCompradas = new ArrayList<>();
        AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
        PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();

        while (resultados.next()) {
            if(resultados.getString(2) != null){
                Atraccion atraccion = atraccionDAO.obtenerPorId(resultados.getInt(2));
                atraccionesCompradas.add(atraccion);
            }
            if(resultados.getString(3) != null){
                List<Atraccion> atraccionesDePromocion = promocionDAO.obtenerAtraccionesDePromocion(resultados.getInt(3));
                atraccionesCompradas.addAll(atraccionesDePromocion);
            }
        }

        return atraccionesCompradas;
    }
    
    public List<Producto> obtenerProductosComprados(int idUsuario) throws SQLException{
    	String sql = "SELECT * FROM itinerario WHERE usuario_id = ?";
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement statement2 = conn.prepareStatement(sql);
        statement2.setInt(1, idUsuario);
        ResultSet resultados = statement2.executeQuery();
        
        List<Producto> productos = new ArrayList<>();
        AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
        PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
        
        while (resultados.next()) {
            if(resultados.getString(2) != null){
                Atraccion atraccion = atraccionDAO.obtenerPorId(resultados.getInt(2));
                productos.add(atraccion);
            }
            if(resultados.getString(3) != null){
            	Promocion promocion = promocionDAO.obtenerPorId(resultados.getInt(3));
                productos.add(promocion);
            }
    	}
        return productos;
    }
}
