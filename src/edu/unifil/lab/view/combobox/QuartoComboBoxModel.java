/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unifil.lab.view.combobox;

import edu.unifil.lab.controller.QuartoJpaController;
import edu.unifil.lab.entity.Quarto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author mhadaniya
 */
public class QuartoComboBoxModel implements ComboBoxModel {
    
    // Set some attributes
    private List<Quarto> quartos;
    private QuartoJpaController quartoJpaController;
    private Quarto auxQuarto;
    
    public QuartoComboBoxModel(){
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservaHotelPU");
        quartoJpaController          = new QuartoJpaController(factory);
        quartos                      = quartoJpaController.findQuartoEntities();
        
    }
    
    @Override
    public void setSelectedItem(Object anItem) {
       auxQuarto = (Quarto) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return auxQuarto;
    }

    @Override
    public int getSize() {
        return quartos.size();
    }

    @Override
    public Object getElementAt(int index) {
        return quartos.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }

    public ArrayList<Quarto> getTipoQuartos() {
        return (ArrayList<Quarto>) quartos;
    }
    
    
    
}
