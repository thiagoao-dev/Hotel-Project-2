package edu.unifil.lab.view;

import edu.unifil.lab.entity.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public final class ReservaHotel extends javax.swing.JFrame implements ActionListener{
    
    /**
     * Creates new form ReservaHotel
     */
    public ReservaHotel() {
        initComponents();
        setSize(1024, 800);
        setLocationRelativeTo(null);
        createUsuarioInternalFrame();
        setContentPane(jDesktopPane1);
        
    }
    
    public ReservaHotel(Usuario usuario){
        initComponents();
        setSize(1024, 800);
        setLocationRelativeTo(null);
        jMenuBar1.add(Box.createHorizontalGlue());
        JMenuItem jmenu = new JMenuItem("Login: " + usuario.getLogin());
        jmenu.setEnabled(false);
        jMenuBar1.add(jmenu);  
        setupMenuItems();
        
        setContentPane(jDesktopPane1);    
    }
    
    protected void setupMenuItems(){  
        jMenuItem1.setMnemonic(KeyEvent.VK_U);
        jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_U, ActionEvent.ALT_MASK));
        jMenuItem1.setActionCommand("usuario");
        jMenuItem1.addActionListener(this);
        
        jMenuItem2.setMnemonic(KeyEvent.VK_T);
        jMenuItem2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.ALT_MASK));
        jMenuItem2.setActionCommand("tipoQuarto");
        jMenuItem2.addActionListener(this);
        
        jMenuItem3.setMnemonic(KeyEvent.VK_Q);
        jMenuItem3.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        jMenuItem3.setActionCommand("quarto");
        jMenuItem3.addActionListener(this);
        
        jMenuItem4.setMnemonic(KeyEvent.VK_V);
        jMenuItem4.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.ALT_MASK));
        jMenuItem4.setActionCommand("visitante");
        jMenuItem4.addActionListener(this);
        
         jMenuItem5.setMnemonic(KeyEvent.VK_R);
        jMenuItem5.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.ALT_MASK));
        jMenuItem5.setActionCommand("reserva");
        jMenuItem5.addActionListener(this);
        
    }
    //React to menu selections.
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("usuario".equals(e.getActionCommand())) { //new
            createUsuarioInternalFrame();
        } if ("tipoQuarto".equals(e.getActionCommand())) { //new
            createTipoQuartoInternalFrame();
        } if ("quarto".equals(e.getActionCommand())) { 
            createQuartoInternalFrame();
        } if("visitante".equals(e.getActionCommand())){
            createVisitanteInternalFrame();
        } if("reserva".equals(e.getActionCommand())){
            createReservaInternalFrame();
        }
    }
    
    protected void quit() {
        int opcao = JOptionPane.showConfirmDialog(rootPane, "Deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
        switch(opcao){
            case 0: //YES
                System.exit(0);
            
            case 1: //NO
                break;
        }
        
    }
    
    protected void createUsuarioInternalFrame(){
        UsuarioInternalFrame usuarioInternalFrame = new UsuarioInternalFrame();
        usuarioInternalFrame.setSize(370, 340);
        usuarioInternalFrame.setVisible(true);         
        usuarioInternalFrame.setLocation((jDesktopPane1.getWidth() - 400)/2, (jDesktopPane1.getHeight()- 300)/2);
        jDesktopPane1.add(usuarioInternalFrame);
        try {
            usuarioInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ReservaHotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     protected void createQuartoInternalFrame(){
        QuartoInternalFrame quartoInternalFrame = new QuartoInternalFrame();
        quartoInternalFrame.setSize(330, 420);
//        [328, 421]
        quartoInternalFrame.setVisible(true);         
        quartoInternalFrame.setLocation((jDesktopPane1.getWidth() - 400)/2, (jDesktopPane1.getHeight()- 300)/2);
        jDesktopPane1.add(quartoInternalFrame);
        try {
            quartoInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ReservaHotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    protected void createTipoQuartoInternalFrame(){
        TipoDeQuartoInternalFrame tipoDeQuartoInternalFrame = new TipoDeQuartoInternalFrame();
        tipoDeQuartoInternalFrame.setSize(326, 420);
        
        tipoDeQuartoInternalFrame.setVisible(true);         
        tipoDeQuartoInternalFrame.setLocation((jDesktopPane1.getWidth() - 400)/2, (jDesktopPane1.getHeight()- 300)/2);
        jDesktopPane1.add(tipoDeQuartoInternalFrame);
        try {
            tipoDeQuartoInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ReservaHotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     protected void createVisitanteInternalFrame(){
        VisitanteInternalFrame visitanteInternalFrame = new VisitanteInternalFrame();
        visitanteInternalFrame.setSize(600, 370);        
        visitanteInternalFrame.setVisible(true);         
        visitanteInternalFrame.setLocation((jDesktopPane1.getWidth() - 400)/2, (jDesktopPane1.getHeight()- 300)/2);
        jDesktopPane1.add(visitanteInternalFrame);
        try {
            visitanteInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ReservaHotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    protected void createReservaInternalFrame(){
        ReservaInternalFrame reservaInternalFrame = new ReservaInternalFrame();
        reservaInternalFrame.setSize(650, 420);  //[650, 420]
        reservaInternalFrame.setVisible(true);         
        reservaInternalFrame.setLocation((jDesktopPane1.getWidth() - 400)/2, (jDesktopPane1.getHeight()- 300)/2);
        jDesktopPane1.add(reservaInternalFrame);
        try {
            reservaInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ReservaHotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu4 = new javax.swing.JMenu();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ReservaHotel");

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));
        jDesktopPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jDesktopPane1.setDragMode(javax.swing.JDesktopPane.OUTLINE_DRAG_MODE);
        jDesktopPane1.setMaximumSize(new java.awt.Dimension(400, 300));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(400, 300));
        jDesktopPane1.setSize(new java.awt.Dimension(400, 300));

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenu5.setText("Administração");

        jMenuItem5.setText("Reserva");
        jMenu5.add(jMenuItem5);

        jMenuItem4.setText("Visitante");
        jMenu5.add(jMenuItem4);

        jMenuItem3.setText("Quarto");
        jMenu5.add(jMenuItem3);

        jMenuItem2.setText("Tipo de Quarto");
        jMenu5.add(jMenuItem2);

        jMenuBar1.add(jMenu5);

        jMenu2.setText("Sistema");

        jMenuItem1.setText("Usuario");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseEntered(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Sair");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu3MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MousePressed
        quit();
    }//GEN-LAST:event_jMenu3MousePressed

    private void jMenuItem1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1MouseEntered

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservaHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReservaHotel().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    // End of variables declaration//GEN-END:variables
}
