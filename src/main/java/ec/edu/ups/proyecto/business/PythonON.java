/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.business;

import ec.edu.ups.proyecto.emtitis.Solicitud;
import ec.edu.ups.proyecto.emtitis.SolicitudSRV;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Vinicio
 */
@Stateless
public class PythonON {

    @Inject
    SolicitudON solicitudON;
/**
 * 
 * @param cedula
 * creamos el servicio predecir cliente 
 * @cedula
 * @return 
 */
    public String predecirClienteCedula(String cedula) {
        try {
            URL url = new URL("http://104.154.101.179:8080/predecir?DNI=" + cedula);//your url i.e fetch data from .
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
                return output;
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return null;
    }
    
    /**
     * utilizamos el metodo get para que nos devuelva una imagen
     * @return 
     */
    public String getImagen() {
        try {
            URL url = new URL("http://104.154.101.179:8080/getImage");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "image/gif");
            if (conn.getResponseCode() != 200) {
                System.out.println("Estamo aca con la img");
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                return output;
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return null;
    }
/**
 * El cliente envia una solicitud para
 * un credito
 * @param solicitud
 * @return 
 */
    public int predecirCliente(Solicitud solicitud) {
        try {
            URL url = new URL("http://104.154.101.179:8080/predecirCliente");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = toJson(solicitud);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println("|" + output + "|");
                if (output.equals("1")) {
                    conn.disconnect();
                    return 1;
                }
                if (output.equals("2")) {
                    conn.disconnect();
                    return 2;
                }
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return 3;
    }
    /**
     * Generar la grafica de 
     * la calificacion del cliente
     * @return 
     */
    public String generarPastel() {
        try {
            URL url = new URL("http://127.0.0.1:5000/getImage");
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Vinicio\\Documents\\NetBeansProjects\\BancaMovil\\src\\main\\webapp\\templete\\img\\pastel.jpg");
            fos.write(response);
            fos.close();
            return  fos.getFD().toString();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "templete/img/pastel.jpg";
    }
    public String toJson(Solicitud solicitud) {
        SolicitudSRV solSVR = solicitudON.convetoJSON(solicitud);
        System.out.println(solSVR.toString());
        return solSVR.toString();
    }

}
