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

package com.unicenta.pos.printer.escpos;

/**
 *
 * @author JG uniCenta
 */
public abstract class UnicodeTranslator {
    
    /**
     *
     * @return
     */
    public abstract byte[] getCodeTable();
    
    /**
     *
     * @param sCad
     * @return
     */
    public final byte[] transString(String sCad) {

        if (sCad == null) {
            return null;
        } else {
            byte bAux[] = new byte[sCad.length()];
            for( int i = 0; i < sCad.length(); i++) {
                bAux[i] = transChar(sCad.charAt(i));
            }
            return bAux;
        }
    }
    
    /**
     *
     * @param sChar
     * @return
     */
    public abstract byte transChar(char sChar);  
 
//                case '\u0000': return -0x80; // 0x80 :
//                case '\u0000': return -0x7F; // 0x81 :
//                case '\u0000': return -0x7E; // 0x82 :
//                case '\u0000': return -0x7D; // 0x83 :
//                case '\u0000': return -0x7C; // 0x84 :
//                case '\u0000': return -0x7B; // 0x85 :
//                case '\u0000': return -0x7A; // 0x86 :
//                case '\u0000': return -0x79; // 0x87 :
//                case '\u0000': return -0x78; // 0x88 :
//                case '\u0000': return -0x77; // 0x89 :
//                case '\u0000': return -0x76; // 0x8A :
//                case '\u0000': return -0x75; // 0x8B :
//                case '\u0000': return -0x74; // 0x8C :
//                case '\u0000': return -0x73; // 0x8D :
//                case '\u0000': return -0x72; // 0x8E :
//                case '\u0000': return -0x71; // 0x8F :
//                case '\u0000': return -0x70; // 0x90 :
//                case '\u0000': return -0x6F; // 0x91 :
//                case '\u0000': return -0x6E; // 0x92 :
//                case '\u0000': return -0x6D; // 0x93 :
//                case '\u0000': return -0x6C; // 0x94 :
//                case '\u0000': return -0x6B; // 0x95 :
//                case '\u0000': return -0x6A; // 0x96 :
//                case '\u0000': return -0x69; // 0x97 :
//                case '\u0000': return -0x68; // 0x98 :
//                case '\u0000': return -0x67; // 0x99 :
//                case '\u0000': return -0x66; // 0x9A :
//                case '\u0000': return -0x65; // 0x9B :
//                case '\u0000': return -0x64; // 0x9C :
//                case '\u0000': return -0x63; // 0x9D :
//                case '\u0000': return -0x62; // 0x9E :
//                case '\u0000': return -0x61; // 0x9F :
//                case '\u0000': return -0x60; // 0xA0 :
//                case '\u0000': return -0x5F; // 0xA1 :
//                case '\u0000': return -0x5E; // 0xA2 :
//                case '\u0000': return -0x5D; // 0xA3 :
//                case '\u0000': return -0x5C; // 0xA4 :
//                case '\u0000': return -0x5B; // 0xA5 :
//                case '\u0000': return -0x5A; // 0xA6 :
//                case '\u0000': return -0x59; // 0xA7 :
//                case '\u0000': return -0x58; // 0xA8 :
//                case '\u0000': return -0x57; // 0xA9 :
//                case '\u0000': return -0x56; // 0xAA :
//                case '\u0000': return -0x55; // 0xAB :
//                case '\u0000': return -0x54; // 0xAC :
//                case '\u0000': return -0x53; // 0xAD :
//                case '\u0000': return -0x52; // 0xAE :
//                case '\u0000': return -0x51; // 0xAF :
//                case '\u0000': return -0x50; // 0xB0 :
//                case '\u0000': return -0x4F; // 0xB1 :
//                case '\u0000': return -0x4E; // 0xB2 :
//                case '\u0000': return -0x4D; // 0xB3 :
//                case '\u0000': return -0x4C; // 0xB4 :
//                case '\u0000': return -0x4B; // 0xB5 :
//                case '\u0000': return -0x4A; // 0xB6 :
//                case '\u0000': return -0x49; // 0xB7 :
//                case '\u0000': return -0x48; // 0xB8 :
//                case '\u0000': return -0x47; // 0xB9 :
//                case '\u0000': return -0x46; // 0xBA :
//                case '\u0000': return -0x45; // 0xBB :
//                case '\u0000': return -0x44; // 0xBC :
//                case '\u0000': return -0x43; // 0xBD :
//                case '\u0000': return -0x42; // 0xBE :
//                case '\u0000': return -0x41; // 0xBF :
//                case '\u0000': return -0x40; // 0xC0 :
//                case '\u0000': return -0x3F; // 0xC1 :
//                case '\u0000': return -0x3E; // 0xC2 :
//                case '\u0000': return -0x3D; // 0xC3 :
//                case '\u0000': return -0x3C; // 0xC4 :
//                case '\u0000': return -0x3B; // 0xC5 :
//                case '\u0000': return -0x3A; // 0xC6 :
//                case '\u0000': return -0x39; // 0xC7 :
//                case '\u0000': return -0x38; // 0xC8 :
//                case '\u0000': return -0x37; // 0xC9 :
//                case '\u0000': return -0x36; // 0xCA :
//                case '\u0000': return -0x35; // 0xCB :
//                case '\u0000': return -0x34; // 0xCC :
//                case '\u0000': return -0x33; // 0xCD :
//                case '\u0000': return -0x32; // 0xCE :
//                case '\u0000': return -0x31; // 0xCF :
//                case '\u0000': return -0x30; // 0xD0 :
//                case '\u0000': return -0x2F; // 0xD1 :
//                case '\u0000': return -0x2E; // 0xD2 :
//                case '\u0000': return -0x2D; // 0xD3 :
//                case '\u0000': return -0x2C; // 0xD4 :
//                case '\u0000': return -0x2B; // 0xD5 :
//                case '\u0000': return -0x2A; // 0xD6 :
//                case '\u0000': return -0x29; // 0xD7 :
//                case '\u0000': return -0x28; // 0xD8 :
//                case '\u0000': return -0x27; // 0xD9 :
//                case '\u0000': return -0x26; // 0xDA :
//                case '\u0000': return -0x25; // 0xDB :
//                case '\u0000': return -0x24; // 0xDC :
//                case '\u0000': return -0x23; // 0xDD :
//                case '\u0000': return -0x22; // 0xDE :
//                case '\u0000': return -0x21; // 0xDF :
//                case '\u0000': return -0x20; // 0xE0 :
//                case '\u0000': return -0x2F; // 0xE1 :
//                case '\u0000': return -0x1E; // 0xE2 :
//                case '\u0000': return -0x1D; // 0xE3 :
//                case '\u0000': return -0x1C; // 0xE4 :
//                case '\u0000': return -0x1B; // 0xE5 :
//                case '\u0000': return -0x1A; // 0xE6 :
//                case '\u0000': return -0x19; // 0xE7 :
//                case '\u0000': return -0x18; // 0xE8 :
//                case '\u0000': return -0x17; // 0xE9 :
//                case '\u0000': return -0x16; // 0xEA :
//                case '\u0000': return -0x15; // 0xEB :
//                case '\u0000': return -0x14; // 0xEC :
//                case '\u0000': return -0x13; // 0xED :
//                case '\u0000': return -0x12; // 0xEE :
//                case '\u0000': return -0x11; // 0xEF :
//                case '\u0000': return -0x10; // 0xF0 :
//                case '\u0000': return -0x0F; // 0xF1 :
//                case '\u0000': return -0x0E; // 0xF2 :
//                case '\u0000': return -0x0D; // 0xF3 :
//                case '\u0000': return -0x0C; // 0xF4 :
//                case '\u0000': return -0x0B; // 0xF5 :
//                case '\u0000': return -0x0A; // 0xF6 :
//                case '\u0000': return -0x09; // 0xF7 :
//                case '\u0000': return -0x08; // 0xF8 :
//                case '\u0000': return -0x07; // 0xF9 :
//                case '\u0000': return -0x06; // 0xFA :
//                case '\u0000': return -0x05; // 0xFB :
//                case '\u0000': return -0x04; // 0xFC :
//                case '\u0000': return -0x03; // 0xFD :
//                case '\u0000': return -0x02; // 0xFE :
//                case '\u0000': return -0x01; // 0xFF :

}
