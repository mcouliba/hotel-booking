/* *
  Application Constants
 */
const constants = {
  authenticate_url : 'http://customer-service-hotelbooking.apps.46.4.112.21.xip.io/customer/authenticate?email='
  , get_customerdetails_url : 'http://customer-service-hotelbooking.apps.46.4.112.21.xip.io/customer/details?customerid='
  , get_bookingstate_url : 'http://booking-state-service-hotelbooking.apps.46.4.112.21.xip.io/getbookingstate'
  , set_search_url : 'http://booking-state-service-hotelbooking.apps.46.4.112.21.xip.io/bookingstate/setsearch'
  , find_hotels_url : 'http://inventory-service-hotelbooking.apps.46.4.112.21.xip.io/hotel/findbyuserid?userid='
  , find_rooms_url : 'http://inventory-service-hotelbooking.apps.46.4.112.21.xip.io/room/findbyuserid?userid='
  , find_reservations_url : 'http://reservation-service-hotelbooking.apps.46.4.112.21.xip.io/reservation/findbycustomerid?customerid='
  , confirm_reservation_url : 'http://reservation-service-hotelbooking.apps.46.4.112.21.xip.io/reservation/confirm?customerid='

};

export default constants;