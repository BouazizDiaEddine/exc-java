import java.io.*;
import java.sql.*;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
public class main {
    public static void main(String[] args) {

 //this one is new branch



        String server = "192.168.30.230";
        String user = "audaxis";
        String pass = "audaxis";
        FTPClient ftpClient = new FTPClient();

        try{
            File myObj = new File("table_file.txt");
            PrintWriter writer = new PrintWriter("table_file.txt", "UTF-8");
            Class.forName("oracle.jdbc.driver.OracleDriver");



            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.30.14:1521:TEST",
                    "dia",
                    "admin");


            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);


            ResultSet rs=stmt.executeQuery("select * from ad_user");

            ResultSetMetaData rsmd = rs.getMetaData();

            int columnsNumber = rsmd.getColumnCount();
            int counter = 1;
            while (rs.next()) {

                for(int i = 1 ; i <= columnsNumber; i++){

                    System.out.print(rs.getString(i) + " ;"); //Print one element of a row
                    writer.print(rs.getString(i) + " ;");
                }

                System.out.println();//Move to the next line to print the next row.
                writer.println();
            }
            ftpClient.connect(server);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            InputStream inputStream = new FileInputStream(myObj);
            boolean done = ftpClient.storeFile("/home/audaxis/Formation_2022/diaeddine/table_file.txt", inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }
            writer.println("1111117 ;1000000 ;1000000 ;2010-03-09 13:36:59 ;100 ;2010-03-09 13:39:06 ;100 ;Pierre ;null ;5e0c7831afecea6a ;newone@audaxis.com ;null ;1000683 ;N ;psp@audaxis.com ;d7f2ac10a0321ab42c48267ccd969c98 ;1000553 ;null ;null ;null ;null ;null ;null ;null ;null ;null ;null ;null ;null ;null ;Y ;null ;E ;null ;pierre ;null ;null ;null ;N ;null ;");


            try {
                // FileReader fr = new java.io.FileReader(myObj);
                // BufferedReader br = new BufferedReader(fr);
                File fis=new File("new.txt");
                Scanner sc=new Scanner(fis);


                String strLine;
                ArrayList list = new ArrayList();

                while (sc.hasNextLine()) {

                    String row = sc.nextLine();
                    list.add(row);
                    System.out.println("row:"+ row);
                }

                sc.close();



                ResultSet rss = stmt.executeQuery("select ad_user_id from ad_user"); // Get your ResultSet from Database

                rss.last();                  // Place the record pointer onto the last row
                int count = rss.getRow();System.out.println("this is count"+count); // Get the row number (there's your count)
                rss.first();                 // Place the record pointer onto the first row for the while loop
                String[] myArray = new String[count]; // Declare and Initialize your array

                counter = 0; // Reset counter to 0 so as to act as a Index incrementer
// Iterate through the ResultSet and fill Array
                while (rss.next()) {
                    myArray[counter] = rss.getString(1);
                    System.out.println(myArray[counter]);
                    counter++;
                }

// See what's in Array...

                for (int i = 0; i < list.size(); i++) {
                    System.out.println("the list content is here"+list.get(i).toString());
                }

                for (int i = 0; i < list.size(); i++) {
                    System.out.println("this is the list size"+list.size());
                    strLine = list.get(i).toString();
                    String[] values = strLine.split(" ;");
                    for (int b=0 ;b< values.length;b++){
                        System.out.println("newvalue"+values[b]);
                    }

                    String  AD_USER_ID=values[0],
                            AD_CLIENT_ID=values[1],
                            AD_ORG_ID=values[2],
                            ISACTIVE=values[3],
                            CREATED=values[4],
                            CREATEDBY=values[5],
                            UPDATED=values[6],
                            UPDATEDBY=values[7],
                            NAME=values[8],
                            DESCRIPTION=values[9],
                            PASSWORD=values[10],
                            EMAIL=values[11],
                            SUPERVISOR_ID=values[12],
                            C_BPARTNER_ID=values[13],
                            PROCESSING=values[14],
                            EMAILUSER=values[15],
                            EMAILUSERPW=values[16],
                            C_BPARTNER_LOCATION_ID=values[17],
                            C_GREETING_ID=values[18],
                            TITLE=values[19],
                            COMMENTS=values[20],
                            PHONE=values[21],
                            PHONE2=values[22],
                            FAX=values[23],
                            LASTCONTACT=values[24],
                            LASTRESULT=values[25],
                            BIRTHDAY=values[26],
                            AD_ORGTRX_ID=values[27],
                            EMAILVERIFY=values[28],
                            C_JOB_ID=values[29],
                            EMAILVERIFYDATE=values[30],
                            ISFULLBPACCESS=values[31],
                            LDAPUSER=values[32],
                            NOTIFICATIONTYPE=values[33],
                            CONNECTIONPROFILE=values[34],
                            VALUE=values[35],
                            AD_TREE_MENUFAVORITE_ID=values[36],
                            AD_TREE_MENUNEW_ID=values[37],
                            BOUNCEDINFO=values[38],
                            ISEMAILBOUNCED=values[39],
                            LASTREGISTRATIONREMINDER=values[40];


                    System.out.println("THIS IS THE DATE WHEN IS WAS CREATED"+CREATED);

                    if(Arrays.asList(myArray).contains(AD_USER_ID)) {
                        System.out.println("this user already exists");
                    }else {
                        stmt.executeUpdate("insert into AD_USER values (" + AD_USER_ID + " ,'1000000' ,'1000000', 'Y' ,TO_DATE(' " + CREATED + "   ', 'yyyy-mm-dd hh:mi:ss') ,'100' ,TO_DATE('2010-03-09 12:39:06' ,'yyyy-mm-dd hh:mi:ss'), '100', 'Pierre' ,null, '5e0c7831afecea6a' , '" + EMAIL + "', null ,'1000683' ,'N' ,'psp@audaxis.com','d7f2ac10a0321ab42c48267ccd969c98' ,'1000553',null, null ,null, null ,null ,null, null, null ,null, null, null ,null ,null ,'Y' ,null, 'E',null ,'pierre' ,null ,null ,null ,'N' ,null )");
                        System.out.println("added the user with ;"+AD_USER_ID);
                    }


                }


            }catch(IOException e)
            {
                e.printStackTrace();
            }



            writer.close();
            con.close();





        }catch(Exception e){ System.out.println(e);}


    }
}