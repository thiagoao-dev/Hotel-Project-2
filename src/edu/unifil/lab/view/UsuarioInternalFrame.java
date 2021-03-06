package edu.unifil.lab.view;

import edu.unifil.lab.controller.UsuarioJpaController;
import edu.unifil.lab.controller.exceptions.NonexistentEntityException;
import edu.unifil.lab.entity.Usuario;
import edu.unifil.lab.view.table.UsuarioTableModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class UsuarioInternalFrame extends javax.swing.JInternalFrame {
    
    // Set some attributes
    private UsuarioTableModel    usuarioTableModel;
    private UsuarioJpaController usuarioJpaController;
    private List<Usuario>        usuarios;   
    private Usuario              auxUsuario;
    
    public UsuarioInternalFrame() {
    
        initComponents();
        
        auxUsuario = new Usuario();
        usuarioTableModel = new UsuarioTableModel();
        tableUsuarioInternalFrame.setModel(usuarioTableModel);
        
        setTitle("Usuário");              
        changeEnable(true);
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservaHotelPU");
        usuarioJpaController = new UsuarioJpaController(factory);
        usuarios = usuarioJpaController.findUsuarioEntities();
    
        txtLogin.grabFocus();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUsuarioInternalFrame = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel1.setText("Login:");

        jLabel2.setText("Password:");

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/unifil/lab/images/add1.jpg"))); // NOI18N
        btnNew.setText("Novo");
        btnNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNewMousePressed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/unifil/lab/images/delete.jpg"))); // NOI18N
        btnDelete.setText("Remover");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteMousePressed(evt);
            }
        });

        tableUsuarioInternalFrame.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableUsuarioInternalFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableUsuarioInternalFrameMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableUsuarioInternalFrame);

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/unifil/lab/images/update.jpg"))); // NOI18N
        btnUpdate.setText("Atualizar");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUpdateMousePressed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/unifil/lab/images/save.jpg"))); // NOI18N
        btnAdd.setText("Inserir");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAddMousePressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 286, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel2)
                                .add(jLabel1))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(txtPassword, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .add(txtLogin)))
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(btnNew, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(btnUpdate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(btnAdd, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(btnDelete, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(txtLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(txtPassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnNew)
                    .add(btnAdd))
                .add(1, 1, 1)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnUpdate)
                    .add(btnDelete))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void changeEnable(boolean status){
        btnAdd.setEnabled(status);
        btnDelete.setEnabled(!status);
        btnUpdate.setEnabled(!status);
    }
            
    private void cleanTxtFields(){
        txtLogin.setText("");
        txtPassword.setText("");
    }
    
    private void btnNewMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewMousePressed
        cleanTxtFields();
        if(!btnAdd.isEnabled()){
            changeEnable(true);
        }
        
    }//GEN-LAST:event_btnNewMousePressed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        setVisible(false);
    }//GEN-LAST:event_formInternalFrameClosed

    private void tableUsuarioInternalFrameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUsuarioInternalFrameMousePressed
        // Check if the selected row is bigger then zero : to continue
        // This way, protect to the application to do not delete the master user
        if(tableUsuarioInternalFrame.getSelectedRow() > 0){
            // Verify if the add button is enable : to continue
            if(btnAdd.isEnabled()){
                // Change the button status
                changeEnable(!btnAdd.isEnabled());
            }
            // Recovery the clicked user listed
            auxUsuario = (Usuario) usuarioTableModel.getValueAt(tableUsuarioInternalFrame.getSelectedRow(), 2);
            // Set the recovered user attributes to the form fields
            txtLogin.setText(auxUsuario.getLogin());
            txtPassword.setText(auxUsuario.getPassword());
        // Check if the user it's trying to select the application master user : to continue
        } else if(tableUsuarioInternalFrame.getSelectedRow() == 0) {
            // Show a message that cannot remove the master user
            JOptionPane.showMessageDialog(rootPane, "Não é possível remover ou alterar o usuário principal", "", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_tableUsuarioInternalFrameMousePressed

    private void btnDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMousePressed
        // Check if delete button is enable : to continue
        if(btnDelete.isEnabled()){
            // Check if the form fields isn't empty and user id : to continue
            auxUsuario = (Usuario) usuarioTableModel.getValueAt(tableUsuarioInternalFrame.getSelectedRow(), 2);
            if(!(txtLogin.getText().isEmpty() && (auxUsuario.getIdUsuario() < 0))){                                
                try {
                    usuarioJpaController.destroy(auxUsuario.getIdUsuario());
                    JOptionPane.showMessageDialog(rootPane, "Usuário removido!", "", JOptionPane.INFORMATION_MESSAGE);
                    usuarioTableModel.fireTableDataChanged();
                    tableUsuarioInternalFrame.setModel(usuarioTableModel);
                    tableUsuarioInternalFrame.repaint();
                    tableUsuarioInternalFrame.revalidate();
                    tableUsuarioInternalFrame.scrollRectToVisible(tableUsuarioInternalFrame.getCellRect(tableUsuarioInternalFrame.getRowCount()-1, 0, true));
                    tableUsuarioInternalFrame.clearSelection();
                    cleanTxtFields();
                    changeEnable(btnDelete.isEnabled());
                    txtLogin.grabFocus();
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(UsuarioInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, "Erro ao remover usuário!", "", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Preencher os campos!", "", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteMousePressed

    private void btnUpdateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMousePressed
        // Check if update button is enable : to continue
        if(btnUpdate.isEnabled()){
            // Check if the form fields isn't empty and user id : to continue
            auxUsuario = (Usuario) usuarioTableModel.getValueAt(tableUsuarioInternalFrame.getSelectedRow(), 2);
            if(!(txtLogin.getText().isEmpty() && (auxUsuario.getIdUsuario() < 0))){
                
                auxUsuario.setLogin(txtLogin.getText());
                auxUsuario.setPassword(txtPassword.getText());             
          
                 try {
                    usuarioJpaController.edit(auxUsuario);
                    JOptionPane.showMessageDialog(rootPane, "Usuário atualizado!", "", JOptionPane.INFORMATION_MESSAGE);
                    usuarioTableModel.fireTableDataChanged();
                    tableUsuarioInternalFrame.repaint();
                    tableUsuarioInternalFrame.revalidate();
                    tableUsuarioInternalFrame.scrollRectToVisible(tableUsuarioInternalFrame.getCellRect(tableUsuarioInternalFrame.getSelectedRow(), 0, true));
                    tableUsuarioInternalFrame.clearSelection();
                    cleanTxtFields(); 
                    changeEnable(btnUpdate.isEnabled());
                    txtLogin.grabFocus();  
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(UsuarioInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(UsuarioInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, "Erro ao atualizar usuário!", "", JOptionPane.ERROR_MESSAGE);
                }                  
            }else{
                JOptionPane.showMessageDialog(rootPane, "Preencher os campos!", "", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUpdateMousePressed

    private void btnAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMousePressed
        // Check if add button is enable : to continue
        if(btnAdd.isEnabled()){
            // Check if the form fields isn't empty : to continue
            if(!(txtLogin.getText().trim().isEmpty() && txtPassword.getText().trim().isEmpty())){
                
                Usuario usuario = new Usuario();
                usuario.setLogin(txtLogin.getText());
                usuario.setPassword(txtPassword.getText());
                
                try {
                    usuarioJpaController.create(usuario);
                    JOptionPane.showMessageDialog(rootPane, "Usuário inserido!", "", JOptionPane.INFORMATION_MESSAGE);
                    usuarioTableModel.fireTableDataChanged();
                    tableUsuarioInternalFrame.setModel(usuarioTableModel);
                    tableUsuarioInternalFrame.repaint();
                    tableUsuarioInternalFrame.revalidate();
                    tableUsuarioInternalFrame.scrollRectToVisible(tableUsuarioInternalFrame.getCellRect(tableUsuarioInternalFrame.getRowCount()-1, 0, true));
                    tableUsuarioInternalFrame.clearSelection();
                    cleanTxtFields();
                    changeEnable(btnAdd.isEnabled());
                    txtLogin.grabFocus();  
                } catch (Exception ex) {
                    Logger.getLogger(UsuarioInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, "Erro ao criar usuário!", "", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Preencher os campos!", "", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAddMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUsuarioInternalFrame;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables
}
