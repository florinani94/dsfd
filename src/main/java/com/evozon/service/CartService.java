package com.evozon.service;

import com.evozon.dao.CartDAO;
import com.evozon.domain.Cart;
import com.evozon.domain.Entry;
import com.evozon.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Set;

/**
 * Created by vladblana on 19/07/2016.
 */
@Service
public class CartService {
    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private ServletContext servletContext;

    public void addCart(Cart cart){
        cartDAO.addCart(cart);

    }

    public void deleteCart(Integer id){
        cartDAO.deleteCart(id);

    }

    public void addProductToCart(Integer productId,Integer cartId) {
        List<Entry> entryList=cartDAO.getEntriesFromCart(productId,cartId);
        if(entryList.size()>0){
            for(Entry e:entryList){
                e.setQuantity(e.getQuantity()+1);
                cartDAO.updateEntry(e);
                cartDAO.computeSubTotalForEntry(e.getEntryId(),cartId);
                cartDAO.computeTotalForCart(cartId);
            }
        }
        else{
            cartDAO.addEntryToCart(productId,cartId);
            addProductToCart(productId,cartId);

        }
    }

    public void deleteEntryFromCart(Integer entryId) {
        cartDAO.deleteEntryFromCart(entryId);
    }


}
