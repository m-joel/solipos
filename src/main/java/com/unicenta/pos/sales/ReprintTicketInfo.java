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

package com.unicenta.pos.sales;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.DataRead;
import com.unicenta.data.loader.DataWrite;
import com.unicenta.data.loader.SerializableRead;
import com.unicenta.data.loader.SerializableWrite;

/**
 * @author JG uniCenta
 * Sept 2018
 * Recall + Reprint of last ticket
 * Allows for reprint ticket from any terminal rather than only local
*/
public class ReprintTicketInfo implements SerializableRead, SerializableWrite {
    
    private static final long serialVersionUID = 7640633837719L;
    private String id;
    private String total;
    private String ticketDate;
    private String UserName;

    
    /** Creates a new instance of ReprintTicketInfo */
    public ReprintTicketInfo() {
    }
    
    /**
     *
     * @param dr
     * @throws BasicException
     */
    @Override
    public void readValues(DataRead dr) throws BasicException {
        id = dr.getString(1);
        total = dr.getString(2);
        ticketDate = dr.getString(3);
        UserName = dr.getString(4);

    }   

    /**
     *
     * @param dp
     * @throws BasicException
     */
    @Override
    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, id);
        dp.setString(2, total);
        dp.setString(3, ticketDate);
        dp.setString(4, UserName);       
    }
    
    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }
    
    public String getTotal() {
        return total;
    }
    
    public String getTicketDate() {
        return ticketDate;
    }    
    
    public String getUserName() {
        return UserName;
    }
}