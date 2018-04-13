/* *
  Application Constants
 */
const constants = {
  get_bookingstate_url : 'http://booking-state-service-hotelbooking.apps.46.4.112.21.xip.io/getbookingstate'
  , set_search_url : 'http://booking-state-service-hotelbooking.apps.46.4.112.21.xip.io/bookingstate/setsearch'
  , find_hotels_url : 'http://hotel-service-hotelbooking.apps.46.4.112.21.xip.io/hotel/findbyuserid?userid='
  , rooms_by_hotel_url : 'http://inventory-service-hotelbooking.apps.46.4.112.21.xip.io/inventory/room/findbyhotel'
};

export default constants;