package lab08.jdbc;
import java.sql.*;

public class NhanVienJDBC {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:nhanvien.db";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

          
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS NhanVien ("
                    + "id INTEGER PRIMARY KEY, "
                    + "ten TEXT NOT NULL, "
                    + "chuc_vu TEXT, "
                    + "luong INTEGER)";
            stmt.executeUpdate(sqlCreateTable);

           
            stmt.executeUpdate("DELETE FROM NhanVien");
            stmt.executeUpdate("INSERT INTO NhanVien (id, ten, chuc_vu, luong) VALUES (1, 'Nguyen Van A', 'Nhan vien', 8000000)");
            stmt.executeUpdate("INSERT INTO NhanVien (id, ten, chuc_vu, luong) VALUES (2, 'Tran Thi B', 'Truong phong', 15000000)");
            stmt.executeUpdate("INSERT INTO NhanVien (id, ten, chuc_vu, luong) VALUES (3, 'Le Van C', 'Nhan vien', 9000000)");

            conn.commit();

           
            rs = stmt.executeQuery("SELECT id, ten, chuc_vu FROM NhanVien");
            System.out.println("=== Danh sach nhan vien ===");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("ten") + " - " + rs.getString("chuc_vu"));
            }

            
            rs.close();
            rs = stmt.executeQuery("SELECT SUM(luong), AVG(luong), MIN(luong), MAX(luong) FROM NhanVien");
            if (rs.next()) {
                System.out.println("Tong luong: " + rs.getLong(1));
                System.out.println("Luong trung binh: " + rs.getDouble(2));
                System.out.println("Luong thap nhat: " + rs.getLong(3));
                System.out.println("Luong cao nhat: " + rs.getLong(4));
            }

            rs.close();
            rs = stmt.executeQuery("SELECT chuc_vu, COUNT(*) FROM NhanVien GROUP BY chuc_vu");
            System.out.println("=== So luong theo chuc vu ===");
            while (rs.next()) {
                System.out.println(rs.getString(1) + ": " + rs.getInt(2));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Khong tim thay driver: " + e.getMessage());
            rollback(conn);
        } catch (SQLException e) {
            System.out.println("Loi SQL: " + e.getMessage());
            rollback(conn);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Loi khi dong tai nguyen: " + e.getMessage());
            }
        }
    }

    private static void rollback(Connection conn) {
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException e) {
            System.out.println("Loi rollback: " + e.getMessage());
        }
    }
}