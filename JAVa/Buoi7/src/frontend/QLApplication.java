package frontend;

import java.util.Date;
import java.util.Scanner;

import backend.QLAccountImpl;
import backend.QLDepartmentImpl;
import backend.QLPositionImpl;
import models.Account;
import models.ConnectionDetail;
import models.Department;
import models.Position;

public class QLApplication {
    QLAccountImpl qlAccount;
    QLDepartmentImpl qlDepartment;
    QLPositionImpl qlPosition;
    ConnectionDetail connectionDetail;

    public QLApplication(ConnectionDetail connectionDetail) {
        qlAccount = new QLAccountImpl(connectionDetail);
        qlDepartment = new QLDepartmentImpl(connectionDetail);
        qlPosition = new QLPositionImpl(connectionDetail);
    }

    private int menu() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (choice <= 0 || choice >= 4) {
            try {
                System.out.println("ADP MANAGEMENT:");
                System.out.println("1. Show all accounts.");
                System.out.println("2. Show all departments.");
                System.out.println("3. Show all postion.");
                System.out.println("4. Insert 1 account");
                System.out.println("5. Insert 1 department");
                System.out.println("6. Insert 1 position");
                System.out.println("7. Update 1 account");
                System.out.println("8. Update 1 department");
                System.out.println("9. Update 1 position");
                System.out.println("10. Delete 1 account");
                System.out.println("11. Delete 1 department");
                System.out.println("12. Delete 1 position");
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        sc.close();
        return choice;
    }

    public void mainApplication() {
        Scanner sc = new Scanner(System.in);
        switch (menu()) {
            case 1:
                System.out.println(
                        "+--------------------+--------------------+--------------------+--------------------+--------------------+");
                System.out.printf("|%25s|%25s|%25s|%25s|%25s|\n", "Id", "Email", "Username", "Fullname", "Create date");
                System.out.println(
                        "+--------------------+--------------------+--------------------+--------------------+--------------------+");

                for (Account account : qlAccount.displayAccount()) {
                    System.out.printf("|%25d|%25s|%25s|%25s|%25s|\n",
                            account.getAccountId(),
                            account.getEmail(),
                            account.getUserName(),
                            account.getFullName(),
                            account.getCreateDate().toString());
                }
                System.out.println(
                        "+--------------------+--------------------+--------------------+--------------------+--------------------+");
                break;
            case 2:
                System.out.println(
                        "+--------------------+--------------------+");
                System.out.printf("|%25s|%25s|\n", "Id", "Department Name");
                System.out.println(
                        "+--------------------+--------------------+");

                for (Department department : qlDepartment.displayDepartment()) {
                    System.out.printf("|%25d|%25s|\n",
                            department.getDepartmentId(),
                            department.getDepartmentName());
                }
                System.out.println(
                        "+--------------------+--------------------+");
                break;
            case 3:
                System.out.println(
                        "+--------------------+--------------------+");
                System.out.printf("|%25s|%25s|\n", "Id", "Position Name");
                System.out.println(
                        "+--------------------+--------------------+");

                for (Position position : qlPosition.displayPosition()) {
                    System.out.printf("|%25d|%25s|\n",
                            position.getPositionId(),
                            position.getPositionName());
                }
                System.out.println(
                        "+--------------------+--------------------+");
                break;
            case 4:
                System.out.println("Insert Account:");
                System.out.println("Account Id: ");
                int accountId = sc.nextInt();
                sc.nextLine();
                System.out.println("Email: ");
                String email = sc.nextLine();
                System.out.println("Username: ");
                String username = sc.nextLine();
                System.out.println("Fullname: ");
                String fullname = sc.nextLine();
                qlAccount.insertAccount(accountId, email, username, fullname);
                break;
            case 5:
                System.out.println("Insert department:");
                System.out.println("Department Id: ");
                int departmentId = sc.nextInt();
                sc.nextLine();
                System.out.println("Department Name: ");
                String departmentName = sc.nextLine();
                qlDepartment.insertDepartment(departmentId, departmentName);
                break;
            case 6:
                System.out.println("Insert position:");
                System.out.println("Position Id: ");
                int positionId = sc.nextInt();
                sc.nextLine();
                System.out.println("Position Name: ");
                String positionName = sc.nextLine();
                qlDepartment.insertDepartment(positionId, positionName);
                break;
            case 7:
                System.out.println("Update Account:");
                System.out.println("Account Id: ");
                accountId = sc.nextInt();
                sc.nextLine();
                System.out.println("Email: ");
                email = sc.nextLine();
                System.out.println("Username: ");
                username = sc.nextLine();
                System.out.println("Fullname: ");
                fullname = sc.nextLine();
                Account updateAccount = new Account(accountId, email, username, fullname, new Date());
                qlAccount.updateAccount(accountId, updateAccount);
                break;
            case 8:
                System.out.println("Update department:");
                System.out.println("Department Id: ");
                departmentId = sc.nextInt();
                sc.nextLine();
                System.out.println("Department Name: ");
                departmentName = sc.nextLine();
                qlDepartment.updateDepartment(departmentId, departmentName);
                break;
            case 9:
                System.out.println("Update position:");
                System.out.println("Position Id: ");
                positionId = sc.nextInt();
                sc.nextLine();
                System.out.println("Position Name: ");
                positionName = sc.nextLine();
                qlPosition.updatePosition(positionId, positionName);
                break;
            case 10:
                System.out.println("Delete Account:");
                System.out.println("Account Id: ");
                accountId = sc.nextInt();
                sc.nextLine();
                qlAccount.deleteAccount(accountId);
                break;
            case 11:
                System.out.println("Delete Department:");
                System.out.println("Department Id: ");
                departmentId = sc.nextInt();
                sc.nextLine();
                qlDepartment.deleteDepartment(departmentId);
                break;
            case 12:
                System.out.println("Delete Position:");
                System.out.println("Position Id: ");
                positionId = sc.nextInt();
                sc.nextLine();
                qlPosition.deletePosition(positionId);
                sc.close();
                break;

            default:
                break;
            
        }
    }
}
