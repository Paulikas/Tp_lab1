package lab_1.MyBatis.dao;

import java.util.List;
import lab_1.MyBatis.Model.Guest;
import org.mybatis.cdi.Mapper;

@Mapper
public interface GuestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GUEST
     *
     * @mbg.generated Tue Apr 28 15:25:19 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GUEST
     *
     * @mbg.generated Tue Apr 28 15:25:19 EEST 2020
     */
    int insert(Guest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GUEST
     *
     * @mbg.generated Tue Apr 28 15:25:19 EEST 2020
     */
    Guest selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GUEST
     *
     * @mbg.generated Tue Apr 28 15:25:19 EEST 2020
     */
    List<Guest> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.GUEST
     *
     * @mbg.generated Tue Apr 28 15:25:19 EEST 2020
     */
    int updateByPrimaryKey(Guest record);
}