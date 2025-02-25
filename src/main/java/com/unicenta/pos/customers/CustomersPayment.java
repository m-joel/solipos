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

package com.unicenta.pos.customers;

import com.unicenta.basic.BasicException;
import com.unicenta.data.gui.MessageInf;
import com.unicenta.data.user.DirtyManager;
import com.unicenta.format.Formats;
import com.unicenta.pos.forms.*;
import com.unicenta.pos.payment.JPaymentSelect;
import com.unicenta.pos.payment.JPaymentSelectCustomer;
import com.unicenta.pos.payment.PaymentInfo;
import com.unicenta.pos.payment.PaymentInfoTicket;
import com.unicenta.pos.printer.TicketParser;
import com.unicenta.pos.printer.TicketPrinterException;
import com.unicenta.pos.scripting.ScriptEngine;
import com.unicenta.pos.scripting.ScriptException;
import com.unicenta.pos.scripting.ScriptFactory;
import com.unicenta.pos.ticket.TicketInfo;
import com.unicenta.pos.util.RoundUtils;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author  adrianromero
 */
public class CustomersPayment extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private AppView app;
    private DataLogicCustomers dlcustomers;
    private DataLogicSales dlsales;
    private DataLogicSystem dlsystem;
    private TicketParser ttp;    
    private JPaymentSelect paymentdialog;
    
    private CustomerInfoExt customerext;
    private DirtyManager dirty;

    /** Creates new form CustomersPayment */
    public CustomersPayment() {

        initComponents();
        
        editorcard.addEditorKeys(m_jKeys);
        txtNotes.addEditorKeys(m_jKeys);
        txtPrePay.addEditorKeys(m_jKeys);

        dirty = new DirtyManager();
        txtNotes.addPropertyChangeListener("Text", dirty);
        txtPrePay.addPropertyChangeListener("Number", dirty);
    }

    /**
     *
     * @param app
     * @throws BeanFactoryException
     */
    @Override
    public void init(AppView app) throws BeanFactoryException {

        this.app = app;
        dlcustomers = (DataLogicCustomers) app.getBean("com.unicenta.pos.customers.DataLogicCustomers");
        dlsales = (DataLogicSales) app.getBean("com.unicenta.pos.forms.DataLogicSales");
        dlsystem = (DataLogicSystem) app.getBean("com.unicenta.pos.forms.DataLogicSystem");
        ttp = new TicketParser(app.getDeviceTicket(), dlsystem);
    }

    /**
     *
     * @return
     */
    @Override
    public Object getBean() {
        return this;
    }

    /**
     *
     * @return
     */
    @Override
    public String getTitle() {
        return AppLocal.getIntString("Menu.CustomersPayment");
    }

    /**
     *
     * @throws BasicException
     */
    @Override
    public void activate() throws BasicException {

        paymentdialog = JPaymentSelectCustomer.getDialog(this);        
        paymentdialog.init(app);

        resetCustomer();

        editorcard.reset();
        editorcard.activate();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean deactivate() {
        if (dirty.isDirty()) {
            int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.wannasave"), 
                AppLocal.getIntString("title.editor"), 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
            
            if (res == JOptionPane.YES_OPTION) {
                save();
                return true;
            } else {
                return res == JOptionPane.NO_OPTION;
            }
        } else {
            return true;
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

    private void editCustomer(CustomerInfoExt customer) {

        customerext = customer;

        txtTaxId.setText(customer.getTaxid());
        txtName.setText(customer.getName());
        txtCard.setText(customer.getCard());
        txtNotes.reset();
        txtNotes.setText(customer.getNotes());
        txtMaxdebt.setText(Formats.CURRENCY.formatValue(customer.getMaxdebt()));
        txtCurdebt.setText(Formats.CURRENCY.formatValue(customer.getAccdebt()));
        txtCurdate.setText(Formats.DATE.formatValue(customer.getCurdate()));
        txtPrePay.setText(null);
        
        txtNotes.setEnabled(true);
        txtPrePay.setEnabled(true);

        dirty.setDirty(false);

        btnSave.setEnabled(true);    
        btnPay.setEnabled(true);
        btnPrePay.setEnabled(true);
        
//        btnPay.setEnabled(customer.getCurdebt() != null && customer.getCurdebt().doubleValue() > 0.0);
        
    }

    private void resetCustomer() {

        customerext = null;

        txtTaxId.setText(null);
        txtName.setText(null);
        txtCard.setText(null);
        txtNotes.reset();
        txtMaxdebt.setText(null);
        txtCurdebt.setText(null);
        txtCurdate.setText(null);
        txtPrePay.setText(null);

        txtNotes.setEnabled(false);
        txtPrePay.setEnabled(false);        

        dirty.setDirty(false);

        btnSave.setEnabled(false);
        btnPay.setEnabled(false);
        btnPrePay.setEnabled(false);        

    }

    private void readCustomer() {

        try {
            CustomerInfoExt customer = dlsales.findCustomerExt(editorcard.getText());
            if (customer == null) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"));
                msg.show(this);
            } else {
                editCustomer(customer);
            }

        } catch (BasicException ex) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), ex);
            msg.show(this);
        }

        editorcard.reset();
        editorcard.activate();
    }

    private void save() {

        customerext.setNotes(txtNotes.getText());
        customerext.setPrePay(txtPrePay.getText());
                
        try {
            dlcustomers.updateCustomerExt(customerext);
            editCustomer(customerext);
        } catch (BasicException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosave"), e);
            msg.show(this);
        }

    }

    private void printTicket(String resname, TicketInfo ticket, CustomerInfoExt customer) {

        String resource = dlsystem.getResourceAsXML(resname);
        if (resource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("ticket", ticket);
                script.put("customer", customer);
                ttp.printTicket(script.eval(resource).toString());
// JG 6 May use multicatch
            } catch (    ScriptException | TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnCustomer = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnPay = new javax.swing.JButton();
        btnPrePay = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        m_jKeys = new com.unicenta.editor.JEditorKeys();
        jPanel5 = new javax.swing.JPanel();
        editorcard = new com.unicenta.editor.JEditorString();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCard = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCurdebt = new javax.swing.JTextField();
        txtCurdate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtMaxdebt = new javax.swing.JTextField();
        txtPrePay = new com.unicenta.editor.JEditorString();
        txtTaxId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNotes = new com.unicenta.editor.JEditorString();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/unicenta/images/customer_sml.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("pos_messages"); // NOI18N
        btnCustomer.setToolTipText(bundle.getString("tooltip.customerpay.customer")); // NOI18N
        btnCustomer.setFocusPainted(false);
        btnCustomer.setFocusable(false);
        btnCustomer.setMargin(new java.awt.Insets(8, 14, 8, 14));
        btnCustomer.setPreferredSize(new java.awt.Dimension(110, 45));
        btnCustomer.setRequestFocusEnabled(false);
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });
        jPanel6.add(btnCustomer);

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/unicenta/images/filesave.png"))); // NOI18N
        btnSave.setToolTipText(bundle.getString("tootltip.save")); // NOI18N
        btnSave.setFocusPainted(false);
        btnSave.setFocusable(false);
        btnSave.setMargin(new java.awt.Insets(8, 14, 8, 14));
        btnSave.setPreferredSize(new java.awt.Dimension(110, 45));
        btnSave.setRequestFocusEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel6.add(btnSave);
        jPanel6.add(jSeparator1);

        btnPay.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/unicenta/images/pay.png"))); // NOI18N
        btnPay.setText(AppLocal.getIntString("button.pay")); // NOI18N
        btnPay.setToolTipText(bundle.getString("tooltip.customerpay.pay")); // NOI18N
        btnPay.setFocusPainted(false);
        btnPay.setFocusable(false);
        btnPay.setMargin(new java.awt.Insets(8, 14, 8, 14));
        btnPay.setMaximumSize(new java.awt.Dimension(110, 44));
        btnPay.setMinimumSize(new java.awt.Dimension(110, 44));
        btnPay.setPreferredSize(new java.awt.Dimension(110, 45));
        btnPay.setRequestFocusEnabled(false);
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });
        jPanel6.add(btnPay);

        btnPrePay.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPrePay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/unicenta/images/customer_add_sml.png"))); // NOI18N
        btnPrePay.setText(AppLocal.getIntString("button.prepay")); // NOI18N
        btnPrePay.setToolTipText(bundle.getString("tooltip.prepay")); // NOI18N
        btnPrePay.setFocusPainted(false);
        btnPrePay.setFocusable(false);
        btnPrePay.setMargin(new java.awt.Insets(8, 14, 8, 14));
        btnPrePay.setMaximumSize(new java.awt.Dimension(110, 44));
        btnPrePay.setMinimumSize(new java.awt.Dimension(110, 44));
        btnPrePay.setPreferredSize(new java.awt.Dimension(110, 45));
        btnPrePay.setRequestFocusEnabled(false);
        btnPrePay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrePayActionPerformed(evt);
            }
        });
        jPanel6.add(btnPrePay);

        jPanel2.add(jPanel6, java.awt.BorderLayout.LINE_START);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        m_jKeys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jKeysActionPerformed(evt);
            }
        });
        jPanel4.add(m_jKeys);

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        editorcard.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        editorcard.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel5.add(editorcard, new java.awt.GridBagConstraints());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/unicenta/images/ok.png"))); // NOI18N
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setMargin(new java.awt.Insets(8, 14, 8, 14));
        jButton1.setPreferredSize(new java.awt.Dimension(110, 45));
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel5.add(jButton1, gridBagConstraints);

        jPanel4.add(jPanel5);

        jPanel3.add(jPanel4, java.awt.BorderLayout.NORTH);

        add(jPanel3, java.awt.BorderLayout.LINE_END);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText(AppLocal.getIntString("label.name")); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText(AppLocal.getIntString("label.notes")); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText(AppLocal.getIntString("label.card")); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 30));

        txtCard.setEditable(false);
        txtCard.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCard.setFocusable(false);
        txtCard.setPreferredSize(new java.awt.Dimension(0, 30));
        txtCard.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(AppLocal.getIntString("label.maxdebt")); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(AppLocal.getIntString("label.curdebt")); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 30));

        txtCurdebt.setEditable(false);
        txtCurdebt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCurdebt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCurdebt.setFocusable(false);
        txtCurdebt.setPreferredSize(new java.awt.Dimension(120, 30));
        txtCurdebt.setRequestFocusEnabled(false);

        txtCurdate.setEditable(false);
        txtCurdate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCurdate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCurdate.setFocusable(false);
        txtCurdate.setPreferredSize(new java.awt.Dimension(120, 30));
        txtCurdate.setRequestFocusEnabled(false);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(AppLocal.getIntString("label.curdate")); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(120, 30));

        txtName.setEditable(false);
        txtName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtName.setFocusable(false);
        txtName.setPreferredSize(new java.awt.Dimension(0, 30));
        txtName.setRequestFocusEnabled(false);

        txtMaxdebt.setEditable(false);
        txtMaxdebt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMaxdebt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMaxdebt.setFocusable(false);
        txtMaxdebt.setPreferredSize(new java.awt.Dimension(120, 30));
        txtMaxdebt.setRequestFocusEnabled(false);

        txtPrePay.setForeground(new java.awt.Color(0, 204, 255));
        txtPrePay.setToolTipText(bundle.getString("tooltip.customerpay.prepay")); // NOI18N
        txtPrePay.setEnabled(false);
        txtPrePay.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtPrePay.setPreferredSize(new java.awt.Dimension(200, 30));

        txtTaxId.setEditable(false);
        txtTaxId.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTaxId.setFocusable(false);
        txtTaxId.setPreferredSize(new java.awt.Dimension(150, 30));
        txtTaxId.setRequestFocusEnabled(false);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText(AppLocal.getIntString("label.taxid")); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText(AppLocal.getIntString("label.prepay")); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 30));

        txtNotes.setToolTipText(bundle.getString("tooltip.customerpay.notes")); // NOI18N
        txtNotes.setEnabled(false);
        txtNotes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNotes.setPreferredSize(new java.awt.Dimension(250, 100));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCard, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTaxId, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrePay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMaxdebt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCurdebt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCurdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTaxId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrePay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCurdebt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxdebt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCurdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        readCustomer();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void m_jKeysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jKeysActionPerformed

        readCustomer();
        
    }//GEN-LAST:event_m_jKeysActionPerformed

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed

        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlcustomers);
        finder.search(null);
        finder.setVisible(true);
        CustomerInfo customer = finder.getSelectedCustomer();
        if (customer != null) {
            try {
                CustomerInfoExt c = dlsales.loadCustomerExt(customer.getId());
                if (c == null) {
                    MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"));
                    msg.show(this);
                } else {
                    editCustomer(c);
                }
            } catch (BasicException ex) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), ex);
                msg.show(this);
            }
        }  
        editorcard.reset();
        editorcard.activate();
                
}//GEN-LAST:event_btnCustomerActionPerformed

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed

        paymentdialog.setPrintSelected(true);
        
        if (paymentdialog.showDialog(customerext.getAccdebt(), null)) {

            // Save the ticket
            TicketInfo ticket = new TicketInfo();
            ticket.setTicketType(TicketInfo.RECEIPT_PAYMENT);

            List<PaymentInfo> payments = paymentdialog.getSelectedPayments();

            double total = 0.0;
            for (PaymentInfo p : payments) {
                total += p.getTotal();
            }

            payments.add(new PaymentInfoTicket(-total, "debtpaid"));

            ticket.setPayments(payments);

            ticket.setUser(app.getAppUserView().getUser().getUserInfo());
            ticket.setActiveCash(app.getActiveCashIndex());
            ticket.setDate(new Date());
            ticket.setCustomer(customerext);

            try {
                dlsales.saveTicket(ticket, app.getInventoryLocation());
            } catch (BasicException eData) {
                MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosaveticket"), eData);
                msg.show(this);
            }


            // reload customer
            CustomerInfoExt c;
            try {
                c = dlsales.loadCustomerExt(customerext.getId());
                if (c == null) {
                    MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"));
                    msg.show(this);
                } else {
                    editCustomer(c);
                }
            } catch (BasicException ex) {
                c = null;
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), ex);
                msg.show(this);
            }

            printTicket(paymentdialog.isPrintSelected()
                    ? "Printer.CustomerPaid"
                    : "Printer.CustomerPaid2",
                    ticket, c);
        }
        
        editorcard.reset();
        editorcard.activate();
        
}//GEN-LAST:event_btnPayActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (dirty.isDirty()) {
            save();

            editorcard.reset();
            editorcard.activate();
        }
        
}//GEN-LAST:event_btnSaveActionPerformed

    private void btnPrePayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrePayActionPerformed

        txtPrePay.setFocusable(true);
        txtPrePay.requestFocusInWindow();

        if (txtPrePay.getText() != null) {
            double prepay = Double.parseDouble(txtPrePay.getText());        
            Formats.CURRENCY.formatValue(RoundUtils.getValue(prepay));
            paymentdialog.setPrintSelected(true);
        
            if (paymentdialog.showDialog(prepay, null)) {            
           
                TicketInfo ticket = new TicketInfo();
                ticket.setTicketType(TicketInfo.RECEIPT_PAYMENT);
                List<PaymentInfo> payments = paymentdialog.getSelectedPayments();

                double total = 0.0;
                for (PaymentInfo p : payments) {
                    total += p.getTotal();
                }
             
                total = Double.parseDouble(txtPrePay.getText());

                payments.add(new PaymentInfoTicket(-total, "debtpaid"));

                ticket.setPayments(payments);

                ticket.setUser(app.getAppUserView().getUser().getUserInfo());
                ticket.setActiveCash(app.getActiveCashIndex());
                ticket.setDate(new Date());
                ticket.setCustomer(customerext);

                try {
                    dlsales.saveTicket(ticket, app.getInventoryLocation());
                } catch (BasicException eData) {
                    MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosaveticket"), eData);
                    msg.show(this);
                }

                CustomerInfoExt c;
                try {
                    c = dlsales.loadCustomerExt(customerext.getId());
                    if (c == null) {
                        MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"));
                        msg.show(this);
                    } else {
                        editCustomer(c);
                    }
                } catch (BasicException ex) {
                    c = null;
                    MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), ex);
                    msg.show(this);
                }

                printTicket(paymentdialog.isPrintSelected()
                    ? "Printer.CustomerPaid"
                    : "Printer.CustomerPaid2",
                    ticket, c);
            }
        
            editorcard.reset();
            editorcard.activate();
        }
    }//GEN-LAST:event_btnPrePayActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCustomer;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnPrePay;
    private javax.swing.JButton btnSave;
    private com.unicenta.editor.JEditorString editorcard;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private com.unicenta.editor.JEditorKeys m_jKeys;
    private javax.swing.JTextField txtCard;
    private javax.swing.JTextField txtCurdate;
    private javax.swing.JTextField txtCurdebt;
    private javax.swing.JTextField txtMaxdebt;
    private javax.swing.JTextField txtName;
    private com.unicenta.editor.JEditorString txtNotes;
    private com.unicenta.editor.JEditorString txtPrePay;
    private javax.swing.JTextField txtTaxId;
    // End of variables declaration//GEN-END:variables
}
