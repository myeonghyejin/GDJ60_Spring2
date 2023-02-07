package com.mhj.s1.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhj.s1.product.ProductOptionDTO;

@Service
public class ProductService {
   @Autowired
   private ProductDAO productDAO;
 // 여기서만 쓰세요~~~(이 클래스 내에서만 사용 가능 멤버변수)

   public int setProductAdd(ProductDTO productDTO, List<ProductOptionDTO> ar ) throws Exception{
      //product, option
      Long productNum=productDAO.getpProductNum();
      productDTO.setProductNum(productNum);
      int result = productDAO.setProductAdd(productDTO);
      if(ar!=null) {
         for(ProductOptionDTO productOptionDTO : ar) {
            productOptionDTO.setProductNum(productNum);
            result = productDAO.setAddProductOption(productOptionDTO);
         }
      }
      return result;
   }
   
   //getList
   public List<ProductDTO> getProductList()throws Exception {
      return productDAO.getProductList();
   }
   
   public ProductDTO getProductDetail(ProductDTO productDTO)throws Exception {
      return productDAO.getProductDetail(productDTO);   
   }
   
   
}