package ar.edu.ubp.das.bean.ws;

import java.util.ArrayList;
import java.util.List;

import ar.edu.ubp.das.bean.NoticiaAvisoBean;

public class NoticiaResponseBean {

	private List<NoticiaAvisoBean> novedades = new ArrayList<NoticiaAvisoBean>();
	private int respuesta;
	private String mensaje;
	
	public List<NoticiaAvisoBean> getNovedades() {
		return novedades;
	}
	public void setNovedades(List<NoticiaAvisoBean> novedades) {
		this.novedades.addAll(novedades);
	}
	public int getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
