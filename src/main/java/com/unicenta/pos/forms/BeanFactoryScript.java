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

import com.unicenta.pos.scripting.ScriptEngine;
import com.unicenta.pos.scripting.ScriptException;
import com.unicenta.pos.scripting.ScriptFactory;
import com.unicenta.pos.util.StringUtils;
import java.io.IOException;

/**
 *
 * @author adrianromero
 */
public class BeanFactoryScript implements BeanFactoryApp {
    
    private BeanFactory bean = null;
    private String script;
    
    /**
     *
     * @param script
     */
    public BeanFactoryScript(String script) {
        this.script = script;
    }
    
    /**
     *
     * @param app
     * @throws BeanFactoryException
     */
    @Override
    public void init(AppView app) throws BeanFactoryException {
        
        // Resource
        try {
            ScriptEngine eng = ScriptFactory.getScriptEngine(ScriptFactory.BEANSHELL);
            eng.put("app", app);
            
            bean = (BeanFactory) eng.eval(StringUtils.readResource(script));
            if (bean == null) {
                // old definition
                bean = (BeanFactory) eng.get("bean");
            }
            
            // todo // llamar al init del bean
            if (bean instanceof BeanFactoryApp) {
                ((BeanFactoryApp) bean).init(app);
            }
// JG 16 May use multicatch
        } catch (ScriptException | IOException e) {
            throw new BeanFactoryException(e);
        }     
    }

    /**
     *
     * @return
     */
    @Override
    public Object getBean() {
        return bean.getBean();
    }
}
