
import java.util.*;

class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        isAvailable = false;
    }

    public void freeRoom() {
        isAvailable = true;
    }
}

class Booking {
    private int bookingId;
    private int roomNumber;
    private String category;
    private String checkInDate;
    private String checkOutDate;
    private double totalPrice;

    public Booking(int bookingId, int roomNumber, String category, String checkInDate, String checkOutDate, double totalPrice) {
        this.bookingId = bookingId;
        this.roomNumber = roomNumber;
        this.category = category;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

class Hotel {
    private String name;
    private List<Room> rooms;
    private List<Booking> bookings;
    private int bookingIdCounter;

    public Hotel(String name) {
        this.name = name;
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        bookingIdCounter = 1000; 
    }

    public void addRoom(int roomNumber, String category) {
        rooms.add(new Room(roomNumber, category));
    }

    public List<Room> getAvailableRooms(String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equals(category)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void makeReservation(int roomNumber, String category, String checkInDate, String checkOutDate, double totalPrice) {
        Room room = findRoom(roomNumber);
        if (room != null && room.isAvailable()) {
            room.bookRoom();
            int bookingId = bookingIdCounter++;
            bookings.add(new Booking(bookingId, roomNumber, category, checkInDate, checkOutDate, totalPrice));
            System.out.println("Reservation successful. Your Booking ID is: " + bookingId);
        } else {
            System.out.println("Room not available for booking.");
        }
    }

    public void viewBookingDetails(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("Room Number: " + booking.getRoomNumber());
                System.out.println("Category: " + booking.getCategory());
                System.out.println("Check-in Date: " + booking.getCheckInDate());
                System.out.println("Check-out Date: " + booking.getCheckOutDate());
                System.out.println("Total Price: " + booking.getTotalPrice());
                return;
            }
        }
        System.out.println("Booking not found.");
    }

    private Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("MyHotel");

        hotel.addRoom(101, "Standard");
        hotel.addRoom(102, "Standard");
        hotel.addRoom(103, "Deluxe");
        hotel.addRoom(104, "Deluxe");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Standard/Deluxe): ");
                    String category = scanner.next();
                    List<Room> availableRooms = hotel.getAvailableRooms(category);
                    if (availableRooms.isEmpty()) {
                        System.out.println("No available rooms in this category.");
                    } else {
                        System.out.println("Available Rooms:");
                        for (Room room : availableRooms) {
                            System.out.println("Room Number: " + room.getRoomNumber() + ", Category: " + room.getCategory());
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter check-in date: ");
                    String checkInDate = scanner.next();
                    System.out.print("Enter check-out date: ");
                    String checkOutDate = scanner.next();

                    double totalPrice = 200.0; 
                    hotel.makeReservation(roomNumber, "Standard", checkInDate, checkOutDate, totalPrice);
                    break;
                case 3:
                    System.out.print("Enter booking ID: ");
                    int bookingId = scanner.nextInt();
                    hotel.viewBookingDetails(bookingId);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}
