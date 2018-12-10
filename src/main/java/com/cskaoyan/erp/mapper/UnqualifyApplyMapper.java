package com.cskaoyan.erp.mapper;

import com.cskaoyan.erp.bean.UnqualifyApply;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.UnqualifyListVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

public interface UnqualifyApplyMapper {

    @Delete({
            "delete from unqualify_apply",
            "where unqualify_apply_id = #{unqualifyApplyId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String unqualifyApplyId);

    @Insert({
            "insert into unqualify_apply (unqualify_apply_id, product_id, ",
            "unqualify_item, unqualify_count, ",
            "assembly_date, emp_id, ",
            "apply_date, note)",
            "values (#{unqualifyApplyId,jdbcType=VARCHAR}, #{productId,jdbcType=VARCHAR}, ",
            "#{unqualifyItem,jdbcType=VARCHAR}, #{unqualifyCount,jdbcType=INTEGER}, ",
            "#{assemblyDate,jdbcType=TIMESTAMP}, #{empId,jdbcType=VARCHAR}, ",
            "#{applyDate,jdbcType=TIMESTAMP}, #{note,jdbcType=VARCHAR})"
    })
    int insert(UnqualifyApply record);

    @Update({
            "update unqualify_apply",
            "set product_id = #{productId,jdbcType=VARCHAR},",
            "unqualify_item = #{unqualifyItem,jdbcType=VARCHAR},",
            "unqualify_count = #{unqualifyCount,jdbcType=INTEGER},",
            "assembly_date = #{assemblyDate,jdbcType=TIMESTAMP},",
            "emp_id = #{empId,jdbcType=VARCHAR},",
            "apply_date = #{applyDate,jdbcType=TIMESTAMP},",
            "note = #{note,jdbcType=VARCHAR}",
            "where unqualify_apply_id = #{unqualifyApplyId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(UnqualifyApply record);

    int count();

    ArrayList<UnqualifyListVO> selectByPageAndRows(PageModel pageModel);

    Integer searchCountByUnqualifyId(String searchValue);

    List<UnqualifyListVO> searchByUnqualifyId(@Param("searchValue") String searchValue, @Param("pageModel") PageModel pageModel);

    Integer searchCountByProductName(String searchValue);

    List<UnqualifyListVO> searchByProductName(@Param("searchValue") String searchValue, @Param("pageModel") PageModel pageModel);
}