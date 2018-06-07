/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbgenerator.utils;

import dbgenerator.db.DBUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santhosh
 */
public class DBGenerateUtils {
    
    private static String url = "";
    
    public static boolean init(String db) {
        
        if(!db.startsWith("jdbc://"))
            url = "jdbc:mysql://localhost:3306/"+db+"?user=root&password=mysql";
        else
            url = db;
        
        return true;
    }
    
    public static List<String[]> getTables() {
        if(DBUtils.connect(url)!=null) {
            try {
                List<String[]> tables = DBUtils.getTables();
                
                return tables;
            } catch (SQLException ex) {
                Logger.getLogger(DBGenerateUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }
    
    public static String[] getTableStructure(String table) {
        try {
            final String sql = "SELECT * FROM "+table;
            
            final ResultSet rs = DBUtils.select(sql);
            
            final ResultSetMetaData rsmd = rs.getMetaData();
            
            final String[] struct = new String[rsmd.getColumnCount()];
            
            for(int i=1;i<=rsmd.getColumnCount();i++) {
                final String columnName = rsmd.getColumnName(i);
                int type = rsmd.getColumnType(i);
                String columnType = "";
                
                switch(type) {
                    case java.sql.Types.INTEGER:
                        columnType = "INT";
                        break;
                    case java.sql.Types.BIGINT:
                        columnType = "BIGINT";
                        break;
                    case java.sql.Types.DOUBLE:
                        columnType = "DOUBLE";
                        break;
                    case java.sql.Types.REAL:
                        columnType = "REAL";
                        break;
                    case java.sql.Types.DATE:
                        columnType = "DATE";
                        break;
                    case java.sql.Types.FLOAT:
                        columnType = "FLOAT";
                        break;
                    case java.sql.Types.DECIMAL:
                        columnType = "DECIMAL";
                        break;
                    case java.sql.Types.VARCHAR:
                        columnType = "VARCHAR";
                        break;
                    default:
                        columnType = "VARCHAR";
                }
                
                struct[i-1] = columnName+":"+columnType;
            }
            
            return struct;
        } catch (SQLException ex) {
            Logger.getLogger(DBGenerateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new String[] {};
    }
    
    
    public static List<String> generateCode(final String packageName,final String outputFolder) {
        final List<String> codes = new ArrayList<String>();
        
        String folder = packageName.replace('.',File.separatorChar);
        
        File finalOut = new File(outputFolder,folder);
        
        if(!finalOut.exists())
            finalOut.mkdirs();
        
        File modelFolder = new File(finalOut.getAbsolutePath(),"model");
        File dbFolder = new File(finalOut.getAbsolutePath(),"db");
        
        if(!modelFolder.exists())
            modelFolder.mkdirs();
        
        if(!dbFolder.exists())
            dbFolder.mkdirs();
        
        final List<String[]> tables = getTables();
        
        tables.stream().skip(1).map(t->t[2]).forEach(t-> {
            String[] struct = getTableStructure(t);
            System.out.println(Arrays.toString(struct));
            
            System.out.println("Generating Model => "+t+"...");
            if(generateTableModel(t, struct, modelFolder,packageName)) {
                codes.add(new File(modelFolder.getAbsolutePath(),createModelName(t)+".java").getAbsolutePath());
                System.out.println("Generating Model => "+t+" Success!");
            } else
                System.out.println("Generating Model => "+t+" Error!");
        });
        
        System.out.println("Generating DBUtils => ...");
        if(generateDBUtils(tables,dbFolder,packageName)) {
            codes.add(new File(dbFolder.getAbsolutePath(),"DBUtils.java").getAbsolutePath());
            System.out.println("Generating DBUtils => Success!");
            return codes;
        } else {
            System.out.println("Generating DBUtils => Error!");
            codes.add("DBUtils.java");
            return codes;
        }
    }
    
    public static boolean generateTableModel(String tableName,String[] struct,File modelFolder,String packagename) {
        PrintWriter pw = null;
        try {
            String modelName = createModelName(tableName);
            
            StringBuilder sb = new StringBuilder("");
            
            sb.append("package "+packagename+".model;\r\n");
            sb.append("\r\n");
            
            sb.append("public class "+modelName+" {\r\n");
            
            for(String column : struct) {
                if(column.split(":")[1].equals("VARCHAR")) {
                    sb.append("\tprivate String "+column.split(":")[0]+";\r\n");
                } else if(column.split(":")[1].equals("INT")) {
                    sb.append("\tprivate int "+column.split(":")[0]+";\r\n");
                } else if(column.split(":")[1].equals("BIGINT")) {
                    sb.append("\tprivate long "+column.split(":")[0]+";\r\n");
                } else if(column.split(":")[1].equals("DOUBLE")) {
                    sb.append("\tprivate double "+column.split(":")[0]+";\r\n");
                } else if(column.split(":")[1].equals("REAL")) {
                    sb.append("\tprivate float "+column.split(":")[0]+";\r\n");
                } else if(column.split(":")[1].equals("FLOAT")) {
                    sb.append("\tprivate float "+column.split(":")[0]+";\r\n");
                }
            }
            
            sb.append("\r\n");
            
            for(String column : struct) {
                sb.append(generateGetterSetter(column));
            } 
            
            sb.append("}\r\n");

            String code = sb.toString();

            File modelFile = new File(modelFolder.getAbsolutePath(),modelName+".java");
            pw = new PrintWriter(modelFile);
            
            pw.println(code);
            pw.flush();
            
            pw.close();
            
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBGenerateUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        
        return false;
    }
    
    public static String createModelName(String tableName) {
        tableName = Character.toTitleCase(tableName.charAt(0))+""+tableName.substring(1);
        
        if(tableName.endsWith("s")) {
            tableName = tableName.substring(0,tableName.length()-1);
        }
        
        return tableName;
    }
    
    public static String generateGetterSetter(String column) {
        
        StringBuilder sb = new StringBuilder("");
        
        if(column.split(":")[1].equals("VARCHAR")) {
            sb.append("\tpublic String get"+column.split(":")[0]+"() {\r\n");
            sb.append("\t\treturn this."+column.split(":")[0]+";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
            sb.append("\tpublic void set"+column.split(":")[0]+"(String "+column.split(":")[0]+") {\r\n");
            sb.append("\t\tthis."+column.split(":")[0]+"="+column.split(":")[0]+";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
        } else if(column.split(":")[1].equals("INT")) {
            sb.append("\tpublic int get"+column.split(":")[0]+"() {\r\n");
            sb.append("\t\treturn this."+column.split(":")[0]+";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
            sb.append("\tpublic void set"+column.split(":")[0]+"(int "+column.split(":")[0]+") {\r\n");
            sb.append("\t\tthis."+column.split(":")[0]+"="+column.split(":")[0]+";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
        } else if(column.split(":")[1].equals("BIGINT")) {
            sb.append("\tpublic long get"+column.split(":")[0]+"() {\r\n");
            sb.append("\t\treturn this."+column.split(":")[0]+";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
            sb.append("\tpublic void set"+column.split(":")[0]+"(long "+column.split(":")[0]+") {\r\n");
            sb.append("\t\tthis."+column.split(":")[0]+"="+column.split(":")[0]+";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
        } else if(column.split(":")[1].equals("DOUBLE")) {
            sb.append("\tpublic double get"+column.split(":")[0]+"() {\r\n");
            sb.append("\t\treturn this."+column.split(":")[0]+";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
            sb.append("\tpublic void set"+column.split(":")[0]+"(double "+column.split(":")[0]+") {\r\n");
            sb.append("\t\tthis."+column.split(":")[0]+"="+column.split(":")[0]+";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
        } else if(column.split(":")[1].equals("REAL")) {
            sb.append("\tpublic float get"+column.split(":")[0]+"() {\r\n");
            sb.append("\t\treturn this."+column.split(":")[0]+";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
            sb.append("\tpublic void set"+column.split(":")[0]+"(float "+column.split(":")[0]+") {\r\n");
            sb.append("\t\tthis."+column.split(":")[0]+"="+column.split(":")[0]+";\r\n");
            sb.append("\t}\r\n");
            sb.append("\r\n");
        }
        
        return sb.toString();
    }

    private static boolean generateDBUtils(List<String[]> tables, File dbFolder,String packageName) {
        PrintWriter pw = null;
        try {
            File dbUtilsFile = new File(dbFolder.getAbsolutePath(),"DBUtils.java");
            final StringBuilder sb = new StringBuilder();
            sb.append("package "+packageName+".db;\r\n");
            sb.append("\r\n");
            sb.append("import com.mysql.jdbc.Driver;\r\n");
            sb.append("import java.sql.DriverManager;\r\n");
            sb.append("import java.sql.Connection;\r\n");
            sb.append("import java.sql.Statement;\r\n");
            sb.append("import java.sql.ResultSet;\r\n");
            sb.append("\r\n");
            sb.append("import java.util.List;\r\n");
            sb.append("import java.util.ArrayList;\r\n");
            sb.append("import "+packageName+".model.*;\r\n");
            sb.append("\r\n\r\n");
            sb.append("public class DBUtils {\r\n");
            sb.append("\tpublic static final String url=\""+url+"\";\r\n");
            sb.append("\tpublic static Connection con=null;\r\n");
            sb.append("\r\n");
            sb.append("\tpublic static boolean connect() throws Exception {\r\n");
            sb.append("\t\tif(con==null || con.isClosed()){\r\n");
            sb.append("\t\t\tClass.forName(com.mysql.jdbc.Driver.class.getCanonicalName());\r\n");
            sb.append("\t\t\tcon = DriverManager.getConnection(url);\r\n");
            sb.append("\t\t\tif(con!=null && !con.isClosed()) {\r\n");
            sb.append("\t\t\t\treturn true;\r\n");
            sb.append("\t\t\t} else {\r\n");
            sb.append("\t\t\t\treturn false;\r\n");
            sb.append("\t\t\t}\r\n");
            sb.append("\t\t} else {\r\n");
            sb.append("\t\t\treturn false;\r\n");
            sb.append("\t\t}\r\n");
            sb.append("\t}\r\n\r\n");
            
            
            tables.stream().skip(1).map(t->t[2]).forEach(t->{
                sb.append(generateSelect(t));
                sb.append("\r\n");
                sb.append(generateInsert(t));
                sb.append("\r\n");
                sb.append(generateDelete(t));
                sb.append("\r\n");
            });
            
            sb.append("}\r\n");
            
            pw = new PrintWriter(dbUtilsFile);
            pw.println(sb.toString());
            pw.flush();
            pw.close();
            
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBGenerateUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
        
        return false;
    }

    private static String generateSelect(String t) {
        final String[] struct = getTableStructure(t);
        
        final String modelName = createModelName(t);
        
        final StringBuilder sb = new StringBuilder();
        
        sb.append("\tpublic static List<"+modelName+"> get"+modelName+"s() throws Exception {\r\n");
        sb.append("\t\tfinal List<"+modelName+"> "+modelName.toLowerCase()+"s = new ArrayList<"+modelName+">();\r\n");
        sb.append("\t\tfinal String sql = \"select * from "+t+"\";\r\n\r\n");
        sb.append("\t\tfinal Statement stmt = con.createStatement();\r\n");
        sb.append("\t\tfinal ResultSet rs = stmt.executeQuery(sql);\r\n\r\n");
        sb.append("\t\twhile(rs.next()){\r\n");
        sb.append("\t\t\t"+modelName+" "+modelName.toLowerCase()+" = new "+modelName+"();\r\n");
        for(String column : struct) {
            String columnname = column.split(":")[0];
            String type = column.split(":")[1];
            
            switch(type) {
                case "INT":
                    sb.append("\t\t\t"+modelName.toLowerCase()+".set"+columnname+"(rs.getInt(\""+columnname+"\"));\r\n");
                    break;
                case "BIGINT":
                    sb.append("\t\t\t"+modelName.toLowerCase()+".set"+columnname+"(rs.getLong(\""+columnname+"\"));\r\n");
                    break;
                case "DOUBLE":
                    sb.append("\t\t\t"+modelName.toLowerCase()+".set"+columnname+"(rs.getDouble(\""+columnname+"\"));\r\n");
                    break;
                case "REAL":
                    sb.append("\t\t\t"+modelName.toLowerCase()+".set"+columnname+"(rs.getFloat(\""+columnname+"\"));\r\n");
                    break;                    
                case "FLOAT":
                    sb.append("\t\t\t"+modelName.toLowerCase()+".set"+columnname+"(rs.getFloat(\""+columnname+"\"));\r\n");
                    break;                    
                case "VARCHAR":
                    sb.append("\t\t\t"+modelName.toLowerCase()+".set"+columnname+"(rs.getString(\""+columnname+"\"));\r\n");
                    break;
                default:
                    sb.append("\t\t\t"+modelName.toLowerCase()+".set"+columnname+"(rs.getString(\""+columnname+"\"));\r\n");
                    break;
            }
        }
        
        sb.append("\t\t\t"+modelName.toLowerCase()+"s.add("+modelName.toLowerCase()+");\r\n");
        sb.append("\t\t}\r\n");
        sb.append("\t\treturn "+modelName.toLowerCase()+"s;\r\n");
        sb.append("\t}\r\n");

        return sb.toString();
    }

    private static String generateInsert(String t) {
        final String[] struct = getTableStructure(t);
        
        final String modelName = createModelName(t);
        
        StringBuilder sb = new StringBuilder();
        
        final String columns = Arrays.stream(struct).skip(1).map(s->s.split(":")[0]).reduce((c1,c2)->c1+","+c2).get();
        
        sb.append("\tpublic static boolean add"+modelName+"("+modelName+" "+modelName.toLowerCase()+") throws Exception {\r\n");
        sb.append("\t\tfinal String sql = \"");
        sb.append("INSERT INTO "+t+" ("+columns+") VALUES("+(struct[1].split(":")[1].equals("VARCHAR")?"'\"+":"\"+"));
        
        String prevType = null;
        for(int i=1;i<struct.length;i++) {
            String column = struct[i];
            String columnname = column.split(":")[0];
            String type = column.split(":")[1];
            
            switch(type) {
                case "INT":
                    if(prevType==null)
                        sb.append(modelName.toLowerCase()+".get"+columnname+"()+\",");
                    else if(prevType.equals("INT") || prevType.equals("BIGINT") || prevType.equals("DOUBLE") || prevType.equals("REAL") || prevType.equals("FLOAT")) {
                        sb.append("\"+"+modelName.toLowerCase()+".get"+columnname+"()+\",");
                    } else if(prevType.equals("VARCHAR")) {
                        sb.append("\"+"+modelName.toLowerCase()+".get"+columnname+"()+\",");
                    }
                    break;
                case "BIGINT":
                    if(prevType==null) {
                        sb.append(modelName.toLowerCase()+".get"+columnname+"()+\",");
                    }else if(prevType.equals("INT") || prevType.equals("BIGINT") || prevType.equals("DOUBLE") || prevType.equals("REAL") || prevType.equals("FLOAT")) {
                        sb.append("\"+"+modelName.toLowerCase()+".get"+columnname+"()+\",");
                    } else if(prevType.equals("VARCHAR")) {
                        sb.append("\"+"+modelName.toLowerCase()+".get"+columnname+"()+\",");
                    }
                    break;
                case "DOUBLE":
                    if(prevType==null) {
                        sb.append(modelName.toLowerCase()+".get"+columnname+"()+\",");
                    }else if(prevType.equals("INT") || prevType.equals("BIGINT") || prevType.equals("DOUBLE") || prevType.equals("REAL") || prevType.equals("FLOAT")) {
                        sb.append("\"+"+modelName.toLowerCase()+".get"+columnname+"()+\",");
                    } else if(prevType.equals("VARCHAR")) {
                        sb.append("\"+"+modelName.toLowerCase()+".get"+columnname+"()+\",");
                    }
                    break;
                case "FLOAT":
                    if(prevType==null) {
                        sb.append(modelName.toLowerCase()+".get"+columnname+"()+\",");
                    }else if(prevType.equals("INT") || prevType.equals("BIGINT") || prevType.equals("DOUBLE") || prevType.equals("REAL") || prevType.equals("FLOAT")) {
                        sb.append("\"+"+modelName.toLowerCase()+".get"+columnname+"()+\",");
                    } else if(prevType.equals("VARCHAR")) {
                        sb.append("\"+"+modelName.toLowerCase()+".get"+columnname+"()+\",");
                    }
                    break;
                case "REAL":
                    if(prevType==null) {
                        sb.append(modelName.toLowerCase()+".get"+columnname+"()+\",");
                    }else if(prevType.equals("INT") || prevType.equals("BIGINT") || prevType.equals("DOUBLE") || prevType.equals("REAL") || prevType.equals("FLOAT")) {
                        sb.append("\"+"+modelName.toLowerCase()+".get"+columnname+"()+\",");
                    } else if(prevType.equals("VARCHAR")) {
                        sb.append("\"+"+modelName.toLowerCase()+".get"+columnname+"()+\",");
                    }
                    break;
                case "VARCHAR":
                    if(prevType==null)
                        sb.append(modelName.toLowerCase()+".get"+columnname+"()+\"',");
                    else if(prevType.equals("VARCHAR"))
                        sb.append("'\"+"+modelName.toLowerCase()+".get"+columnname+"()+\"',");
                    else if(prevType.equals("INT") || prevType.equals("BIGINT") || prevType.equals("DOUBLE") || prevType.equals("REAL") || prevType.equals("FLOAT")) {
                        sb.append("'\"+"+modelName.toLowerCase()+".get"+columnname+"()+\"',");
                    }
                    break;
                default:
                    if(prevType==null)
                        sb.append(modelName.toLowerCase()+".get"+columnname+"()+\"',");
                    else if(prevType.equals("VARCHAR"))
                        sb.append("'\"+"+modelName.toLowerCase()+".get"+columnname+"()+\"',");
                    else if(prevType.equals("INT") || prevType.equals("BIGINT") || prevType.equals("DOUBLE") || prevType.equals("REAL") || prevType.equals("FLOAT")) {
                        sb.append("'\"+"+modelName.toLowerCase()+".get"+columnname+"()+\"',");
                    }
                    break;
            }
            
            prevType = type;
        }
        
        if(sb.toString().endsWith(",")) {
            sb = new StringBuilder(sb.toString().subSequence(0, sb.toString().length()-1));
        }
        
        sb.append(")\";\r\n");
        sb.append("\t\tfinal Statement stmt = con.createStatement();\r\n");
        sb.append("\t\tif(stmt.executeUpdate(sql)>0) {\r\n");
        sb.append("\t\t\treturn true;\r\n");
        sb.append("\t\t} else {\r\n");
        sb.append("\t\t\treturn false;\r\n");
        sb.append("\t\t}\r\n");
        sb.append("\t}\r\n");
        
        return sb.toString();
    }

    private static String generateDelete(String t) {
        final String[] struct = getTableStructure(t);
        
        final String modelName = createModelName(t);
        
        StringBuilder sb = new StringBuilder();
        
        final String column = struct[0];
        
        sb.append("\tpublic static boolean remove"+modelName+"("+modelName+" "+modelName.toLowerCase()+") throws Exception {\r\n");
        sb.append("\t\tfinal String sql = \"DELETE FROM "+t+" WHERE "+column.split(":")[0]+"="+(column.split(":")[1].equals("VARCHAR")?"'\"+":"\"+")+modelName.toLowerCase()+".get"+column.split(":")[0]+"()"+(column.split(":")[1].equals("VARCHAR")?"'\"":"")+";\r\n");
        sb.append("\t\tfinal Statement stmt = con.createStatement();\r\n");
        sb.append("\t\tif(stmt.executeUpdate(sql)>0) {\r\n");
        sb.append("\t\t\treturn true;\r\n");
        sb.append("\t\t} else { \r\n");
        sb.append("\t\t\treturn false;\r\n");
        sb.append("\t\t}\r\n");
        sb.append("\t}\r\n");
        
        return sb.toString();
    }
}
