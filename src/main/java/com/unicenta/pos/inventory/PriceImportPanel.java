//    SOLiPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2018 SOLiPOS
//    Portions Contributed by: John D L 2013
//    https://unicenta.com
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
import com.unicenta.data.user.EditorListener;
import com.unicenta.data.user.EditorRecord;
import com.unicenta.data.user.ListProviderCreator;
import com.unicenta.data.user.SaveProvider;
import com.unicenta.pos.forms.AppLocal;
import com.unicenta.pos.forms.DataLogicSales;
import com.unicenta.pos.panels.JPanelTable2;
import com.unicenta.pos.ticket.ProductFilter;
import java.awt.Component;
import javax.swing.JButton;

/**
 *
 * @author adrianromero
 * Created on 1 de marzo de 2007, 22:15
 *
 */
public class PriceImportPanel extends JPanelTable2 implements EditorListener {

    private ProductsEditor jeditor;
    private ProductFilter jproductfilter;    
    
    private DataLogicSales m_dlSales = null;
    
    /** Creates a new instance of ProductsPanel2 */
    public PriceImportPanel() {
    }
    
    /**
     *
     */
    @Override
    protected void init() {   
        m_dlSales = (DataLogicSales) app.getBean("com.unicenta.pos.forms.DataLogicSales");
        
        // el panel del filtro
        jproductfilter = new ProductFilter();
        jproductfilter.init(app);

        row = m_dlSales.getProductsRow();

        lpr =  new ListProviderCreator(m_dlSales.getProductCatQBF(), jproductfilter);

        spr = new SaveProvider(
            m_dlSales.getProductCatUpdate(),
            m_dlSales.getProductCatInsert(),
            m_dlSales.getProductCatDelete());
        
        // el panel del editor
        jeditor = new ProductsEditor(app, dirty);       
    }
    
    /**
     *
     * @return
     */
    @Override
    public EditorRecord getEditor() {
        return jeditor;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Component getFilter() {
        return jproductfilter.getComponent();
    }

    /**
     *
     * @return
     */
    @Override
    public Component getToolbarExtras() {
        
        JButton btnScanPal = new JButton();
        btnScanPal.setText("ScanPal");
        btnScanPal.setVisible(app.getDeviceScanner() != null);
        btnScanPal.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanPalActionPerformed(evt);
            }
        });      
        
        return btnScanPal;
    }
    
    private void btnScanPalActionPerformed(java.awt.event.ActionEvent evt) {                                           
  
        JDlgUploadProducts.showMessage(this, app.getDeviceScanner(), bd);
    }

    /**
     *
     * @return
     */
    @Override
    public String getTitle() {
        return AppLocal.getIntString("Menu.StockImport");
    }

    /**
     *
     * @throws BasicException
     */
    @Override
    public void activate() throws BasicException {
        
        jeditor.activate(); 
        jproductfilter.activate();
        
        super.activate();
    }

    /**
     *
     * @param value
     */
    @Override
    public void updateValue(Object value) {
    }    
}
