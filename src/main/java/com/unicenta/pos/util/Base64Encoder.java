//    SOLiPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2017 uniCenta
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

package com.unicenta.pos.util;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author JG uniCenta
 */
public class Base64Encoder {
    
    /**
     *
     * @param base64
     * @return
     */
    public static byte[] decode(String base64) {

        try {
            return Base64.decodeBase64(base64.getBytes("ASCII"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     *
     * @param raw
     * @return
     */
    public static String encode(byte[] raw) {
        try {
            return new String(Base64.encodeBase64(raw), "ASCII");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    
    /**
     *
     * @param raw
     * @return
     */
    public static String encodeChunked(byte[] raw) {
        try {
            return new String(Base64.encodeBase64Chunked(raw), "ASCII");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}