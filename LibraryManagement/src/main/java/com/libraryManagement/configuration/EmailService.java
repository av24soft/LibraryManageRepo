package com.libraryManagement.configuration;


import com.libraryManagement.entity.Seat;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRenewalReminderWithInvoice(String toEmail, String userName, Seat seat) throws MessagingException, IOException {

        ByteArrayOutputStream invoicePdf = generateInvoicePdf(userName, seat);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(toEmail);
        helper.setSubject("Seat Subscription Expiration Reminder");
        helper.setText("Dear " + userName + ",\n\nYour seat subscription is set to expire on " +
                seat.getEndDate() + ". Please find attached the invoice for your subscription.\n\nThank you!");

        InputStreamSource attachmentSource = new ByteArrayResource(invoicePdf.toByteArray());
        helper.addAttachment("Invoice.pdf", attachmentSource);

        mailSender.send(mimeMessage);
    }

    private ByteArrayOutputStream generateInvoicePdf(String userName, Seat seat) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new com.itextpdf.text.Paragraph("Invoice"));
            document.add(new com.itextpdf.text.Paragraph("Username: " + userName));
            document.add(new com.itextpdf.text.Paragraph("Seat Number: " + seat.getSeatNo()));
            document.add(new com.itextpdf.text.Paragraph("Fees: " + seat.getFees()));
            document.add(new com.itextpdf.text.Paragraph("Start Date: " + seat.getStartDate()));
            document.add(new com.itextpdf.text.Paragraph("End Date: " + seat.getEndDate()));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream;
    }
}

