package pdf;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tabla_PdfVO {

    DAO dao = null;

    public void visualizar_PdfVO(JTable tabla) {
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dt.addColumn("codigopdf");
        dt.addColumn("nombrepdf");
        dt.addColumn("archivopdf");

//        ImageIcon icono = null;
//        if (get_Image("/Imagen/32pdf.png") != null) {
//            icono = new ImageIcon(get_Image("/Imagen/32pdf.png"));
//        }

        dao = new DAO();
        Modelo vo = new Modelo();
        ArrayList<Modelo> list = dao.Listar_PdfVO();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[3];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getNombrepdf();
//                if (vo.getArchivopdf() != null) {
//                    fila[2] = new JButton(icono);
//                } else {
//                    fila[2] = new JButton("Vacio");
//                }

                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(32);
        }
    }

//    public Image get_Image(String ruta) {
//        try {
//            ImageIcon imageIcon = new ImageIcon(getClass().getResource(ruta));
//            Image mainIcon = imageIcon.getImage();
//            return mainIcon;
//        } catch (Exception e) {
//        }
//        return null;
//    }
}
