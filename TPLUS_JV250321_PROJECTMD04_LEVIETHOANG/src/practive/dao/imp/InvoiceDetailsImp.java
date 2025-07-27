package dao.imp;

import dao.InvoiceDAO;
import dao.InvoiceDetailsDAO;
import entity.InvoiceDetails;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class InvoiceDetailsImp implements InvoiceDetailsDAO {

    @Override
    public boolean addInvoiceDetails(InvoiceDetails invoicedetail) {
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_invoicedetails(?,?,?,?)}");
            callSt.setInt(1, invoicedetail.getInvoiceId());
            callSt.setInt(2, invoicedetail.getProductId());
            callSt.setInt(3, invoicedetail.getQuantity());
            callSt.setFloat(4, invoicedetail.getUnitPrice());
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}
