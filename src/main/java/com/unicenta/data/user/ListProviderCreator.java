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

package com.unicenta.data.user;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.SentenceList;
import com.unicenta.data.loader.TableDefinition;
import java.util.*;

/**
 *
 * @author JG uniCenta
 */
public class ListProviderCreator implements ListProvider {
    
    private SentenceList sent;
    private EditorCreator prov;
    private Object params;
    
    /** Creates a new instance of ListProviderEditor
     * @param sent
     * @param prov */
    public ListProviderCreator(SentenceList sent, EditorCreator prov) {
        this.sent = sent;
        this.prov = prov;
        params = null;
    }
    
    /**
     *
     * @param sent
     */
    public ListProviderCreator(SentenceList sent) {
        this(sent, null);
    }
    
    /**
     *
     * @param table
     */
    public ListProviderCreator(TableDefinition table) {        
        this(table.getListSentence(), null);
    }

    /**
     *
     * @return
     * @throws BasicException
     */
    @Override
    public List loadData() throws BasicException {       
        params = (prov == null) ? null : prov.createValue();
        return refreshData();
    }
    
    /**
     *
     * @return
     * @throws BasicException
     */
    @Override
    public List refreshData() throws BasicException {
        return sent.list(params);
    }    
}
