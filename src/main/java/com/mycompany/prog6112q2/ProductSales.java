/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog6112q2;

import java.util.Locale;

/**
 *
 * @author RC_Student_Lab
 */
public class ProductSales implements iProductSales{
    
    private final int[][] salesData;
    private final int SALES_LIMIT = 500;
    
    public ProductSales(int[][] data){
    this.salesData = data; 
    }
    
    @Override
    public int[][] GetProductSales(){
    return salesData;
    }
    
    @Override
    public int GetTotalSales(){
    int total = 0;
    for (int[] yearSales:salesData){
     for (int sale: yearSales){
     total += sale;
     }
    }
    return total;
    }
    
    @Override
    public double GetAverageSales(){
    int total = GetTotalSales();
    int numQuarters = 0;
    for(int[] yearSales:salesData){
        numQuarters += yearSales.length;
    }
    if (numQuarters == 0 ) return 0.0;
    return (double) total / numQuarters ; 
    }
    
    @Override
    public int GetSalesOverLimit(){
    int count = 0 ;
    for (int[] yearSales: salesData){
     for(int sale : yearSales){
       if(sale> SALES_LIMIT){
       count ++;
       }
     }
    }
    return count;
    }
    
    public int GetProductsProcessed(){
    return salesData.length;
    }
    
    public String formatSalesData(){
    StringBuilder sb = new StringBuilder();
    String[] headers = {"Year","Microphone","Speakers","Mixing Desk"};
    
    sb.append(String.format("%-10d %-12d %-10d %-12d", (Object[])headers));
    sb.append("----------------------------------------------------");
    
    for(int i= 0; i<salesData.length; i++){
     sb.append(String.format("%-10d %-12d %-10d %-12d", i + 1, salesData[i][0], salesData[i][1], salesData[i][2]));
    }
    return sb.toString();
    }

    @Override
    public int GetSalesUnderLimit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int GetProductsProduct() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
