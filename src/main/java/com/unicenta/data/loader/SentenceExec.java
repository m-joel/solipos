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

package com.unicenta.data.loader;

import com.unicenta.basic.BasicException;

/**
 *
 * @author JG uniCenta
 */
public interface SentenceExec {
    
    /**
     *
     * @return
     * @throws BasicException
     */
    public int exec() throws BasicException;

    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
    public int exec(Object params) throws BasicException;

    /**
     *
     * @param params
     * @return
     * @throws BasicException
     */
    public int exec(Object... params) throws BasicException;
}
