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

package com.unicenta.pos.inventory;

import com.unicenta.basic.BasicException;
import com.unicenta.data.user.DirtyManager;
import com.unicenta.data.user.EditorRecord;
import com.unicenta.format.Formats;
import com.unicenta.pos.forms.AppLocal;
import java.awt.Component;
import java.util.UUID;

/**
 *
 * @author adrian
 */
public class AttributeValuesEditor extends javax.swing.JPanel implements EditorRecord {

    private Object id;
    private Object attid;

    private Object insertid;

    /** Creates new form AttributesValuesEditor
     * @param dirty */
    public AttributeValuesEditor(DirtyManager dirty) {
        
        initComponents();

        jValue.getDocument().addDocumentListener(dirty);
    }

    /**
     *
     * @param insertid
     */
    public void setInsertId(String insertid) {

        this.insertid = insertid;
    }

    /**
     *
     */
    @Override
    public void refresh() {
    }

    /**
     *
     */
    @Override
    public void writeValueEOF() {

        id = null;
        attid = null;
        jValue.setText(null);

        jValue.setEnabled(false);
    }

    /**
     *
     */
    @Override
    public void writeValueInsert() {

        id = UUID.randomUUID().toString();
        attid = insertid;
        jValue.setText(null);

        jValue.setEnabled(true);
    }

    /**
     *
     * @param value
     */
    @Override
    public void writeValueEdit(Object value) {

        Object[] obj = (Object[]) value;

        id = obj[0];
        attid = obj[1];
        jValue.setText(Formats.STRING.formatValue(obj[2]));

        jValue.setEnabled(true);
    }

    /**
     *
     * @param value
     */
    @Override
    public void writeValueDelete(Object value) {

        Object[] obj = (Object[]) value;

        id = obj[0];
        attid = obj[1];
        jValue.setText(Formats.STRING.formatValue(obj[2]));

        jValue.setEnabled(false);
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
        
        return new Object[] {
            id,
            attid,
            Formats.STRING.formatValue(jValue.getText())
        };
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jValue = new javax.swing.JTextField();

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText(AppLocal.getIntString("label.value")); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(110, 30));

        jValue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jValue.setPreferredSize(new java.awt.Dimension(220, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jValue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jValue;
    // End of variables declaration//GEN-END:variables


}
