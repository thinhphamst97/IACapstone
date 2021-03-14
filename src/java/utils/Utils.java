/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashMap;

/**
 *
 * @author Natsu
 */
public class Utils {
    public static Integer delFromCart(HashMap<String, Integer> cart, String id) {
        Integer result = null;
        if (cart.containsKey(id))
            result = cart.remove(id); //return null Nếu không tìm thấy để xóa
        return result;
    }
    
}
