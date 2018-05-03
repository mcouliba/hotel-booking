import constants from './constants';

/* *
  Functions
 */
const functions = {
    restUrlCall: async (url) => {
        const response = await fetch(url)
            .catch(e => console.log("Error when calling rest url", url));
        const json = await response.json();
        return json;
    }
    , saveBookingState: (bookingState, state) => {
        bookingState.state = state;
        fetch(constants.set_search_url, {
            method: 'POST'
            , headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            }
            , body: JSON.stringify(bookingState)
        })
        .catch(e => console.log("Error when saving Search State"));
    }
    , confirmReservation: async (userid) => {
        const url = constants.confirm_reservation_url + userid;
        return functions.restUrlCall(url);
    }
    , authenticate: async (email) => {
        const url = constants.authenticate_url + email;
        return functions.restUrlCall(url);
    }
    , getCustomerDetails: async (customerid) => {
        const url = constants.get_customerdetails_url + customerid;
        return functions.restUrlCall(url);
    }
    , findRooms: async (customerid) => {
        const url = constants.find_rooms_url + customerid;
        return functions.restUrlCall(url);
    }
}

export default functions;
