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

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.DataRead;
import com.unicenta.data.loader.IKeyed;
import com.unicenta.data.loader.SerializerRead;

/**
 *
 * @author  Jack
 * @version 
 */
public class ProductInfo implements IKeyed {

    private static final long serialVersionUID = 8712449444103L;
    private String m_sID;
    private String m_sRef;
    private String m_sCode;    
    private String m_sName;

    /** Creates new ProductInfo
     * @param id
     * @param ref
     * @param code
     * @param name */
    public ProductInfo(String id, String ref, String code, String name) {
        m_sID = id;
        m_sRef = ref;
        m_sCode = code;
        m_sName = name;
    }

    /**
     *
     * @return
     */
    @Override
    public Object getKey() {
        return m_sID;
    }

    /**
     *
     * @param sID
     */
    public void setID(String sID) {
        m_sID = sID;
    }
    public String getID() {
        return m_sID;
    }

    /**
     *
     * @return
    */  
    public String getRef() {
        return m_sRef;
    }
    public void setRef(String sRef) {
        m_sRef = sRef;
    }

    
    /**
     *
     * @return
     */
    public String getCode() {
        return m_sCode;
    }
    public void setCode(String sCode) {
        m_sCode = sCode;
    }
    
    /**
     *
     * @return
     */
    public String getName() {
        return m_sName;
    }
    public void setName(String sName) {
        m_sName = sName;
    }

 
    @Override
    public String toString() {
        return m_sName;
    }

    /**
     *
     * @return
     */
    public static SerializerRead getSerializerRead() {
        return new SerializerRead() {
            @Override
            public Object readValues(DataRead dr) throws BasicException {
            return new ProductInfo(dr.getString(1), dr.getString(2), 
                dr.getString(3), dr.getString(4));
        }};
    }
}