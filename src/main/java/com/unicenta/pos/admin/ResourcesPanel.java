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

package com.unicenta.pos.admin;

import com.unicenta.data.gui.ListCellRendererBasic;
import com.unicenta.data.loader.ComparatorCreator;
import com.unicenta.data.loader.TableDefinition;
import com.unicenta.data.loader.Vectorer;
import com.unicenta.data.user.EditorRecord;
import com.unicenta.data.user.ListProvider;
import com.unicenta.data.user.ListProviderCreator;
import com.unicenta.data.user.SaveProvider;
import com.unicenta.pos.forms.AppLocal;
import com.unicenta.pos.forms.DataLogicSystem;
import com.unicenta.pos.panels.JPanelTable;
import javax.swing.ListCellRenderer;

/**
 *
 * @author adrianromero
 */
public class ResourcesPanel extends JPanelTable {

    private TableDefinition tresources;
    private ResourcesView jeditor;
    
    /** Creates a new instance of JPanelResources */
    public ResourcesPanel() {
    }
    
    /**
     *
     */
    protected void init() {
        DataLogicAdmin dlAdmin = (DataLogicAdmin) app.getBean("com.unicenta.pos.admin.DataLogicAdmin");
        tresources = dlAdmin.getTableResources();         
        jeditor = new ResourcesView(dirty);           
    }

    /**
     *
     * @return
     */
    @Override
    public boolean deactivate() {
        if (super.deactivate()) {
            DataLogicSystem dlSystem = (DataLogicSystem) app.getBean("com.unicenta.pos.forms.DataLogicSystem");
            dlSystem.resetResourcesCache();
            return true;
        } else {
            return false;
        }    
    }
    
    /**
     *
     * @return
     */
    public ListProvider getListProvider() {
        return new ListProviderCreator(tresources);
    }
    
    /**
     *
     * @return
     */
    public SaveProvider getSaveProvider() {
        return new SaveProvider(tresources);        
    }
    
    /**
     *
     * @return
     */
    @Override
    public Vectorer getVectorer() {
        return tresources.getVectorerBasic(new int[] {1});
    }
    
    /**
     *
     * @return
     */
    @Override
    public ComparatorCreator getComparatorCreator() {
        return tresources.getComparatorCreator(new int[] {1, 2});
    }
    
    /**
     *
     * @return
     */
    @Override
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(tresources.getRenderStringBasic(new int[] {1}));
    }
    
    /**
     *
     * @return
     */
    public EditorRecord getEditor() {
        return jeditor;
    }
    
    /**
     *
     * @return
     */
    public String getTitle() {
        return AppLocal.getIntString("Menu.Resources");
    }        
}
