package datalayer;

import beans.Department;
import java.sql.*;
import java.util.ArrayList;

public class DALDepartment {

    private Connection cn;

    public DALDepartment() {
        cn = DBOperations.getConnection();
    }

    public eStatus saveDepartment(beans.Department department) 
    {

        eStatus ret = null;

        try 
        {

            PreparedStatement ps = cn.prepareStatement("insert into Departments values(?,?,?,?,?,?)");

            ps.setString(1, department.getName());
            ps.setString(2, department.getAddress());
            ps.setString(3, department.getCity());
            ps.setString(4, department.getContactNo());
            ps.setString(5, department.getEmailId());
            ps.setString(6, department.getContactPerson());

            ps.executeUpdate();
            ret = eStatus.Suceeded;
        } 
        catch (Exception ex) 
        {
            ret = eStatus.Fail;
        }

        return ret;
    }

    public eStatus deleteDepartment(int pDepartmentId) 
    {
        eStatus ret = null;

        try 
        {
            PreparedStatement ps = cn.prepareStatement("Delete from Departments where DepartmentId=?");

            ps.setInt(1, pDepartmentId);

            ps.executeUpdate();

            ret = eStatus.Delete;
        } 
        catch (Exception ex) 
        {
            ret = eStatus.Fail;
        }

        return ret;
    }

    public eStatus updateDepartment(beans.Department department) 
    {
        eStatus ret = null;

        try 
        {
            PreparedStatement ps = cn.prepareStatement("update departments set Name=?,Address=?,City=?,ContactNo=?,EmailId=?,ContactPerson=? where DepartmentId=?");
            ps.setString(1, department.getName());
            ps.setString(2, department.getAddress());
            ps.setString(3, department.getCity());
            ps.setString(4, department.getContactNo());
            ps.setString(5, department.getEmailId());
            ps.setString(6, department.getContactPerson());
            ps.setInt(7, department.getDepartmentId());

            ps.executeUpdate();
            ret = eStatus.Update;
        } 
        catch (Exception ex) 
        {
            ret = eStatus.Fail;
        }

        return ret;
    }

    public beans.Department getDepartment(int pDepartmentId) 
    {
        beans.Department d = new Department();

        try 
        {
            PreparedStatement ps = cn.prepareStatement("Select * from Departments where DepartmentId=?");
            ps.setInt(1, pDepartmentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                d.setDepartmentId(rs.getInt("DepartmentId"));
                d.setName(rs.getString("Name"));
                d.setAddress(rs.getString("Address"));
                d.setCity(rs.getString("City"));
                d.setContactNo(rs.getString("ContactNo"));
                d.setEmailId(rs.getString("EmailId"));
                d.setContactPerson(rs.getString("ContactPerson"));
            }

            rs.close();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }

        return d;
    }

    public eStatus exsists(String name) 
    {
        eStatus ret = null;

        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("Select Name from departments");

                while (rs.next()) 
                {
                    if (name.equals(rs.getString("Name"))) 
                    {
                        ret = eStatus.Duplicate;
                        break;
                    } 
                    else 
                    {
                        ret = eStatus.Suceeded;
                    }
                }
            rs.close();
        } 
        catch (Exception ex) 
        {
            ret = eStatus.Fail;
        }

        return ret;
    }

    public ArrayList<beans.Department> getDepartments() 
    {
        ArrayList<beans.Department> AllDepartments = new ArrayList<Department>();

        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("Select * from departments");

            beans.Department d;

            while (rs.next()) 
            {
                d = new Department();
                d.setDepartmentId(rs.getInt("DepartmentId"));
                d.setName(rs.getString("Name"));
                d.setAddress(rs.getString("Address"));
                d.setCity(rs.getString("City"));
                d.setContactNo(rs.getString("ContactNo"));
                d.setEmailId(rs.getString("EmailId"));
                d.setContactPerson(rs.getString("ContactPerson"));

                AllDepartments.add(d);
            }

            rs.close();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }

        return AllDepartments;
    }
}
