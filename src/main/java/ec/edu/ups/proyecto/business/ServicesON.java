/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.dao.CuentaDAO;
import ec.edu.ups.proyecto.dao.TransferenciasDAO;
import ec.edu.ups.proyecto.emtitis.Cliente;
import ec.edu.ups.proyecto.emtitis.Cuenta;
import ec.edu.ups.proyecto.emtitis.DepositoSRV;
import ec.edu.ups.proyecto.emtitis.Mensajes;
import ec.edu.ups.proyecto.emtitis.RetiroSRV;
import ec.edu.ups.proyecto.emtitis.SolicitudSRV;
import ec.edu.ups.proyecto.emtitis.TransferenciaSRV;
import ec.edu.ups.proyecto.emtitis.Transferencias;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    TransferenciasDAO transferenciasDAO;

    @Inject
    SolicitudON solicitudON;
/**
 * Servici de deposito
 * @param deposito
 * @return 
 */
    public Mensajes DepositoSRV(DepositoSRV deposito) {
        try {
            Cuenta cuentAux = cuentaDAO.findByNuemor(deposito.getNumeroCuenta());
            if (cuentAux != null) {
                Cliente clienteAux = cuentAux.getCliente();
                BigDecimal bd = new BigDecimal(clienteAux.getCuentaList().get(0).getSaldo() + deposito.getCantidad());
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                clienteAux.getCuentaList().get(0).setSaldo(bd.doubleValue());
                clienteON.actualizarClienteTrasaccion(clienteAux, "Deposito SRV", new BigDecimal(deposito.getCantidad()).doubleValue());
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
/**
 * RetiroSVR
 * @param retiro
 * @return 
 */
    public Mensajes RetiroSRV(RetiroSRV retiro) {
        try {
            Cuenta cuentAux = cuentaDAO.findByNuemor(retiro.getNumeroCuenta());
            if (cuentAux != null) {
                Cliente clienteAux = cuentAux.getCliente();
                BigDecimal bd = new BigDecimal(clienteAux.getCuentaList().get(0).getSaldo() - retiro.getCantidad());
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                clienteAux.getCuentaList().get(0).setSaldo(bd.doubleValue());
                clienteON.actualizarClienteTrasaccion(clienteAux, "Retiro SRV", new BigDecimal(retiro.getCantidad()).doubleValue());
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
/**
 * En este metodo se realiza
 * trasnferencias internas
 * @param transferenciaSrv
 * @return 
 */
    public Mensajes TransferenciasInternaSRV(TransferenciaSRV transferenciaSrv) {
        try {
            Cuenta cuentOri = cuentaDAO.findByNuemor(transferenciaSrv.getNumeroCuentaOrigen());
            if (cuentOri != null) {
                Cuenta cuentDes = cuentaDAO.findByNuemor(transferenciaSrv.getNumeroCuentaDestino());
                if (cuentDes != null) {
                    //Act cliente destinatario suma su dinero
                    Cliente clienteAuxDes = cuentDes.getCliente();
                    BigDecimal bd = new BigDecimal(clienteAuxDes.getCuentaList().get(0).getSaldo() + transferenciaSrv.getCantidad());
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    clienteAuxDes.getCuentaList().get(0).setSaldo(bd.doubleValue());
                    clienteON.actualizarCliente(clienteAuxDes);

                    //Act cliente que envia resta su dinero
                    Cliente clienteAuxOri = cuentOri.getCliente();
                    bd = new BigDecimal(clienteAuxOri.getCuentaList().get(0).getSaldo() - transferenciaSrv.getCantidad());
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    clienteAuxOri.getCuentaList().get(0).setSaldo(bd.doubleValue());
                    clienteON.actualizarCliente(clienteAuxOri);

                    //Guardamos la transferencia
                    Transferencias transferencia = new Transferencias();
                    transferencia.setFecha(new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate()));
                    transferencia.setCantidad(transferenciaSrv.getCantidad());
                    transferencia.setConcepto(transferenciaSrv.getConcepto());
                    transferencia.setOrdenante(cuentOri);
                    transferencia.setBeneficiario(cuentDes);

                    transferenciasDAO.insert(transferencia);
                    //Codigo : 6
                    //Nombre: BM_E006
                    //Descripcion: Transferencia exitosa
                    return new Mensajes(6, "BM_E006", "Transferencia exitosa");
                } else {
                    //Codigo : 5
                    //Nombre: BM_E005
                    //Descripcion: No se encuentra registrado el numero de cuenta ingresado
                    return new Mensajes(4, "BM_E005", "No se encuentra registrado el numero de cuenta destino");
                }
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
/**
 * envio de solicitud
 * @return 
 */
    public List<SolicitudSRV> enviarDataSet() {
        List<SolicitudSRV> lista = solicitudON.enviarDataSet();
        try {
            agregarCSV(lista);
        } catch (IOException ex) {
            Logger.getLogger(ServicesON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
/**
 * Gregar documentos 
 * para la solicitud
 * @param lista
 * @throws IOException 
 */
    public void agregarCSV(List<SolicitudSRV> lista) throws IOException {
        String archCSV = "C:\\Users\\Vinicio\\Documents\\proyectoanalisisdatos\\apiAnalisis\\Datasets\\DatasetBanco\\mio.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(archCSV), ';');
        writer.writeAll(convertir(lista));
        writer.close();
    }
    /**
     * convertir
     * @param emps
     * @return 
     */

    private static List<String[]> convertir(List<SolicitudSRV> emps) {
        List<String[]> records = new ArrayList<String[]>();
        records.add(new String[]{"DNI", "PLAZOMESESCREDITO".replace("\"", ""), "HISTORIALCREDITO", "PROPOSITOCREDITO",
            "MONTOCREDITO", "SALDOCUENTAAHORROS", "TIEMPOEMPLEO", "TASAPAGO",
            "ESTADOCIVILYSEXO", "GARANTE", "AVALUOVIVIENDA", "ACTIVOS",
            "EDAD", "VIVIENDA", "CANTIDADCREDITOSEXISTENTES", "EMPLEO",
            "TRABAJADOREXTRANJERO", "TIPOCLIENTE"
        });
//						
        Iterator<SolicitudSRV> it = emps.iterator();
        while (it.hasNext()) {
            SolicitudSRV emp = it.next();
            records.add(new String[]{emp.getDni(), emp.getPlazomesescredito(), emp.getHistorialcredito(), emp.getPropositocredito(),
                emp.getMontocredito(), emp.getSaldocuentaahorros(), emp.getTiempoempleo(), emp.getTasapago(),
                emp.getEstadocivilysexo(), emp.getGarante(), emp.getAvaluovivienda(), emp.getActivos(), emp.getEdad(),
                emp.getVivienda(), emp.getCantidadcreditosexistentes(), emp.getEmpleo(), emp.getTrabajadorextranjero(), emp.getTipocliente()
            });
        }
        return records;
    }
    /**
     * servicios python
     * @param cedula
     * @return 
     */

    
    public  String ServiciosPython(String cedula){
         try {
            URL url = new URL("http://127.0.0.1:8000/apiAnalisis/predecirTipoCliente/?Dni=" + cedula);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output +" >>>>");
                return output;
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return null;
    }
}
