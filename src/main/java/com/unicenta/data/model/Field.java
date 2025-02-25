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

package com.unicenta.data.model;

import com.unicenta.data.loader.Datas;
import com.unicenta.format.Formats;

/**
 *
 * @author adrian
 */
public class Field {
    
    private String label;
    private Datas data;
    private Formats format;
    
    private boolean searchable;
    private boolean comparable;
    private boolean title;
    
    /**
     *
     * @param label
     * @param data
     * @param format
     * @param title
     * @param searchable
     * @param comparable
     */
    public Field(String label, Datas data, Formats format, boolean title, boolean searchable, boolean comparable) {
        this.label = label;
        this.data = data;
        this.format = format;
        this.title = title;
        this.searchable = searchable;
        this.comparable = comparable;             
    }
    
    /**
     *
     * @param label
     * @param data
     * @param format
     */
    public Field(String label, Datas data, Formats format) {
        this(label, data, format, false, false, false);
    }
    
    /**
     *
     * @return
     */
    public String getLabel() {
        return label;
    }
    
    /**
     *
     * @return
     */
    public Formats getFormat() {
        return format;
    }
    
    /**
     *
     * @return
     */
    public Datas getData() {
        return data;
    }
    
    /**
     *
     * @return
     */
    public boolean isSearchable() {
        return searchable;
    }
    
    /**
     *
     * @return
     */
    public boolean isComparable() {
        return comparable;
    }
    
    /**
     *
     * @return
     */
    public boolean isTitle() {
        return title;
    }    
}
