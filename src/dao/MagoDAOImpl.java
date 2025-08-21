package dao;

import model.Mago;

import java.sql.SQLException;
import java.util.ArrayList;

public class MagoDAOImpl {
    public static ArrayList<Mago> getTodosMagos() throws SQLException{
        ArrayList<Integer> ids = MagoDAO.idsMagos();
        ArrayList<Mago> magos = new ArrayList<>();

        for (int id : ids){
            magos.add(MagoDAO.buscarMago(id));
        }
        return magos;
    }
}
