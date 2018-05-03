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

    async componentWillMount() {
        await this.findRooms();
        await this.getCustomerDetails();
    }

    async getCustomerDetails() {
        const response = await fetch(constants.get_customerdetails_url + this.props.credentials.customerid)
                    .catch(e => console.log("Error when getting customer details"));
//        const response = await functions.getCustomerDetails(this.props.credentials.customerid);
        const json = await response.json();
        await this.setState({customer : json});
    };

    async findRooms() {
        const response = await fetch(constants.find_rooms_url + this.props.credentials.customerid)
            .catch(e => console.log("Error when finding rooms"));
//        const response = await functions.findRooms(this.props.credentials.customerid);
        const json = await response.json();
        await this.setState({rooms : json.content});
    };

    handleRoomBookingState = async (room: any) => {
        const o = Object.assign({}, this.state.bookingState);
        o.selection.room = room;
        await this.setState({ bookingState: o });
        await functions.saveBookingState(this.state.bookingState, '/roomlist');
        return await this.state.bookingState;
    };

  render() {
        const { search } = this.state.bookingState;
        const { hotel } = this.state.bookingState.selection;

        return (
              <div className="container-fluid container-pf-nav-pf-vertical">
                      <div className="row">
                        <div className="col-md-12">
                          <div className="page-header">
                            <h1>Rooms in <strong>{hotel.name}</strong>
                            <br/>from <strong>{search.date_in}</strong> to <strong>{search.date_out}</strong></h1>
                          </div>
                        </div>
                      </div>

                      <div className="row">
                        <div className="col-md-12">
                          <br />
                          <RoomListView
                            rooms={ this.state.rooms }
                            customer={ this.state.customer }
                            handleBookingState={this.handleRoomBookingState}
                          />
                        </div>
                      </div>
                    </div>
            );
  }
}

export default withRouter(RoomListPage);