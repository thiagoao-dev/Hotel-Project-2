package edu.unifil.lab.view.table;

import edu.unifil.lab.controller.QuartoJpaController;
import edu.unifil.lab.controller.TipoQuartoJpaController;
import edu.unifil.lab.entity.Quarto;
import edu.unifil.lab.entity.TipoQuarto;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mhadaniya
 */
public class QuartoTableModel extends AbstractTableModel{
    
    // Set some attributes
    String[]                columnNames = {"Id", "Descrição", "Capacidade", "Tipo de Quarto"};
    List<Quarto>            quartos;
    List<TipoQuarto>        tipoQuartos;
    QuartoJpaController     quartoJpaController;
    TipoQuartoJpaController tipoQuartoJpaController;
    
    public QuartoTableModel() {
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservaHotelPU");
        quartoJpaController          = new QuartoJpaController(factory);
        tipoQuartoJpaController      = new TipoQuartoJpaController(factory);
        quartos                      = quartoJpaController.findQuartoEntities();
        tipoQuartos                  = tipoQuartoJpaController.findTipoQuartoEntities();
    
    }

    @Override
    public String getColumnName(int column) {
            return columnNames[column];
    }
    
    @Override
    public int getRowCount() {
        if(quartos.isEmpty()){
            return 0;
        }else{
            return quartos.size();
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
                if(quartos.get(rowIndex).getIdQuarto() < 0){
                    return "";
                }else{
                    return quartos.get(rowIndex).getIdQuarto();
                }
                
            case 1:
                if(!(quartos.get(rowIndex) == null)){
                    return quartos.get(rowIndex).getDescricaoQuarto();
                }else{
                    return "";
                }
            
            case 2:
                if(quartos.get(rowIndex).getCapacidade() < 0){
                    return "";
                }else{
                    return quartos.get(rowIndex).getCapacidade();
                }   
           
            case 3:
                if(quartos.get(rowIndex).getTipo().getIdTipo() < 0){
                    return "";
                }else{
                    return quartos.get(rowIndex).getTipo().getDescricao();
                }
                                
            default:
                return quartos.get(rowIndex);
        }
    }

    @Override
    public void fireTableDataChanged() {
        quartos = quartoJpaController.findQuartoEntities();
    }
    
    
    
    
    
}
