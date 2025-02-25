//    SOLiPOS  - Touch Friendly Point Of Sale
//    Copyright (c)  uniCenta & previous Openbravo POS works
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

package com.unicenta.data.loader;

import java.util.*;
import com.unicenta.basic.BasicException;

/**
 *
 * @author JG uniCenta
 */
public class NormalBuilder implements ISQLBuilderStatic {
    
    public String sentence;
    
    /** Creates a new instance of NormalBuilder
     * @param sSentence */
    public NormalBuilder(String sSentence) {
        sentence = sSentence;
    }
    
    /**
     *
     * @param write
     * @param params
     * @return
     * @throws BasicException
     */
    @Override
    public String getSQL(SerializerWrite write, Object params) throws BasicException {
        
        NormalParameter normalParameter = new NormalParameter(sentence);
        if (write != null) {
            write.writeValues(normalParameter, params);
        }
        return normalParameter.getSentence();
    }
    private static class NormalParameter implements DataWrite {
    
        private String m_sSentence;
        private ArrayList m_aParams; // of String
        
        public NormalParameter(String sSentence) {
            m_sSentence = sSentence;
            m_aParams = new ArrayList();
        }
        
        @Override
        public void setDouble(int paramIndex, Double dValue) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, DataWriteUtils.getSQLValue(dValue));
        }
        
        @Override
        public void setBoolean(int paramIndex, Boolean bValue) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, DataWriteUtils.getSQLValue(bValue));
        }       
        @Override
        public void setInt(int paramIndex, Integer iValue) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, DataWriteUtils.getSQLValue(iValue));
        }
        
        @Override
        public void setString(int paramIndex, String sValue) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, DataWriteUtils.getSQLValue(sValue));
        }
        
        @Override
        public void setTimestamp(int paramIndex, java.util.Date dValue) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, DataWriteUtils.getSQLValue(dValue));
        }
//        public void setBinaryStream(int paramIndex, java.io.InputStream in, int length) throws DataException{
//            throw new DataException("Param type not allowed");      
//        }
        @Override
        public void setBytes(int paramIndex, byte[] value) throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.noparamtype"));
        }
        @Override
        public void setObject(int paramIndex, Object value) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, DataWriteUtils.getSQLValue(value));
        }
        
        private void ensurePlace(int i) {
            m_aParams.ensureCapacity(i);
            while (i >= m_aParams.size()){
                m_aParams.add(null);
            }
        }
        
        public String getSentence() {
            
            StringBuilder sNewSentence = new StringBuilder();
            int iCount = 0;
            int iPos;
            int iLast = 0;
            while ((iPos = m_sSentence.indexOf('?', iLast)) > 0) {
                sNewSentence.append(m_sSentence.substring(iLast, iPos));
                if (iCount < m_aParams.size() && m_aParams.get(iCount) != null) {
                    // el valor que viene
                    sNewSentence.append(m_aParams.get(iCount));
                } else {
                    // nulo
                    sNewSentence.append(DataWriteUtils.getSQLValue((Object) null));
                }
                iCount++;
                iLast = iPos + 1;
            }
            sNewSentence.append(m_sSentence.substring(iLast));
            
            return sNewSentence.toString(); // sustituida
        }                
    }    
}
