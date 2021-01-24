package ar.edu.ubp.das.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.db.Dao;
import ar.edu.ubp.das.token.bean.TokenBean;

public class MSTokenDao extends Dao<TokenBean, TokenBean>{

	@Override
	public TokenBean delete(TokenBean token) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TokenBean insert(TokenBean token) throws SQLException {
		try {
			this.connect();
			
			this.setProcedure("dbo.INSERTAR_TOKEN(?,?,?,?)");
			this.setParameter(1, token.getIdServicio());
			this.setParameter(2, token.getToken());
			this.setParameter(3, token.getFechaCreacion());
			this.setParameter(4, token.getFechaExpiracion());
			
			this.executeUpdate();
			
			return token;
			
		} finally {
			this.close();
		}
	}

	@Override
	public TokenBean make(ResultSet resultSet) throws SQLException {
		TokenBean token = new TokenBean();
		
		token.setFechaCreacion(resultSet.getTimestamp("Fecha_Creacion"));
		token.setFechaExpiracion(resultSet.getTimestamp("Fecha_Expiracion"));
		token.setId(resultSet.getInt("Id"));
		token.setToken(resultSet.getString("Token"));
		token.setIdGobierno(resultSet.getString("Id_Gobierno"));
		token.setIdServicio(resultSet.getString("Id_Servicio"));

		return token;
	}

	@Override
	public List<TokenBean> select(TokenBean token) throws SQLException {
		try {
			this.connect();
			this.setProcedure("dbo.OBTENER_TOKEN_VALIDO(?)");
			this.setParameter(1, token.getIdServicio());
			
			List<TokenBean> listaTokens = this.executeQuery();
			
			return listaTokens;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			this.close();
		}
	}
	
	public TokenBean buscarToken(String servicio) throws SQLException {
		try {
			this.connect();
			this.setProcedure("dbo.OBTENER_TOKEN_VALIDO(?)");
			this.setParameter(1, servicio);
			
			List<TokenBean> listaTokens = this.executeQuery();
			
			return listaTokens.isEmpty() ? null : listaTokens.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			this.close();
		}
	}

	@Override
	public TokenBean update(TokenBean token) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean valid(TokenBean token) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}	
}
