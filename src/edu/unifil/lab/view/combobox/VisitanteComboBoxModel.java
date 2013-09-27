package edu.unifil.lab.view.combobox;

import edu.unifil.lab.controller.QuartoJpaController;
import edu.unifil.lab.controller.VisitanteJpaController;
import edu.unifil.lab.entity.Quarto;
import edu.unifil.lab.entity.Visitante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class VisitanteComboBoxModel implements ComboBoxModel {

    // Set some attributes
    private List<Visitante> visitantes;
    private VisitanteJpaController visitanteJpaController;
    private Visitante auxVisitante;
    
    public VisitanteComboBoxModel(){
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservaHotelPU");
        visitanteJpaController       = new VisitanteJpaController(factory);
        visitantes                   = visitanteJpaController.findVisitanteEntities();
        
    }
    
    @Override
    public void setSelectedItem(Object anItem) {
       auxVisitante = (Visitante) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return auxVisitante;
    }

    @Override
    public int getSize() {
        return visitantes.size();
    }

    @Override
    public Object getElementAt(int index) {
        if(index < visitantes.size()){
            return visitantes.get(index);
        }else{
            return null;
        }
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }

    public ArrayList<Visitante> getVisitantes() {
        return (ArrayList<Visitante>) visitantes;
    }
    
    
    
}
