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

package com.unicenta.pos.payment;

import com.unicenta.plugins.Application;
import com.unicenta.plugins.common.AppContext;
import com.unicenta.pos.util.RoundUtils;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Slf4j
public class PaymentGatewayPaymentSense implements PaymentGateway {

    /** Creates a new instance of PaymentGatewayExt */
    public PaymentGatewayPaymentSense() {
    }

    Window getPaymentWindow(double amount) {
        if (amount > 0) {
            ArrayList<JPaymentSelectReceipt> paymentWindows = new ArrayList<>();
            for (Window window : Window.getWindows()) {
                if (window instanceof JPaymentSelectReceipt) {
                    paymentWindows.add((JPaymentSelectReceipt) window);
                }
            }
            return paymentWindows.get(paymentWindows.size()-1);
        }
        else {
            ArrayList<JPaymentSelectRefund> paymentWindows = new ArrayList<>();
            for (Window window : Window.getWindows()) {
                if (window instanceof JPaymentSelectRefund) {
                    paymentWindows.add((JPaymentSelectRefund) window);
                }
            }
            return paymentWindows.get(paymentWindows.size()-1);
        }

    }

    /**
     *
     * @param payinfo
     */
    @Override
    public void execute(PaymentInfoMagcard payinfo) {

        int timer = 0;
        int timeout = 180;

        double roundedValue = RoundUtils.round(payinfo.getTotal());
        new Application().paymentSenseTransaction(roundedValue, getPaymentWindow(roundedValue));

        while (AppContext.getIsProcessing() == null || AppContext.getIsProcessing()) {
            try {
                log.info("uniCenta-oPos: waiting for payment to complete ....");
                Thread.sleep(1000);
                timer += 1;
                if (timer > timeout) break;
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }

        if (AppContext.getPaymentResult() == null) {
            payinfo.paymentError("Transaction Error! Please try again", "No Response");
        }
        if (AppContext.getPaymentResult().getTransactionResult().equals("SUCCESSFUL")){
            payinfo.setCardName(AppContext.getPaymentResult().getCardSchemeName());
            payinfo.setVerification(AppContext.getPaymentResult().getPaymentMethod());
            payinfo.setChipAndPin(true);
            payinfo.paymentOK(
                    AppContext.getPaymentResult().getAuthCode(),
                    AppContext.getPaymentResult().getTransactionId(),
                    AppContext.getPaymentResult().getTransactionResult()
            );
        }
        else {
            payinfo.paymentError("Transaction Error! Please try again", AppContext.getPaymentResult().getTransactionResult());
        }
    }
}
