//    SOLiPOS  - Touch Friendly Point Of Sale
//    Copyright (c)  uniCenta & previous Openbravo POS works
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


package com.unicenta.data.loader;

import java.io.Reader;
import com.unicenta.basic.BasicException;

/**
 *
 * @author adrianromero
 */
public class BatchSentenceScript extends BatchSentence {

    private String m_sScript;
    
    /** Creates a new instance of BatchSentenceScript
     * @param s
     * @param script */
    public BatchSentenceScript(Session s, String script) {
        super(s);
        m_sScript = script;
    }
    
    /**
     *
     * @return
     * @throws BasicException
     */
    protected Reader getReader() throws BasicException {
        
        return new java.io.StringReader(m_sScript);
    }      
}
