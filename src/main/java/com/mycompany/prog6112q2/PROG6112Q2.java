/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog6112q2;

/**
 *
 * @author RC_Student_Lab
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;


public class PROG6112Q2 extends JFrame {
    
    private JButton loadButton, saveButton, clearButton;
    private JTextArea displayArea;
    private JLabel totalLabel, avgLabel, overLabel, underLabel, processedLabel;
    private JMenuItem exitItem, loadMenuItem, saveMenuItem, clearMenuItem;

    private ProductSales salesProcessor = null;

    private final int[][] MOCK_SALES_DATA = {
        {300, 150, 700},
        {250, 200, 600}
    };

    public PROG6112Q2() {
        super("Product Sales Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);
        setLayout(new BorderLayout());

        createMenuBar();
        createPanel();
        
       
        clearApplication(); 
        
        setVisible(true);
    }
    

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
       
        JMenu fileMenu = new JMenu("File");
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> exitApplication());
        fileMenu.add(exitItem);
        
        
        JMenu toolsMenu = new JMenu("Tools");
        loadMenuItem = new JMenuItem("Load Product Data");
        saveMenuItem = new JMenuItem("Save Product Data");
        clearMenuItem = new JMenuItem("Clear");

        loadMenuItem.addActionListener(e -> loadProductData());
        saveMenuItem.addActionListener(e -> saveProductData());
        clearMenuItem.addActionListener(e -> clearApplication());
        
        toolsMenu.add(loadMenuItem);
        toolsMenu.add(saveMenuItem);
        toolsMenu.add(clearMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);
    }
    
    private void createPanel() {
    
        JPanel buttonPanel = new JPanel(new FlowLayout());
        loadButton = new JButton("Load Product Data");
        saveButton = new JButton("Save Product Data");
        clearButton = new JButton("Clear");
        
        loadButton.addActionListener(e -> loadProductData());
        saveButton.addActionListener(e -> saveProductData());
        clearButton.addActionListener(e -> clearApplication());
        
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
       
        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        
        JPanel statsPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        totalLabel = new JLabel("Total Sales: 0");
        avgLabel = new JLabel("Average Sales: 0");
        overLabel = new JLabel("Sales over limit: 0");
        underLabel = new JLabel("Sales under limit: 0");
        processedLabel = new JLabel("Years Processed: 0"); 

        statsPanel.add(new JLabel("Total Sales:")); statsPanel.add(totalLabel);
        statsPanel.add(new JLabel("Average Sales:")); statsPanel.add(avgLabel);
        statsPanel.add(new JLabel("Sales over limit (500):")); statsPanel.add(overLabel);
        statsPanel.add(new JLabel("Sales under limit (500):")); statsPanel.add(underLabel);
        statsPanel.add(new JLabel("")); statsPanel.add(new JSeparator());
        statsPanel.add(new JLabel("Years Processed:")); statsPanel.add(processedLabel);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.SOUTH);
    }
 
    private void loadProductData() {
      
        salesProcessor = new ProductSales(MOCK_SALES_DATA);
        
        
        displayArea.setText("--- PRODUCT SALES DATA ---\n" + salesProcessor.formatSalesData());
        
    
        updateStatistics();
        
        JOptionPane.showMessageDialog(this, "Product data loaded and processed.");
    }
    
 
    private void updateStatistics() {
        if (salesProcessor == null) return;
        
        DecimalFormat df = new DecimalFormat("0"); 
        
        totalLabel.setText(df.format(salesProcessor.GetTotalSales()));
        avgLabel.setText(df.format(salesProcessor.GetAverageSales()));
        overLabel.setText(String.valueOf(salesProcessor.GetSalesOverLimit()));
        underLabel.setText(String.valueOf(salesProcessor.GetSalesUnderLimit()));
        processedLabel.setText(String.valueOf(salesProcessor.GetProductsProcessed()));
        
      
        displayArea.append("\n\n--- RESULTS ---\n");
        displayArea.append(String.format("Total Sales: %d%n", salesProcessor.GetTotalSales()));
        displayArea.append(String.format("Average Sales: %s%n", df.format(salesProcessor.GetAverageSales())));
        displayArea.append(String.format("Sales over limit: %d%n", salesProcessor.GetSalesOverLimit()));
        displayArea.append(String.format("Sales under limit: %d%n", salesProcessor.GetSalesUnderLimit()));
    }

    private void saveProductData() {
        if (salesProcessor == null) {
            JOptionPane.showMessageDialog(this, "Please load data first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (PrintWriter writer = new PrintWriter(new File("data.txt"))) {
           
            writer.println("DATA LOG");
            writer.println("****");
            writer.println(salesProcessor.formatSalesData());
      
            writer.println("RESULTS");
            writer.println("***");
            writer.println("Total Sales: " + salesProcessor.GetTotalSales());
            writer.println("Average Sales: " + new DecimalFormat("0").format(salesProcessor.GetAverageSales()));
            writer.println("Sales over limit: " + salesProcessor.GetSalesOverLimit());
            writer.println("Sales under limit: " + salesProcessor.GetSalesUnderLimit());
            writer.println("Years processed: " + salesProcessor.GetProductsProcessed());
            
            JOptionPane.showMessageDialog(this, "Data successfully saved to data.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearApplication() {
        displayArea.setText("");
        totalLabel.setText("0");
        avgLabel.setText("0");
        overLabel.setText("0");
        underLabel.setText("0");
        processedLabel.setText("0");
        salesProcessor = null; 
        JOptionPane.showMessageDialog(this, "Application data cleared.");
    }

    private void exitApplication() {
        System.exit(0);
    }

    public static void main(String[] args) {
           SwingUtilities.invokeLater(PROG6112Q2::new);
 
    }
}
