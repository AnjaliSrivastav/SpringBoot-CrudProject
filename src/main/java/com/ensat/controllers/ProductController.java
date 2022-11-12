package com.ensat.controllers;

import com.ensat.entities.Product;
import com.ensat.exception.ProductServiceException;
import com.ensat.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Product controller.
 */
@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts()
    {
        return ResponseEntity.ok(this.productService.listAllProducts());
    }

    @RequestMapping("/product/{id}")
    public String showProduct(@PathVariable Integer id) throws ProductServiceException {
        String productName = "";
       // return ResponseEntity.ok(this.productService.getProductById(pId));
        Product product = null;
        try{
            product = this.productService.getProductById(id);
            productName = product.getName();


        }catch (Exception e){
            throw new ProductServiceException("product not found with the id "+id);
        }
        return "the product is :"+productName;
    }

    //I need to correct this PUT method
    @PutMapping("/product/edit/{id}")
    public ResponseEntity<?> editFullResource(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

   @PatchMapping(value = "/product/edit/{id}/{name}")
    public ResponseEntity<Product> editPartially(@PathVariable Integer id, @PathVariable String name) {
       Product product = productService.getProductById(id);
       product.setName(name);
       return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.OK);
    }


    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return "redirect:/product/" + product.getId();
    }

    @DeleteMapping("/product/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id) {
        //Product product = productService.getProductById(id);
       // productService.deleteProduct();
        productService.deleteProduct(id);
        return "redirect:/products";
    }



}
