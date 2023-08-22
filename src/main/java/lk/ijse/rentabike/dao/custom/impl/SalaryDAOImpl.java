package lk.ijse.rentabike.dao.custom.impl;

import lk.ijse.rentabike.dao.custom.SalaryDAO;
import lk.ijse.rentabike.dao.custom.impl.util.SQLUtil;
import lk.ijse.rentabike.entity.Attendence;
import lk.ijse.rentabike.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public boolean save(Salary entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Salary (salaryId, description, amount, type, month, employeeId) VALUES (?, ?, ?, ?, ?, ?)",
                entity.getSalaryId(), entity.getDescription(), entity.getAmount(), entity.getType(), entity.getMonth(), entity.getEmployeeId());
    }

    @Override
    public ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> allSalaries = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Salary");
        while (rst.next()) {
            allSalaries.add(new Salary(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getString(4),
                    rst.getString(5), rst.getString(6)));
        }
        return allSalaries;
    }

    @Override
    public boolean update(Salary entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Salary SET description = ?, amount = ?, type = ?, month = ?, employeeId = ? WHERE salaryId = ?",
                entity.getDescription(), entity.getAmount(), entity.getType(), entity.getMonth(), entity.getEmployeeId(), entity.getSalaryId());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Salary WHERE salaryId=?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT salaryId FROM Salary ORDER BY salaryId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("salaryId");
            int newCustomerId = Integer.parseInt(id.replace("S00-", "")) + 1;
            return String.format("S00-%03d", newCustomerId);
        } else {
            return "S00-001";
        }
    }

    @Override
    public Salary search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Salary WHERE salaryId = ?", s);
        Salary salary = new Salary();

        if (rst.next()) {
            salary.setSalaryId(rst.getString("salaryId"));
            salary.setDescription(rst.getString("description"));
            salary.setAmount(rst.getDouble("amount"));
            salary.setType(rst.getString("type"));
            salary.setMonth(rst.getString("month"));
            salary.setEmployeeId(rst.getString("employeeId"));
        }

        return salary;
    }
}
