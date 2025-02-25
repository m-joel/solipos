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

package com.unicenta.format;

import java.text.ParseException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author JG uniCenta
 */
public class FormatsRESOURCE extends Formats {
    
    private ResourceBundle m_rb;
    private String m_sPrefix;
    
    /** Creates a new instance of FormatsRESOURCE
     * @param rb
     * @param sPrefix */
    public FormatsRESOURCE(ResourceBundle rb, String sPrefix) {
        m_rb = rb;
        m_sPrefix = sPrefix;
    }

    /**
     *
     * @param value
     * @return
     */
    @Override
    protected String formatValueInt(Object value) {
        try {
            return m_rb.getString(m_sPrefix + (String) value);
        } catch (MissingResourceException e) {
            return (String) value;
        }
    }   

    /**
     *
     * @param value
     * @return
     * @throws ParseException
     */
    @Override
    protected Object parseValueInt(String value) throws ParseException {
        return value;
    }

    /**
     *
     * @return
     */
    @Override
    public int getAlignment() {
        return javax.swing.SwingConstants.LEFT;
    }    
}
