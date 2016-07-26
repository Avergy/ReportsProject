package views;


import client.RestClient;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Order;
import entity.OrderItem;
import entity.Phone;
import entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Stateless
public class PdfView {

    @EJB
    RestClient restClient;

    public ByteArrayOutputStream getPdfReportOrders(Date from, Date to)
            throws IOException {
        List<Order> orders = restClient.getReport(from, to);
        List<User> users = new ArrayList<User>();
        List<Phone> phones = new ArrayList<Phone>();
        for(Order order: orders)
        {
            User user = new User(order.getLogin(), order.getFirstName(), order.getSecondName());
            if(users.contains(user))
            {
                user = users.get(users.indexOf(user));
                user.setTotal(user.getTotal() + order.getCost());
            }
            else {
                user.setTotal(order.getCost());
                users.add(user);
            }
            for(OrderItem item: order.getOrderItems())
            {
                Phone phone = new Phone(item.getIdPhone(), item.getName(),item.getPrice(),
                        item.getBrand(), item.getColor(), item.getQuantityStock());
                if(phones.contains(phone))
                {
                    phone = phones.get(phones.indexOf(phone));
                    phone.setQuantityOrder(phone.getQuantityOrder() + item.getQuantity());
                }
                else{
                    phone.setQuantityOrder(item.getQuantity());
                    phones.add(phone);
                }
            }
        }
        users.sort(new Comparator<User>() {
            public int compare(User o1, User o2) {
                if(o1.getTotal() < o2.getTotal()) return 1;
                else if(o1.getTotal()>o2.getTotal()) return -1;
                return 0;
            }
        });
        phones.sort(new Comparator<Phone>() {
            public int compare(Phone o1, Phone o2) {
                if(o1.getQuantityOrder() < o2.getQuantityOrder()) return 1;
                else if(o1.getQuantityOrder() > o2.getQuantityOrder()) return -1;
                return 0;
            }
        });
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font tableTitleFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, stream);
            document.open();
            document.addTitle("Report");
            PdfPTable table = new PdfPTable(5);
            table.setSpacingBefore(20);
            table.setSpacingAfter(20);
            table.setWidthPercentage(100);
            table.getDefaultCell().setPadding(5);
            PdfPCell cell;
            String[] tableHeaders = new String[]{
                    "Date",
                    "Login",
                    "FirstName",
                    "LastName",
                    "Total, rub"
            };
            //table.addCell(new Phrase("#", tableTitleFont));
            for (String header : tableHeaders) {
                cell = new PdfPCell(new Phrase(header, tableTitleFont));
                //cell.setColspan(2);
                table.addCell(cell);
            }


            long totalSum = 0;
            for (Order order: orders) {
                totalSum += order.getCost();
                //table.addCell(String.valueOf(order.getId()));
                table.addCell(format.format(order.getDate()));
                table.addCell(order.getLogin());
                table.addCell(order.getFirstName());
                table.addCell(order.getSecondName());
                table.addCell(String.valueOf(order.getCost()));
            }
            cell = new PdfPCell(new Phrase("Total: " + totalSum  + " rub"));

            cell.setColspan(6);
            table.addCell(cell);
            Paragraph paragraph = new Paragraph();
            paragraph.setSpacingAfter(10);
            paragraph.setFont(titleFont);
            paragraph.add("Report");
            //paragraph.add("Proceed report for period \nfrom " + format.format(from) + "  " + format.format(to));
            document.add(paragraph);
            document.add(table);

            table = new PdfPTable(4);
            table.setSpacingBefore(20);
            table.setSpacingAfter(20);
            table.setWidthPercentage(100);
            table.getDefaultCell().setPadding(5);
            tableHeaders = new String[]{
                    "Login",
                    "FirstName",
                    "LastName",
                    "Total, rub"
            };
            //table.addCell(new Phrase("#", tableTitleFont));
            for (String header : tableHeaders) {
                cell = new PdfPCell(new Phrase(header, tableTitleFont));
                //cell.setColspan(2);
                table.addCell(cell);
            }


            for (User user: users) {
                table.addCell(user.getLogin());
                table.addCell(user.getFirstName());
                table.addCell(user.getSecondName());
                table.addCell(String.valueOf(user.getTotal()));
            }
            paragraph = new Paragraph();
            paragraph.setSpacingAfter(10);
            paragraph.setFont(titleFont);
            paragraph.add("Top users");
            document.add(paragraph);
            document.add(table);


            table = new PdfPTable(6);
            table.setSpacingBefore(20);
            table.setSpacingAfter(20);
            table.setWidthPercentage(100);
            table.getDefaultCell().setPadding(5);
            tableHeaders = new String[]{
                    "Name",
                    "Brand",
                    "Color",
                    "Out of stock",
                    "Price, rub",
                    "Selle"
            };
            for (String header : tableHeaders) {
                cell = new PdfPCell(new Phrase(header, tableTitleFont));
                table.addCell(cell);
            }

            for (Phone phone: phones) {
                table.addCell(phone.getName());
                table.addCell(phone.getBrand());
                table.addCell(phone.getColor());
                table.addCell(String.valueOf(phone.getQuantityStock()));
                table.addCell(String.valueOf(phone.getPrice()));
                table.addCell(String.valueOf(phone.getQuantityOrder()));
            }
            paragraph = new Paragraph();
            paragraph.setSpacingAfter(10);
            paragraph.setFont(titleFont);
            paragraph.add("Top phones");
            document.add(paragraph);
            document.add(table);

        } catch (DocumentException e) {
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
        return stream;
    }

}
