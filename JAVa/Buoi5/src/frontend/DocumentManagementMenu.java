package frontend;

import java.util.Scanner;

import backend.services.DocumentManagementService;

public class DocumentManagementMenu {
    DocumentManagementService service;

    public DocumentManagementMenu(DocumentManagementService service) {
        this.service = service;
    }

    private int menu() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (choice <= 0 || choice >= 4) {
            try {
                System.out.println("DOCUMENT MANAGEMENT:");
                System.out.println("1. Thêm mới tài liêu: Sách, tạp chí, báo.");
                System.out.println("2. Xoá tài liệu theo mã tài liệu.");
                System.out.println("3. Hiện thị thông tin về tài liệu.");
                System.out.println("4. Tìm kiếm tài liệu theo loại: Sách, tạp chí, báo.");

                    choice = sc.nextInt();
                    sc.nextLine();
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        sc.close();
        return choice;
    }

    private int getDocumentTypeInput() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (true) {
            try {
                System.out.println("Choose 1 type of document:");
                System.out.println("1. Book");
                System.out.println("2. Magazine");
                System.out.println("3. Newspaper");

                choice = sc.nextInt();
                if (choice > 0 && choice < 4)
                    break;
                sc.nextLine();

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        sc.close();
        return choice;
    }

    public void documentManagement() {
        Scanner sc = new Scanner(System.in);
        switch (menu()) {
            case 1:
                switch (getDocumentTypeInput()) {
                    case 1:
                        // book
                        int documentId;
                        while (true) {
                            System.out.println("Input documentId:");
                                documentId = sc.nextInt();
                                sc.nextLine();
                                break;
                        }

                        String publisher;
                        System.out.println("Input publisher:");
                        publisher = sc.nextLine();

                        int publishAmount;
                        while (true) {
                            System.out.println("Input publishAmount: ");
                                publishAmount = sc.nextInt();
                                sc.nextLine();
                                if (publishAmount >= 0) {
                                    break;
                            }
                        }

                        String author;
                        System.out.println("Input author: ");
                        author = sc.nextLine();

                        int pageNumber;
                        while (true) {
                            System.out.println("Input pageNumber: ");
                                pageNumber = sc.nextInt();
                                sc.nextLine();
                                if (pageNumber > 0) {
                                    break;
                                }
                        }

                        service.insertBook(documentId, publisher, publishAmount, author, pageNumber);

                        break;
                    case 2:
                        // mags
                        while (true) {
                            System.out.println("Input documentId:");
                                documentId = sc.nextInt();
                                sc.nextLine();
                                break;
                            
                        }

                        System.out.println("Input publisher:");
                        publisher = sc.nextLine();

                        while (true) {
                            System.out.println("Input publishAmount: ");
                                publishAmount = sc.nextInt();
                                sc.nextLine();
                                if (publishAmount >= 0) {
                                    break;
                            }
                        }

                        int publishNo;
                        while (true) {
                            System.out.println("Input publishNo: ");
                                publishNo = sc.nextInt();
                                sc.nextLine();
                                if (publishNo > 0) {
                                    break;
                            }
                        }

                        int publishMonth;
                        while (true) {
                            System.out.println("Input publishMonth: ");
                                publishMonth = sc.nextInt();
                                sc.nextLine();
                                if (publishMonth > 0 && publishMonth <= 12) {
                                    break;
                            }
                        }

                        service.insertMag(documentId, publisher, publishAmount, publishNo, publishMonth);
                        break;
                    case 3:
                        // news
                        while (true) {
                            System.out.println("Input documentId:");
                                documentId = sc.nextInt();
                                sc.nextLine();
                                break;
                        }

                        System.out.println("Input publisher:");
                        publisher = sc.nextLine();

                        while (true) {
                            System.out.println("Input publishAmount: ");
                                publishAmount = sc.nextInt();
                                sc.nextLine();
                                if (publishAmount >= 0) {
                                    break;
                                
                            }
                        }

                        int publishDay;
                        while (true) {
                            System.out.println("Input publishDay: ");
                                publishDay = sc.nextInt();
                                sc.nextLine();
                                if (publishDay > 0 && publishDay <= 31) {
                                    break;
                            }
                        }
                        service.insertNews(documentId, publisher, publishAmount, publishDay);
                        break;
                    default:
                        // document
                        // news
                        while (true) {
                            System.out.println("Input documentId:");
                                documentId = sc.nextInt();
                                sc.nextLine();
                                break;
                        }

                        System.out.println("Input publisher:");
                        publisher = sc.nextLine();

                        while (true) {
                            System.out.println("Input publishAmount: ");
                                publishAmount = sc.nextInt();
                                sc.nextLine();
                                if (publishAmount >= 0) {
                                    break;
                                
                            }
                        }
                        service.insertDoc(documentId, publisher, publishAmount);
                        break;
                }
                break;
            case 2:
                int documentId;
                while (true) {
                    System.out.println("Input documentId:");
                        documentId = sc.nextInt();
                        sc.nextLine();
                        break;
                }
                service.remove(documentId);
                break;
            case 3:
                service.displayInfo();
                break;
            case 4:
                switch (getDocumentTypeInput()) {
                    case 1:
                        service.showBooks();
                        break;
                    case 2:
                        service.showMags();
                    case 3:
                        service.showNews();
                    default:
                        break;
                }
                break;
            default:
                break;

        }
        sc.close();
    }
}
