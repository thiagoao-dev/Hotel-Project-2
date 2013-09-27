package edu.unifil.lab.view.table;

import edu.unifil.lab.controller.VisitanteJpaController;
import edu.unifil.lab.entity.Visitante;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

public class VisitanteTableModel extends AbstractTableModel{
    
    // Set some attributes
    String[]               columnNames = {"Id", "Nome", "Telefone", "Email"};
    List<Visitante>        visitantes;
    VisitanteJpaController visitanteJpaController;
    
    public VisitanteTableModel() {
        // Create the persistence connection entity
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservaHotelPU");
        visitanteJpaController       = new VisitanteJpaController(factory);
        visitantes                   = visitanteJpaController.findVisitanteEntities();
    }

    @Override
    public String getColumnName(int column) {
            return columnNames[column];
    }
    
    @Override
    public int getRowCount() {        
        if(visitantes == null || visitantes.size() < 0){
            return 0;
        }else{
            return visitantes.size();
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
                if(visitantes.get(rowIndex).getIdVisitante()< 0){
                    return "";
                }else{
                    return visitantes.get(rowIndex).getIdVisitante();
                }
                
            case 1:
                if(!(visitantes.get(rowIndex) == null)){
                    return visitantes.get(rowIndex).getNome();
                }else{
                    return "";
                }
            
            case 2:
                if(!(visitantes.get(rowIndex) == null)){
                    return visitantes.get(rowIndex).getTelefone();
                }else{
                    return "";
                }
                    
            case 3:
                if(!(visitantes.get(rowIndex) == null)){
                    return visitantes.get(rowIndex).getEmail();
                }else{
                    return "";
                }

                                
            default:
                return visitantes.get(rowIndex);
        }
    }

    @Override
    public void fireTableDataChanged() {
        visitantes = visitanteJpaController.findVisitanteEntities();
    }
    
    
    
    
    
}
