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

package com.unicenta.pos.sales;

import com.unicenta.pos.ticket.TicketInfo;

/**
 *
 * @author JG uniCenta
 */
public interface TicketsEditor {
    
    /**
     *
     * @param oTicket
     * @param oTicketExt
     */
    public void setActiveTicket(TicketInfo oTicket, Object oTicketExt); // el ticket mas informacion extra...

    /**
     *
     * @return
     */
    public TicketInfo getActiveTicket(); 
}
