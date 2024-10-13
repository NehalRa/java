import java.util.ArrayList;
import java.util.Scanner;

class Guest{
    String name;
    String checkInTime;
    String checkOutTime;

    public Guest(String name, String checkInTime)
    {
        this.name = name;
        this.checkInTime = checkInTime;
        this.checkOutTime = null;
    }
}
class Hotel{
    int id;
    String name;
    int totalRooms;
    int roomRate;
    ArrayList<Guest> guests;

    public Hotel(int id, String name, int totalRooms, int roomRate) 
    {
        this.id = id;
        this.name = name;
        this.totalRooms = totalRooms;
        this.roomRate = roomRate;
        this.guests = new ArrayList<>();
    }

    public boolean AvailableRooms() 
    {
        return guests.size() < totalRooms;
    }

    public void checkInGuest(Guest guest) {
        if (AvailableRooms()) 
        {
            guests.add(guest);
            System.out.println(guest.name + " has checked in at " + guest.checkInTime);
        } 
        else {
            System.out.println("No available rooms in " + name);
        }
    }

    public void checkOutGuest(String guestName) 
    {
        for (Guest guest : guests) {
            if (guest.name.equals(guestName)) {
                guest.checkOutTime = "Checked out at current time";
                guests.remove(guest);
                System.out.println(guestName + " has checked out.");
                return;
            }
        }
        System.out.println("Guest not found.");
    }

    public void Showguests() {
        for (Guest guest : guests) {
            System.out.println("Guest Name: " + guest.name + ", Check-In Time: " + guest.checkInTime + 
                               ", Check-Out Time: " + (guest.checkOutTime == null ? "Not checked out" : guest.checkOutTime));
        }
    }
}

class HotelManager {
    ArrayList<Hotel> hotels;
    int hotelIdCounter;

    public HotelManager() {
        hotels = new ArrayList<>();
        hotelIdCounter = 1;
    }

    public void addHotel(String name, int totalRooms, int roomRate)
    {
        Hotel hotel = new Hotel(hotelIdCounter++, name, totalRooms, roomRate);
        hotels.add(hotel);
        System.out.println("Hotel " + name + " added.");
    }

    public void removeHotel(int id) 
    {
        for (Hotel hotel : hotels)
        {
            if (hotel.id == id) 
            {
                hotels.remove(hotel);
                System.out.println("Hotel removed.");
                return;
            }
        }
        System.out.println("Hotel not found.");
    }

    public Hotel getHotelById(int id)
    {
        for (Hotel hotel : hotels)
        {
            if (hotel.id == id) 
            {
                return hotel;
            }
        }
        System.out.println("Hotel not found.");
        return null;
    }

    public void listHotels() 
    {
        for (Hotel hotel : hotels) 
        {
            System.out.println("Hotel ID: " + hotel.id + ", Name: " + hotel.name + 
                               ", Rooms: " + hotel.totalRooms + ", Rate: " + hotel.roomRate);
        }
    }

    public void reportAvailableRooms() 
    {
        for (Hotel hotel : hotels) 
        {
            System.out.println(hotel.name + " has " + (hotel.totalRooms - hotel.guests.size()) + " rooms available.");
        }
    }

    public void reportGuests() {
        for (Hotel hotel : hotels) {
            System.out.println("Hotel: " + hotel.name);
            hotel.Showguests();
        }
    }
}


public class pltask
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        HotelManager manager = new HotelManager();
        int choice;

        while (true) 
        {
            System.out.println("\n1. Manage hotel");
            System.out.println("2. Manage guest");
            System.out.println("3. Report");
            

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                
                    System.out.println("\n1. Add hotel");
                    System.out.println("2. Remove hotel");
                    System.out.println("3. Show the list of hotels");
                    int hotelChoice = sc.nextInt();

                    if (hotelChoice == 1) 
                    {
                        System.out.print("Enter hotel name: ");
                        String hotelName = sc.next();
                        System.out.print("Enter total rooms: ");
                        int totalRooms = sc.nextInt();
                        System.out.print("Enter room rate: ");
                        int roomRate = sc.nextInt();
                        manager.addHotel(hotelName, totalRooms, roomRate);
                    } 
                    else if (hotelChoice == 2) 
                    {
                        System.out.print("Enter hotel ID to remove: ");
                        int hotelId = sc.nextInt();
                        manager.removeHotel(hotelId);
                    } 
                    else if (hotelChoice == 3) 
                    {
                        manager.listHotels();
                    }
                    break;

                case 2:
                    
                    System.out.println("\n1. Check in");
                    System.out.println("2. Check out");
                    int guestChoice = sc.nextInt();

                    if (guestChoice == 1) {
                        System.out.print("Enter hotel ID: ");
                        int hotelId = sc.nextInt();
                        Hotel selectedHotel = manager.getHotelById(hotelId);
                        if (selectedHotel != null) 
                        {
                            System.out.print("Enter guest name: ");
                            String guestName = sc.next();
                            selectedHotel.checkInGuest(new Guest(guestName, "Checked in at current time"));
                        }
                    } 
                    else if (guestChoice == 2) 
                    {
                        System.out.print("Enter hotel ID: ");
                        int hotelId = sc.nextInt();
                        Hotel selectedHotel = manager.getHotelById(hotelId);
                        if (selectedHotel != null) 
                        
                        {
                            System.out.print("Enter guest name to check out: ");
                            String guestName = sc.next();
                            selectedHotel.checkOutGuest(guestName);
                        }
                    }
                    break;

                case 3:
                 
                    System.out.println("\n1. Show available rooms with hotel name");
                    System.out.println("2. Show all guests with hotel name, check-in & check-out time");
                    int reportChoice = sc.nextInt();

                    if (reportChoice == 1)
                    {
                        manager.reportAvailableRooms();
                    } 
                    else if (reportChoice == 2) 
                    {
                        manager.reportGuests();
                    }
                    break;

              default:
                 System.out.println("You Enter the choice is wrong");
            break;
            
            }
        }
    }
}
