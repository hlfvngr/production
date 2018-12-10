package com.cskaoyan.erp.mapper;

import com.cskaoyan.erp.bean.FinalMeasureCheck;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.FinalMeasureListVO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

public interface FinalMeasureCheckMapper {
    @Delete({
            "delete from final_measure_check",
            "where f_measure_check_id = #{fMeasureCheckId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String fMeasureCheckId);

    @Insert({
            "insert into final_measure_check (f_measure_check_id, order_id, ",
            "check_item, cdate, ",
            "measure_data, emp_id, ",
            "result, note)",
            "values (#{fMeasureCheckId,jdbcType=VARCHAR}, #{orderId,jdbcType=VARCHAR}, ",
            "#{checkItem,jdbcType=VARCHAR}, #{cdate,jdbcType=TIMESTAMP}, ",
            "#{measureData,jdbcType=VARCHAR}, #{empId,jdbcType=VARCHAR}, ",
            "#{result,jdbcType=VARCHAR}, #{note,jdbcType=VARCHAR})"
    })
    int insert(FinalMeasureCheck record);

    @Update({
            "update final_measure_check",
            "set order_id = #{orderId,jdbcType=VARCHAR},",
            "check_item = #{checkItem,jdbcType=VARCHAR},",
            "cdate = #{cdate,jdbcType=TIMESTAMP},",
            "measure_data = #{measureData,jdbcType=VARCHAR},",
            "emp_id = #{empId,jdbcType=VARCHAR},",
            "result = #{result,jdbcType=VARCHAR},",
            "note = #{note,jdbcType=VARCHAR}",
            "where f_measure_check_id = #{fMeasureCheckId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(FinalMeasureCheck record);

    Integer count();

    ArrayList<FinalMeasureListVO> selectByPageAndRows(PageModel pageModel);

    Integer searchCountByMeasureId(String searchValue);

    List<FinalMeasureListVO> searchByMeasureId(@Param("searchValue") String searchValue, @Param("pageModel") PageModel pageModel);

    Integer searchCountByOrderId(String searchValue);

    List<FinalMeasureListVO> searchByOrderId(@Param("searchValue") String searchValue, @Param("pageModel") PageModel pageModel);
}