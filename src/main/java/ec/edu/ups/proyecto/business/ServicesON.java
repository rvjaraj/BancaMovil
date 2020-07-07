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
import ec.edu.ups.proyecto.emtitis.Mensajes;
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

    public Mensajes DepositoSRV(String numeroCuenta, Double cantidad) {
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
                return new Mensajes(2, "BM_E002", "Deposito satisfactoria");
            } else {
                //Codigo : 1
                //Nombre: BM_E001
                //Descripcion: No se encuentra registrado el numero de cuenta ingresado
                return new Mensajes(1, "BM_E001", "No se encuentra registrado el numero de cuenta ingresado");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " >>>>");
            return new Mensajes(0, "BM_E000", "Vaya a saber quien| Diga a los desarroladores que arreglen");
        }
    }

    public Mensajes RetiroSRV(String numeroCuenta, Double cantidad) {
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
                return new Mensajes(3, "BM_E003", "Retiro satisfactoria");
            } else {
                //Codigo : 1
                //Nombre: BM_E001
                //Descripcion: No se encuentra registrado el numero de cuenta ingresado
                return new Mensajes(1, "BM_E001", "No se encuentra registrado el numero de cuenta ingresado");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " >>>>");
            return new Mensajes(0, "BM_E000", "Vaya a saber quien| Diga a los desarroladores que arreglen");
        }
    }

    public Mensajes TransaccionInternaSRV(String numeroCuentaOrigen, String numeroCuentaDestino, Double cantidad, String concepto) {
        try {
            Cuenta cuentOri = cuentaDAO.findByNuemor(numeroCuentaOrigen);
            if (cuentOri != null) {
                Cuenta cuentDes = cuentaDAO.findByNuemor(numeroCuentaDestino);
                if (cuentDes != null) {
                    //Act cliente destinatario suma su dinero
                    Cliente clienteAuxDes = cuentDes.getCliente();
                    BigDecimal bd = new BigDecimal(clienteAuxDes.getCuentaList().get(0).getSaldo() + cantidad);
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    clienteAuxDes.getCuentaList().get(0).setSaldo(bd.doubleValue());
                    clienteON.actualizarCliente(clienteAuxDes);
                    
                    //Act cliente que envia resta su dinero
                    Cliente clienteAuxOri = cuentOri.getCliente();
                    bd = new BigDecimal(clienteAuxOri.getCuentaList().get(0).getSaldo() + cantidad);
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    clienteAuxOri.getCuentaList().get(0).setSaldo(bd.doubleValue());
                    clienteON.actualizarCliente(clienteAuxOri);
                    
                    //Guardamos la trasacion
                    
                } else {
                    //Codigo : 5
                    //Nombre: BM_E005
                    //Descripcion: No se encuentra registrado el numero de cuenta ingresado
                    return new Mensajes(4, "BM_E005", "No se encuentra registrado el numero de cuenta destino");
                }
                //Codigo : 3
                //Nombre: BM_E002
                //Descripcion: Transferencias satisfactoria
                return new Mensajes(3, "BM_E003", "Retiro satisfactoria");
            } else {
                //Codigo : 4
                //Nombre: BM_E004
                //Descripcion: No se encuentra registrado el numero de cuenta ingresado
                return new Mensajes(4, "BM_E004", "No se encuentra registrado el numero de cuenta origen");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " >>>>");
            return new Mensajes(0, "BM_E000", "Vaya a saber quien| Diga a los desarroladores que arreglen");
        }
    }
}
