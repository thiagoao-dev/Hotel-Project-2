/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unifil.lab.view.combobox;

import edu.unifil.lab.controller.TipoQuartoJpaController;
import edu.unifil.lab.entity.TipoQuarto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class TipoQuartoComboBoxModel implements ComboBoxModel {
    
    // Set some attributes
    private List<TipoQuarto> tipoQuartos; 
    private TipoQuartoJpaController tipoQuartoJpaController;
    private TipoQuarto auxTipoQuarto;
    
    public TipoQuartoComboBoxModel(){
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservaHotelPU");
        tipoQuartoJpaController      = new TipoQuartoJpaController(factory);
        tipoQuartos                  = tipoQuartoJpaController.findTipoQuartoEntities();
        
    }
    
    @Override
    public void setSelectedItem(Object anItem) {
        auxTipoQuarto = (TipoQuarto) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return auxTipoQuarto;
    }

    @Override
    public int getSize() {
        return tipoQuartos.size();
    }

    @Override
    public Object getElementAt(int index) {
        return tipoQuartos.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }

    public ArrayList<TipoQuarto> getTipoQuartos() {
        return (ArrayList<TipoQuarto>) tipoQuartos;
    }
    
    
    
}
