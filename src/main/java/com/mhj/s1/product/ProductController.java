package com.mhj.s1.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product/*")
public class ProductController {
   @Autowired
   private ProductService productService;
   
   @RequestMapping(value="list")
   public ModelAndView getProductList() throws Exception {
      ModelAndView mv = new ModelAndView();
      List<ProductDTO> ar = productService.getProductList();
      System.out.println(ar.size()>0);
      mv.setViewName("product/productList");
      mv.addObject("list",ar);
      return mv;
      }
//      
//      List<ProductDTO> ar = productService.getProductList();
//      System.out.println(ar.size()>0);
//      return "product/productList";
//   }
   
   @RequestMapping(value="detail")
   public String getProductDetail(ProductDTO productDTO,Model model) throws Exception {
      //parameter의 이름은 setter의 이름과 같아야함
      System.out.println("Product Detail");
      //String num = request.getParameter("num");
//      ProductDTO productDTO = new ProductDTO();
//      productDTO.setPRODUCTNUM(num);
//      productDTO.setPRODUCTNAME(name);
      productDTO=productService.getProductDetail(productDTO);
      System.out.println(productDTO!=null);
      model.addAttribute("dto", productDTO);
      return "product/productDetail";
   }
   
   @RequestMapping(value="productAdd",method = RequestMethod.GET)
   public void productAdd() {
      //void를 사용하면 메서드 이름과 주소값의 이름이 같으면 return 값이 없어도 찾을수 있음
   } // list로 돌아감.(jsp)
   
   @RequestMapping(value="productAdd",method = RequestMethod.POST)
   public String productAdd(ProductDTO productDTO) throws Exception {
      int result = productService.setProductAdd(productDTO, null);
      System.out.println(result==1);
      return "redirect:./list";
   }//DBㅔ insert
   
   
   @RequestMapping(value="update")
   public ModelAndView update() {
      ModelAndView mv = new ModelAndView();
      mv.setViewName("product/productUpdate");
      return mv;
   }

}