package edu.unifil.lab.view.table;

import edu.unifil.lab.controller.UsuarioJpaController;
import edu.unifil.lab.entity.Usuario;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel{
    
    String[]             columnNames = {"Nome","Password"};
    List<Usuario>        usuarios;
    UsuarioJpaController usuarioJpaController;
    
    public UsuarioTableModel() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservaHotelPU");
        usuarioJpaController         = new UsuarioJpaController(factory);
        usuarios                     = usuarioJpaController.findUsuarioEntities();
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return columnNames[0];
                
            case 1:
                return columnNames[1];
            
            default:
                return "";                
        }
    }
    
    @Override
    public int getRowCount() {
        if(usuarios.isEmpty()){
            return 0;
        }else{
            return usuarios.size();
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                if(usuarios.get(rowIndex).getLogin().isEmpty()){
                    return "";
                }else{
                    return usuarios.get(rowIndex).getLogin();
                }
                
            case 1:
                if(usuarios.get(rowIndex).getPassword().isEmpty()){
                    return "";
                }else{
                    return usuarios.get(rowIndex).getPassword();
                }                
                
            default:
                return usuarios.get(rowIndex);
        }
    }

    @Override
    public void fireTableDataChanged() {
        usuarios = usuarioJpaController.findUsuarioEntities();
    }
    
}
