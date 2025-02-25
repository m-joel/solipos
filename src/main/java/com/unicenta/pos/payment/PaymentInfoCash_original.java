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

package com.unicenta.pos.payment;

import com.unicenta.format.Formats;

/**
 *
 * @author JG uniCenta
 */
public class PaymentInfoCash_original extends PaymentInfo {
    
    private final double m_dPaid;
    private final double m_dTotal;
    private double m_dTendered;    
    private final String m_dCardName =null;
//    private double m_dTip;    
    
    /** Creates a new instance of PaymentInfoCash
     * @param dTotal
     * @param dPaid */
    public PaymentInfoCash_original(double dTotal, double dPaid) {
        m_dTotal = dTotal;
        m_dPaid = dPaid;
    }
    
    @Override
    public PaymentInfo copyPayment(){
        return new PaymentInfoCash_original(m_dTotal, m_dPaid);
    }
    
    @Override
    public String getTransactionID(){
        return "no ID";
    }

    @Override
    public String getName() {
        return "cash";
    }   
    @Override
    public double getTotal() {
        return m_dTotal;
    }   
/**    public double getTip() {
        return m_dTip;
    }
     * @return 
*/
    @Override
    public double getPaid() {
        return m_dPaid;
    }

    @Override
    public double getTendered() {
        return m_dTendered;
    }

    @Override
    public double getChange(){
       return m_dPaid - m_dTotal;
   }

    @Override
   public String getCardName() {
       return m_dCardName;
   }
    public String printPaid() {
        return Formats.CURRENCY.formatValue(m_dPaid);
    }   
    public String printChange() {
        return Formats.CURRENCY.formatValue(m_dPaid - m_dTotal);
    }

    @Override
    public String getVoucher() {
        return null;
    }
    
}
