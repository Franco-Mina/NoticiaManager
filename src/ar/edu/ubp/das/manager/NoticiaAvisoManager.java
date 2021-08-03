package ar.edu.ubp.das.manager;

import java.sql.SQLException;

import com.google.gson.Gson;

import ar.edu.ubp.das.bean.NoticiaAvisoBean;
import ar.edu.ubp.das.bean.ws.NoticiaResponseBean;
import ar.edu.ubp.das.bean.ws.NoticiasRequestBean;
import ar.edu.ubp.das.conections.ConnectionManager;
import ar.edu.ubp.das.db.Dao;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.logger.Logger;
import ar.edu.ubp.das.token.db.ConsoleTokenManger;

public class NoticiaAvisoManager {
	
	private final String cadenaConexion = "jdbc:sqlserver://172.10.3.106;databaseName=gobierno_provincial;user=sa;password=Francomina1";
	private final String usuario        = "sa";
	private final String password       = "Francomina1";
	private final String logPath        = "c:/Logger/NoticiaTask/";

	public int ObtenerNoticias() {
		//llamar al servicio
		NoticiasRequestBean noticiaRequest = new NoticiasRequestBean();
		noticiaRequest.setUsuario("u1");
		
		ConnectionManager connectionManager = new ConnectionManager("src/ar/edu/ubp/das/manager/conexiones.xml", 
				new  ConsoleTokenManger(this.cadenaConexion,this.usuario,this.password),logPath);
		
		String json = connectionManager.callApi(2, noticiaRequest);
		
		Gson gson = new Gson();
		
		NoticiaResponseBean response = gson.fromJson(json, NoticiaResponseBean.class);
		
		try {
			return this.GuardarEnDb(response);
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getLogger(this.logPath).escribirLog(e);
			return -1;
		}		
	}
	
	public int GuardarEnDb(NoticiaResponseBean response) throws SQLException, ClassNotFoundException{
		try {
			Dao<NoticiaAvisoBean, NoticiaAvisoBean> dao = DaoFactory.getDao("NoticiaAviso", "ar.edu.ubp.das", 
				"com.microsoft.sqlserver.jdbc.SQLServerDriver", this.cadenaConexion, "MS");
		
			for (NoticiaAvisoBean noticiaAvisoBean : response.getNovedades()) {
				dao.insert(noticiaAvisoBean);
			}
		}catch (Exception e) {
			Logger.getLogger(this.logPath).escribirLog(e);
			Gson gson = new Gson();
			Logger.getLogger(this.logPath+"/MensajesNoGuardados/").escribirLog(gson.toJson(response));
			return -1;
		}
		return 0;
	}
}
