package repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class implementation {
	public static int emp_count = 0;
	public static char loc = '\0';
	public static String time1 = "";
	public static String exec = "";

	public static void main(String[] args) {
		
		int order_count = 4;
		int noOfExecutives = 5;
		char restaurants_avail[] = { 'A', 'B', 'C', 'D', 'E' };
		char destinations[] = { 'A', 'B', 'C', 'D', 'E' };
		char restaurants[] = new char[noOfExecutives];
		for (int i = 0; i < noOfExecutives; i++) {
			restaurants[i] = '\0';
		}
		char dest_point[] = new char[noOfExecutives];
		for (int i = 0; i < noOfExecutives; i++) {
			dest_point[i] = '\0';
		}
		String Time[] = new String[noOfExecutives];
		for (int i = 0; i < noOfExecutives; i++) {
			Time[i] = "";
		}
		String pickup_time[] = new String[noOfExecutives];
		for (int i = 0; i < noOfExecutives; i++) {
			pickup_time[i] = "";
		}
		String delivery_time[] = new String[noOfExecutives];
		for (int i = 0; i < noOfExecutives; i++) {
			delivery_time[i] = "";
		}
		String executives[] = new String[noOfExecutives];
		int j = 1;
		for (int i = 0; i < noOfExecutives; i++) {
			executives[i] = "DE" + j;
			j++;
		}
		int charges_earned[] = new int[noOfExecutives];
		for (int i = 0; i < noOfExecutives; i++) {
			charges_earned[i] = 0;
		}
		int allowance[] = new int[noOfExecutives];
		for (int i = 0; i < noOfExecutives; i++) {
			allowance[i] = 0;
		}
		int orders[] = new int[noOfExecutives];
		for (int i = 0; i < noOfExecutives; i++) {
			orders[i] = 0;
		}
		
		int bookingId = 1;
		Scanner sc = new Scanner(System.in);
		char restaurant = '\0';
		char dropLoc = '\0';
		String time = "";
		for (int i = 0; i < order_count; i++) {
			System.out.print("Customer ID: ");
			sc.nextInt();
			System.out.print("Restaurant: ");
			restaurant = sc.next().charAt(0);
			if (!new String(restaurants_avail).contains(restaurant + "")) {
				System.out.println("Invalid Restaurant. Expected values(A,B,C,D,E)");
				break;
			}
			System.out.print("Destination Point: ");
			dropLoc = sc.next().charAt(0);
			if (!new String(destinations).contains(dropLoc + "")) {
				System.out.println("Invalid Destination. Expected values(A,B,C,D,E)");
				break;
			}
			System.out.print("Time: ");
			time = sc.nextLine();
			time = sc.nextLine();
			handleBooking(bookingId, noOfExecutives, executives, charges_earned);
			bookingId++;
			assignDeliveryExecutive(noOfExecutives, restaurant, dropLoc, time, restaurants, dest_point, Time,
					pickup_time, delivery_time, executives, charges_earned, orders, allowance);
		}
		sc.close();
		deliveryHistory(noOfExecutives, restaurant, dropLoc, restaurants, dest_point, Time, pickup_time, delivery_time,
				executives, charges_earned, orders, allowance);
	}

	public static void handleBooking(int bookingId, int noOfExecutives, String executives[], int charges_earned[]) {
		System.out.println("Booking ID:" + bookingId);
		System.out.println("Available Executives: ");
		System.out.println("Executive\t\tDelivery Charge Earned");
		for (int i = 0; i < noOfExecutives; i++) {
			System.out.println(executives[i] + "\t\t\t" + charges_earned[i] + "\n");
		}
	}

	public static void assignDeliveryExecutive(int noOfExecutives, char restaurant, char dropLoc, String time,
			char restaurants[], char dest_point[], String Time[], String pickup_time[], String delivery_time[],
			String executives[], int charges_earned[], int orders[], int allowance[]) {
		
		if (dropLoc == loc) {
			int diffmin = 0;
			try {
				SimpleDateFormat df = new SimpleDateFormat("hh.mm a");
				Date d1 = df.parse(time);
				Date d2 = df.parse(time1);
				long diffms = Math.abs(d2.getTime() - d1.getTime());
				diffmin = (int) (diffms / (60 * 1000));
			} catch (ParseException e) {
				System.out.println("Invalid time Format. Please enter times in 'hh.mm a' format.");
			}
			
			if (diffmin < 15) {
				int pos = 0;
				for (int i = 0; i < noOfExecutives; i++) {
					if (exec == executives[i]) {
						if (orders[i] >= 5) {
							i = i + 1;
							exec = executives[i];
						}
						pos = i;
						break;
					}
				}
				if (orders[pos] < 1) {
					charges_earned[pos] = 50;
					allowance[pos] += 10;
					emp_count++;
					Time[pos] = time;
				}
				orders[pos] += 1;
				if (orders[pos] > 1 && orders[pos] <= 5) {
					charges_earned[pos] += 5;
				}
				restaurants[pos] = restaurant;
				dest_point[pos] = dropLoc;
				// Time[pos]=time;
				time1 = time;
				loc = dropLoc;
				System.out.println("Allotted Delivery Executive: " + exec);
			}
		} else {
			int min = charges_earned[0];
			int pos = 0;
			int i;
			if (min != 0) {
				for (i = 1; i < noOfExecutives; i++) {
					if (charges_earned[i] < min) {
						min = charges_earned[i];
						pos = i;
						if (min == 0) {
							break;
						}
					}
				}
				System.out.println("Allotted Delivery Executive: " + executives[i]);
				charges_earned[i] += 50;
				allowance[i] += 10;
				orders[i] += 1;
				restaurants[i] = restaurant;
				dest_point[i] = dropLoc;
				Time[i] = time;
				time1 = time;
				emp_count++;
				exec = executives[i];
				loc = dropLoc;
			} else {
				System.out.println("Allotted Delivery Executive: " + executives[pos]);
				charges_earned[pos] += 50;
				allowance[pos] += 10;
				orders[pos] += 1;
				restaurants[pos] = restaurant;
				dest_point[pos] = dropLoc;
				Time[pos] = time;
				time1 = time;
				emp_count++;
				exec = executives[pos];
				loc = dropLoc;
			}

		}
	}

	public static void deliveryHistory(int noOfExecutives, char restaurant, char dropLoc, char restaurants[],
			char dest_point[], String Time[], String pickup_time[], String delivery_time[], String executives[],
			int charges_earned[], int orders[], int allowance[]) {
		
		System.out.println(
				"TRIP  EXECUTIVE RESTAURANT DESTINATION POINT  ORDERS  PICK-UP_TIME DELIVERY_TIME DELIVERY CHARGE");
		for (int i = 0; i < emp_count; i++) {
			String pickuptime = "";
			String deliverytime = "";
			String time = Time[i];
			try {
				DateFormat df = new SimpleDateFormat("hh.mm");
				Date d1 = df.parse(time);
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(d1);
				cal1.add(Calendar.MINUTE, 15);
				Date time15 = cal1.getTime();

				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(d1);
				cal2.add(Calendar.MINUTE, 45);
				Date time45 = cal2.getTime();

				DateFormat df1 = new SimpleDateFormat("hh.mm");
				pickuptime = df1.format(time15);
				deliverytime = df1.format(time45);
			} catch (ParseException e) {
				System.out.println("Invalid time Format. Please enter times in 'hh.mm a' format.");
			}
			pickup_time[i] = pickuptime;
			delivery_time[i] = deliverytime;
		}
		int j = 0;
		for (int i = 0; i < emp_count; i++) {
			j += 1;
			System.out.println(j + "\t" + executives[i] + "\t" + restaurants[i] + "\t\t" + dest_point[i] + "\t\t"
					+ orders[i] + "\t" + pickup_time[i] + "\t\t" + delivery_time[i] + "\t" + charges_earned[i]);
		}

		System.out.println("Total Earned");
		System.out.println("Executive  Allowance  Deliver Charges  Total");
		for (int i = 0; i < emp_count; i++) {
			System.out.println(executives[i] + "\t\t" + allowance[i] + "\t\t" + charges_earned[i] + "\t"
					+ (allowance[i] + charges_earned[i]));
		}
	}

}
