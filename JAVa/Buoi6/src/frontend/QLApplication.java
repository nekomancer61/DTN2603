package frontend;

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
        qlAccount = new QLAccountImpl();
        qlDepartment = new QLDepartmentImpl();
        qlPosition = new QLPositionImpl();
        this.connectionDetail = connectionDetail;
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
        switch (menu()) {
            case 1:
                System.out.println(
                        "+--------------------+--------------------+--------------------+--------------------+--------------------+");
                System.out.printf("|%25s|%25s|%25s|%25s|%25s|\n", "Id", "Email", "Username", "Fullname", "Create date");
                System.out.println(
                        "+--------------------+--------------------+--------------------+--------------------+--------------------+");

                for (Account account : qlAccount.displayAccount(connectionDetail)) {
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

                for (Department department : qlDepartment.displayDepartment(connectionDetail)) {
                    System.out.printf("|%25d|%25s|\n",
                        department.getDepartmentId(),
                        department.getDepartmentName()
                    );
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

                for (Position position : qlPosition.displayPosition(connectionDetail)) {
                    System.out.printf("|%25d|%25s|\n",
                        position.getPositionId(),
                        position.getPositionName()
                    );
                }
                System.out.println(
                        "+--------------------+--------------------+");
                break;
            default:
                break;
        }
    }
}
