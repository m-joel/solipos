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

package com.unicenta.pos.mant;
import com.unicenta.basic.BasicException;
import com.unicenta.data.gui.ListCellRendererBasic;
import com.unicenta.data.loader.Datas;
import com.unicenta.data.loader.TableDefinition;
import com.unicenta.data.loader.Vectorer;
import com.unicenta.data.user.EditorRecord;
import com.unicenta.data.user.ListProvider;
import com.unicenta.data.user.ListProviderCreator;
import com.unicenta.data.user.SaveProvider;
import com.unicenta.format.Formats;
import com.unicenta.pos.forms.AppLocal;
import com.unicenta.pos.forms.DataLogicSales;
import com.unicenta.pos.panels.JPanelTable;
import javax.swing.ListCellRenderer;

/**
 *
 * @author adrianromero
 */
public class JPanelPlaces extends JPanelTable {
    
    private TableDefinition tplaces;
    private PlacesEditor jeditor;
    
    /** Creates a new instance of JPanelPlaces */
    public JPanelPlaces() {
    }
    
    /**
     *
     */
    @Override
    protected void init() {
        DataLogicSales dlSales = null;
        dlSales = (DataLogicSales) app.getBean("com.unicenta.pos.forms.DataLogicSales");
        
        tplaces = new TableDefinition(app.getSession(),
            "places"
            , new String[] {"ID", "NAME", "SEATS", "X", "Y", "FLOOR", "WIDTH", "HEIGHT"}
            , new String[] {"ID", AppLocal.getIntString("label.name"), 
                AppLocal.getIntString("label.seats"), "X", "Y", 
                AppLocal.getIntString("label.placefloor")}
            , new Datas[] {
                Datas.STRING, Datas.STRING, Datas.STRING, 
                Datas.INT, Datas.INT, 
                Datas.STRING,
                Datas.INT, Datas.INT }
            , new Formats[] {
                Formats.STRING, Formats.STRING, Formats.STRING, 
                Formats.INT, Formats.INT, 
                Formats.NULL,
                Formats.INT, Formats.INT }
            , new int[] {0}
        ); 
        jeditor = new PlacesEditor(dlSales, dirty); 
    }
        
    /**
     *
     * @return
     */
    @Override
    public ListProvider getListProvider() {
        return new ListProviderCreator(tplaces);
    }
    
    /**
     *
     * @return
     */
    @Override
    public SaveProvider getSaveProvider() {
        return new SaveProvider(tplaces);      
    }
    
    /**
     *
     * @return
     */
    @Override
    public Vectorer getVectorer() {
        return tplaces.getVectorerBasic(new int[]{1});
    }
    
    /**
     *
     * @return
     */
    @Override
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(tplaces.getRenderStringBasic(new int[]{1}));
    }
    
    /**
     *
     * @return
     */
    @Override
    public EditorRecord getEditor() {
        return jeditor;
    }

    /**
     *
     * @return
     */
    @Override
    public String getTitle() {
        return AppLocal.getIntString("Menu.Tables");
    }

    /**
     *
     * @throws BasicException
     */
    @Override
    public void activate() throws BasicException {
        jeditor.activate();
        super.activate();        
    }     
}