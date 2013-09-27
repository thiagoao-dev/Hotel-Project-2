package edu.unifil.lab.view.table;

import edu.unifil.lab.controller.TipoQuartoJpaController;
import edu.unifil.lab.entity.TipoQuarto;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

public class TipoQuartoTableModel extends AbstractTableModel{
    
    // Set some attribute
    String[]                columnNames = {"Id","Descrição"};
    List<TipoQuarto>        tipoQuartos;
    TipoQuartoJpaController tipoQuartoJpaController;
    
    public TipoQuartoTableModel() {
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservaHotelPU");
        tipoQuartoJpaController      = new TipoQuartoJpaController(factory);
        tipoQuartos                  = tipoQuartoJpaController.findTipoQuartoEntities();
    
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
        if(tipoQuartos.isEmpty()){
            return 0;
        }else{
            return tipoQuartos.size();
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
                if(tipoQuartos.get(rowIndex).getIdTipo() < 0){
                    return "";
                }else{
                    return tipoQuartos.get(rowIndex).getIdTipo();
                }
                
            case 1:
                if(tipoQuartos.get(rowIndex).getDescricao().isEmpty()){
                    return "";
                }else{
                    return tipoQuartos.get(rowIndex).getDescricao();
                }           
                                
            default:
                return tipoQuartos.get(rowIndex);
        }
    }

    @Override
    public void fireTableDataChanged() {
        tipoQuartos = tipoQuartoJpaController.findTipoQuartoEntities();
    }
    
    
    
    
    
}
