package com.mhj.s1.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mhj.s1.product.ProductOptionDTO;
import com.mhj.s1.util.DBConnection;

@Repository
public class ProductDAO {
   @Autowired
   private SqlSession sqlSession;
   private final String NAMESPACE="com.mhj.s1.product.ProductDAO.";
   
   //delete
   public int setProductDelete(Long productNum) throws Exception{
      return sqlSession.delete(NAMESPACE+"setProductDelete", productNum);
   }
   
   //add
   public int setProductAdd(ProductDTO productDTO) throws Exception{
      return sqlSession.insert(NAMESPACE+"setProductAdd", productDTO);
      
   }   
   
   //getDetail
   public ProductDTO getProductDetail(ProductDTO productDTO) throws Exception {
      return sqlSession.selectOne(NAMESPACE+"getProductDetail", productDTO);
      //selectone은 1개만 받아올 수 있음
   }
   
   //getList
   public List<ProductDTO> getProductList() throws Exception{
      return sqlSession.selectList(NAMESPACE+"getProductList");
   }
   
   //getNum
   public Long getpProductNum() throws Exception {
      return sqlSession.selectOne(NAMESPACE+"getProductNum");
   }
//   ----------------------------------------------------------------------
   public int setAddProductOption(ProductOptionDTO productOptionDTO) throws Exception {
      Connection connection = DBConnection.getConnection();
      String sql = "INSERT INTO PRODUCTOPTION VALUES(PRODUCT_SEQ.NEXTVAL,?,?,?,?";
      PreparedStatement st = connection.prepareStatement(sql);
      st.setLong(1,productOptionDTO.getProductNum());
      st.setString(2,productOptionDTO.getOptionName());
      st.setLong(3, productOptionDTO.getOptionPrice());
      st.setLong(4,productOptionDTO.getOptionStock());
      int result = st.executeUpdate();
      DBConnection.disConnection(st, connection);
      return result;
      
      
   }
   public List<ProductOptionDTO> getProduct_OptionList() throws Exception{
      List<ProductOptionDTO> ar = new ArrayList<ProductOptionDTO>();
      Connection connection = DBConnection.getConnection();
      String sql = "SELECT * FROM PRODUCTOPTION ";
      PreparedStatement st =connection.prepareStatement(sql);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
         ProductOptionDTO productOptionDTO = new ProductOptionDTO();
         productOptionDTO.setOptionNum(rs.getLong("OPTIONNUM"));
         productOptionDTO.setProductNum(rs.getLong("PRODUCTNUM"));
         ar.add(productOptionDTO);
      }
      DBConnection.disConnection(rs, st, connection);
      return ar;
   }


   
   
   public static void main(String[] args) {
      ProductDAO productDAO = new ProductDAO();
      try {
         List<ProductOptionDTO> ar = productDAO.getProduct_OptionList();
         System.out.println(ar.size()!=0);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }

}