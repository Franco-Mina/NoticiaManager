package ar.edu.ubp.das.bean.ws;

import java.sql.Timestamp;

public class TokenResponseBean {

	private String token;
	private Timestamp fechaExpiracion;
	private int respuesta;
	private String mensaje;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Timestamp getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(Timestamp fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
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
