package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.mysql.cj.protocol.Resultset;

import ch21_jdbc.DB;



public class PhoneDAO {

	public Vector listPhone(){
		Vector items = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DB.dbConn();
			StringBuilder sb = new StringBuilder();
			sb.append(" select name,number ");
			sb.append(" from phon ");
			sb.append(" order by name ");
			pstmt=conn.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row = new Vector();
				String name = rs.getString("name");
				String number = rs.getString("number");
				row.add(name);
				row.add(number);
				
				items.add(row);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return items;
	}
	public int insertPhone(PhoneDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=DB.dbConn();
			StringBuilder sb = new StringBuilder();
			sb.append("insert into phon (name,number) ");
			sb.append(" values (?,?)");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getNumber());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	public int updatePhone(PhoneDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=DB.dbConn();
			StringBuilder sb = new StringBuilder();
			sb.append("update Phon ");
			sb.append(" set number=? ");
			sb.append(" where name=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getNumber());
			pstmt.setString(2, dto.getName());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
				
			
		}
		return result;
		
	}
	public Vector searchPhone(String name) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.dbConn();
			StringBuilder sb = new StringBuilder();
			sb.append("select name, number from phon where name like ? order by name");
			System.out.println(sb.toString());
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, "%"+name+"%");//like '%박%';
			rs = pstmt.executeQuery();//sql 실행
			while(rs.next()) {//다음 레코드가 있으면 true
				Vector row=new Vector();
				row.add(rs.getString("name"));
				row.add(rs.getString("number"));
				items.add(row);//레코드 1개를 벡터에 추가(벡터 items에
				//백터 row를 저장)
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return items;//백터 리턴
	}//searchScore()
	public PhoneDTO viewPhone(String name) {
		PhoneDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DB.dbConn();
			StringBuilder sb = new StringBuilder();
			sb.append("select name,number");
			sb.append(" from phon ");
			sb.append(" where name=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String number = rs.getString("number");
				
				dto = new PhoneDTO(name, number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
				
	}
	public int deletePhone(String name) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=DB.dbConn();
			StringBuilder sb = new StringBuilder();
			sb.append("delete from phon where name=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
	

