package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBanco {
    private static final String URL = "jdbc:postgresql://localhost:5432/guilda_arcanos";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    public static Connection conectar() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
