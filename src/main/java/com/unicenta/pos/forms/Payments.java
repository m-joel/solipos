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

package com.unicenta.pos.forms;

import java.util.HashMap;

/**
 *
 * @author Jack Gerrard
 */
public class Payments {
    private Double amount;
    private Double tendered;
    private final HashMap paymentPaid;
    private final HashMap paymentTendered;
    private final HashMap rtnMessage;
    private String name;
    private final HashMap paymentVoucher;
    private final HashMap paymentNote;

    /**
     *
     */
    public Payments() {
        paymentPaid =  new HashMap();
        paymentTendered =  new HashMap();
        rtnMessage = new HashMap();
        paymentVoucher = new HashMap();
        paymentNote = new HashMap();
    }

    /**
     *
     * @param pName
     * @param pAmountPaid
     * @param pTendered
     * @param rtnMsg
     */

    public void addPayment (String pName, Double pAmountPaid, Double pTendered, String rtnMsg){
        if (paymentPaid.containsKey(pName)){
            paymentPaid.put(pName,Double.parseDouble(paymentPaid.get(pName).toString()) + pAmountPaid);
            paymentTendered.put(pName,Double.parseDouble(paymentTendered.get(pName).toString()) + pTendered);
            rtnMessage.put(pName, rtnMsg);
        }else {
            paymentPaid.put(pName, pAmountPaid);
            paymentTendered.put(pName,pTendered);
            rtnMessage.put(pName, rtnMsg);
        }
    }

    /**
     *
     * @param pName
     * @param pAmountPaid
     * @param pTendered
     * @param rtnMsg
     * @param pVoucher
     */
    public void addPayment (String pName, Double pAmountPaid, Double pTendered, String rtnMsg, String pVoucher, String pNote){
        if (paymentPaid.containsKey(pName)){
            paymentPaid.put(pName,Double.parseDouble(paymentPaid.get(pName).toString()) + pAmountPaid);
            paymentTendered.put(pName,Double.parseDouble(paymentTendered.get(pName).toString()) + pTendered);
            rtnMessage.put(pName, rtnMsg);
            paymentVoucher.put(pName, pVoucher);
            paymentNote.put(pName, pNote);

        }else {
            paymentPaid.put(pName, pAmountPaid);
            paymentTendered.put(pName,pTendered);
            rtnMessage.put(pName, rtnMsg);
            paymentNote.put(pName, pNote);
            if (pVoucher !=null) {
                paymentVoucher.put(pName, pVoucher);
            } else {
                pVoucher = "0";
                paymentVoucher.put(pName, pVoucher);
            }
        }
    }


    /**
     *
     * @param pName
     * @return
     */
    public Double getTendered (String pName){
        return(Double.parseDouble(paymentTendered.get(pName).toString()));
    }

    /**
     *
     * @param pName
     * @return
     */
    public Double getPaidAmount (String pName){
        return(Double.parseDouble(paymentPaid.get(pName).toString()));
    }

    /**
     *
     * @return
     */
    public Integer getSize(){
        return (paymentPaid.size());
    }

    /**
     *
     * @param pName
     * @return
     */
    public String getRtnMessage(String pName){
        return (rtnMessage.get(pName).toString());
    }

    public String getVoucher(String pName){
        return (paymentVoucher.get(pName).toString());
    }

    public String getNote(String pName) {
        String note = null;
        if (paymentNote.get(pName) != null) {
            note = paymentNote.get(pName).toString();

        }
        return note ;
    }

    public String getFirstElement(){
        String rtnKey= paymentPaid.keySet().iterator().next().toString();
        return(rtnKey);
    }

    /**
     *
     * @param pName
     */
    public void removeFirst (String pName){
        paymentPaid.remove(pName);
        paymentTendered.remove(pName);
        rtnMessage.remove(pName);
    }

}