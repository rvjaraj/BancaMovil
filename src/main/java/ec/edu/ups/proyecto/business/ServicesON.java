/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.dao.ClienteDAO;
import ec.edu.ups.proyecto.dao.CuentaDAO;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Cuenta;
import ec.edu.ups.proyecto.emtitis.Errores;
import ec.edu.ups.proyecto.emtitis.Transaciones;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Vinicio
 */
@Stateless
public class ServicesON {

    @Inject
    CuentaDAO cuentaDAO;
    
    @Inject
    ClienteON clienteON;
    
    @Inject
    ClienteDAO clienteDAO;

    @Inject
    TransaccionesON transaccionesON;

    public Errores DepositoSRV(String numeroCuenta, Double cantidad) {
        try {
            Cuenta cuentAux = cuentaDAO.findByNuemor(numeroCuenta);
            if (cuentAux != null) {
                Cliente clienteAux = cuentAux.getCliente();
                BigDecimal bd = new BigDecimal(clienteAux.getCuentaList().get(0).getSaldo() + cantidad);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                clienteAux.getCuentaList().get(0).setSaldo(bd.doubleValue());
                clienteON.actualizarClienteTrasaccion(clienteAux, "Deposito SRV", new BigDecimal(cantidad).doubleValue());
                //Codigo : 2
                //Nombre: BM_E002
                //Descripcion: Deposito satisfactoria
                return new Errores(2, "BM_E002", "Deposito satisfactoria");
            } else {
                //Codigo : 1
                //Nombre: BM_E001
                //Descripcion: No se encuentra registrado el numero de cuenta ingresado
                return new Errores(1, "BM_E001", "No se encuentra registrado el numero de cuenta ingresado");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " >>>>");
            return new Errores(0, "BM_E000", "Vaya a saber quien| Diga a los desarroladores que arreglen");
        }
    }

    public Errores RetiroSRV(String numeroCuenta, Double cantidad) {
        try {
            Cuenta cuentAux = cuentaDAO.findByNuemor(numeroCuenta);
            if (cuentAux != null) {
                 Cliente clienteAux = cuentAux.getCliente();
                BigDecimal bd = new BigDecimal(clienteAux.getCuentaList().get(0).getSaldo() - cantidad);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                clienteAux.getCuentaList().get(0).setSaldo(bd.doubleValue());
                clienteON.actualizarClienteTrasaccion(clienteAux, "Retiro SRV", new BigDecimal(cantidad).doubleValue());
                //Codigo : 3
                //Nombre: BM_E002
                //Descripcion: Transferencias satisfactoria
                return new Errores(3, "BM_E003", "Retiro satisfactoria");
            } else {
                //Codigo : 1
                //Nombre: BM_E001
                //Descripcion: No se encuentra registrado el numero de cuenta ingresado
                return new Errores(1, "BM_E001", "No se encuentra registrado el numero de cuenta ingresado");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " >>>>");
            return new Errores(0, "BM_E000", "Vaya a saber quien| Diga a los desarroladores que arreglen");
        }
    }

    public Errores TransaccionInternaSRV(String numeroCuentaOrigen, String numeroCuentaDestino, Double cantidad, String concepto) {
        try {
            Cuenta cuentOri = cuentaDAO.findByNuemor(numeroCuentaOrigen);
            if (cuentOri != null) {
                Cuenta cuentDes = cuentaDAO.findByNuemor(numeroCuentaDestino);
                if (cuentDes != null) {
                    Cliente clienteAuxDES = cuentOri.getCliente();
                    BigDecimal bd = new BigDecimal(clienteAuxDES.getCuentaList().get(0).getSaldo() - cantidad);
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    clienteAuxDES.getCuentaList().get(0).setSaldo(bd.doubleValue());
                    System.out.println("Cliente Oringe acutalizado " + clienteAuxDES.getCuentaList().get(0).getSaldo());

                    Cliente clienteAuxDET = cuentDes.getCliente();
                    bd = new BigDecimal(clienteAuxDET.getCuentaList().get(0).getSaldo() + cantidad);
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    clienteAuxDET.getCuentaList().get(0).setSaldo(bd.doubleValue());
                    System.out.println("Cliente Oringe acutalizado " + clienteAuxDES.getCuentaList().get(0).getSaldo());

                    Transaciones transacion = new Transaciones();

                    transacion.setCantidad(String.valueOf(cantidad));

                    transacion.setCuentaid(clienteAuxDES.getCuentaList().get(0));
                    transacion.setFecha(new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate()));
                    transacion.setTipo("Retiro SRV");
                    System.out.println("Cliente acutalizado " + clienteAuxDES.getCuentaList().get(0).getSaldo());
                    transaccionesON.guardarTransaciones(transacion);
                } else {
                    //Codigo : 5
                    //Nombre: BM_E005
                    //Descripcion: No se encuentra registrado el numero de cuenta ingresado
                    return new Errores(4, "BM_E005", "No se encuentra registrado el numero de cuenta destino");
                }
                //Codigo : 3
                //Nombre: BM_E002
                //Descripcion: Transferencias satisfactoria
                return new Errores(3, "BM_E003", "Retiro satisfactoria");
            } else {
                //Codigo : 4
                //Nombre: BM_E004
                //Descripcion: No se encuentra registrado el numero de cuenta ingresado
                return new Errores(4, "BM_E004", "No se encuentra registrado el numero de cuenta origen");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " >>>>");
            return new Errores(0, "BM_E000", "Vaya a saber quien| Diga a los desarroladores que arreglen");
        }
    }
}
