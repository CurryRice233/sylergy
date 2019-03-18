package com.example.sylergy.integration.product.dao.imp;

import android.support.annotation.NonNull;

import com.example.sylergy.integration.product.dao.DAOProduct;
import com.example.sylergy.integration.firebase.FirebaseUtil;

import com.example.sylergy.objects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAOProductImp implements DAOProduct { ;

    @Override
    public Product readById(Long barcode) {
        final List<Product> listProducts = new ArrayList<>();

        DatabaseReference database = FirebaseUtil.getSpecifiedReference("Products");
        Query query = FirebaseUtil.getQueryByChild(database, "barcode")
                .equalTo(barcode.toString());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listProducts.clear();
                if(dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        listProducts.add(d.getValue(Product.class));
                    }

                }
                else{
                   listProducts.add(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return listProducts.get(0);
    }

}