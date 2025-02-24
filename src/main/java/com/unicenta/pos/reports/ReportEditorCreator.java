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

package com.unicenta.pos.reports;

import com.unicenta.basic.BasicException;
import com.unicenta.data.user.FilterEditorCreator;
import com.unicenta.pos.forms.AppView;
import java.awt.Component;

/**
 *
 * @author adrianromero
 */
public interface ReportEditorCreator extends FilterEditorCreator {
    
    /**
     *
     * @param app
     */
    public void init(AppView app);

    /**
     *
     * @throws BasicException
     */
    public void activate() throws BasicException;

    /**
     *
     * @return
     */
    public Component getComponent();
}
