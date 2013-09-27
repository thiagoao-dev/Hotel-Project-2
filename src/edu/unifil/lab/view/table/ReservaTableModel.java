package edu.unifil.lab.view.table;

import edu.unifil.lab.controller.ReservaJpaController;
import edu.unifil.lab.entity.Reserva;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mhadaniya
 */
public class ReservaTableModel extends AbstractTableModel{
    
    String[] columnNames = {"Cod.Reserva","Visitante", "Quarto", "Data Entrada", "Data de Saída", "Pagamento", "Status"};
    List<Reserva> reservas;
    ReservaJpaController reservaJpaController;    
    
    public ReservaTableModel() {
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservaHotelPU");
        reservaJpaController         = new ReservaJpaController(factory);
        reservas                     = reservaJpaController.findReservaEntities();
        
    }

    @Override
    public String getColumnName(int column) {
            return columnNames[column];
    }
    
    @Override
    public int getRowCount() {
        if(reservas.isEmpty()){
            return 0;
        }else{
            return reservas.size();
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
                if(reservas.get(rowIndex).getCodReserva() < 0){
                    return "";
                }else{
                    return reservas.get(rowIndex).getCodReserva();
                }
                
            case 1:
                if(!(reservas.get(rowIndex) == null)){
                    return reservas.get(rowIndex).getIdVisitante().getNome();
                }else{
                    return "";
                }
                
            case 2:
                if(!(reservas.get(rowIndex) == null)){
                    return reservas.get(rowIndex).getIdQuarto().getDescricaoQuarto();
                }else{
                    return "";
                }
            
            case 3:
                if(!(reservas.get(rowIndex) == null)){
                    String newstring = new SimpleDateFormat("dd-MM-yyyy").format(reservas.get(rowIndex).getDataEntrada());
                    return newstring;
                }else{
                    return "";
                }
            
            case 4:
                if(!(reservas.get(rowIndex) == null)){
                    String newstring = new SimpleDateFormat("dd-MM-yyyy").format(reservas.get(rowIndex).getDataSaida());
                    return newstring;                    
                }else{
                    return "";
                }
            
            case 5:
                if(!(reservas.get(rowIndex) == null)){
                    return reservas.get(rowIndex).getPagamento();
                }else{
                    return "";
                }
                
            case 6:
                if(!(reservas.get(rowIndex) == null)){
                    return reservas.get(rowIndex).getStatus();
                }else{
                    return "";
                }
                
            default:
                return reservas.get(rowIndex);
        }
    }

    @Override
    public void fireTableDataChanged() {
        reservas = reservaJpaController.findReservaEntities();
    }
    
    
    
    
    
}
