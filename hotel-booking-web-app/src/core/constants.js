/* *
  Application Constants
 */
const constants = {
  authenticate_url : 'http://customer-service-hotel-booking-customer.apps.cndsw.openshiftworkshop.com/customer/authenticate?email='
  , get_customerdetails_url : 'http://customer-service-hotel-booking-customer.apps.cndsw.openshiftworkshop.com/customer/details?customerid='
  , get_bookingstate_url : 'http://booking-state-service-hotel-booking-state.apps.cndsw.openshiftworkshop.com/getbookingstate'
  , set_search_url : 'http://booking-state-service-hotel-booking-state.apps.cndsw.openshiftworkshop.com/bookingstate/setsearch'
  , find_hotels_url : 'http://inventory-service-hotel-booking-inventory.apps.cndsw.openshiftworkshop.com/hotel/findbyuserid?userid='
  , find_rooms_url : 'http://inventory-service-hotel-booking-inventory.apps.cndsw.openshiftworkshop.com/room/findbyuserid?userid='
  , find_reservations_url : 'http://reservation-service-hotel-booking-reservation.apps.cndsw.openshiftworkshop.com/reservation/findbycustomerid?customerid='
  , confirm_reservation_url : 'http://reservation-service-hotel-booking-reservation.apps.cndsw.openshiftworkshop.com/reservation/confirm?customerid='

};

export default constants;