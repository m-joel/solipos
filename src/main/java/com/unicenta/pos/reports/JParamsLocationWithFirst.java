//    SOLiPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2018 uniCenta & previous Openbravo POS works
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

package com.unicenta.pos.reports;

import java.util.List;

/**
 *
 * @author adrian
 */
public class JParamsLocationWithFirst extends JParamsLocation {
    
    /** Creates a new instance of JParamsLocationWithFirst */
    public JParamsLocationWithFirst() {
        super();
    }
    
    /**
     *
     * @param a
     */
    @Override
    protected void addFirst(List a) {
        a.add(0, null);
    }    
}
