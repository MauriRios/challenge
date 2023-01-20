/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.demo.challenge.servicesInterfaces;

import com.demo.challenge.entitys.Product;
import com.demo.challenge.entitys.Provider;
import java.util.List;

/**
 *
 * @author mauri
 */

public interface IProviderService {
    
    public List<Provider> getProviders();
    
    //public void saveProvider(Provider provider, int providerId); 

    public void saveProvider(Provider provider);

    public void deleteProvider(int id);
    
    public Provider findProvider(int id);
    
}
