//    SOLiPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-20167
//    2008-2013 Openbravo, S.L.
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

package com.unicenta.pos.sales;

import com.unicenta.pos.ticket.TaxInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adrianromero
 */
public class TaxesLogicElement {
    
    private TaxInfo tax;
    private List<TaxesLogicElement> taxsons;
    
    /**
     *
     * @param tax
     */
    public TaxesLogicElement(TaxInfo tax) {
        this.tax = tax;
        // JG June 2013 use diamond inference
        this.taxsons = new ArrayList<>();
    }
    
    /**
     *
     * @return
     */
    public TaxInfo getTax() {
        return tax;
    }
    
    /**
     *
     * @return
     */
    public List<TaxesLogicElement> getSons() {
        return taxsons;
    }
}
