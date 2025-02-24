//    SOLiPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2018 uniCenta
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
//    along with SOLiPOS.  If not, see <http://www.gnu.org/licenses/>
package com.unicenta.pos.forms;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LicenseSystem extends JFrame {

    private JTextField serialNumberField;
    private JTextField keyField;
    private JButton checkButton;

    public LicenseSystem() {
        AppConfig m_config = new AppConfig(new File(System.getProperty("user.home"), AppLocal.APP_ID + ".properties"));
        setTitle("License System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        String serial = m_config.getDeviceSerialNumber();

        // **Step 1: Generate License Key**
        JLabel serialLabel = new JLabel("Device Serial Number:");
        serialNumberField = new JTextField();
        serialNumberField.setText(serial);
        serialNumberField.setEditable(false);
        add(serialLabel);
        add(serialNumberField);

        JLabel serialLabel1 = new JLabel("Enter License Key:");
        keyField = new JTextField();
        add(serialLabel1);
        add(keyField);

        // **Step 2: Key Check Dialog**
        checkButton = new JButton("Check Key");
        add(checkButton);

        // **Check Key Button Action**
        checkButton.addActionListener(e -> actionCheck(m_config, keyField.getText()));

        setVisible(true);
    }

    // Show the key verification dialog
    private void actionCheck(AppConfig config, String checkKey) {
        if (config.keyCheck(checkKey)) {
            JOptionPane.showMessageDialog(null, "License Key is valid! Access granted. Restart Application", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                config.setLicenseKey(checkKey);
            } catch (IOException ex) {
                Logger.getLogger(LicenseSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(1);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid License Key! Access denied.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
