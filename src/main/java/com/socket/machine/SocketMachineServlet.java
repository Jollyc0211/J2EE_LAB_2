package com.socket.machine;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/SocketMachineServlet")
public class SocketMachineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SocketMachineServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String socketType = request.getParameter("socket_type");
        String quantityStr = request.getParameter("quantity");
        String customerName = request.getParameter("customer_name");
        String customerEmail = request.getParameter("customer_email");
        String errorMessage = "";
        
        if (customerName == null || customerName.trim().isEmpty()) {
            errorMessage = "Error: Customer name is missing or invalid";
        }else if (!customerName.matches("^[a-zA-Z\\s]+$")) {
            errorMessage = "Error: Invalid Customer name";
        }

        
        int quantity = -1;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) throw new IllegalArgumentException();
        } catch (Exception e) {
        	quantity = 0;
        	errorMessage = "Error: Invalid quantity";
        }

     
        double pricePerSocket = 0;
        if(socketType != null) {
        switch (socketType.toUpperCase()) {
            case "TYPE_A":
                pricePerSocket = 10.0;
                break;
            case "TYPE_B":
                pricePerSocket = 15.0;
                break;
            case "TYPE_C":
                pricePerSocket = 20.0;
                break;
            default:
            	errorMessage = "Error: Invalid socket type";
        }
        }else {
        	errorMessage = "Socket type is missing";
        }

        double totalPrice = pricePerSocket * quantity;
        if(errorMessage.equals("")) {
        	response.getWriter().println("<!DOCTYPE html>\r\n"
        			+ "<html>\r\n"
        			+ "<head>\r\n"
        			+ "<meta charset=\"UTF-8\">\r\n"
        			+ "<title>Socket Server Application</title>\r\n"
        			+ "</head>\r\n"
        			+ "<body>"
        			+ "Customer Name:" + customerName + "\r\n<br>"
        			+ "Customer Email:" + customerEmail + "\r\n<br>"
        			+ "Socket Type:" + socketType + "\r\n<br>"
        			+ "Quantity:" + quantityStr + "\r\n<br>"
        			+ "Price Socket:" + pricePerSocket + "\r\n<br>"
        			+ "Total Price:" + totalPrice + "\r\n<br>"
        			
        			+ "</body>\r\n"
        			+ "</html>");
        }else {
        	response.getWriter().println("<!DOCTYPE html>\r\n"
        			+ "<html>\r\n"
        			+ "<head>\r\n"
        			+ "<meta charset=\"UTF-8\">\r\n"
        			+ "<title>Socket Server Application</title>\r\n"
        			+ "</head>\r\n"
        			+ "<body>"
        			+ errorMessage
        			+ "</body>\r\n"
        			+ "</html>");
        }
        
        
    }

		
		
	}


