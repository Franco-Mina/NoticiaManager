package ar.edu.ubp.das.manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.gson.Gson;

import ar.edu.ubp.das.bean.NoticiaAvisoBean;
import ar.edu.ubp.das.bean.ws.NoticiaResponseBean;
import ar.edu.ubp.das.bean.ws.NoticiasRequestBean;
import ar.edu.ubp.das.conections.ConnectionManager;
import ar.edu.ubp.das.token.db.ConsoleTokenManger;

public class NoticiaAvisoManager {
	
	private String cadenaConexion = "jdbc:sqlserver://172.10.3.106;databaseName=gobierno_provincial";
	private String usuario        = "sa";
	private String password       = "Francomina1";
	

	public int ObtenerNoticias() {
		//llamar al servicio
		NoticiasRequestBean noticiaRequest = new NoticiasRequestBean();
		noticiaRequest.setUsuario("u1");
		
		ConnectionManager connectionManager = new ConnectionManager("src/ar/edu/ubp/das/manager/conexiones.xml", 
				new  ConsoleTokenManger(this.cadenaConexion,this.usuario,this.password));
		
		String json = connectionManager.callApi(2, noticiaRequest);
		
		Gson gson = new Gson();
		
		NoticiaResponseBean response = gson.fromJson(json, NoticiaResponseBean.class);
		
		try {
			return this.GuardarEnDb(response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}		
	}
	
	public int GuardarEnDb(NoticiaResponseBean response) throws SQLException, ClassNotFoundException{
		
		Connection conn;
		CallableStatement stmt;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn=DriverManager.getConnection(this.cadenaConexion,this.usuario,this.password);
		conn.setAutoCommit(false);
		
		try {
			stmt = conn.prepareCall("{CALL dbo.INSERTAR_NOTICIA_AVISO(?,?,?)}");
			
			for (NoticiaAvisoBean noticiaAvisoBean : response.getNovedades()) {
				stmt.setString(1, noticiaAvisoBean.getTipo());
				stmt.setString(2, noticiaAvisoBean.getMensaje());
				stmt.setString(3, "municipio");
				stmt.executeUpdate();
			}
			
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}finally {
			conn.setAutoCommit(true);
			conn.close();
		}
		
		return 0;
	}
}
