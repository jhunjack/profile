package com.example.crudeprofile;
 
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
 
@Controller
public class ProductController {
 
    @Autowired
    private ProductService service;
   
    

    @GetMapping("/")
    public String viewHomePage(Model model,
    		@Param("keyword") String keyword) {
        List<Product> listProducts = service.listAll(keyword);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("keyword", keyword);
        
         
        return "index";
    }
   

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
         
        return "new_product";
    }
   

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);
         
        return "redirect:/";
    }
    
    
    @RequestMapping("/edito/{id}")
    public ModelAndView showEditProductForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_product");
        
        Product product = service.get(id);
        mav.addObject("product", product);
         
        return mav;
    }
    

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";       
    }
    
    
 
     
   
     
   
     
  
}