//    SOLiPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2018 SOLiPOS
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

package com.unicenta.pos.ticket;

import com.unicenta.format.Formats;
import com.unicenta.pos.util.ThumbNailBuilder;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author uniCenta 2017
 *
 */
public class ProductRenderer extends DefaultListCellRenderer {
                
    ThumbNailBuilder tnbprod;

    /** Creates a new instance of ProductRenderer */
    public ProductRenderer() {   
//        tnbprod = new ThumbNailBuilder(48,48, "com/unicenta/images/package.png");
        tnbprod = new ThumbNailBuilder(48,48, "com/unicenta/images/null.png");        
      
       
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, null, index, isSelected, cellHasFocus);
        
        ProductInfoExt prod = (ProductInfoExt) value;
       
        if (prod != null) {
                        
            setText("<html><center>" 
                    + prod.getReference() 
                    + " - " 
                    + prod.getName() + " - " + Formats.CURRENCY.formatValue(prod.getPriceSell())
//                    + "<br>" + prod.getStockUnits() + " | " + Formats.DATE.formatValue(prod.getMemoDate()) + " | " + prod.getPriceSellTax(tax)
                    );
            
            Image img = tnbprod.getThumbNail(prod.getImage());
            setIcon(img == null ? null :new ImageIcon(img));
        }
        return this;
    }      
}
