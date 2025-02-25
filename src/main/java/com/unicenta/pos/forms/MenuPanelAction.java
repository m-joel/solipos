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

package com.unicenta.pos.forms;

import com.unicenta.plugins.Application;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 *
 * @author adrianromero
 */
@Slf4j
public class MenuPanelAction extends AbstractAction {

    private final AppView m_App;
    private final String m_sMyView;

    /** Creates a new instance of MenuPanelAction
     * @param app
     * @param icon
     * @param keytext
     * @param sMyView */
    public MenuPanelAction(AppView app, String icon, String keytext, String sMyView) {
        putValue(Action.SMALL_ICON, new ImageIcon(JPrincipalApp.class.getResource(icon)));
        putValue(Action.NAME, AppLocal.getIntString(keytext));
        putValue(AppUserView.ACTION_TASKNAME, sMyView);
        m_App = app;
        m_sMyView = sMyView;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {

        if (m_sMyView.equals("plugins.configure") ) {
            log.debug("Configure plugins");
            new Application().initScreen("Dashboard", getActiveWindow());
        }
        else {
            m_App.getAppUserView().showTask(m_sMyView);
        }
    }

    private Window getActiveWindow() {
        for (Window window: Window.getWindows()) {
            if (window instanceof JRootFrame) {
                return window;
            }
        }
        return null;
    }
}
