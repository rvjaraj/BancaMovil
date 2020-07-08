package ec.edu.ups.proyecto.emtitis;

public class DepositoSRV {

	private String numeroCuenta;
	private double cantidad;
	
	public DepositoSRV() {
		
	}
	
	public DepositoSRV(String numeroCuenta, double cantidad) {
		this.numeroCuenta = numeroCuenta;
		this.cantidad = cantidad;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
