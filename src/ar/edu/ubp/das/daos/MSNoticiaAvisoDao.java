package ar.edu.ubp.das.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.bean.NoticiaAvisoBean;
import ar.edu.ubp.das.db.Dao;

public class MSNoticiaAvisoDao extends Dao<NoticiaAvisoBean, NoticiaAvisoBean> {

	public MSNoticiaAvisoDao(String provider, String connectionString) {
		super(provider,connectionString);
	}
	
	@Override
	public NoticiaAvisoBean delete(NoticiaAvisoBean arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoticiaAvisoBean insert(NoticiaAvisoBean arg0) throws SQLException {
		this.connect();
		
		this.setProcedure("dbo.INSERTAR_NOTICIA_AVISO(?,?,?)");
		this.setParameter(1, arg0.getTipo());
		this.setParameter(2, arg0.getMensaje());
		this.setParameter(3, "municipio");
		this.executeUpdate();
		
		return arg0;
	}

	@Override
	public NoticiaAvisoBean make(ResultSet arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticiaAvisoBean> select(NoticiaAvisoBean arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoticiaAvisoBean update(NoticiaAvisoBean arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean valid(NoticiaAvisoBean arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
