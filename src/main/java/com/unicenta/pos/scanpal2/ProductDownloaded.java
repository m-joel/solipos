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

package com.unicenta.pos.scanpal2;

/**
 *
 * @author JG uniCenta
 */
public class ProductDownloaded {
    
    private String m_sCode;
    private double m_dQuantity;
    
    /**
     *
     */
    public ProductDownloaded() {
    }
    
    /**
     *
     * @param value
     */
    public void setCode(String value) {
        m_sCode = value;
    }

    /**
     *
     * @return
     */
    public String getCode() {
        return m_sCode;
    }

    /**
     *
     * @param value
     */
    public void setQuantity(double value) {
        m_dQuantity = value;
    }

    /**
     *
     * @return
     */
    public double getQuantity() {
        return m_dQuantity;
    }
}