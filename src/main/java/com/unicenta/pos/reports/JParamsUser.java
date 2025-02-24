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

package com.unicenta.pos.reports;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.Datas;
import com.unicenta.data.loader.QBFCompareEnum;
import com.unicenta.data.loader.SerializerWrite;
import com.unicenta.data.loader.SerializerWriteBasic;
import com.unicenta.pos.admin.PeopleInfo;
import com.unicenta.pos.admin.DataLogicAdmin;
import com.unicenta.pos.admin.JPeopleFinder;
import com.unicenta.pos.forms.AppLocal;
import com.unicenta.pos.forms.AppView;
import java.awt.Component;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author  adrianromero
 */
public class JParamsUser extends javax.swing.JPanel implements ReportEditorCreator {
    
    private DataLogicAdmin dlPeople;
    private PeopleInfo currentpeople;
    
    /** Creates new form JParamsUser */
    public JParamsUser() {

        initComponents();
        
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                currentpeople = null;
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                currentpeople = null;
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                currentpeople = null;
            }
        });
    }

    /**
     *
     * @param app
     */
    @Override
    public void init(AppView app) {
        dlPeople = (DataLogicAdmin) app.getBean("com.unicenta.pos.admin.DataLogicAdmin");
    }
    
    /**
     *
     * @throws BasicException
     */
    @Override
    public void activate() throws BasicException {
        
        currentpeople = null;
        jTextField1.setText(null);        
    }
            
    /**
     *
     * @return
     */
    @Override
    public SerializerWrite getSerializerWrite() {
        return new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING});
    }

    /**
     *
     * @return
     */
    @Override
    public Component getComponent() {
        return this;
    }
    
    /**
     *
     * @return
     * @throws BasicException
     */
    @Override
    public Object createValue() throws BasicException {
        
        if (currentpeople == null) {
            if (jTextField1.getText() == null || jTextField1.getText().equals("")) {
                return new Object[] {QBFCompareEnum.COMP_NONE, null, QBFCompareEnum.COMP_NONE, null};
            } else {
                return new Object[] {QBFCompareEnum.COMP_NONE, null, QBFCompareEnum.COMP_RE, jTextField1.getText()};
            }
        } else {
            return new Object[] {QBFCompareEnum.COMP_EQUALS, currentpeople.getID(), QBFCompareEnum.COMP_NONE, null};
        }
    }     
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnUser = new javax.swing.JButton();

        setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setPreferredSize(new java.awt.Dimension(425, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText(AppLocal.getIntString("label.user")); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(110, 30));

        jTextField1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(200, 30));

        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/unicenta/images/customer_sml.png"))); // NOI18N
        btnUser.setToolTipText("Get Customers");
        btnUser.setPreferredSize(new java.awt.Dimension(80, 45));
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed

        JPeopleFinder finder = JPeopleFinder.getPeopleFinder(this, dlPeople);
        finder.search(currentpeople);
        finder.setVisible(true);
        currentpeople = finder.getSelectedPeople();
        if (currentpeople == null) {
            jTextField1.setText(null);
        } else {
            jTextField1.setText(currentpeople.getName());
        }
        
    }//GEN-LAST:event_btnUserActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    
}
