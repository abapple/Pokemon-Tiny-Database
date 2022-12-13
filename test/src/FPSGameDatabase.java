import java.io.InputStream;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.InputMismatchException;

import java.util.Queue;

import java.util.Scanner;

public class FPSGameDatabase {

  public static void main(String[] args) {

    Connection connection = null;

    try {

      // create a database connection

      connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

      Statement statement = connection.createStatement();

      statement.setQueryTimeout(30); // set timeout to 30 sec.

      // POKEINFO table

      statement.executeUpdate("drop table if exists POKEINFO");

      statement.executeUpdate(

          "create table POKEINFO (Name string, NationalNum integer, Species string, PRIMARY KEY(NationalNum))");

      statement.executeUpdate("insert into POKEINFO values('Bulbasaur', 1, 'Seed')");

      statement.executeUpdate("insert into POKEINFO values('Evee', 133, 'Evolution')");

      statement.executeUpdate("insert into POKEINFO values('Flareon', 36, 'Flame')");

      statement.executeUpdate("insert into POKEINFO values('Vaporeon', 134, 'Bubble Jet')");

      statement.executeUpdate("insert into POKEINFO values('Mew', 151, 'New Species')");

      statement.executeUpdate("insert into POKEINFO values('Rhyhorn', 111, 'Spikes')");

      statement.executeUpdate("insert into POKEINFO values('Kadabra', 64, 'Psi')");

      statement.executeUpdate("insert into POKEINFO values('Nidorina', 30, 'Poison Pin')");

      /*
       * 
       * ResultSet rs = statement.executeQuery("select * from POKEINFO");
       * 
       * while(rs.next()) // read the result set
       * 
       * {
       * 
       * System.out.println("Name = " + rs.getString("Name"));
       * 
       * System.out.println("NationalNum = " + rs.getInt("NationalNum"));
       * 
       * System.out.println("Species = " + rs.getString("Species"));
       * 
       * }
       * 
       */

      // outputDB(statement, "Name", "NationalNum", "Species", "POKEINFO");

      // POKETYPE Table

      statement.executeUpdate("drop table if exists POKETYPE");

      statement.executeUpdate("create table POKETYPE (Name string, Type string, PRIMARY KEY(Name, Type), "

          + "FOREIGN KEY(Name) REFERENCES POKEINFO(Name) ON UPDATE CASCADE ON DELETE CASCADE)");

      statement.executeUpdate("insert into POKETYPE values('Bulbasaur','Grass')");

      statement.executeUpdate("insert into POKETYPE values('Bulbasaur','Poison')");

      statement.executeUpdate("insert into POKETYPE values('Evee', 'Normal')");

      statement.executeUpdate("insert into POKETYPE values('Flareon', 'Fire')");

      statement.executeUpdate("insert into POKETYPE values('Vaporeon', 'Water')");

      statement.executeUpdate("insert into POKETYPE values('Mew', 'Psychic')");

      statement.executeUpdate("insert into POKETYPE values('Rhyhorn', 'Ground')");

      statement.executeUpdate("insert into POKETYPE values('Rhyhorn', 'Rock')");

      statement.executeUpdate("insert into POKETYPE values('Kadabra', 'Psychic')");

      statement.executeUpdate("insert into POKETYPE values('Nidorina','Poison')");

      // outputDB(statement, "Name", "Type", "", "POKETYPE");

      // POKEABILITIES Table

      statement.executeUpdate("drop table if exists POKEABILITIES");

      statement

          .executeUpdate("create table POKEABILITIES (Name string, Abilities string, PRIMARY KEY(Name, Abilities),"

              + "FOREIGN KEY(Name) REFERENCES POKEINFO(Name) ON UPDATE CASCADE ON DELETE CASCADE)");

      statement.executeUpdate("insert into POKEABILITIES values('Bulbasaur','Overgrow')");

      statement.executeUpdate("insert into POKEABILITIES values('Bulbasaur','Chlorophyll')");

      statement.executeUpdate("insert into POKEABILITIES values('Evee', 'Run Away')");

      statement.executeUpdate("insert into POKEABILITIES values('Evee', 'Adaptability')");

      statement.executeUpdate("insert into POKEABILITIES values('Evee', 'Anticipation')");

      statement.executeUpdate("insert into POKEABILITIES values('Flareon', 'Flash Fire')");

      statement.executeUpdate("insert into POKEABILITIES values('Flareon', 'Guts')");

      statement.executeUpdate("insert into POKEABILITIES values('Vaporeon', 'Water Absorb')");

      statement.executeUpdate("insert into POKEABILITIES values('Vaporeon', 'Hydration')");

      statement.executeUpdate("insert into POKEABILITIES values('Mew', 'Synchronize')");

      statement.executeUpdate("insert into POKEABILITIES values('Rhyhorn', 'Lightning Rod')");

      statement.executeUpdate("insert into POKEABILITIES values('Rhyhorn', 'Rock Head')");

      statement.executeUpdate("insert into POKEABILITIES values('Rhyhorn', 'Reckless')");

      statement.executeUpdate("insert into POKEABILITIES values('Kadabra', 'Synchronize')");

      statement.executeUpdate("insert into POKEABILITIES values('Kadabra', 'Magic Guard')");

      statement.executeUpdate("insert into POKEABILITIES values('Nidorina','Poison Point')");

      statement.executeUpdate("insert into POKEABILITIES values('Nidorina','Rivalry')");

      statement.executeUpdate("insert into POKEABILITIES values('Nidorina','Hustle')");

      // outputDB(statement, "Name", "Abilities", "", "POKEABILITIES");

      // EVOLUTIONS table

      statement.executeUpdate("drop table if exists EVOLUTIONS");

      statement.executeUpdate(

          "create table EVOLUTIONS (Name string, PreviousEvolution string, NextEvolution string, PRIMARY KEY(Name, NextEvolution),"

              + "FOREIGN KEY(Name) REFERENCES POKEINFO(Name) ON UPDATE CASCADE ON DELETE CASCADE)");

      statement.executeUpdate("insert into EVOLUTIONS values('Bulbasaur', '', 'Ivysaur')");

      statement.executeUpdate("insert into EVOLUTIONS values('Evee', '', 'Vaporeon')");

      statement.executeUpdate("insert into EVOLUTIONS values('Evee', '',  'Jolteon')");

      statement.executeUpdate("insert into EVOLUTIONS values('Evee', '',  'Flareon')");

      statement.executeUpdate("insert into EVOLUTIONS values('Evee', '',  'Espeon')");

      statement.executeUpdate("insert into EVOLUTIONS values('Evee', '',  'Umbreon')");

      statement.executeUpdate("insert into EVOLUTIONS values('Evee', '',  'Leafeon')");

      statement.executeUpdate("insert into EVOLUTIONS values('Evee', '',  'Glaceon')");

      statement.executeUpdate("insert into EVOLUTIONS values('Evee', '',  'Sylveon')");

      statement.executeUpdate("insert into EVOLUTIONS values('Flareon', 'Evee', '')");

      statement.executeUpdate("insert into EVOLUTIONS values('Vaporeon', 'Evee', '')");

      statement.executeUpdate("insert into EVOLUTIONS values('Mew', '', '')");

      statement.executeUpdate("insert into EVOLUTIONS values('Rhyhorn', '', 'Rhydon')");

      statement.executeUpdate("insert into EVOLUTIONS values('Kadabra', 'Abra', 'Alakazam')");

      statement.executeUpdate("insert into EVOLUTIONS values('Nidorina','Nidoran', 'Nidoqueen')");

      // outputDB(statement, "Name", "PreviousEvolution", "NextEvolution",

      // "EVOLUTIONS");

      PokeOption(statement, connection);

      outputDB(statement, "Name", "NationalNum", "Species", "POKEINFO");

      outputDB(statement, "Name", "PreviousEvolution", "NextEvolution", "EVOLUTIONS");

      outputDB(statement, "Name", "Abilities", "", "POKEABILITIES");

      outputDB(statement, "Name", "Type", "", "POKETYPE");

    } catch (SQLException e) {

      // Error message "out of memory", probably means no database file found

      System.err.println(e.getMessage());

    } finally {

      try {

        if (connection != null)

          connection.close();

      } catch (SQLException e) {

        System.err.println(e.getMessage()); // connection close failed.

      }

    }

  }

  public static void outputDB(Statement statement, String col1, String col2, String col3, String table) {

    try {

      ResultSet rs = statement.executeQuery("select * from " + table);

      System.out.printf("%-19s%-19s%-19s\n", col1, col2, col3);

      while (rs.next()) // read the result set

      {

        // 10 spaces between column names

        if (!col3.equals("")) {

          String x = rs.getString(col1), z = rs.getString(col3);

          if (rs.getInt(col2) != 0) {

            Integer y = rs.getInt(col2);

            System.out.printf("%-19s%-19s%-19s\n", x, y, z);

          } else {

            String y = rs.getString(col2);

            System.out.printf("%-19s%-19s%-19s\n", x, y, z);

          }

        } else {

          String x = rs.getString(col1);

          String y = rs.getString(col2);

          System.out.printf("%-19s%-19s\n", x, y);

        }

        // spacing code from user Sabuj Hassan on StackOverflow

      }

    } catch (SQLException e) {

      // Error message "out of memory", probably means no database file found

      System.err.println(e.getMessage());

    }

    System.out.println();

  }

  public static void PokeOption(Statement statement, Connection connection) {

    // Giving Users choice

    Scanner sc = new Scanner(System.in);

    System.out.println("Hello! Welcome to the Pokemon Pokedex (MINI EDITION)!");

    while (true) {

      System.out.println();

      System.out.println("What action would you like to take?");

      System.out.println("Please enter the number of the option you'd like:");

      System.out.println("1. List Pokemon Names\n2. List Pokemon Specific Data\n3. List Pokemon Types\n" +

          "4. List Pokemon of a Specific Type\n5. Delete a Pokemon\n6. Add a Pokemon\n7. Update Pokemon Info\n8. Exit\n");

      int choice = 0;

      while (true) {

        // sc.nextLine();

        String thing;

        thing = sc.next();

        try {

          choice = Integer.parseInt(thing);

          break;

        } catch (NumberFormatException e) {

          System.out.println("Input a number");

        }

      }

      if (choice == 8) {

        break;

      }

      try {

        switch (choice) {

          case 1: // List Pokemon Names

          {

            ResultSet rs = statement.executeQuery("select Name from POKEINFO");

            System.out.println("Name");

            while (rs.next()) // read the result set

            {

              System.out.println(rs.getString("Name"));

            }

            rs.close();

            break;

          }

          case 2:// List Pokemon Specific Data

          {

            System.out.println("So you want to know a pokemons data? Which Pokemon?");

            ResultSet rs = statement.executeQuery("select Name from POKEINFO");

            System.out.println("Name");

            while (rs.next()) // read the result set

            {

              System.out.println(rs.getString("Name"));

            }

            System.out.println();

            String pokemon = sc.next();

            System.out.println();

            String varname1 = ""

                + "select POKEINFO.Name,  POKEINFO.NationalNum, POKETYPE.Type, POKEINFO.Species, POKEABILITIES.Abilities, EVOLUTIONS.NextEvolution,EVOLUTIONS.PreviousEvolution "

                + "from ((POKEINFO JOIN POKETYPE "

                + "ON POKEINFO.Name = POKETYPE.Name) "

                + "JOIN POKEABILITIES "

                + "ON POKETYPE.Name = POKEABILITIES.Name) "

                + "JOIN EVOLUTIONS "

                + "ON POKETYPE.Name = EVOLUTIONS.Name "

                + "WHERE POKEINFO.Name LIKE '" + pokemon + "'";

            rs = statement.executeQuery(varname1);

            String a = "Name", b = "NationalNum", c = "Type", d = "Species", e = "Abilities", f = "NextEvolution",

                g = "PreviousEvolution";

            System.out.printf("%-19s%-19s%-19s%-19s%-19s%-19s%-19s\n", a, b, c, d, e, f, g);

            while (rs.next()) // read the result set

            {

              String a1 = rs.getString(a), c1 = rs.getString(c), d1 = rs.getString(d), e1 = rs.getString(e),

                  f1 = rs.getString(f), g1 = rs.getString(g);

              int b1 = rs.getInt(b);

              System.out.printf("%-19s%-19s%-19s%-19s%-19s%-19s%-19s\n", a1, b1, c1, d1, e1, f1, g1);

            }

            rs.close();

            break;

          }

          case 3: // List all pokemon Types

          {

            ResultSet rs = statement.executeQuery("select distinct Type from POKETYPE");

            System.out.println("Type");

            while (rs.next()) // read the result set

            {

              System.out.println(rs.getString("Type"));

            }

            rs.close();

            break;

          }

          case 4: // List pokemon of a specific type

          {

            System.out.println("Which type of Pokemon would you like to see?");

            ResultSet rs = statement.executeQuery("select distinct Type from POKETYPE");

            while (rs.next()) // read the result set

            {

              System.out.println(rs.getString("Type"));

            }

            System.out.println();

            String type = sc.next();

            System.out.println();

            rs = statement.executeQuery("select Name from POKETYPE where Type LIKE '" + type + "'");

            System.out.println("Name");

            while (rs.next()) // read the result set

            {

              System.out.println(rs.getString("Name"));

            }

            rs.close();

            break;

          }

          case 5: // Delete a pokemon

          {

            System.out.println("So you want to delete a pokemons data? Which Pokemon?");

            ResultSet rs = statement.executeQuery("select Name from POKEINFO");

            System.out.println("Name");

            while (rs.next()) // read the result set

            {

              System.out.println(rs.getString("Name"));

            }

            System.out.println();

            String pokemon = sc.next();

            System.out.println();

            String delete = ""

                + "delete from POKEINFO "

                + "WHERE Name LIKE '" + pokemon + "'";

            statement.executeUpdate(delete);

            delete = ""

                + "delete from POKETYPE "

                + "WHERE Name LIKE '" + pokemon + "'";

            statement.executeUpdate(delete);

            delete = ""

                + "delete from POKEABILITIES "

                + "WHERE Name LIKE '" + pokemon + "'";

            statement.executeUpdate(delete);

            delete = ""

                + "delete from EVOLUTIONS "

                + "WHERE Name LIKE '" + pokemon + "'";

            statement.executeUpdate(delete);

            rs.close();

            // try {

            // Connection con = connection;

            // Statement s = con.createStatement();

            // s.execute(delete);

            // } catch (Exception ex) {

            // ex.printStackTrace();

            // }

            break;

          }

          case 6: // add a pokemon

          {

            System.out.println(

                "Please enter the Pokemon's information separated by commas with:\n Name, NationalNum, Species, Type / Type, Abilities / Abilities"

                    + ", Previous Evolution, Next Evolution");

            System.out.println(

                "Please leave spaces as above, including single space null values; limit of 4 abilities and types.");

            sc.nextLine();

            String values = sc.nextLine();

            // System.out.println(values);

            // String[] arr = values.split(",");

            // make sure its possible

            String split[] = values.split(", ");

            // for (String a : arr){

            // System.out.println(a);

            // split[].add(a);

            // }

            // pokeinfo

            if (split.length < 7) {

              System.out.println("Please check your formatting");

            } else {

              try {

                int num = Integer.parseInt(split[1]);

              } catch (NumberFormatException e) {

                System.out.println("NationalNum must be a number.");

                break;

              }

              String query = "insert into POKEINFO (Name, NationalNum, Species) values ('" + split[0] + "','"

                  + Integer.valueOf(split[1]) + "','" + split[2] + "')";

              statement.executeUpdate(query);

              // poketype

              String[] types = split[3].split(" / ");

              for (int i = 0; types.length > i; i++) {

                // for each new type is a new row inserted into table

                query = "insert into POKETYPE (Name, Type) values('" + split[0] + "','" + types[i] + "')";

                statement.executeUpdate(query);

              }

              // pokeabilities

              String[] abilities = split[4].split(" / ");

              for (int i = 0; abilities.length > i; i++) {

                // for each new ability is a new row inserted into table

                query = "insert into POKEABILITIES (Name, Abilities) values('" + split[0] + "','" + abilities[i] + "')";

                statement.executeUpdate(query);

              }

              // evolutions

              query = "insert into EVOLUTIONS (Name, PreviousEvolution, NextEvolution) values ('" + split[0] + "','"

                  + split[5] + "','" + split[6] + "')";

              statement.executeUpdate(query);

            }

            break;

          }

          case 7:// Update pokemon Info

          {

            System.out.println("which Pokemon would you like to update?");

            ResultSet rs = statement.executeQuery("select Name from POKEINFO");

            System.out.println("Name");

            while (rs.next()) // read the result set

            {

              System.out.println(rs.getString("Name"));

            }

            String name = sc.next();

            System.out.println("What information would you like to change?");

            System.out.println("Name\nNationalNum\nSpecies");

            String col = sc.next();

            System.out.println("What is your new value?");

            String value = sc.next();

            String query = ""

                + "UPDATE POKEINFO"

                + " SET " + col + " = '" + value + "'"

                + " WHERE Name LIKE '" + name + "'";

            statement.executeUpdate(query);

            rs.close();

            break;

          }

          default: {

            System.out.println("I'm sorry, that is not an option. Try again?");

            break;

          }

        }

      } catch (SQLException e) {

        System.err.println(e.getMessage());

      }

    }

    sc.close();

  }

}