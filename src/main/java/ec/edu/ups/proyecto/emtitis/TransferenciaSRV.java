package ec.edu.ups.proyecto.emtitis;

public class TransferenciaSRV {

	private String numeroCuentaOrigen;
	private String numeroCuentaDestino;
	private double cantidad;
	private String concepto;
	
	public TransferenciaSRV() {
		
	}
	
	public TransferenciaSRV(String numeroCuentaOrigen, String numeroCuentaDestino, double cantidad, String concepto) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
		this.numeroCuentaDestino = numeroCuentaDestino;
		this.cantidad = cantidad;
		this.concepto = concepto;
	}

	public String getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}

	public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}

	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
	
	
}
