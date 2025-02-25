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

package com.unicenta.pos.suppliers;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.DataRead;
import com.unicenta.data.loader.SerializerRead;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jack Gerrard
 * Used in Supplier's transactions tab to display all this Supplier's
 * ticketline values
 */
@Slf4j
public class SupplierTransaction {

    Date transactionDate;
    String productName;
    String unit;
    Double price;
    Integer reason;

    String supplierId;   

    /**
     * Main method to return all supplier's transactions from StockDiary
     */
    public SupplierTransaction() {
    }

    /**
     *
     * @param transactionDate
     * @param productName
     * @param unit
     * @param price
     * @param reason
     * @param sId
     */
    public SupplierTransaction(Date transactionDate, String productName, String unit, Double price, Integer reason, String sId) {
        this.transactionDate = transactionDate;
        this.productName = productName;
        this.unit = unit;
        this.price = price;
        this.reason = reason;

        this.supplierId = sId;        
    }


    /**
     *
     * @return ticket's transaction date
     */
    public Date getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }    
    
    /**
     *
     * @return product name string 
     */
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     *
     * @return quantity string value
     */
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    /**
     *
     * @return price value
     */
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     *
     * @return 
     */
    public Integer getReason() {
        return reason;
    }
    public void setReason(Integer reason) {
        this.reason = reason;
    }


    /**
     *
     * @return supplier's account name string
     */
    public String getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    
    /**
     *
     * @return stockdiary for this supplier
     */
    public static SerializerRead getSerializerRead() {
        return new SerializerRead() {

            @Override
            public Object readValues(DataRead dr) throws BasicException {

                String dateValue = dr.getString(1);
                String productName = dr.getString(2);
                String unit = dr.getString(3);
                Double price = dr.getDouble(4);
                Integer reason = dr.getInt(5);

                String supplierId = dr.getString(6);                


                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = null;
                try {
                    date = formatter.parse(dateValue);
                } catch (ParseException ex) {
                    log.error(ex.getMessage());
                }
                return new SupplierTransaction(date, productName, unit, price, reason, supplierId);                
            }
        };
    }
}
