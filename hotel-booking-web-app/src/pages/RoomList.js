import React from 'react';
import { withRouter } from 'react-router-dom';
import RoomListView from '../components/ListView/RoomListView';
import constants from '../core/constants';
import functions from '../core/functions';
import PageBase from './PageBase';

export class RoomListPage extends PageBase {
    constructor(props: any) {
        super(props);
        this.state.rooms = [];
        this.state.customer = {};
    }

    componentWillMount() {
        this.updateBookingState();
        this.findRooms();
        this.getCustomerDetails();
    }

findHotelsByCity() {
        fetch(constants.find_hotels_url + this.props.credentials.customerid)
            .then(response => response.json())
            .then(data => {
                this.setState({hotels : data.content})
            })
            .catch(e => console.log("Error when finding hotels"));
    };

     getCustomerDetails() {
        fetch(constants.get_customerdetails_url + this.props.credentials.customerid)
            .then(response => response.json())
            .then(data => {
                this.setState({customer : data})
            })
            .catch(e => console.log("Error when getting customer details"));
    };

     findRooms() {
        fetch(constants.find_rooms_url + this.props.credentials.customerid)
           .then(response => response.json())
           .then(data => {
               this.setState({rooms : data.content})
           })
           .catch(e => console.log("Error when finding rooms"));
    };

    handleRoomBookingState = async (room: any) => {
        const newBookingState = Object.assign({}, this.state.bookingState);
        newBookingState.selection.room = room;
        await functions.saveBookingState(this.state.bookingState, '/roomlist');
        return newBookingState;
    };

  render() {
        const { search } = this.state.bookingState;
        const { hotel } = this.state.bookingState.selection;

        return (
            <div>
                <div className="page-header">
                    <h1>Rooms in <strong>{hotel.name}</strong>
                    <br/>from <strong>{search.date_in}</strong> to <strong>{search.date_out}</strong></h1>
                </div>

                <br />
                <RoomListView
                    rooms={ this.state.rooms }
                    customer={ this.state.customer }
                    handleBookingState={this.handleRoomBookingState}
                />
            </div>
            );
  }
}

export default withRouter(RoomListPage);