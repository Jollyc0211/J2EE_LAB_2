package com.socket.machine;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SocketMachineServlet
 */
@WebServlet("/SocketMachineServlet")
public class SocketMachineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SocketMachineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String socketType = request.getParameter("socket_type");
        String quantityStr = request.getParameter("quantity");
        String customerName = request.getParameter("customer_name");
        String customerEmail = request.getParameter("customer_email");
        String errorMessage = "";

        // Validate input
        int quantity = -1;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) throw new IllegalArgumentException();
        } catch (Exception e) {
        	quantity = 0;
        	errorMessage = "Error: Invalid quantity";
        }

        // Calculate the total price based on socket type and quantity
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


