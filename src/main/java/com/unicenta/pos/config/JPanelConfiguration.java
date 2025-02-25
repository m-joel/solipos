//    SOLiPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2025 SOLiPOS & previous Openbravo POS works
//    https://solipos.ch
//
//    This file is part of SOLiPOS
//
//    SOLiPOS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   SOLiPOS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with SOLiPOS.  If not, see <http://www.gnu.org/licenses/>.

package com.unicenta.pos.config;

import com.unicenta.basic.BasicException;
import com.unicenta.data.gui.JMessageDialog;
import com.unicenta.data.gui.MessageInf;
import com.unicenta.pos.forms.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
/**
 *
 * @author adrianromero
 */
public class JPanelConfiguration extends JPanel implements JPanelView {
        
    private List<PanelConfig> m_panelconfig;

    private AppConfig config;
    
    /** Creates new form JPanelConfiguration
     * @param oApp */
    public JPanelConfiguration(AppView oApp) {
        this(oApp.getProperties()); 
        if (oApp!= null) {
            jbtnExit.setVisible(false);
        }
        
        
    }
    
    /**
     *
     * @param props
     */
    public JPanelConfiguration(AppProperties props) {
        
        initComponents();
        config = new AppConfig(props.getConfigFile());

        m_panelconfig = new ArrayList<>();
        
        PanelConfig panel;
        
        panel = new JPanelConfigDatabase();
        m_panelconfig.add(panel);
        jPanelDatabase.add(panel.getConfigComponent());
        
        panel = new JPanelConfigGeneral();
        m_panelconfig.add(panel);
        jPanelGeneral.add(panel.getConfigComponent());
        
        panel = new JPanelConfigLocale();
        m_panelconfig.add(panel);
        jPanelLocale.add(panel.getConfigComponent());
        
        panel = new JPanelConfigPayment();
        m_panelconfig.add(panel);
        jPanelPayment.add(panel.getConfigComponent());

        panel = new JPanelConfigPeripheral();
        m_panelconfig.add(panel);
        jPanelPeripheral.add(panel.getConfigComponent());
        
        panel = new JPanelConfigSystem();
        m_panelconfig.add(panel);
        jPanelSystem.add(panel.getConfigComponent());
        
        panel = new JPanelTicketSetup();
        m_panelconfig.add(panel);
        jPanelTicketSetup.add(panel.getConfigComponent());

        panel = new JPanelConfigCompany();
        m_panelconfig.add(panel);
        jPanelCompany.add(panel.getConfigComponent());

    }
        
    private void restoreProperties() {
        
        if (config.delete()) {
            loadProperties();
        } else {
            JMessageDialog.showMessage(this, 
                    new MessageInf(MessageInf.SGN_WARNING, 
                            AppLocal.getIntString("message.cannotdeleteconfig")));            
        }
    }
    
    private void loadProperties() {
        
        config.load();
        
        // paneles auxiliares
        for (PanelConfig c: m_panelconfig) {
            c.loadProperties(config);
        }
    }
    
    private void saveProperties() {
        
        // paneles auxiliares
        for (PanelConfig c: m_panelconfig) {
            c.saveProperties(config);
        }
        
        try {
            config.save();
            JOptionPane.showMessageDialog(this, 
                AppLocal.getIntString("message.restartchanges"), 
                AppLocal.getIntString("message.title"), 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JMessageDialog.showMessage(this, 
                new MessageInf(MessageInf.SGN_WARNING, 
                AppLocal.getIntString("message.cannotsaveconfig"), e));
        }
    }

    /**
     *
     * @return
     */
    @Override
    public JComponent getComponent() {
        return this;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getTitle() {
        return AppLocal.getIntString("Menu.Configuration");
    }

    /**
     *
     * @throws BasicException
     */
    @Override
    public void activate() throws BasicException {
        loadProperties();        
    }
    
    /**
     *
     * @return
     */
    @Override
    public boolean deactivate() {
        
        boolean haschanged = false;
        for (PanelConfig c: m_panelconfig) {
            if (c.hasChanged()) {
                haschanged = true;
            }
        }        
        
        
        if (haschanged) {
            int res = JOptionPane.showConfirmDialog(this, 
                    AppLocal.getIntString("message.wannasave"), 
                    AppLocal.getIntString("title.editor"), 
                    JOptionPane.YES_NO_CANCEL_OPTION, 
                    JOptionPane.QUESTION_MESSAGE);
            
            if (res == JOptionPane.YES_OPTION) {
                saveProperties();
                return true;
            } else {
                return res == JOptionPane.NO_OPTION;
            }
        } else {
            return true;
        }
    }      

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelGeneral = new javax.swing.JPanel();
        jPanelLocale = new javax.swing.JPanel();
        jPanelPayment = new javax.swing.JPanel();
        jPanelPeripheral = new javax.swing.JPanel();
        jPanelSystem = new javax.swing.JPanel();
        jPanelTicketSetup = new javax.swing.JPanel();
        jPanelCompany = new javax.swing.JPanel();
        jPanelDatabase = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jbtnRestore = new javax.swing.JButton();
        jbtnExit = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();

        setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(950, 600));

        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(930, 550));

        jPanelGeneral.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanelGeneral.setPreferredSize(new java.awt.Dimension(0, 400));
        jPanelGeneral.setLayout(new javax.swing.BoxLayout(jPanelGeneral, javax.swing.BoxLayout.LINE_AXIS));
        jTabbedPane1.addTab("General", jPanelGeneral);

        jPanelLocale.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanelLocale.setPreferredSize(new java.awt.Dimension(0, 400));
        jPanelLocale.setLayout(new javax.swing.BoxLayout(jPanelLocale, javax.swing.BoxLayout.LINE_AXIS));
        jTabbedPane1.addTab("Locale", jPanelLocale);

        jPanelPayment.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanelPayment.setPreferredSize(new java.awt.Dimension(0, 400));
        jPanelPayment.setLayout(new javax.swing.BoxLayout(jPanelPayment, javax.swing.BoxLayout.LINE_AXIS));
        jTabbedPane1.addTab("Payment Method", jPanelPayment);

        jPanelPeripheral.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanelPeripheral.setPreferredSize(new java.awt.Dimension(0, 400));
        jPanelPeripheral.setLayout(new javax.swing.BoxLayout(jPanelPeripheral, javax.swing.BoxLayout.LINE_AXIS));
        jTabbedPane1.addTab("Peripherals", jPanelPeripheral);

        jPanelSystem.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanelSystem.setPreferredSize(new java.awt.Dimension(0, 400));
        jPanelSystem.setLayout(new javax.swing.BoxLayout(jPanelSystem, javax.swing.BoxLayout.LINE_AXIS));
        jTabbedPane1.addTab("System Options", jPanelSystem);

        jPanelTicketSetup.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanelTicketSetup.setPreferredSize(new java.awt.Dimension(0, 400));
        jPanelTicketSetup.setLayout(new javax.swing.BoxLayout(jPanelTicketSetup, javax.swing.BoxLayout.LINE_AXIS));
        jTabbedPane1.addTab("Ticket Setup", jPanelTicketSetup);

        jPanelCompany.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanelCompany.setLayout(new javax.swing.BoxLayout(jPanelCompany, javax.swing.BoxLayout.LINE_AXIS));
        jTabbedPane1.addTab("Company", jPanelCompany);

        jPanelDatabase.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanelDatabase.setPreferredSize(new java.awt.Dimension(0, 400));
        jPanelDatabase.setLayout(new javax.swing.BoxLayout(jPanelDatabase, javax.swing.BoxLayout.LINE_AXIS));
        jTabbedPane1.addTab("Database Setup", jPanelDatabase);

        jbtnRestore.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbtnRestore.setText(AppLocal.getIntString("button.factory")); // NOI18N
        jbtnRestore.setMaximumSize(new java.awt.Dimension(103, 33));
        jbtnRestore.setMinimumSize(new java.awt.Dimension(103, 33));
        jbtnRestore.setPreferredSize(new java.awt.Dimension(110, 45));
        jbtnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRestoreActionPerformed(evt);
            }
        });

        jbtnExit.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbtnExit.setText(AppLocal.getIntString("Button.Exit")); // NOI18N
        jbtnExit.setMaximumSize(new java.awt.Dimension(70, 33));
        jbtnExit.setMinimumSize(new java.awt.Dimension(70, 33));
        jbtnExit.setPreferredSize(new java.awt.Dimension(110, 45));
        jbtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExitActionPerformed(evt);
            }
        });

        jbtnSave.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbtnSave.setText(AppLocal.getIntString("button.save")); // NOI18N
        jbtnSave.setMaximumSize(new java.awt.Dimension(70, 33));
        jbtnSave.setMinimumSize(new java.awt.Dimension(70, 33));
        jbtnSave.setPreferredSize(new java.awt.Dimension(110, 45));
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnRestore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jbtnRestore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jbtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRestoreActionPerformed

        if (JOptionPane.showConfirmDialog(this, 
                AppLocal.getIntString("message.configfactory"), 
                AppLocal.getIntString("message.title"), 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {          
            restoreProperties();
        }
        
    }//GEN-LAST:event_jbtnRestoreActionPerformed

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed

        saveProperties();
        
    }//GEN-LAST:event_jbtnSaveActionPerformed

    private void jbtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExitActionPerformed
        deactivate();
        System.exit(0);
    }//GEN-LAST:event_jbtnExitActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCompany;
    private javax.swing.JPanel jPanelDatabase;
    private javax.swing.JPanel jPanelGeneral;
    private javax.swing.JPanel jPanelLocale;
    private javax.swing.JPanel jPanelPayment;
    private javax.swing.JPanel jPanelPeripheral;
    private javax.swing.JPanel jPanelSystem;
    private javax.swing.JPanel jPanelTicketSetup;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtnExit;
    private javax.swing.JButton jbtnRestore;
    private javax.swing.JButton jbtnSave;
    // End of variables declaration//GEN-END:variables
    
}
