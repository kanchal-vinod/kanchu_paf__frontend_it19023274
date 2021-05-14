package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class productf {
	//DB connection
		private Connection connect() {
			
			Connection con = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

				public String readproduct() {
					
					
					//System.out.println("read");
					String output = "";
					

					try {
						Connection con = connect();
						if (con == null) {
							return "Error while connecting to the database for reading.";
						}

						// Prepare the html table to be displayed
						output = "<table border='1'><tr><th>prname</th>" + "<th>prdate</th><th>prdes</th>"
								 + "<th>Update</th><th>Remove</th></tr>";

						String query = "select * from product";
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);

						while (rs.next()) {

							String pid = Integer.toString(rs.getInt("pid"));
							String prname = rs.getString("prname");
							String prdate = rs.getString("prdate");
							String prdes = rs.getString("prdes");
							

							// Add into the html table

							
							output += "<td>" + prname + "</td>";		
							output += "<td>" + prdate + "</td>";
							output += "<td>" + prdes + "</td>";
							

							// buttons
							output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
									+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-pid='"
									+ pid + "'>" + "</td></tr>";

						}

						con.close();

						// Complete the html table
						
						output += "</table>";
						
					} catch (Exception e) {
						output = "Error while reading the Appointment.";
						System.err.println(e.getMessage());
					}
					
					

					return output;
					
					
				}

				
				public String insertproduct(String prname, String prdate, String prdes) {
					
					
					String output = "";
					
					System.out.println("insert " + prname + prdate + prdes );

					try {
						Connection con = connect();

						if (con == null) {
							return "Error while connecting to the database";
						}

						// create a prepared statement
						String query = " insert into product (`prname`,`prdate`,`prdes`)"
								+ " values (?, ?, ?)";

						PreparedStatement preparedStmt = con.prepareStatement(query);

						// binding values
						preparedStmt.setString(1, prname);
						preparedStmt.setString(2, prdate);
						preparedStmt.setString(3, prdes);
						

						// execute the statement
						preparedStmt.execute();
						con.close();

						// Create JSON Object to show successful msg.
						String newproduct = readproduct();
						output = "{\"status\":\"success\", \"data\": \"" + newproduct + "\"}";
					} catch (Exception e) {
						// Create JSON Object to show Error msg.
						output = "{\"status\":\"error\", \"data\": \"Error while Inserting Product.\"}";
						System.err.println(e.getMessage());
					}

					return output;
				}

				// Update updateCustomer
				public String updateproduct(String prname, String prdate, String prdes, int pid) {
					
					
					String output = "";

					
					
					try {
						Connection con = connect();

						if (con == null) {
							return "Error while connecting to the database for updating.";
						}

						// create a prepared statement
						String query = "update product SET prname=?,prdate=?,prdes=? WHERE pid=?";

						PreparedStatement preparedStmt = con.prepareStatement(query);

						// binding values
						preparedStmt.setString(1, prname);
						preparedStmt.setString(2, prdate);
						preparedStmt.setString(3, prdes);	
						preparedStmt.setInt(4, pid);

						// execute prepared statement
						preparedStmt.execute();
						con.close();

						// create JSON object to show successful msg
						String newproduct = readproduct();
						output = "{\"status\":\"success\", \"data\": \"" + newproduct + "\"}";
					} catch (Exception e) {
						output = "{\"status\":\"error\", \"data\": \"Error while Updating product Details.\"}";
						System.err.println(e.getMessage());
					}

					return output;
				}

				
				
				public String deleteproduct(String pid) {
					
					
					String output = "";
					
					

					try {
						Connection con = connect();

						if (con == null) {
							return "Error while connecting to the database for deleting.";
						}

						// create a prepared statement
						String query = "delete from product where pid=?";

						PreparedStatement preparedStmt = con.prepareStatement(query);

						
						preparedStmt.setInt(1, Integer.parseInt(pid));
						// execute the statement
						preparedStmt.execute();
						con.close();

						
						String newproduct = readproduct();
						output = "{\"status\":\"success\", \"data\": \"" + newproduct + "\"}";
						
					} catch (Exception e) {
						
						
						output = "{\"status\":\"error\", \"data\": \"Error while Deleting product.\"}";
						System.err.println(e.getMessage());

					}

					return output;
					
					
				}

}
