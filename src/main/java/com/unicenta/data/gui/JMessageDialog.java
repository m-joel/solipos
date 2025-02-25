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

package com.unicenta.data.gui;

import com.unicenta.data.loader.LocalRes;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author  adrian
 */
public class JMessageDialog extends javax.swing.JDialog {
    
    /** Creates new form JMessageDialog */
    private JMessageDialog(java.awt.Frame parent, boolean modal) {        
        super(parent, modal);       
    }
    /** Creates new form JMessageDialog */
    private JMessageDialog(java.awt.Dialog parent, boolean modal) {        
        super(parent, modal);       
    }
    
    private static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }
    
    /**
     *
     * @param parent
     * @param inf
     */
    public static void showMessage(Component parent, MessageInf inf) {
        
        Window window = getWindow(parent);      
        
        JMessageDialog myMsg;
        if (window instanceof Frame) { 
            myMsg = new JMessageDialog((Frame) window, true);
        } else {
            myMsg = new JMessageDialog((Dialog) window, true);
        }
        
        myMsg.initComponents();
        myMsg.applyComponentOrientation(parent.getComponentOrientation());
        myMsg.jscrException.setVisible(false);        
        myMsg.getRootPane().setDefaultButton(myMsg.jcmdOK);
        
        myMsg.jlblIcon.setIcon(inf.getSignalWordIcon());
        myMsg.jlblErrorCode.setText(inf.getErrorCodeMsg());
        myMsg.jlblMessage.setText("<html>" + inf.getMessageMsg());
        
        // Capturamos el texto de la excepcion...
        if (inf.getCause() == null) {
            myMsg.jtxtException.setText(null);
        } else {            
            StringBuilder sb = new StringBuilder(); 
            
            if (inf.getCause() instanceof Throwable) {
                Throwable t = (Throwable) inf.getCause();
                while (t != null) {
                    sb.append(t.getClass().getName());
                    sb.append(": \n");
                    sb.append(t.getMessage());
                    sb.append("\n\n");
                    t = t.getCause();
                }
            } else if (inf.getCause() instanceof Throwable[]) {
                Throwable[] m_aExceptions = (Throwable[]) inf.getCause();
                for (int i = 0; i < m_aExceptions.length; i++) {
                    sb.append(m_aExceptions[i].getClass().getName());
                    sb.append(": \n");
                    sb.append(m_aExceptions[i].getMessage());
                    sb.append("\n\n");
                }             
            } else if (inf.getCause() instanceof Object[]) {
                Object [] m_aObjects = (Object []) inf.getCause();
                for (int i = 0; i < m_aObjects.length; i++) {
                    sb.append(m_aObjects[i].toString());
                    sb.append("\n\n");
                }             
            } else if (inf.getCause() instanceof String) {
                sb.append(inf.getCause().toString());
            } else {
                sb.append(inf.getCause().getClass().getName());
                sb.append(": \n");
                sb.append(inf.getCause().toString());
            }
            myMsg.jtxtException.setText(sb.toString());  
        }       
        myMsg.jtxtException.setCaretPosition(0);            
        
        //myMsg.show();
        myMsg.setVisible(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jlblErrorCode = new javax.swing.JLabel();
        jlblMessage = new javax.swing.JLabel();
        jscrException = new javax.swing.JScrollPane();
        jtxtException = new javax.swing.JTextArea();
        jlblIcon = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jcmdOK = new javax.swing.JButton();
        jcmdMore = new javax.swing.JButton();

        setTitle(LocalRes.getIntString("title.message")); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        jlblErrorCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jlblErrorCode.setText("jlblErrorCode");
        jPanel4.add(jlblErrorCode);

        jlblMessage.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jlblMessage.setText("jlblMessage");
        jlblMessage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jlblMessage.setMinimumSize(new java.awt.Dimension(200, 100));
        jlblMessage.setPreferredSize(new java.awt.Dimension(200, 100));
        jPanel4.add(jlblMessage);

        jscrException.setAlignmentX(0.0F);

        jtxtException.setEditable(false);
        jtxtException.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jscrException.setViewportView(jtxtException);

        jPanel4.add(jscrException);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        jlblIcon.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jlblIcon.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(jlblIcon, java.awt.BorderLayout.LINE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jcmdOK.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jcmdOK.setText(LocalRes.getIntString("button.OK")); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("pos_messages"); // NOI18N
        jcmdOK.setActionCommand(bundle.getString("button.OK")); // NOI18N
        jcmdOK.setMaximumSize(new java.awt.Dimension(65, 33));
        jcmdOK.setMinimumSize(new java.awt.Dimension(65, 33));
        jcmdOK.setPreferredSize(new java.awt.Dimension(65, 33));
        jcmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdOKActionPerformed(evt);
            }
        });
        jPanel2.add(jcmdOK);

        jcmdMore.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jcmdMore.setText(LocalRes.getIntString("button.information")); // NOI18N
        jcmdMore.setMaximumSize(new java.awt.Dimension(65, 33));
        jcmdMore.setMinimumSize(new java.awt.Dimension(65, 33));
        jcmdMore.setPreferredSize(new java.awt.Dimension(65, 33));
        jcmdMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdMoreActionPerformed(evt);
            }
        });
        jPanel2.add(jcmdMore);

        jPanel3.add(jPanel2, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(455, 277));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jcmdMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdMoreActionPerformed
        
        // Add your handling code here:
        jcmdMore.setEnabled(false);
        jscrException.setVisible(true);
        setSize(getWidth(), 310);
// JG 25 May 2013 change for JDK 7
        validate();
//        validateTree();
        
    }//GEN-LAST:event_jcmdMoreActionPerformed

    private void jcmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdOKActionPerformed
        // Add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jcmdOKActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jcmdMore;
    private javax.swing.JButton jcmdOK;
    private javax.swing.JLabel jlblErrorCode;
    private javax.swing.JLabel jlblIcon;
    private javax.swing.JLabel jlblMessage;
    private javax.swing.JScrollPane jscrException;
    private javax.swing.JTextArea jtxtException;
    // End of variables declaration//GEN-END:variables
    
}
