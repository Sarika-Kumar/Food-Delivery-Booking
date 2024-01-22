# Food-Delivery-Booking
A food delivery company has 'n' number of delivery executives. For simplicity take the count as 5 but the program should work for any number of delivery executives (Let
their names be identified as DE1, DE2....DE-n). There are only 5 restaurants in the city for pick-up and 5 drop locations (Each location can have multiple customers). After 
delivering a food package , the delivery executive waits there for delivery allotment.
Each customer is identified uniquely by a Customer-ID.

Write a program that does the following, 

#### Constraints : 
1. Delivery charge for every single order is Rs 50 for the delivery executive.
2. If multiple orders (say n) are from the same delivery location within 15 mins period, combine orders to a maximum of 5 per delivery executive. In such case, the delivery charge will be base rate Rs.50 + Rs.5 for every other order (50+5 * (n-1)). 
3. An allowance of Rs.10 will be given for every trip made. Combined orders will be counted as a single trip.
4. Assign the subsequent bookings giving preference to the executive who has earned the least delivery charge among the other available delivery executives excluding trip allowance.
5. Every trip will take 30 mins to reach the destination.

#### Functions :
1. Write a function to handle booking.
2. Write a function to assign delivery executive
3. Write a function that can display delivery executive's activity thus far. (This should contain commission earned , allowance earned(calculated based on criteria 2 and 3).

